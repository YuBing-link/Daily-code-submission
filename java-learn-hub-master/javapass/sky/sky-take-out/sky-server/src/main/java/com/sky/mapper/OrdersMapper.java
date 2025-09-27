package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrdersMapper {
    void insert(Orders orders);

    @Select("select * from orders where number = #{outTradeNo}")
    Orders getByNumber(String outTradeNo);

    void update(Orders orders);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);
    @Select("select COUNT(case when status= '2' then 1 end ) AS toBeConfirmed" +
            ",COUNT(case when status='3' then 1 end ) AS confirmed" +
            ",COUNT(case when status='4' then 1 end ) AS deliveryInProgress from orders")
    OrderStatisticsVO statistics();
    @Update("update orders set status = 4 where id = #{id}")
    void delivery(Long id);
    @Update("update orders set status = 5 where id = #{id}")
    void complete(Long id);

//    @Update("update orders set status = 3 where id = #{id}")
//    void confirm(OrdersConfirmDTO ordersConfirmDTO);

}
