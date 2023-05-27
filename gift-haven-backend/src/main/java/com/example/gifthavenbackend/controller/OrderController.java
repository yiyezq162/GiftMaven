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
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 订单分页查询
     *
     * @param id       可以根据订单的id查询
     * @param pageNo   分页当前页
     * @param pageSize 每页大小
     * @return result
     */
    @GetMapping("/list")
    public Result<Map<String, Object>> OrderListPage(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {


        HashMap<String, Object> all = orderService.findAll(pageNo, pageSize, id);

        return Result.success(all);
    }

    /**
     * 根据订单id查询订单
     *
     * @param id 订单id
     * @return result
     */
    @GetMapping("/{id}")
    public Result<OrdersEntity> getOrderById(@PathVariable("id") Integer id) {
        OrdersEntity ordersEntity = orderService.findGiftEntityByGiftId(id);
        return Result.success(ordersEntity);
    }

    /**
     * 添加订单
     *
     * @param ordersEntity 订单实体
     * @return result
     */
    @PostMapping
    public Result<?> addOrder(@RequestBody OrdersEntity ordersEntity) {
        orderService.save(ordersEntity);
        return Result.success();
    }

    /**
     * 修改订单
     *
     * @param ordersEntity 订单实体
     * @return result
     */
    @PutMapping
    public Result<?> updateOrder(@RequestBody OrdersEntity ordersEntity) {
        orderService.save(ordersEntity);
        return Result.success("添加成功");
    }

    /**
     * 删除订单
     *
     * @param id 订单id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return Result.success("删除成功");
    }
}
