package org.taobao.controller;

import org.taobao.pojo.Result;
import org.taobao.pojo.User;
import org.taobao.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家控制器
 */
@RestController
@RequestMapping("/admin/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    /**
     * 获取所有商家列表
     *
     * @return 商家列表
     */
    @GetMapping("/list")
    public Result<List<User>> getMerchantList() {
        try {
            List<User> merchantList = merchantService.getMerchantList();
            return Result.success(merchantList);
        } catch (Exception e) {
            return Result.error("获取商家列表失败：" + e.getMessage());
        }
    }

    /**
     * 审核商家
     *
     * @param merchantId  商家ID
     * @param status      状态：active-通过，inactive-拒绝
     * @param auditRemark 审核备注
     * @return 审核结果
     */
    @PutMapping("/audit/{merchantId}")
    public Result<String> auditMerchant(@PathVariable Integer merchantId, @RequestParam String status,
            @RequestParam(required = false) String auditRemark) {
        try {
            merchantService.auditMerchant(merchantId, status, auditRemark);
            return Result.success("审核商家成功");
        } catch (Exception e) {
            return Result.error("审核商家失败：" + e.getMessage());
        }
    }
}