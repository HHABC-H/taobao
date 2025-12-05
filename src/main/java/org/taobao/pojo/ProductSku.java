package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品SKU表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku {
    private Integer skuId; // SKU ID
    private Integer productId; // 商品ID
    private String skuName; // SKU名称
    private String skuType; // SKU规格分类
    private BigDecimal price; // 价格
    private Integer stock; // 库存数量
    private Integer soldCount; // 销量
    private String skuImage; // SKU图片
    private String status; // 状态：on_sale, off_sale
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间
}