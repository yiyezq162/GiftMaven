package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
public interface AdminRepository extends JpaRepository<AdminEntity, Integer>, JpaSpecificationExecutor<AdminEntity> {

    /**
     * 根据管理员昵称查询管理员
     *
     * @param username 管理员昵称
     * @return AdminEntity
     */
    AdminEntity findAdminEntityByUsername(String username);

    /**
     * 根据管理员id查询管理员
     *
     * @param id 管理员id
     * @return AdminEntity
     */
    AdminEntity getAdminEntityByAdminId(Integer id);

    /**
     * 根据管理员id查询管理员
     *
     * @param id 管理员id
     * @return AdminEntity
     */
    AdminEntity findAdminEntityByAdminId(Integer id);
}
