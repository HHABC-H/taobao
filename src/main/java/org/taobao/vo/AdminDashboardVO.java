package org.taobao.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AdminDashboardVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 今日新增用户数
    private Integer newUserCount;

    // 今日交易额
    private BigDecimal todayTransactionAmount;

    // 今日新增订单数
    private Integer newOrderCount;

    // 今日完成订单数
    private Integer completedOrderCount;
}