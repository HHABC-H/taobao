package org.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taobao.context.BaseContext;
import org.taobao.dto.OrderCreateDTO;
import org.taobao.dto.OrderListResponseDTO;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;
import org.taobao.pojo.Shop;
import org.taobao.service.OrderService;
import org.taobao.service.ShopService;

import java.util.List;
import java.util.Map;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    /**
     * 创建订单
     * 
     * @param orderCreateDTO 订单创建DTO
     * @return 订单ID
     */
    @PostMapping("/create")
    public Result<Integer> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        try {
            Integer orderId = orderService.createOrder(orderCreateDTO);
            return Result.success(orderId);
        } catch (Exception e) {
            return Result.error("订单创建失败：" + e.getMessage());
        }
    }

    /**
     * 获取订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单列表和总订单数
     */
    @GetMapping("/list")
    public Result<OrderListResponseDTO> getOrderList(OrderQueryDTO orderQueryDTO) {
        try {
            List<Orders> orderList = orderService.getOrderList(orderQueryDTO);
            Integer totalCount = orderService.getOrderCount(orderQueryDTO);

            OrderListResponseDTO response = new OrderListResponseDTO();
            response.setOrders(orderList);
            response.setTotalCount(totalCount);

            return Result.success(response);
        } catch (Exception e) {
            return Result.error("获取订单列表失败：" + e.getMessage());
        }
    }

    /**
     * 商家获取店铺订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 店铺订单列表
     */
    @GetMapping("/shop/list")
    public Result<List<Orders>> getShopOrderList(OrderQueryDTO orderQueryDTO) {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            // 根据商家ID获取店铺
            Shop shop = shopService.getShopByMerchantId(merchantId);
            // 设置店铺ID到查询条件中
            orderQueryDTO.setShopId(shop.getShopId());
            // 查询订单列表
            List<Orders> orderList = orderService.getOrderList(orderQueryDTO);
            return Result.success(orderList);
        } catch (Exception e) {
            return Result.error("获取店铺订单列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取订单详情
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("/{orderId}")
    public Result<Orders> getOrderById(@PathVariable Integer orderId) {
        try {
            Orders order = orderService.getOrderById(orderId);
            return Result.success(order);
        } catch (Exception e) {
            return Result.error("获取订单详情失败：" + e.getMessage());
        }
    }

    /**
     * 获取订单商品列表
     * 
     * @param orderId 订单ID
     * @return 订单商品列表
     */
    @GetMapping("/items/{orderId}")
    public Result<List<OrderItem>> getOrderItems(@PathVariable Integer orderId) {
        try {
            List<OrderItem> orderItems = orderService.getOrderItems(orderId);
            return Result.success(orderItems);
        } catch (Exception e) {
            return Result.error("获取订单商品列表失败：" + e.getMessage());
        }
    }

    /**
     * 修改订单状态（通用接口）
     * 支持的状态值：pending, paid, shipped, completed, cancelled
     * 
     * @param orderId 订单ID
     * @param status  订单状态
     * @return 修改结果
     */
    @PutMapping("/status/{orderId}")
    public Result<String> updateOrderStatus(@PathVariable Integer orderId, @RequestParam String status) {
        try {
            // 验证状态值是否合法
            if (!isValidOrderStatus(status)) {
                return Result.error("无效的订单状态值");
            }

            orderService.updateOrderStatus(orderId, status);
            return Result.success("订单状态修改成功");
        } catch (Exception e) {
            return Result.error("订单状态修改失败：" + e.getMessage());
        }
    }

    /**
     * 验证订单状态是否合法
     * 
     * @param status 订单状态
     * @return 是否合法
     */
    private boolean isValidOrderStatus(String status) {
        return "pending".equals(status) ||
                "paid".equals(status) ||
                "shipped".equals(status) ||
                "completed".equals(status) ||
                "cancelled".equals(status);
    }

    /**
     * 获取订单状态统计
     * 
     * @return 订单状态统计数据
     */
    @GetMapping("/status/statistics")
    public Result<Map<String, Long>> getOrderStatusStatistics() {
        try {
            // 从BaseContext中获取当前用户ID
            Integer userId = BaseContext.getCurrentId().intValue();
            // 获取订单状态统计
            Map<String, Long> statistics = orderService.getOrderStatusStatistics(userId);
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error("获取订单状态统计失败：" + e.getMessage());
        }
    }
}
