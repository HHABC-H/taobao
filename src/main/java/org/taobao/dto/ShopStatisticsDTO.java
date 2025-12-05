package org.taobao.dto;

import lombok.Data;

/**
 * 店铺统计DTO
 */
@Data
public class ShopStatisticsDTO {
    private Integer shopId; // 店铺ID
    private String shopName; // 店铺名称
    private Integer productCount; // 商品数量
    private Integer orderCount; // 订单数量
    private Double totalSales; // 总销售额
    private Integer pendingOrderCount; // 待处理订单数量
    private Integer onSaleProductCount; // 在售商品数量
    private Integer offSaleProductCount; // 下架商品数量
}
