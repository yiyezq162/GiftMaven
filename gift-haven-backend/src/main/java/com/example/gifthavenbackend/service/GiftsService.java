package com.example.gifthavenbackend.service;

import com.example.gifthavenbackend.entity.GiftsEntity;

import java.util.HashMap;

/**
 * @author 黎锦斌
 * * @date 2023/5/11
 */
public interface GiftsService {
    HashMap<String, Object> findAll(int pageNo, int pageSize, String name);

    GiftsEntity findGiftEntityByGiftId(Integer id);

    void save(GiftsEntity giftsEntity);

    void deleteGiftById(Integer id);
}
