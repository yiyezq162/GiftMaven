package com.example.gifthavenbackend.repository;

import com.example.gifthavenbackend.entity.AdminEntity;
import org.springframework.data.repository.CrudRepository;


/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */
public interface AdminRepository extends CrudRepository<AdminEntity,Integer> {

    AdminEntity findAdminEntityByUsername(String username);

}
