package org.taobao.controller;

import org.taobao.context.BaseContext;
import org.taobao.dto.UserQueryDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.User;
import org.taobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户列表
     * 
     * @param userQueryDTO 查询条件
     * @return 用户列表
     */
    @GetMapping("/user/list")
    public Result<List<User>> getUserList(UserQueryDTO userQueryDTO) {
        try {
            List<User> userList = userService.getUserList(userQueryDTO);
            return Result.success(userList);
        } catch (Exception e) {
            return Result.error("获取用户列表失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID获取用户详情
     * 
     * @param userId 用户ID
     * @return 用户详情
     */
    @GetMapping("/user/{userId}")
    public Result<User> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("获取用户详情失败：" + e.getMessage());
        }
    }

    /**
     * 禁用/启用用户
     * 
     * @param userId 用户ID
     * @param status 状态：active-启用，inactive-禁用
     * @return 操作结果
     */
    @PutMapping("/user/status/{userId}")
    public Result<String> updateUserStatus(@PathVariable Long userId, @RequestBody String status) {
        try {
            userService.updateUserStatus(userId, status);
            return Result.success("更新用户状态成功");
        } catch (Exception e) {
            return Result.error("更新用户状态失败：" + e.getMessage());
        }
    }
}