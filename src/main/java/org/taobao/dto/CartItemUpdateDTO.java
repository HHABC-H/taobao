package org.taobao.dto;

import lombok.Data;

/**
 * 购物车更新DTO
 */
@Data
public class CartItemUpdateDTO {
    private Integer cartItemId; // 购物车项ID
    private Integer quantity; // 商品数量
    private Integer skuId; // SKU ID（用于修改商品规格）
}