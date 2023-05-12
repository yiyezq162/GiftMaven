package com.example.gifthavenbackend.repository;


import com.example.gifthavenbackend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>,
        JpaSpecificationExecutor<CustomerEntity> {
    List<CustomerEntity> findByNameLike(String s);

    CustomerEntity findCustomerEntityByCustomerId(Integer id);
}
