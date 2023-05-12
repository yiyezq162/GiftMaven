package com.example.gifthavenbackend.service;

import com.example.gifthavenbackend.entity.CustomerEntity;

import java.util.HashMap;

/**
 * @author 黎锦斌
 * * @date 2023/5/13
 */
public interface CustomerService {
    HashMap<String, Object> findAll(int pageNo, int pageSize, String name);

    CustomerEntity findGiftEntityByGiftId(Integer id);

    void save(CustomerEntity customerEntity);

    void deleteCustomerById(Integer id);
}
