package com.atguigu.edu.service;

import com.atguigu.edu.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-12
 */
public interface OrderService extends IService<Order> {

    String createOrders(String cid, String mid);
}
