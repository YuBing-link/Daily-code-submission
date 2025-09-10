package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

     List<Long> getSetMealDishIdsByDishId(List<Long> ids);

     void deleteBatch(List<Long> ids);
     @Select("select * from setmeal_dish where setmeal_id=#{id}")
     List<SetmealDish> select(Long id);

     void insertBatch(List<SetmealDish> setmealDishes);
     @Delete("delete from setmeal_dish where setmeal_id=#{id}")
     void delete(Long id);
}
