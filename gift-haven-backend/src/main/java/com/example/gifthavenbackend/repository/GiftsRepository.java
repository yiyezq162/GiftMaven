package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.GiftsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 黎锦斌
 * * @date 2023/5/11
 */
public interface GiftsRepository extends JpaRepository<GiftsEntity, Integer>,
        JpaSpecificationExecutor<GiftsEntity> {
    GiftsEntity findGiftsEntityByName(String name);

    GiftsEntity findGiftEntityByGiftId(Integer id);

    void deleteGiftsEntityByGiftId(Integer id);
}
