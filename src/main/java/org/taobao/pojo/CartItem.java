package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 购物车表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer cartItemId; // 购物车项ID
    private Integer userId; // 用户ID
    private Integer skuId; // SKU ID
    private Integer quantity; // 商品数量
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间
    private ProductSku sku; // SKU详细信息
}