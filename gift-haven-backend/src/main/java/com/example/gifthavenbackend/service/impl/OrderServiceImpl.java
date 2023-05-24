package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.entity.OrdersEntity;
import com.example.gifthavenbackend.repository.OrderRepository;
import com.example.gifthavenbackend.service.OrderService;
import org.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        orderEntity.setCustomerEntity(orderEntity.getCustomerEntity());
        if (isJsonArray(orderEntity.getAddress())) {
            JSONArray jsonArray = new JSONArray(orderEntity.getAddress());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); i++) {
                if (i > 0) { // 在非第一个元素后加上逗号
                    sb.append(" ");
                }
                sb.append(jsonArray.getString(i)); // 获取当前元素并添加到字符串中
            }
            orderEntity.setAddress(sb.toString());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date());
        orderEntity.setCreateAt(Timestamp.valueOf(dateString));
        if (orderEntity.getDeleted() == null) {
            orderEntity.setDeleted("0");
        }
        orderRepository.save(orderEntity);
    }

    public static boolean isJsonArray(String str) {
        if (str == null) {
            return false;
        }
        str = str.trim();
        if (!str.startsWith("[") || !str.endsWith("]")) {
            return false;
        }
        try {
            new JSONArray(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleteGiftById(Integer id) {
        OrdersEntity ordersEntityByOrderId = orderRepository.findOrdersEntityByOrderId(id);
        ordersEntityByOrderId.setDeleted("1");
        save(ordersEntityByOrderId);
    }
}
