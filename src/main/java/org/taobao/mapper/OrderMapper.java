package org.taobao.mapper;

import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * 订单Mapper
 */
public interface OrderMapper {

    /**
     * 插入订单
     * 
     * @param orders 订单信息
     */
    void insertOrder(Orders orders);

    /**
     * 插入订单商品项
     * 
     * @param orderItem 订单商品项
     */
    void insertOrderItem(OrderItem orderItem);

    /**
     * 获取订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单列表
     */
    List<Orders> getOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * 根据ID获取订单
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    Orders getOrderById(Integer orderId);

    /**
     * 根据订单ID获取订单商品项
     * 
     * @param orderId 订单ID
     * @return 订单商品项列表
     */
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    /**
     * 更新订单状态
     * 
     * @param orderId 订单ID
     * @param status  订单状态
     */
    void updateOrderStatus(Integer orderId, String status);

    /**
     * 更新订单支付状态
     * 
     * @param orderId       订单ID
     * @param paymentStatus 支付状态
     */
    void updatePaymentStatus(Integer orderId, String paymentStatus);

    /**
     * 更新订单物流状态
     * 
     * @param orderId        订单ID
     * @param shippingStatus 物流状态
     */
    void updateShippingStatus(Integer orderId, String shippingStatus);
    
    /**
     * 获取用户订单状态统计
     * 
     * @param userId 用户ID
     * @return 各状态订单数量的Map
     */
    Map<String, Long> getOrderStatusStatistics(Long userId);
}
