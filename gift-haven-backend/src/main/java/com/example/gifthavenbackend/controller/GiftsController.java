package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.GiftsEntity;
import com.example.gifthavenbackend.service.GiftsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/11
 */

@ApiOperation("商品信息")
@RestController
@RequestMapping("/gifts")
public class GiftsController {

    @Resource
    private GiftsService giftsService;

    @GetMapping("/list")
    public Result<Map<String, Object>> giftsListPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        HashMap<String, Object> all = giftsService.findAll(pageNo, pageSize, name);

        return Result.success(all);
    }

    @GetMapping("/{id}")
    public Result<GiftsEntity> getGiftById(@PathVariable("id") Integer id) {
        GiftsEntity giftsEntity = giftsService.findGiftEntityByGiftId(id);
        return Result.success(giftsEntity);
    }

    @PostMapping
    public Result<?> addGift(@RequestBody GiftsEntity giftsEntity) {
        giftsService.save(giftsEntity);
        return Result.success();
    }

    @PutMapping
    public Result<?> updateAdmin(@RequestBody GiftsEntity giftsEntity) {
        giftsService.save(giftsEntity);
        return Result.success("添加成功");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteAdminById(@PathVariable("id") Integer id) {
        giftsService.deleteGiftById(id);
        return Result.success("删除成功");
    }

}
