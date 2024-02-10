package com.gcu.public_examination_planet.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gcu.public_examination_planet.domain.Orders;

/**
 * @author HealMe
 * @description 针对表【orders(订单)】的数据库操作Service
 * @createDate 2024-02-09 23:42:09
 */
public interface OrdersService extends IService<Orders> {
    IPage<Orders> getOrderListByPage(Integer currentPage, Integer pageSize);
}
