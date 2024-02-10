package com.gcu.public_examination_planet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gcu.public_examination_planet.domain.Orders;
import com.gcu.public_examination_planet.service.OrdersService;
import com.gcu.public_examination_planet.mapper.OrdersMapper;
import com.gcu.public_examination_planet.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * @author HealMe
 * @description 针对表【orders(订单)】的数据库操作Service实现
 * @createDate 2024-02-09 23:42:09
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService{

    public IPage<Orders> getOrderListByPage(Integer currentPage, Integer pageSize){
        return page(new Page<>(currentPage, pageSize), new QueryWrapper<Orders>().eq("user_id", TokenUtils.getCurrentUser().getUserId()).orderByDesc("create_time"));
    }
}




