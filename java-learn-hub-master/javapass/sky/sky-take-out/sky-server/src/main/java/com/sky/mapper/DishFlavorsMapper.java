package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.constant.MessageConstant;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorsMapper {
    void insertBatch(List<DishFlavor> flavors);

    @Delete("delete from dish_flavor where id=#{id}")
    void delete(Long id);

    void deleteBatch(List<Long> Ids);
    
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);
    
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> selectByDishId(Long dishId);
}