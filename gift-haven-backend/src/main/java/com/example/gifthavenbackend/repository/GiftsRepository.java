package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.GiftsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 黎锦斌
 * * @date 2023/5/11
 */
public interface GiftsRepository extends JpaRepository<GiftsEntity, Integer>, JpaSpecificationExecutor<GiftsEntity> {

    /**
     * 根据商品id查询
     *
     * @param id 商品id
     * @return GiftsEntity
     */
    GiftsEntity findGiftEntityByGiftId(Integer id);

    /**
     * 根据商品名称模糊查询
     *
     * @param name 商品名称
     * @return List<GiftEntity>
     */
    List<GiftsEntity> findByNameLike(String name);
}
