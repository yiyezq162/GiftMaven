package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.AdminEntity;
import com.example.gifthavenbackend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/8
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/lists")
    public Result<List<AdminEntity>> adminList() {
        List<AdminEntity> list = adminService.findAll();
        return Result.success(list);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody AdminEntity adminEntity) {
        Map<String, Object> data = adminService.login(adminEntity);
        if (data != null) {
            return Result.success(data);
        } else {
            return Result.fail(20002, "用户不存在");
        }
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam(value = "token") String token) {
        Map<String, Object> data = adminService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20003, "用户信息获取失败");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        adminService.logout(token);
        return Result.success("注销成功");
    }

    @GetMapping("/list")
    public Result<HashMap<String, Object>> getUserListPage(@RequestParam(value = "username", required = false) String username,
                                                           @RequestParam(value = "page",defaultValue = "0") int pageNo,
                                                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize) {

        HashMap<String, Object> all = adminService.findAll(pageNo, pageSize, username);
        return Result.success(all);
    }

    @PostMapping
    public Result<?> addAdmin(@RequestBody AdminEntity adminEntity) {
        adminService.save(adminEntity);
        return Result.success("添加成功");
    }

    @PutMapping
    public Result<?> updateAdmin(@RequestBody AdminEntity adminEntity) {
        adminEntity.setPassword(null);
        adminService.update(adminEntity);
        return Result.success("添加成功");
    }

    @GetMapping("/{id}")
    public Result<AdminEntity> getAdminById(@PathVariable("id") Integer id) {
        AdminEntity admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteAdminById(@PathVariable("id") Integer id) {
        adminService.deleteAdminById(id);
        return Result.success("删除成功");
    }
}
