package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.taobao.constant.MessageConstant;
import org.taobao.constant.StatusConstant;
import org.taobao.dto.UserLoginDTO;
import org.taobao.dto.UserProfileUpdateDTO;
import org.taobao.dto.UserQueryDTO;
import org.taobao.dto.UserRegisterDTO;
import org.taobao.exception.AccountLockedException;
import org.taobao.exception.AccountNotFoundException;
import org.taobao.exception.PasswordErrorException;
import org.taobao.exception.AccountAlreadyExistsException;
import org.taobao.mapper.UserMapper;
import org.taobao.pojo.User;
import org.taobao.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();

        // 1、先根据账号查询用户信息
        User user = userMapper.findByAccount(account);

        // 2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            // 账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对，对前端传入的密码进行md5加密处理
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!encryptedPassword.equals(user.getPassword())) {
            // 密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (StatusConstant.DISABLE.equals(user.getStatus()) || StatusConstant.LOCKED.equals(user.getStatus())) {
            // 账号被禁用或锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 3、返回实体对象
        return user;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        // 1、检查账号是否已存在
        User existingUser = userMapper.findByAccount(userRegisterDTO.getAccount());
        if (existingUser != null) {
            throw new AccountAlreadyExistsException("账号已存在");
        }

        // 2、创建User对象
        User user = new User();
        user.setAccount(userRegisterDTO.getAccount());
        user.setUserType(userRegisterDTO.getUserType());

        // 3、对密码进行MD5加密
        String encryptedPassword = DigestUtils.md5DigestAsHex(userRegisterDTO.getPassword().getBytes());
        user.setPassword(encryptedPassword);

        // 4、设置默认值
        // 商家注册时默认锁定，需要管理员启用
        if ("merchant".equals(userRegisterDTO.getUserType())) {
            user.setStatus(StatusConstant.LOCKED); // 商家默认锁定
        } else {
            user.setStatus(StatusConstant.ENABLE); // 其他用户默认启用
        }
        // 如果用户提供了用户名，使用提供的值，否则使用账号作为用户名
        user.setUsername(userRegisterDTO.getUsername() != null && !userRegisterDTO.getUsername().isEmpty()
                ? userRegisterDTO.getUsername()
                : userRegisterDTO.getAccount());
        user.setGender(userRegisterDTO.getGender() != null ? userRegisterDTO.getGender() : "unknown"); // 默认未知性别
        user.setPhone(userRegisterDTO.getPhone() != null ? userRegisterDTO.getPhone() : ""); // 默认空手机号
        user.setEmail(userRegisterDTO.getEmail() != null ? userRegisterDTO.getEmail() : ""); // 默认空邮箱
        user.setAvatarUrl(userRegisterDTO.getAvatarUrl() != null ? userRegisterDTO.getAvatarUrl() : ""); // 默认空头像

        // 5、设置创建时间和更新时间
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        // 6、插入用户数据
        userMapper.insert(user);
    }

    @Override
    public User getUserProfile(Long userId) {
        // 根据用户ID查询用户详情
        return userMapper.findById(userId);
    }

    @Override
    public void updateUserProfile(Long userId, UserProfileUpdateDTO userProfileUpdateDTO) {
        // 1、查询用户是否存在
        User existingUser = userMapper.findById(userId);
        if (existingUser == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 2、更新用户字段
        Date now = new Date();
        existingUser.setUsername(userProfileUpdateDTO.getUsername());
        existingUser.setGender(userProfileUpdateDTO.getGender());
        existingUser.setBirthday(userProfileUpdateDTO.getBirthday());
        existingUser.setPhone(userProfileUpdateDTO.getPhone());
        existingUser.setEmail(userProfileUpdateDTO.getEmail());
        // 更新头像URL（如果有提供）
        if (userProfileUpdateDTO.getAvatarUrl() != null) {
            existingUser.setAvatarUrl(userProfileUpdateDTO.getAvatarUrl());
        }
        existingUser.setUpdateTime(now);

        // 3、保存更新
        userMapper.update(existingUser);
    }

    @Override
    public List<User> getUserList(UserQueryDTO userQueryDTO) {
        // 计算偏移量
        if (userQueryDTO.getPageNum() != null && userQueryDTO.getPageSize() != null) {
            userQueryDTO.setOffset((userQueryDTO.getPageNum() - 1) * userQueryDTO.getPageSize());
        }
        return userMapper.getUserList(userQueryDTO);
    }

    @Override
    public Integer getUserCount(UserQueryDTO userQueryDTO) {
        return userMapper.getUserCount(userQueryDTO);
    }

    @Override
    public User getUserById(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        return user;
    }

    @Override
    public void updateUserStatus(Long userId, String status) {
        // 1、查询用户是否存在
        User existingUser = userMapper.findById(userId);
        if (existingUser == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 2、更新用户状态
        userMapper.updateStatus(userId, status);
    }
}