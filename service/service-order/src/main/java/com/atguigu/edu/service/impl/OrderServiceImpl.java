package com.atguigu.edu.service.impl;

import com.atguigu.edu.RpcClient.courseClient;
import com.atguigu.edu.RpcClient.ucenterClient;
import com.atguigu.edu.entity.Order;
import com.atguigu.edu.mapper.OrderMapper;
import com.atguigu.edu.service.OrderService;
import com.atguigu.orderVo.courseVo;
import com.atguigu.orderVo.ucenterMemberVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private courseClient courseClient;
    @Autowired
    private ucenterClient ucenterClient;
    @Override
    public String createOrders(String cid, String mid) {

//        通过远程调用获取用户信息
        courseVo courseVo = courseClient.getCourseById(cid);

//        通过远程调用获取课程信息
        ucenterMemberVo ucenterMemberVo = ucenterClient.getMemberInfoById(mid);

        Order order = new Order();
        BeanUtils.copyProperties(courseVo,order);
        BeanUtils.copyProperties(ucenterMemberVo,order);
        UUID uuid = UUID.randomUUID();
        order.setOrderNo(   uuid.toString());
        return null;
    }
}
