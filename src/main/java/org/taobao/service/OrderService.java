package org.taobao.service;

import org.taobao.dto.OrderCreateDTO;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * 订单Service接口
 */
public interface OrderService {

    /**
     * 创建订单
     * 
     * @param orderCreateDTO 订单创建DTO
     * @return 订单ID
     */
    Integer createOrder(OrderCreateDTO orderCreateDTO);

    /**
     * 获取订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单列表
     */
    List<Orders> getOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * 获取订单总数
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单总数
     */
    Integer getOrderCount(OrderQueryDTO orderQueryDTO);

    /**
     * 获取订单详情
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    Orders getOrderById(Integer orderId);

    /**
     * 获取订单商品列表
     * 
     * @param orderId 订单ID
     * @return 订单商品列表
     */
    List<OrderItem> getOrderItems(Integer orderId);

    /**
     * 更新订单状态
     * 
     * @param orderId 订单ID
     * @param status  订单状态
     */
    void updateOrderStatus(Integer orderId, String status);
    
    /**
     * 获取用户订单状态统计
     * 
     * @param userId 用户ID
     * @return 包含各种订单状态数量的Map
     */
    Map<String, Long> getOrderStatusStatistics(Integer userId);
}
