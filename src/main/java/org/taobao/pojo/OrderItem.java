package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单项表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer itemId; // 订单项ID
    private Integer orderId; // 订单ID
    private Integer skuId; // SKU ID
    private String productName; // 商品名称
    private String skuType; // 商品规格
    private BigDecimal price; // 单价
    private Integer quantity; // 数量
    private BigDecimal totalPrice; // 总价
    private Date createTime; // 创建时间
}