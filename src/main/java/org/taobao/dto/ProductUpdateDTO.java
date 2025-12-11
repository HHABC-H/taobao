package org.taobao.dto;

import lombok.Data;

import java.util.List;

/**
 * 商品更新DTO
 */
@Data
public class ProductUpdateDTO {
    private String productName; // 商品名称
    private String description; // 商品描述
    private String mainImages; // 商品主图URL（多个URL用逗号分隔）
    private String detailImages; // 商品详情图URL（多个URL用逗号分隔）
    private Integer categoryId; // 分类ID
    private String status; // 商品状态：on_sale, off_sale
    private List<ProductSkuUpdateDTO> skus; // 商品SKU列表
}
