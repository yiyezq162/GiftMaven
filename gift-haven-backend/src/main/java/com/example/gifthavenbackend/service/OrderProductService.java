package com.example.gifthavenbackend.service;

import com.example.gifthavenbackend.entity.OrderProductEntity;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
public interface OrderProductService {
    List<OrderProductEntity> getOrderProductByOrderId(Integer id);

    void saveAll(Iterable<OrderProductEntity> ordersEntityList);
}
