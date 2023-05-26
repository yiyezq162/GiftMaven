package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.OrdersEntity;
import com.example.gifthavenbackend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/14
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public Result<Map<String, Object>> OrderListPage(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {


        HashMap<String, Object> all = orderService.findAll(pageNo, pageSize, id);

        return Result.success(all);
    }

    @GetMapping("/{id}")
    public Result<OrdersEntity> getOrderById(@PathVariable("id") Integer id) {
        OrdersEntity ordersEntity = orderService.findGiftEntityByGiftId(id);
        return Result.success(ordersEntity);
    }

    @PostMapping
    public Result<?> addOrder(@RequestBody OrdersEntity ordersEntity) {
        orderService.save(ordersEntity);
        return Result.success();
    }

    @PutMapping
    public Result<?> updateOrder(@RequestBody OrdersEntity ordersEntity) {
        orderService.save(ordersEntity);
        return Result.success("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return Result.success("删除成功");
    }
}
