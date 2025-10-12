package com.byc.mpcodegenerator.system.service.impl;

import com.byc.mpcodegenerator.system.entity.Orders;
import com.byc.mpcodegenerator.system.mapper.OrdersMapper;
import com.byc.mpcodegenerator.system.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author byc
 * @since 2025-10-12
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
