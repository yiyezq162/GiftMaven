package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
public interface OrderRepository extends JpaRepository<OrdersEntity, Integer>, JpaSpecificationExecutor<OrdersEntity> {

    /**
     * 根据订单id查询订单
     *
     * @param id 订单id
     * @return OrdersEntity
     */
    OrdersEntity findOrdersEntityByOrderId(Integer id);
}
