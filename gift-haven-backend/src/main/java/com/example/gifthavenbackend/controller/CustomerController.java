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
 * 顾客Controller
 */
@RestController()
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    /**
     * 分页返回顾客数据
     *
     * @param name     顾客的名字
     * @param pageNo   当前页数
     * @param pageSize 每页大小
     * @return result
     */
    @GetMapping("/list")
    public Result<Map<String, Object>> customerListPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        HashMap<String, Object> all = customerService.findAll(pageNo, pageSize, name);

        return Result.success(all);
    }

    /**
     * 根据顾客id查询顾客信息
     *
     * @param id 顾客id
     * @return result
     */
    @GetMapping("/{id}")
    public Result<CustomerEntity> getCustomerById(@PathVariable("id") Integer id) {
        CustomerEntity customerEntity = customerService.findGiftEntityByGiftId(id);
        return Result.success(customerEntity);
    }

    /**
     * 新增顾客
     *
     * @param customerEntity 顾客实体对象
     * @return result
     */
    @PostMapping
    public Result<?> addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
        return Result.success("添加成功");
    }

    /**
     * 修改顾客
     *
     * @param customerEntity 顾客实体
     * @return result
     */
    @PutMapping
    public Result<?> updateCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
        return Result.success("修改成功");
    }

    /**
     * 根据id删除顾客
     *
     * @param id 顾客id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteCustomerById(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return Result.success("删除成功");
    }
}
