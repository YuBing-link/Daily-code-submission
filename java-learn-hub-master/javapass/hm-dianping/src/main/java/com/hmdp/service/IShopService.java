package com.hmdp.service;

import com.hmdp.entity.Shop;
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
public interface IShopService extends IService<Shop> {

    Shop getRedisById(Long id);

    Long redisSave(Shop shop);

    void updateRedisById(Shop shop);

    List<Shop> queryPage(Integer typeId, Integer current);

    List<Shop> queryPageByName(String name, Integer current);
}
