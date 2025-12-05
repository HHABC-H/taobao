package org.taobao.service;

import org.taobao.pojo.User;

import org.taobao.dto.UserLoginDTO;
import org.taobao.dto.UserProfileUpdateDTO;
import org.taobao.dto.UserRegisterDTO;

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
}
