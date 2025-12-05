package org.taobao.dto;

import lombok.Data;

/**
 * 用户地址更新DTO
 */
@Data
public class UserAddressUpdateDTO {
    private Integer addressId; // 地址ID
    private String fullAddress; // 完整地址
    private String recipientName; // 收货人姓名
    private String phone; // 收货人电话
    private Boolean isDefault; // 是否默认地址
}
