package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorsMapper {
    void insertBatch(List<DishFlavor> flavors);

    @Delete("delete from dish_flavor where id=#{id}")
    void delete(Long id);

    void deleteBatch(List<Long> Ids);
}
