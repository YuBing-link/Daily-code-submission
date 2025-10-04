package com.sky.Task;

import com.sky.constant.MessageConstant;
import com.sky.entity.Orders;
import com.sky.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrdersMapper ordersMapper;
    @Scheduled(cron = "0 * * * * ?")
    public void removeTimeoutOrders() {
        log.info("定时清理超时订单：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        List<Orders> orders = ordersMapper.removeTimeoutOrders(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if (orders != null && orders.size() > 0) {
            for (Orders order : orders) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason(MessageConstant.ORDER_TIME_OUT);
                order.setCancelTime(LocalDateTime.now());
                ordersMapper.update(order);
            }
        }

    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeDeliveryOrders() {

        log.info("定时清理派送中的订单：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        List<Orders> orders = ordersMapper.removeTimeoutOrders(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusDays(1));
        if (orders != null && orders.size() > 0) {
            for (Orders order : orders) {
                order.setStatus(Orders.COMPLETED);
                ordersMapper.update(order);
            }
        }
    }

}
