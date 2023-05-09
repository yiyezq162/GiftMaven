package com.example.gifthavenbackend.repository;


import com.example.gifthavenbackend.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
}
