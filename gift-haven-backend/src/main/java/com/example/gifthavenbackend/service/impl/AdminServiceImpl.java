package com.example.gifthavenbackend.service.impl;

import com.example.gifthavenbackend.config.JwtUtil;
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
    @Resource
    private JwtUtil jwtUtil;

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
            // String key = "login" + UUID.randomUUID();
            // 存入redis
            admin.setPassword(null);
            //redisTemplate.opsForValue().set(key, admin, 36, TimeUnit.HOURS);
            String key = jwtUtil.createToken(admin);
            HashMap<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 从redis查询token
        // Object obj = redisTemplate.opsForValue().get(token);
        AdminEntity admin = null;
        try {
            admin = jwtUtil.parseToken(token, AdminEntity.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        if (admin != null) {
            // 反序列化
            // AdminEntity admin = JSON.parseObject(JSON.toJSONString(obj), AdminEntity.class);
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
    // redisTemplate.delete(token);
    }

    @Override
    public HashMap<String, Object> findAll(int pageNo, int pageSize, String name) {
        if (pageNo >= 1) pageNo -= 1;
        HashMap<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<AdminEntity> page = adminRepository.findAll(pageable);
        if (name != null) {
            AdminEntity admin = adminRepository.findAdminEntityByUsername(name);
            List<AdminEntity> list = new ArrayList<>();
            list.add(admin);
            map = new HashMap<>();
            map.put("total", 1);
            map.put("rows", list);
            return map;
        }
        List<AdminEntity> list = page.getContent();
        map.put("total", page.getTotalElements());
        map.put("rows", list);
        return map;
    }

    @Override
    public void save(AdminEntity adminEntity) {
        //未知BUG，数据库设置了deleted字段默认值为0，传入的对象也没有deleted值，但是默认值设置不生效
        //先这样写吧，值不为一，则为0
        if (!Objects.equals(adminEntity.getDeleted(), "1")) {
            adminEntity.setDeleted("0");
        }
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
        save(admin);
    }
}
