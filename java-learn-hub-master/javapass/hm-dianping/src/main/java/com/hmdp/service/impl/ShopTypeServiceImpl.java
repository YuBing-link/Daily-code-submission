package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmdp.annotation.RedisAutoConvert;
import com.hmdp.config.RedisConfig;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @RedisAutoConvert
    public List<ShopType> redisSelectlist() {
        List<ShopType> shopTypeList = redisTemplate.opsForList().range("type:list", 0, -1);
        if (shopTypeList != null && shopTypeList.size() > 0) {
            System.out.println(shopTypeList);
            return shopTypeList;
        }
        shopTypeList = this.query().orderByDesc("sort").list();
        redisTemplate.opsForList().leftPushAll("type:list", shopTypeList);
        return shopTypeList;
    }
}
