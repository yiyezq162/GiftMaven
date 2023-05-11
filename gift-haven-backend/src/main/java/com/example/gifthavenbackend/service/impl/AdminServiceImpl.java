package com.example.gifthavenbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.gifthavenbackend.entity.AdminEntity;
import com.example.gifthavenbackend.repository.AdminRepository;
import com.example.gifthavenbackend.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 黎锦斌
 * * @date 2023/5/9
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        AdminEntity admin = adminRepository.findAdminEntityByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return User.withUsername(admin.getUsername()).password(admin.getPassword()).roles("user").build();
    }

    @Override
    public List<AdminEntity> findAll() {
        Iterable<AdminEntity> all = adminRepository.findAll();
        return (List<AdminEntity>) all;
    }

    @Override
    public Map<String, Object> login(AdminEntity adminEntity) {
        AdminEntity admin = adminRepository.findAdminEntityByUsername(adminEntity.getUsername());
        if (admin != null && Objects.equals(admin.getPassword(), adminEntity.getPassword())) {
            String key = "login" + UUID.randomUUID();
            //存入redis
            admin.setPassword(null);
            redisTemplate.opsForValue().set(key, admin, 36, TimeUnit.HOURS);

            HashMap<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 从redis查询token
        Object obj = redisTemplate.opsForValue().get(token);
        // 反序列化
        AdminEntity admin = JSON.parseObject(JSON.toJSONString(obj), AdminEntity.class);
        if (admin != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", admin.getUsername());
            data.put("avatar", admin.getAvatar());
            data.put("roles", admin.getRoles());
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public HashMap<String, Object> findAll(int pageNo, int pageSize, String username) {
        if (pageNo >= 1) pageNo -= 1;
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AdminEntity> page = adminRepository.findAll(pageable);
        if (username != null) {
            AdminEntity admin = adminRepository.findAdminEntityByUsername(username);
            List<AdminEntity> list = new ArrayList<>();
            list.add(admin);
            map = new HashMap<>();
            map.put("total", 1);
            map.put("rows", list);
            return map;
        }
        List<AdminEntity> list = page.getContent();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    @Override
    public void save(AdminEntity adminEntity) {
        adminRepository.save(adminEntity);
    }

    @Override
    public void update(AdminEntity adminEntity) {
        adminRepository.save(adminEntity);
    }

    @Override
    public AdminEntity getAdminById(Integer id) {
        return adminRepository.getAdminEntityByAdminId(id);
    }

    @Override
    public void deleteAdminById(Integer id) {
        AdminEntity admin = adminRepository.findAdminEntityByAdminId(id);
        admin.setDeleted("1");
        update(admin);
    }
}
