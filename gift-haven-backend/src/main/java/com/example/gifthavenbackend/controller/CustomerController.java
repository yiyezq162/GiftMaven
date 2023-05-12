package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.CustomerEntity;
import com.example.gifthavenbackend.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/7
 */
@RestController()
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    @GetMapping("/list")
    public Result<Map<String, Object>> customerListPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        HashMap<String, Object> all = customerService.findAll(pageNo, pageSize, name);

        return Result.success(all);
    }

    @GetMapping("/{id}")
    public Result<CustomerEntity> getCustomerById(@PathVariable("id") Integer id) {
        CustomerEntity customerEntity = customerService.findGiftEntityByGiftId(id);
        return Result.success(customerEntity);
    }

    @PostMapping
    public Result<?> addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<?> updateCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteCustomerById(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return Result.success("删除成功");
    }
}
