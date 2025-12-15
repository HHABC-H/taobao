package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.dto.OrderQueryDTO;
import org.taobao.mapper.AdminMapper;
import org.taobao.mapper.OrderMapper;
import org.taobao.pojo.OrderItem;
import org.taobao.pojo.Orders;
import org.taobao.pojo.UserAddress;
import org.taobao.service.AdminService;
import org.taobao.service.OrderService;
import org.taobao.service.UserAddressService;
import org.taobao.vo.AdminDashboardVO;
import org.taobao.vo.PageResult;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAddressService userAddressService;

    @Override
    public AdminDashboardVO getDashboardData() {
        // 获取今日新增用户数
        Integer newUserCount = adminMapper.getTodayNewUserCount();

        // 获取今日交易额
        BigDecimal todayTransactionAmount = adminMapper.getTodayTransactionAmount();

        // 获取今日新增订单数
        Integer newOrderCount = adminMapper.getTodayNewOrderCount();

        // 获取今日完成订单数
        Integer completedOrderCount = adminMapper.getTodayCompletedOrderCount();

        // 构建返回对象
        AdminDashboardVO dashboardVO = new AdminDashboardVO();
        dashboardVO.setNewUserCount(newUserCount != null ? newUserCount : 0);
        dashboardVO
                .setTodayTransactionAmount(todayTransactionAmount != null ? todayTransactionAmount : BigDecimal.ZERO);
        dashboardVO.setNewOrderCount(newOrderCount != null ? newOrderCount : 0);
        dashboardVO.setCompletedOrderCount(completedOrderCount != null ? completedOrderCount : 0);

        return dashboardVO;
    }

    @Override
    public PageResult<Orders> adminGetOrderList(OrderQueryDTO orderQueryDTO) {
        // 设置偏移量用于分页
        orderQueryDTO.setOffset((orderQueryDTO.getPageNum() - 1) * orderQueryDTO.getPageSize());

        // 获取订单列表
        List<Orders> orderList = adminMapper.getAdminOrderList(orderQueryDTO);

        // 获取订单总数
        Integer total = adminMapper.getAdminOrderCount(orderQueryDTO);

        // 为每个订单加载订单项信息并填充收货人信息
        for (Orders order : orderList) {
            List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItems(orderItems);

            // 从用户地址表中获取收货人姓名和电话
            fillConsigneeInfoFromUserAddress(order);
        }

        // 构建分页结果
        return PageResult.build(orderList, total.longValue(), orderQueryDTO.getPageNum(), orderQueryDTO.getPageSize());
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
    public Orders adminGetOrderDetail(Integer orderId) {
        // 获取订单详情，包含orderItems
        return orderService.getOrderById(orderId);
    }

    @Override
    public void adminCancelOrder(Integer orderId, String status) {
        // 更新订单状态
        orderService.updateOrderStatus(orderId, status);
    }
}