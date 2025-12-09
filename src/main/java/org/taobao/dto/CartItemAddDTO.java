package org.taobao.dto;

import lombok.Data;

/**
 * 购物车添加DTO
 */
@Data
public class CartItemAddDTO {
    private Integer skuId; // SKU ID
    private Integer quantity; // 商品数量
    private String updateType; // 更新类型：increment（增量）或set（直接设置），默认：increment
}