package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户地址表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    private Integer addressId; // 地址ID
    private Integer userId; // 用户ID
    private String fullAddress; // 完整地址
    private String recipientName; // 收货人姓名
    private String phone; // 收货人电话
    private Boolean isDefault; // 是否默认地址
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间
}