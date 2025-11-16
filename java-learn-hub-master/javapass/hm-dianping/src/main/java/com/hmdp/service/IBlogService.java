package com.hmdp.service;

import com.hmdp.dto.Result;
import com.hmdp.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IBlogService extends IService<Blog> {

    Result queryBlogById(Long id);

    List<Blog> orderByHot(Integer current);

    Long saveBlog(Blog blog);

    void likeBlog(Long id);

    List<Blog> queryMyBlog(Integer current);

    Result likeBlogTop5(Long id);
}
