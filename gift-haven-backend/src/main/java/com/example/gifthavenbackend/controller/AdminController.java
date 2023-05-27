package com.example.gifthavenbackend.controller;

import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.entity.AdminEntity;
import com.example.gifthavenbackend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黎锦斌
 * * @date 2023/5/8
 * 管理员Controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 登录
     *
     * @param adminEntity 管理员账号和密码
     * @return result
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody AdminEntity adminEntity) {
        Map<String, Object> data = adminService.login(adminEntity);
        if (data != null) {
            return Result.success(data);
        } else {
            return Result.fail(20002, "用户不存在");
        }
    }

    /**
     * 通过Token获得管理员详情
     *
     * @param token 生成的token
     * @return result
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam(value = "token") String token) {
        Map<String, Object> data = adminService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20003, "用户信息获取失败");
    }

    /**
     * 注销登录，就是删除token
     *
     * @param token 传入token
     * @return result
     */
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        adminService.logout(token);
        return Result.success("注销成功");
    }

    /**
     * 分页获取管理员列表
     *
     * @param username 根据管理员名称查询时的参数
     * @param pageNo   分页的当前页
     * @param pageSize 分页的大小
     * @return result
     */
    @GetMapping("/list")
    public Result<HashMap<String, Object>> getUserListPage(@RequestParam(value = "username", required = false) String username,
                                                           @RequestParam(value = "page", defaultValue = "0") int pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        HashMap<String, Object> all = adminService.findAll(pageNo, pageSize, username);
        return Result.success(all);
    }

    /**
     * 添加管理员
     *
     * @param adminEntity 传入管理员对象
     * @return result
     */
    @PostMapping
    public Result<?> addAdmin(@RequestBody AdminEntity adminEntity) {
        adminService.save(adminEntity);
        return Result.success("添加成功");
    }

    /**
     * 修改管理员
     *
     * @param adminEntity 传入管理员对象
     * @return result
     */
    @PutMapping
    public Result<?> updateAdmin(@RequestBody AdminEntity adminEntity) {
        adminEntity.setPassword(null);
        adminService.save(adminEntity);
        return Result.success("添加成功");
    }

    /**
     * 根据Id查询管理员详情
     *
     * @param id 管理员id
     * @return result
     */
    @GetMapping("/{id}")
    public Result<AdminEntity> getAdminById(@PathVariable("id") Integer id) {
        AdminEntity admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

    /**
     * 删除管理员
     *
     * @param id 管理员id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteAdminById(@PathVariable("id") Integer id) {
        adminService.deleteAdminById(id);
        return Result.success("删除成功");
    }
}
