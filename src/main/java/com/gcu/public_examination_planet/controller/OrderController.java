package com.gcu.public_examination_planet.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcu.public_examination_planet.common.Result;
import com.gcu.public_examination_planet.domain.Orders;
import com.gcu.public_examination_planet.service.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author HealMe
 * @Description
 * @date 2024/2/9 16:54
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrdersService ordersService;

    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody Orders orders) {
        if ("course".equals(orders.getOrderType())){
            Orders orderByCourseId = ordersService.getOne(new QueryWrapper<Orders>().eq("user_id",orders.getUserId()).eq("course_id", orders.getCourseId()).ne("order_status","已取消"));
            if (orderByCourseId == null){
                orders.setOrderCode(IdUtil.simpleUUID());
                ordersService.save(orders);
                return Result.success(orders);
            }
            return Result.success(orderByCourseId);
        } else{
            Orders orderByVip = ordersService.getOne(new QueryWrapper<Orders>().eq("user_id", orders.getUserId()).eq("order_type", "vip").ne("order_status", "已取消"));
            if (orderByVip == null){
                orders.setOrderCode(IdUtil.simpleUUID());
                ordersService.save(orders);
            }
            return Result.success(orderByVip);
        }
    }

    /**
     * 分页获取订单列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getOrdersList")
    public Result getOrdersList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(ordersService.getOrderListByPage(currentPage,pageSize));
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancelOrder")
    public Result cancelOrder(@RequestParam("orderId") Integer orderId) {
        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("已取消");
        return Result.success(ordersService.updateById(orders));
    }

}
