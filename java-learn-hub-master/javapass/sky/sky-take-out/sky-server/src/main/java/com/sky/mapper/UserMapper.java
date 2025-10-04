package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User user);
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
    @Select("select COUNT(id) from user where create_time < #{end}")
    Integer countByCreateTime(LocalDateTime end);
    @Select("select COUNT(id) from user where  create_time > #{begin} and create_time < #{end}")
    Integer countByNewCreateTime(LocalDateTime begin, LocalDateTime end);
    @Select("select COUNT(id) from user where  create_time > #{begin} and create_time < #{end}")
    Integer countByMap(Map map);
}
