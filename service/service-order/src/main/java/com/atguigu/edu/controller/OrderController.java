package com.atguigu.edu.controller;


import com.atguigu.commontuils.R;
import com.atguigu.edu.entity.Order;
import com.atguigu.edu.service.OrderService;
import com.atguigu.jwt.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author author：吴嘉伟
 * @since 2021-12-12
 */

@RestController
@RequestMapping("/eduOrder/order")
public class OrderController {

    @Autowired
    OrderService orderService;

//    根据课程id生成订单类
    @PostMapping("/getOrder/{cid}")
    public R saveOrder(@PathVariable String cid, HttpServletRequest request){
        String mid = jwtUtils.getMemberIdByJwtToken(request);
        String orderNo=orderService.createOrders(cid,mid);
        return R.ok().data("orderNO",orderNo);
    }

    @GetMapping("/getOrder/oid")
    public R getOrderId(@PathVariable String oid){
        Order order = orderService.getById(oid);
        return R.ok().data("order",order);

    }




}

