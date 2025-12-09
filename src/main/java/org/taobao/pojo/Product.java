package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId; // 商品ID
    private String productName; // 商品名称
    private String description; // 商品描述
    private String mainImages; // 商品主图列表（JSON数组字符串）
    private String detailImages; // 商品详情图列表（JSON数组字符串）
    private Integer categoryId; // 分类ID
    private Integer merchantId; // 商家ID
    private Integer shopId; // 店铺ID，关联shop表
    private String status; // 商品状态：on_sale, off_sale
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间
    private List<ProductSku> skus; // 商品SKU列表
    private BigDecimal price; // 商品第一个SKU的价格
}