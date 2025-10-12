package com.byc.mpcodegenerator.system.service.impl;

import com.byc.mpcodegenerator.system.entity.Category;
import com.byc.mpcodegenerator.system.mapper.CategoryMapper;
import com.byc.mpcodegenerator.system.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
