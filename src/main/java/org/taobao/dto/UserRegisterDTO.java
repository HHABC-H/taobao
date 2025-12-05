package org.taobao.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户注册请求DTO
 */
@Data
public class UserRegisterDTO {
    // 必填字段
    private String account; // 账号，用于登录
    private String password; // 密码
    private String userType; // 用户类型：operator, merchant, customer, visitor
    
    // 可选字段
    private String username; // 用户名（网名）
    private String realName; // 真实姓名
    private String gender; // 性别：male, female, unknown
    private Date birthday; // 出生日期
    private String phone; // 手机号码
    private String email; // 邮箱地址
    private String avatarUrl; // 用户头像图片URL
}