package org.taobao.service;

import org.taobao.vo.AdminDashboardVO;

import org.taobao.dto.OrderQueryDTO;
import org.taobao.pojo.Orders;
import org.taobao.vo.PageResult;

import java.util.List;

public interface AdminService {

    /**
     * 获取管理员首页统计数据
     * 
     * @return 首页统计数据
     */
    AdminDashboardVO getDashboardData();

    /**
     * 管理员获取订单列表（分页）
     * 
     * @param orderQueryDTO 查询条件
     * @return 订单分页结果
     */
    PageResult<Orders> adminGetOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * 管理员获取订单详情
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    Orders adminGetOrderDetail(Integer orderId);

    /**
     * 管理员取消订单
     * 
     * @param orderId 订单ID
     * @param status  订单状态
     */
    void adminCancelOrder(Integer orderId, String status);
}