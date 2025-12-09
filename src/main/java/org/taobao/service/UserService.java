package org.taobao.service;

import org.taobao.dto.UserLoginDTO;
import org.taobao.dto.UserProfileUpdateDTO;
import org.taobao.dto.UserQueryDTO;
import org.taobao.dto.UserRegisterDTO;
import org.taobao.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 用户注册
     */
    void register(UserRegisterDTO userRegisterDTO);

    /**
     * 获取用户个人详情
     * @param userId 用户ID
     * @return 用户详情
     */
    User getUserProfile(Long userId);

    /**
     * 修改用户个人详情
     * @param userId 用户ID
     * @param userProfileUpdateDTO 更新信息
     */
    void updateUserProfile(Long userId, UserProfileUpdateDTO userProfileUpdateDTO);

    /**
     * 获取用户列表
     * @param userQueryDTO 查询条件
     * @return 用户列表
     */
    List<User> getUserList(UserQueryDTO userQueryDTO);

    /**
     * 根据ID获取用户详情
     * @param userId 用户ID
     * @return 用户详情
     */
    User getUserById(Long userId);

    /**
     * 更新用户状态
     * @param userId 用户ID
     * @param status 状态：active-启用，inactive-禁用
     */
    void updateUserStatus(Long userId, String status);
}
