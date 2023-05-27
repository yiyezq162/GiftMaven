package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer>, JpaSpecificationExecutor<OrderProductEntity> {


    /**
     * 根据订单id查询订单商品表
     *
     * @param orderId
     * @return List<OrderProductEntity>
     */
    List<OrderProductEntity> findOrderProductEntityByOrderId(@Param("orderId") int orderId);

    /**
     * 根据订单商品表id查询订单商品
     *
     * @param productId 订单商品表id
     * @return OrderProductEntity
     */
    OrderProductEntity findOrderProductEntityByProductId(@Param("productId") int productId);

}
