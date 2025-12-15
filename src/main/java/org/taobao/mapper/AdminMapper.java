package org.taobao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Orders;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AdminMapper {
    /**
     * 获取今日新增用户数
     * 
     * @return 今日新增用户数
     */
    Integer getTodayNewUserCount();

    /**
     * 获取今日交易额
     * 
     * @return 今日交易额
     */
    BigDecimal getTodayTransactionAmount();

    /**
     * 获取今日新增订单数
     * 
     * @return 今日新增订单数
     */
    Integer getTodayNewOrderCount();

    /**
     * 获取今日完成订单数
     * 
     * @return 今日完成订单数
     */
    Integer getTodayCompletedOrderCount();

    /**
     * 管理员获取订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单列表
     */
    List<Orders> getAdminOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * 管理员获取订单总数
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单总数
     */
    Integer getAdminOrderCount(OrderQueryDTO orderQueryDTO);
}