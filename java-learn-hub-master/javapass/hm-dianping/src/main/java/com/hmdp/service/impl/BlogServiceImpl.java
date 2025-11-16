package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.Blog;
import com.hmdp.entity.User;
import com.hmdp.mapper.BlogMapper;
import com.hmdp.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.service.IUserService;
import com.hmdp.utils.SystemConstants;
import com.hmdp.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private IUserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
        save(blog);
        return blog.getId();
    }
    public void isLike(Blog blog) {
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
        List<Blog> records = page.getRecords();
        return records;
    }

    @Override
    public Result likeBlogTop5(Long id) {
        Set<Object> range = redisTemplate.opsForZSet().range("blog:liked:" + id, 0, 4);
        List<Long> collect = range.stream()
                .map(o->Long.parseLong(o.toString()))
                .collect(Collectors.toList());
        if (collect.size() == 0) {
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
}
