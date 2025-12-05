package org.taobao.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登录返回VO
 */
@Data
@Builder
public class UserLoginVO {
    private String account;
    private String username;
    private String userType;
    private String token;
}