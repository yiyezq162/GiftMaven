package com.example.gifthavenbackend.service;

import com.example.gifthavenbackend.entity.AdminEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
public interface AdminService extends UserDetailsService {

    List<AdminEntity> findAll();

    Map<String, Object> login(AdminEntity adminEntity);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);
    HashMap<String,Object> findAll(int pageNo, int pageSize, String username);

    void save(AdminEntity adminEntity);

    AdminEntity getAdminById(Integer id);

    void deleteAdminById(Integer id);
}
