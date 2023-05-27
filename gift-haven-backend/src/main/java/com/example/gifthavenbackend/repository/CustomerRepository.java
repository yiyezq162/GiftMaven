package com.example.gifthavenbackend.repository;


import com.example.gifthavenbackend.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {

    /**
     * 根据顾客昵称模糊查询
     *
     * @param name 顾客昵称
     * @return List<CustomerEntity>
     */
    List<CustomerEntity> findByNameLike(String name);

    /**
     * 根据顾客id查询
     *
     * @param id 顾客id
     * @return CustomerEntity
     */
    CustomerEntity findCustomerEntityByCustomerId(Integer id);
}
