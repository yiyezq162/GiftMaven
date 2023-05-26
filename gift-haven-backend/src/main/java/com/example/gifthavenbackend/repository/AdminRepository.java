package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
public interface AdminRepository extends JpaRepository<AdminEntity, Integer>,
        JpaSpecificationExecutor<AdminEntity> {

    AdminEntity findAdminEntityByUsername(String username);

    @Override
    List<AdminEntity> findAll();

    AdminEntity getAdminEntityByAdminId(Integer id);

    AdminEntity findAdminEntityByAdminId(Integer id);
}
