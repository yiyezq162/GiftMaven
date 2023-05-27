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
 * 订单商品Controller
 * 负责处理订单和商品间的关系
 */
@RestController
@RequestMapping("/product")
public class OrderProductController {

    @Resource
    private OrderProductService orderProductService;

    /**
     * 根据订单id查询订单商品数据
     *
     * @param id 订单id
     * @return result
     */
    @GetMapping("/order/{id}")
    public Result<List<OrderProductEntity>> getOrderProductById(@PathVariable("id") Integer id) {
        List<OrderProductEntity> orderProductEntity = orderProductService.getOrderProductByOrderId(id);
        return Result.success(orderProductEntity);
    }

    /**
     * 添加订单商品数据
     *
     * @param ordersEntityList 订单商品的列表
     * @return result
     */
    @PostMapping
    public Result<?> addOrder(@RequestBody Iterable<OrderProductEntity> ordersEntityList) {
        orderProductService.saveAll(ordersEntityList);
        return Result.success();
    }

    /**
     * 根据订单商品id删除数据
     *
     * @param id 订单商品id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteOrderProduct(@PathVariable("id") Integer id) {
        orderProductService.delete(id);
        return Result.success();
    }
}
