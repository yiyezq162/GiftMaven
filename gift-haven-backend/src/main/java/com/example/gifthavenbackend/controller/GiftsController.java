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
 * 礼品Controller
 */
@ApiOperation("商品信息")
@RestController
@RequestMapping("/gifts")
public class GiftsController {

    @Resource
    private GiftsService giftsService;

    /**
     * 商品分页查询
     *
     * @param name     商品名称
     * @param pageNo   当前页
     * @param pageSize 分页大小
     * @return result
     */
    @GetMapping("/list")
    public Result<Map<String, Object>> giftsListPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {


        HashMap<String, Object> all = giftsService.findAll(pageNo, pageSize, name);

        return Result.success(all);
    }

    /**
     * 根据id查询礼品
     *
     * @param id 礼品id
     * @return result
     */
    @GetMapping("/{id}")
    public Result<GiftsEntity> getGiftById(@PathVariable("id") Integer id) {
        GiftsEntity giftsEntity = giftsService.findGiftEntityByGiftId(id);
        return Result.success(giftsEntity);
    }

    /**
     * 添加礼品
     *
     * @param giftsEntity 礼品实体
     * @return result
     */
    @PostMapping
    public Result<?> addGift(@RequestBody GiftsEntity giftsEntity) {
        giftsService.save(giftsEntity);
        return Result.success();
    }

    /**
     * 修改礼品
     *
     * @param giftsEntity 礼品实体
     * @return result
     */
    @PutMapping
    public Result<?> updateGift(@RequestBody GiftsEntity giftsEntity) {
        giftsService.save(giftsEntity);
        return Result.success("添加成功");
    }

    /**
     * 根据id删除礼品
     *
     * @param id 礼品id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteGiftById(@PathVariable("id") Integer id) {
        giftsService.deleteGiftById(id);
        return Result.success("删除成功");
    }

}
