package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.Blog;
import com.hmdp.entity.Follow;
import com.hmdp.entity.User;
import com.hmdp.mapper.BlogMapper;
import com.hmdp.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.service.IFollowService;
import com.hmdp.service.IUserService;
import com.hmdp.utils.ScrollResult;
import com.hmdp.utils.SystemConstants;
import com.hmdp.utils.ThreadPool;
import com.hmdp.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private IFollowService followService;

    @Override
    public Result queryBlogById(Long id) {
        Blog blog = getById(id);
        if (blog == null) {
            return Result.fail("数据不存在");
        }
        queryUserBlog(blog);
        isLike(blog);
        return Result.ok(blog);
    }

    @Override
    public List<Blog> orderByHot(Integer current) {
        Page<Blog> page =   query()
                .orderByDesc("liked")
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        // 获取当前页数据
        List<Blog> records = page.getRecords();
        // 查询用户
        records.forEach(blog -> {
            queryUserBlog(blog);
            isLike(blog);
        });
        return records;
    }

    private void queryUserBlog(Blog blog) {
        Long userId = blog.getUserId();
        User user = userService.getById(userId);
        blog.setName(user.getNickName());
        blog.setIcon(user.getIcon());
    }

    @Override
    public Long saveBlog(Blog blog) {
        // 获取登录用户
        UserDTO user = UserHolder.getUser();
        blog.setUserId(user.getId());

        // 保存探店博文
        boolean isSuccess = save(blog);
        if (!isSuccess) {
            return null;
        }
        ThreadPool.threadPoolExecutor.submit(() -> {
            // 1. 获取粉丝数据
            List<Follow> fans = followService.query().eq("follow_user_id", user.getId()).list();
            // 2. 推送数据
            for (Follow fan : fans) {
                Long followUserId = fan.getUserId();
                String key = "feed:" + followUserId;
                redisTemplate.opsForZSet().add(key, blog.getId(), System.currentTimeMillis());
            }
        });
        return blog.getId();
    }
    public void isLike(Blog blog) {
        if (UserHolder.getUser() == null) {
            return;
        }
        UserDTO user = UserHolder.getUser();
        String key="blog:liked:" + blog.getId();
        Double score = redisTemplate.opsForZSet().score(key, user.getId());
        blog.setIsLike(score != null);
    }
    @Override
    public void likeBlog(Long id) {
        UserDTO userId = UserHolder.getUser();
        String key="blog:liked:" + id;
        Double score = redisTemplate.opsForZSet().score(key, userId.getId());
        if (score == null) {
            boolean success = update().setSql("liked = liked + 1").eq("id", id).update();
            if (success) {
                redisTemplate.opsForZSet().add(key, userId.getId(),System.currentTimeMillis());
            }
        }else {
            boolean success = update().setSql("liked = liked - 1").eq("id", id).update();
            if (success) {
                redisTemplate.opsForZSet().remove(key, userId.getId());
            }
        }

    }

    @Override
    public List<Blog> queryMyBlog(Integer current) {
        // 获取登录用户
        UserDTO user = UserHolder.getUser();
        // 根据用户查询
        Page<Blog> page = query()
                .eq("user_id", user.getId()).page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        // 获取当前页数据
        return page.getRecords();
    }

    @Override
    public Result likeBlogTop5(Long id) {
        Set<Object> range = redisTemplate.opsForZSet().range("blog:liked:" + id, 0, 4);
        assert range != null;
        List<Long> collect = range.stream()
                .map(o->Long.parseLong(o.toString()))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            return Result.ok();
        }
        String sqlCollect = StrUtil.join(",", collect);
        List<UserDTO> userDTOS = userService.query().in("id", collect)
                .last("order by field( id,"+sqlCollect+")")
                .list()
                .stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
                .collect(Collectors.toList());
        return Result.ok(userDTOS);
    }

    @Override
    public Result queryBlogOfFollow(Long max, Integer offset) {
        Long userId = UserHolder.getUser().getId();
        String key = "feed:" + userId;
        Set<ZSetOperations.TypedTuple<Object>> typedTuples =
                redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, 0, max, offset, 2);
        assert typedTuples != null;
        List<Long> ids = new ArrayList<>(typedTuples.size());
        long minTime = 0;
        int os = 1;
        for (ZSetOperations.TypedTuple<Object> typedTuple : typedTuples) {
            ids.add(Long.parseLong(typedTuple.getValue().toString()));
            long time = typedTuple.getScore().longValue();
            if (time == minTime) {
                os++;
            }else {
                minTime = time;
                os = 1;
            }
        }
        String idStr = StrUtil.join(",", ids);
        List<Blog> blogs = query().in("id", ids)
                .last("order by field(id," + idStr + ")")
                .list();
        for (Blog blog : blogs) {
            queryUserBlog(blog);
            isLike(blog);
        }
        ScrollResult scrollResult = new ScrollResult();
        scrollResult.setList(blogs);
        scrollResult.setOffset(os);
        scrollResult.setMinTime(minTime);
        return Result.ok(scrollResult);
    }

}
