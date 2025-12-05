package org.taobao.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品SKU更新DTO
 */
@Data
public class ProductSkuUpdateDTO {
    private Integer skuId; // SKU ID
    private String skuName; // SKU名称
    private String skuType; // SKU规格分类
    private BigDecimal price; // 价格
    private Integer stock; // 库存数量
    private String skuImage; // SKU图片
    private String status; // 状态：on_sale, off_sale
}
