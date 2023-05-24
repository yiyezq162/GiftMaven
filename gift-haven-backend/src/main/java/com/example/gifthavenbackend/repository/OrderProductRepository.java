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
public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer>,
        JpaSpecificationExecutor<OrderProductEntity> {

//    @Query("SELECT ope, p FROM OrderProductEntity ope JOIN ope.giftsEntity p WHERE ope.orderId = :orderId")
    List<OrderProductEntity> findOrderProductEntityByOrderId(@Param("orderId") int orderId);

}
