package org.taobao.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户个人详情更新DTO
 */
@Data
public class UserProfileUpdateDTO {
    private String username; // 用户名（网名）
    private String gender; // 性别：male, female, unknown
    private Date birthday; // 出生日期
    private String phone; // 手机号码
    private String email; // 邮箱地址
    private String avatarUrl; // 用户头像URL
}