package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheUtils;
import com.hmdp.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CacheUtils cacheUtils;


    /**
     * 根据id查询商铺信息
     * @param id 商铺id
     * @return 商铺详情数据
     */
    @Override
    public Shop getRedisById(Long id) {
        return cacheUtils.getHotKey(
                CACHE_SHOP_KEY, id,
                this::getById,
                30L, TimeUnit.MINUTES,
                Shop.class);
    }
    /**
     * 新增商铺信息
     * @param shop 商铺数据
     * @return 商铺id
     */
    @Override
    public Long redisSave(Shop shop) {
        if (shop== null){
            throw new RuntimeException("保存失败");
        }
        save(shop);
        redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + shop.getId(), BeanUtil.beanToMap(shop));
        redisTemplate.expire(CACHE_SHOP_KEY + shop.getId(), 30, TimeUnit.MINUTES);
        loadShopData();
        return shop.getId();
    }

    /**
     * 更新商铺信息
     * @param shop 商铺数据
     * @return 无
     */

    @Override
    public void updateRedisById(Shop shop) {
        if (shop== null){
            throw new RuntimeException("更新失败");
        }
        this.updateById(shop);
        redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + shop.getId(), BeanUtil.beanToMap(shop));
        loadShopData();
    }
    /**
     * 根据商铺类型分页查询商铺信息
     * @param typeId 商铺类型
     * @param current 页码
     * @return 商铺列表
     */

    public List<Shop> queryPage(Integer typeId, Integer current) {
        Page<Shop> page = this.query()
                .eq("type_id", typeId)
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        return page.getRecords();
    }
    @Override
    public Result queryPageLoad(Integer typeId, Integer current, Double x, Double y) {
        if (x == null || y == null) {
            return Result.ok(queryPage(typeId, current));
        }
        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
        int end = (current ) * SystemConstants.DEFAULT_PAGE_SIZE;
        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults = redisTemplate.opsForGeo().search(
                "shop:geo:" + typeId,
                GeoReference.fromCoordinate(x, y),
                new Distance(5000),
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                        .includeCoordinates().includeDistance().limit(end)
        );
        if (geoResults == null) {
            return Result.ok();
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> content = geoResults.getContent();
        if (content.size() <= from) {
            return Result.ok();
        }
        List<Long> ids = new ArrayList<>(content.size());
        Map<String, Distance> distanceMap = new HashMap<>(content.size());
        content.stream().skip(from).forEach(result -> {
            String ShopId = result.getContent().getName().toString();
            ids.add(Long.valueOf(ShopId));
            Distance distance = result.getDistance();
            distanceMap.put(ShopId, distance);
        });
        String str = StrUtil.join(",", ids);
        List<Shop> shop = query().in("id", ids).last("ORDER BY FIELD(id, " + str + ")").list();
        for (Shop shop1 : shop) {
            shop1.setDistance(distanceMap.get(shop1.getId().toString()).getValue());
        }
        return Result.ok(shop);
    }

    /**
     * 根据商铺名称关键字分页查询商铺信息
     * @param name 商铺名称关键字
     * @param current 页码
     * @return 商铺列表
     */
    @Override
    public List<Shop> queryPageByName(String name, Integer current) {
        Page<Shop> page = this.query()
                .like(StrUtil.isNotBlank(name), "name", name)
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        return page.getRecords();
    }

    public void loadShopData() {
        List<Shop> loadShopList = this.list();
        Map<Long, List<Shop>> collect = loadShopList.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        for (Map.Entry<Long, List<Shop>> entry : collect.entrySet()){
            Long typeId = entry.getKey();
            String key = "shop:geo:" + typeId;
            List<Shop> value = entry.getValue();
            List<RedisGeoCommands.GeoLocation<Object>> locations=new ArrayList<>(value.size());
            for (Shop shop : value) {
                locations.add(new RedisGeoCommands.GeoLocation<>(
                        shop.getId(),
                        new Point(shop.getX(), shop.getY())
                ));
            }
            redisTemplate.opsForGeo().add(key, locations);
        }
    }
}
