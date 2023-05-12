package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.entity.CustomerEntity;
import com.example.gifthavenbackend.repository.CustomerRepository;
import com.example.gifthavenbackend.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 黎锦斌
 * * @date 2023/5/13
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerRepository customerRepository;

    @Override
    public HashMap<String, Object> findAll(int pageNo, int pageSize, String name) {
        if (pageNo >= 1) pageNo -= 1;
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CustomerEntity> page = customerRepository.findAll(pageable);
        if (name != null) {
            List<CustomerEntity> gifts = customerRepository.findByNameLike("%" + name + "%");
            map = new HashMap<>();
            map.put("total", gifts.size());
            map.put("rows", gifts);
            return map;
        }
        List<CustomerEntity> list = page.getContent();
        map.put("total", page.getTotalElements());
        map.put("rows", list);
        return map;
    }

    @Override
    public CustomerEntity findGiftEntityByGiftId(Integer id) {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(id);
        return customerEntity;
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        //未知BUG，数据库设置了deleted字段默认值为0，传入的对象也没有deleted值，但是默认值设置不生效
        //先这样写吧，值不为一，则为0
        if (!Objects.equals(customerEntity.getDeleted(), "1")) {
            customerEntity.setDeleted("0");
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        CustomerEntity customerEntity = customerRepository.findCustomerEntityByCustomerId(id);
        customerEntity.setDeleted("1");
        save(customerEntity);
    }
}
