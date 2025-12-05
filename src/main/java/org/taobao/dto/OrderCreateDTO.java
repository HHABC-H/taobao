package org.taobao.dto;

import lombok.Data;

import java.util.List;

/**
 * 订单创建DTO
 */
@Data
public class OrderCreateDTO {
    private String consignee; // 收货人姓名
    private String phone; // 收货人电话
    private String address; // 收货地址
    private Integer addressId; // 地址ID
    private String paymentMethod; // 支付方式
    private List<OrderItemDTO> orderItems; // 订单商品列表
}
