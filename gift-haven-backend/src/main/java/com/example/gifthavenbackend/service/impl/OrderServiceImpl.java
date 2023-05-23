package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.entity.OrdersEntity;
import com.example.gifthavenbackend.repository.OrderRepository;
import com.example.gifthavenbackend.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;


    @Override
    public HashMap<String, Object> findAll(int pageNo, int pageSize, Integer id) {
        if (pageNo >= 1) pageNo -= 1;
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<OrdersEntity> page = orderRepository.findAll(pageable);
        List<OrdersEntity> list = page.getContent();
        map.put("total", page.getTotalElements());
        map.put("rows", list);
        return map;
    }

    @Override
    public OrdersEntity findGiftEntityByGiftId(Integer id) {
        return orderRepository.findOrdersEntityByOrderId(id);
    }

    @Override
    public void save(OrdersEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    @Override
    public void deleteGiftById(Integer id) {
        OrdersEntity ordersEntityByOrderId = orderRepository.findOrdersEntityByOrderId(id);
        ordersEntityByOrderId.setDeleted("1");
        save(ordersEntityByOrderId);
    }
}
