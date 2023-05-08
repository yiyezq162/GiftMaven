package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.RestBean;
import com.example.gifthavenbackend.entity.CustomerEntity;
import com.example.gifthavenbackend.repository.CustomerRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
@RestController
public class CustomerController {
    @Resource
    CustomerRepository customerRepository;

    @GetMapping("/customers/{id}")
    public RestBean<Optional<CustomerEntity>> findByCustomerId(@PathVariable("id") Integer customerId){
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        return RestBean.sueecss(customer);
    }

    @GetMapping("/customerslist")
    public RestBean<Iterable<CustomerEntity>> findCustomersList(){
        Iterable<CustomerEntity> entities = customerRepository.findAll();
        return RestBean.sueecss(entities);
    }
}
