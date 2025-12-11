package org.taobao.dto;

import lombok.Data;

import java.util.List;

/**
 * 商品创建DTO
 */
@Data
public class ProductCreateDTO {
    private String productName; // 商品名称
    private String description; // 商品描述
    private String mainImages; // 商品主图URL（多个URL用逗号分隔）
    private String detailImages; // 商品详情图URL（多个URL用逗号分隔）
    private Integer categoryId; // 分类ID
    private Integer shopId; // 店铺ID
    private List<ProductSkuCreateDTO> skus; // 商品SKU列表
}
