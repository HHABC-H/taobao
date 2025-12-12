package org.taobao.service;

import org.taobao.pojo.User;

import java.util.List;

/**
 * 商家Service接口
 */
public interface MerchantService {
    /**
     * 获取所有商家列表
     *
     * @return 商家列表
     */
    List<User> getMerchantList();

    /**
     * 审核商家
     *
     * @param merchantId  商家ID
     * @param status      状态：active-通过，inactive-拒绝
     * @param auditRemark 审核备注
     */
    void auditMerchant(Integer merchantId, String status, String auditRemark);
}