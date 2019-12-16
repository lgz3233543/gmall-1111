package com.lian.gmall.oms.service.impl;

import com.lian.gmall.oms.entity.Order;
import com.lian.gmall.oms.mapper.OrderMapper;
import com.lian.gmall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
