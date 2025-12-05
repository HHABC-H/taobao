package org.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taobao.dto.OrderCreateDTO;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.Orders;
import org.taobao.pojo.OrderItem;
import org.taobao.service.OrderService;

import java.util.List;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * 
     * @param orderCreateDTO 订单创建DTO
     * @return 创建结果
     */
    @PostMapping("/create")
    public Result<String> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        try {
            Integer orderId = orderService.createOrder(orderCreateDTO);
            return Result.success("订单创建成功，订单ID：" + orderId);
        } catch (Exception e) {
            return Result.error("订单创建失败：" + e.getMessage());
        }
    }

    /**
     * 获取订单列表
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单列表
     */
    @GetMapping("/list")
    public Result<List<Orders>> getOrderList(OrderQueryDTO orderQueryDTO) {
        try {
            List<Orders> orderList = orderService.getOrderList(orderQueryDTO);
            return Result.success(orderList);
        } catch (Exception e) {
            return Result.error("获取订单列表失败：" + e.getMessage());
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
     * 取消订单
     * 
     * @param orderId 订单ID
     * @return 取消结果
     */
    @PutMapping("/cancel/{orderId}")
    public Result<String> cancelOrder(@PathVariable Integer orderId) {
        try {
            orderService.cancelOrder(orderId);
            return Result.success("订单取消成功");
        } catch (Exception e) {
            return Result.error("订单取消失败：" + e.getMessage());
        }
    }

    /**
     * 支付订单
     * 
     * @param orderId 订单ID
     * @return 支付结果
     */
    @PutMapping("/pay/{orderId}")
    public Result<String> payOrder(@PathVariable Integer orderId) {
        try {
            orderService.payOrder(orderId);
            return Result.success("订单支付成功");
        } catch (Exception e) {
            return Result.error("订单支付失败：" + e.getMessage());
        }
    }

    /**
     * 确认收货
     * 
     * @param orderId 订单ID
     * @return 确认结果
     */
    @PutMapping("/confirm/{orderId}")
    public Result<String> confirmOrder(@PathVariable Integer orderId) {
        try {
            orderService.confirmOrder(orderId);
            return Result.success("订单确认收货成功");
        } catch (Exception e) {
            return Result.error("订单确认收货失败：" + e.getMessage());
        }
    }
}
