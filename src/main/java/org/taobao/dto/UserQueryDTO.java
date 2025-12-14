package org.taobao.dto;

import lombok.Data;

/**
 * 用户查询DTO
 */
@Data
public class UserQueryDTO {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户类型：customer-普通用户, merchant-商家, operator-管理员
     */
    private String userType;

    /**
     * 状态：active-启用, inactive-禁用
     */
    private String status;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    /**
     * 偏移量，用于分页
     */
    private Integer offset;
}