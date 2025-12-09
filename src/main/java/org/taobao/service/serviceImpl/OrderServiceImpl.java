package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.context.BaseContext;
import org.taobao.dto.OrderCreateDTO;
import org.taobao.dto.OrderItemDTO;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.mapper.OrderMapper;
import org.taobao.mapper.ProductSkuMapper;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;
import org.taobao.pojo.ProductSku;
import org.taobao.pojo.UserAddress;
import org.taobao.service.OrderService;
import org.taobao.service.UserAddressService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private UserAddressService userAddressService;

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

        // 设置收货人信息
        // 优先从用户地址表获取默认地址信息
        try {
            UserAddress defaultAddress = userAddressService.getDefaultAddress();
            if (defaultAddress != null) {
                orders.setShippingAddress(defaultAddress.getFullAddress());
                orders.setConsigneeName(defaultAddress.getRecipientName());
                orders.setPhone(defaultAddress.getPhone());
            } else {
                // 如果没有默认地址，使用传入的地址信息
                orders.setShippingAddress(orderCreateDTO.getAddress());
                orders.setConsigneeName(orderCreateDTO.getConsignee());
                orders.setPhone(orderCreateDTO.getPhone());
            }
        } catch (Exception e) {
            // 如果获取默认地址失败，使用传入的地址信息
            orders.setShippingAddress(orderCreateDTO.getAddress());
            orders.setConsigneeName(orderCreateDTO.getConsignee());
            orders.setPhone(orderCreateDTO.getPhone());
        }

        orders.setStatus("pending"); // 待支付状态

        // 计算总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItemDTO> orderItems = orderCreateDTO.getOrderItems();

        // 检查订单商品列表是否为空
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("订单商品列表不能为空");
        }

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
            // 通过skuId查询SKU信息，包含商品名称
            ProductSku sku = productSkuMapper.findByIdWithProductInfo(itemDTO.getSkuId());
            if (sku == null) {
                throw new IllegalArgumentException("SKU不存在，skuId：" + itemDTO.getSkuId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orders.getOrderId());
            orderItem.setSkuId(itemDTO.getSkuId());
            // 设置商品名称和SKU类型
            orderItem.setProductName(sku.getProductName()); // 使用SPU商品名称
            orderItem.setSkuType(sku.getSkuType());
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

        // 计算偏移量
        if (orderQueryDTO.getPageNum() != null && orderQueryDTO.getPageSize() != null) {
            orderQueryDTO.setOffset((orderQueryDTO.getPageNum() - 1) * orderQueryDTO.getPageSize());
        }

        // 获取订单列表
        List<Orders> ordersList = orderMapper.getOrderList(orderQueryDTO);

        // 为每个订单加载订单项信息并处理收货人信息
        for (Orders order : ordersList) {
            List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItems(orderItems);

            // 从用户地址表中获取收货人姓名和电话
            fillConsigneeInfoFromUserAddress(order);
        }

        return ordersList;
    }

    /**
     * 从用户地址表中获取收货人姓名和电话信息
     * 
     * @param order 订单对象
     */
    private void fillConsigneeInfoFromUserAddress(Orders order) {
        try {
            // 根据用户ID和订单中的地址文本来查找对应的地址记录
            UserAddress address = userAddressService.getAddressByUserIdAndText(
                    order.getUserId(), order.getShippingAddress());
            if (address != null) {
                order.setConsigneeName(address.getRecipientName());
                order.setPhone(address.getPhone());
            }
        } catch (Exception e) {
            // 如果根据地址文本找不到匹配的地址，忽略错误，不影响主要功能
            // 可能用户已经删除了该地址或者地址有所变更
        }
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        Orders order = orderMapper.getOrderById(orderId);
        if (order != null) {
            List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(orderId);
            order.setOrderItems(orderItems);

            // 从用户地址表中获取收货人姓名和电话
            fillConsigneeInfoFromUserAddress(order);
        }
        return order;
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
    public Map<String, Long> getOrderStatusStatistics(Integer userId) {
        // 创建订单状态统计Map，初始值都为0
        Map<String, Long> result = new HashMap<>();
        result.put("pending", 0L);
        result.put("paid", 0L);
        result.put("shipped", 0L);
        result.put("completed", 0L);
        result.put("cancelled", 0L);

        try {
            // 调用mapper获取各状态订单数量
            List<Map<String, Object>> statusList = orderMapper.getOrderStatusStatistics(userId);

            // 如果查询结果不为空，更新统计数据
            if (statusList != null && !statusList.isEmpty()) {
                // 如果是单行结果（CASE WHEN查询）
                if (statusList.size() == 1) {
                    Map<String, Object> statusMap = statusList.get(0);
                    for (Map.Entry<String, Long> entry : result.entrySet()) {
                        Object value = statusMap.get(entry.getKey());
                        if (value != null) {
                            result.put(entry.getKey(), ((Number) value).longValue());
                        }
                    }
                } else {
                    // 如果是多行结果（GROUP BY查询）
                    for (Map<String, Object> statusMap : statusList) {
                        String status = (String) statusMap.get("status");
                        Long count = ((Number) statusMap.get("count")).longValue();
                        if (result.containsKey(status)) {
                            result.put(status, count);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // 如果查询失败，返回初始值为0的统计结果
            e.printStackTrace();
        }

        return result;
    }
}
