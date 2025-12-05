package org.taobao.dto;

import lombok.Data;

/**
 * 购物车添加DTO
 */
@Data
public class CartItemAddDTO {
    private Integer skuId; // SKU ID
    private Integer quantity; // 商品数量
}