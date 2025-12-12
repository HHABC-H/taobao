package org.taobao.dto;

import lombok.Data;
import org.taobao.pojo.Orders;

import java.util.List;

/**
 * 订单列表响应DTO
 */
@Data
public class OrderListResponseDTO {
    /**
     * 订单列表
     */
    private List<Orders> orders;

    /**
     * 总订单数
     */
    private Integer totalCount;
}