package org.taobao.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品SKU创建DTO
 */
@Data
public class ProductSkuCreateDTO {
    private String skuName; // SKU名称
    private String skuType; // SKU规格分类
    private BigDecimal price; // 价格
    private Integer stock; // 库存数量（可选，默认为50）
    private String skuImage; // SKU图片
}
