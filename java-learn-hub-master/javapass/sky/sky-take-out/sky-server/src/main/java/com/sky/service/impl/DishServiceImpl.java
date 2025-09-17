package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorsMapper dishFlavorsMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;
    @Override
    @Transactional
    public void dishWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // 添加菜品
        dishMapper.insert(dish);
        // 添加口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });
            dishFlavorsMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page=dishMapper.pageQuery(dishPageQueryDTO);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //判断菜品是否可以删除--条件：起售，关联套餐
        for(Long id:ids){
            int status = dishMapper.getById(id);
            if(status == StatusConstant.ENABLE){
               throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        List<Long> setMealDishIdsByDishId = setmealDishMapper.getSetMealDishIdsByDishId(ids);
        if (setMealDishIdsByDishId!=null&&!setMealDishIdsByDishId.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //删除菜品操作以及口味
//        for (Long id:ids){
//            dishMapper.delete(id);
//            dishFlavorsMapper.delete(id);
//        }
        dishMapper.deleteBatch(ids);
        dishFlavorsMapper.deleteBatch(ids);
    }

    @Override
    public DishVO dishWithFlavorsById(Long id) {
        Dish dish = dishMapper.select(id);
        if (dish == null) {
            return null;
        }
        List<DishFlavor> dishFlavor = dishFlavorsMapper.selectByDishId(id);
        DishVO dishVO=new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        // 添加对dishFlavor的空值检查
        if (dishFlavor != null) {
            dishVO.setFlavors(dishFlavor);
        }
        return dishVO;
    }

    @Override
    public void updateDishWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);
        // 删除菜品对应的所有口味
        dishFlavorsMapper.deleteByDishId(dishDTO.getId());
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorsMapper.insertBatch(flavors);
        }
    }

    @Override
        public List<Dish> list(Long categoryId) {
            Dish dish = Dish.builder()
                    .categoryId(categoryId)
                    .status(StatusConstant.ENABLE)
                    .build();
            return dishMapper.list(dish);
        }

    @Override
        /**
         * 条件查询菜品和口味
         * @param dish
         * @return
         */
        public List<DishVO> listWithFlavor(Dish dish) {
            List<Dish> dishList = dishMapper.list(dish);

            List<DishVO> dishVOList = new ArrayList<>();

            for (Dish d : dishList) {
                DishVO dishVO = new DishVO();
                BeanUtils.copyProperties(d,dishVO);

                //根据菜品id查询对应的口味
                List<DishFlavor> flavors = dishFlavorsMapper.selectByDishId(d.getId());

                dishVO.setFlavors(flavors);
                dishVOList.add(dishVO);
            }

            return dishVOList;
        }

    @Override
    public void startOrStop(Integer status, Long id) {
            Dish dish = Dish.builder()
                    .id(id)
                    .status(status)
                    .build();
        dishMapper.update(dish);
    }


}