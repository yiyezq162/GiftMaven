package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.OrderProductEntity;
import com.example.gifthavenbackend.service.OrderProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
@RestController
@RequestMapping("/product")
public class OrderProductController {

    @Resource
    private OrderProductService orderProductService;

    @GetMapping("/order/{id}")
    public Result<List<OrderProductEntity>> getOrderProductById(@PathVariable("id") Integer id) {
        List<OrderProductEntity> orderProductEntity = orderProductService.getOrderProductByOrderId(id);
        return Result.success(orderProductEntity);
    }

    @PostMapping
    public Result<?> addOrder(@RequestBody Iterable<OrderProductEntity> ordersEntityList) {
        orderProductService.saveAll(ordersEntityList);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteOrderProduct(@PathVariable("id") Integer id) {
        orderProductService.delete(id);
        return Result.success();
    }
}
