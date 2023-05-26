package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.entity.GiftsEntity;
import com.example.gifthavenbackend.repository.GiftsRepository;
import com.example.gifthavenbackend.service.GiftsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author 黎锦斌
 * * @date 2023/5/11
 */

@Service
public class GiftsServiceImpl implements GiftsService {
    @Resource
    GiftsRepository giftsRepository;

    @Override
    public HashMap<String, Object> findAll(int pageNo, int pageSize, String name) {
        if (pageNo >= 1) pageNo -= 1;
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<GiftsEntity> page = giftsRepository.findAll(pageable);
        if (name != null) {
            List<GiftsEntity> gifts = giftsRepository.findByNameLike("%" + name + "%");
            map = new HashMap<>();
            map.put("total", gifts.size());
            map.put("rows", gifts);
            return map;
        }
        List<GiftsEntity> list = page.getContent();
        map.put("total", page.getTotalElements());
        map.put("rows", list);
        return map;
    }

    @Override
    public GiftsEntity findGiftEntityByGiftId(Integer id) {
        GiftsEntity giftsEntity = giftsRepository.findGiftEntityByGiftId(id);
        return giftsEntity;
    }

    @Override
    public void save(GiftsEntity giftsEntity) {
        //未知BUG，数据库设置了deleted字段默认值为0，传入的对象也没有deleted值，但是默认值设置不生效
        //先这样写吧，值不为一，则为0
        if (!Objects.equals(giftsEntity.getDeleted(), "1")) {
            giftsEntity.setDeleted("0");
        }
        giftsRepository.save(giftsEntity);
    }

    @Override
    public void deleteGiftById(Integer id) {
        GiftsEntity giftsEntity = giftsRepository.findGiftEntityByGiftId(id);
        giftsEntity.setDeleted("1");
        save(giftsEntity);
    }
}
