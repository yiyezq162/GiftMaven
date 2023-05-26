package com.example.gifthavenbackend.service;

import com.example.gifthavenbackend.entity.OrdersEntity;

import java.util.HashMap;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
public interface OrderService {
    HashMap<String, Object> findAll(int pageNo, int pageSize, Integer id);

    OrdersEntity findGiftEntityByGiftId(Integer id);

    void save(OrdersEntity orderEntity);

    void deleteOrderById(Integer id);
}
