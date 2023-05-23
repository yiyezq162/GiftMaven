package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.entity.OrderProductEntity;
import com.example.gifthavenbackend.repository.GiftsRepository;
import com.example.gifthavenbackend.repository.OrderProductRepository;
import com.example.gifthavenbackend.service.OrderProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Resource
    private OrderProductRepository orderProductRepository;
    @Resource
    private GiftsRepository giftsRepository;

    @Override
    public List<OrderProductEntity> getOrderProductByOrderId(Integer id) {
        List<OrderProductEntity> orderProductEntityList = orderProductRepository.findOrderProductEntityByOrderId(id);
        return orderProductEntityList;
    }

    @Override
    public void saveAll(Iterable<OrderProductEntity> ordersEntityList) {

        for (OrderProductEntity entity : ordersEntityList){
            entity.setGiftsEntity(giftsRepository.findGiftEntityByGiftId(entity.getGiftId()));
            // 对 entity 进行操作，例如：
            if (entity.getDeleted() == null) {
                entity.setDeleted("0");
            }
            orderProductRepository.save(entity);
        }
    }
}
