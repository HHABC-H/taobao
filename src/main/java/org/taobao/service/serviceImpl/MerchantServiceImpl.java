package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.mapper.MerchantMapper;
import org.taobao.pojo.User;
import org.taobao.service.MerchantService;

import java.util.List;

/**
 * 商家Service实现类
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public List<User> getMerchantList() {
        return merchantMapper.getMerchantList();
    }

    @Override
    public void auditMerchant(Integer merchantId, String status, String auditRemark) {
        merchantMapper.auditMerchant(merchantId, status, auditRemark);
    }
}