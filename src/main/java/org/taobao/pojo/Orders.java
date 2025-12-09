package org.taobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer orderId; // 订单ID
    private String orderNo; // 订单编号
    private Integer userId; // 用户ID
    private BigDecimal totalAmount; // 订单总金额
    private String status; // 订单状态：pending, paid, shipped, completed, cancelled
    private String shippingAddress; // 收货地址
    private String consigneeName; // 收货人姓名
    private String phone; // 收货人电话
    private Date paymentTime; // 支付时间
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间
    
    // 订单项列表
    private List<OrderItem> orderItems;
}