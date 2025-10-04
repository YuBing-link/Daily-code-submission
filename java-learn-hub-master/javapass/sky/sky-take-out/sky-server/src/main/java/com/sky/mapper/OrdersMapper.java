package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.SalesTop10ReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @Select("select * from orders where status = #{status} and order_time < #{time}")
    List<Orders> removeTimeoutOrders(Integer status, LocalDateTime time);
    @Select("select SUM(amount) from orders where order_time > #{begin} and order_time < #{end} and status != 1")
    Double sumByOrderTime(LocalDateTime begin, LocalDateTime end);
    @Select("select COUNT(id) from orders where order_time > #{begin} and order_time < #{end} and status not in(1,2)")
    Integer countByTime(LocalDateTime begin, LocalDateTime end);
    @Select("select COUNT(id) from orders where order_time > #{begin} and order_time < #{end} and status = 5")
    Integer countByValidTime(LocalDateTime begin, LocalDateTime end);
    @Select("select order_detail.name nameList,count(order_detail.number) numberList from orders left join order_detail on orders.id = order_detail.order_id where orders.order_time > #{begin} and orders.order_time < #{end} and status = 5 group by order_detail.name order by count(order_detail.number) desc limit 10")
    List<SalesTop10ReportVO> getSalesTop10(LocalDateTime begin, LocalDateTime end);

    Integer countByMap(Map map);

    Double sumByMap(Map map);

//    @Update("update orders set status = 3 where id = #{id}")
//    void confirm(OrdersConfirmDTO ordersConfirmDTO);

}
