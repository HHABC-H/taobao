package org.taobao.dto;

import lombok.Data;

/**
 * 订单查询DTO
 */
@Data
public class OrderQueryDTO {
    private Integer userId; // 用户ID
    private String orderStatus; // 订单状态
    private String orderNo; // 订单编号
    private String consignee; // 收货人
    private String phone; // 收货人电话
    private String startDate; // 开始日期
    private String endDate; // 结束日期
    private Integer pageNum = 1; // 页码，默认1
    private Integer pageSize = 10; // 每页条数，默认10
}
