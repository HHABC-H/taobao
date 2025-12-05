package org.taobao.dto;

import lombok.Data;

/**
 * 订单商品项DTO
 */
@Data
public class OrderItemDTO {
    private Integer productId; // 商品ID
    private Integer skuId; // SKU ID
    private Integer quantity; // 购买数量
    private Double price; // 商品单价
}
