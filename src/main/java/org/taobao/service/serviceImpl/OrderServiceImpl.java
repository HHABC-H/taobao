package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.context.BaseContext;
import org.taobao.dto.OrderCreateDTO;
import org.taobao.dto.OrderItemDTO;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.mapper.OrderMapper;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;
import org.taobao.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 订单Service实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer createOrder(OrderCreateDTO orderCreateDTO) {
        // 获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 生成订单编号
        String orderNo = UUID.randomUUID().toString().replace("-", "");

        // 创建订单对象
        Orders orders = new Orders();
        orders.setOrderNo(orderNo);
        orders.setUserId(userId);
        orders.setShippingAddress(orderCreateDTO.getAddress());
        orders.setStatus("pending"); // 待支付状态

        // 计算总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItemDTO> orderItems = orderCreateDTO.getOrderItems();
        for (OrderItemDTO item : orderItems) {
            BigDecimal itemAmount = BigDecimal.valueOf(item.getPrice())
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemAmount);
        }
        orders.setTotalAmount(totalAmount);
        orders.setCreateTime(new Date());
        orders.setUpdateTime(new Date());

        // 插入订单
        orderMapper.insertOrder(orders);

        // 插入订单商品项
        for (OrderItemDTO itemDTO : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orders.getOrderId());
            orderItem.setSkuId(itemDTO.getSkuId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(itemDTO.getPrice()));
            BigDecimal itemTotalPrice = BigDecimal.valueOf(itemDTO.getPrice())
                    .multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            orderItem.setTotalPrice(itemTotalPrice);
            orderItem.setCreateTime(new Date());

            orderMapper.insertOrderItem(orderItem);
        }

        return orders.getOrderId();
    }

    @Override
    public List<Orders> getOrderList(OrderQueryDTO orderQueryDTO) {
        // 如果未指定用户ID，则使用当前登录用户ID
        if (orderQueryDTO.getUserId() == null) {
            orderQueryDTO.setUserId(BaseContext.getCurrentId().intValue());
        }

        return orderMapper.getOrderList(orderQueryDTO);
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public List<OrderItem> getOrderItems(Integer orderId) {
        return orderMapper.getOrderItemsByOrderId(orderId);
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {
        orderMapper.updateOrderStatus(orderId, status);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        orderMapper.updateOrderStatus(orderId, "cancelled");
    }

    @Override
    public void confirmOrder(Integer orderId) {
        orderMapper.updateOrderStatus(orderId, "completed");
    }

    @Override
    public void payOrder(Integer orderId) {
        orderMapper.updateOrderStatus(orderId, "paid");
    }
    
    @Override
    public Map<String, Long> getOrderStatusStatistics(Long userId) {
        // 调用mapper获取各状态订单数量
        return orderMapper.getOrderStatusStatistics(userId);
    }
}
