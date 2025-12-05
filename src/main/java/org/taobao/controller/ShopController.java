package org.taobao.controller;

import org.taobao.context.BaseContext;
import org.taobao.dto.ShopCreateDTO;
import org.taobao.dto.ShopQueryDTO;
import org.taobao.dto.ShopStatisticsDTO;
import org.taobao.dto.ShopUpdateDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.Shop;
import org.taobao.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 店铺Controller
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 根据店铺ID获取店铺信息
     * 
     * @param shopId 店铺ID
     * @return 店铺信息
     */
    @GetMapping("/{shopId}")
    public Result<Shop> getShopById(@PathVariable Integer shopId) {
        try {
            Shop shop = shopService.getShopById(shopId);
            return Result.success(shop);
        } catch (Exception e) {
            return Result.error("获取店铺信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取当前商家的店铺信息
     * 
     * @return 店铺信息
     */
    @GetMapping("/my")
    public Result<Shop> getMyShop() {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            Shop shop = shopService.getShopByMerchantId(merchantId);
            return Result.success(shop);
        } catch (Exception e) {
            return Result.error("获取店铺信息失败：" + e.getMessage());
        }
    }

    /**
     * 创建店铺
     * 
     * @param shopCreateDTO 店铺创建信息
     * @return 创建结果
     */
    @PostMapping("/create")
    public Result<String> createShop(@RequestBody ShopCreateDTO shopCreateDTO) {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            shopService.createShop(merchantId, shopCreateDTO);
            return Result.success("店铺创建成功，正在审核中");
        } catch (Exception e) {
            return Result.error("店铺创建失败：" + e.getMessage());
        }
    }

    /**
     * 更新店铺信息
     * 
     * @param shopUpdateDTO 店铺更新信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result<String> updateShop(@RequestBody ShopUpdateDTO shopUpdateDTO) {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            shopService.updateShopByMerchantId(merchantId, shopUpdateDTO);
            return Result.success("店铺信息更新成功");
        } catch (Exception e) {
            return Result.error("店铺信息更新失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取店铺列表
     * 
     * @param shopQueryDTO 查询条件
     * @return 店铺列表
     */
    @GetMapping("/list")
    public Result<List<Shop>> getShopList(ShopQueryDTO shopQueryDTO) {
        try {
            List<Shop> shopList = shopService.getShopList(shopQueryDTO);
            return Result.success(shopList);
        } catch (Exception e) {
            return Result.error("获取店铺列表失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取店铺统计信息
     * 
     * @param shopId 店铺ID
     * @return 店铺统计信息
     */
    @GetMapping("/statistics/{shopId}")
    public Result<ShopStatisticsDTO> getShopStatistics(@PathVariable Integer shopId) {
        try {
            ShopStatisticsDTO statistics = shopService.getShopStatistics(shopId);
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error("获取店铺统计信息失败：" + e.getMessage());
        }
    }
    
    /**
     * 获取当前商家店铺的统计信息
     * 
     * @return 店铺统计信息
     */
    @GetMapping("/statistics/my")
    public Result<ShopStatisticsDTO> getMyShopStatistics() {
        try {
            // 从BaseContext中获取当前用户ID
            Integer merchantId = BaseContext.getCurrentId().intValue();
            // 获取当前商家的店铺
            Shop shop = shopService.getShopByMerchantId(merchantId);
            // 获取店铺统计信息
            ShopStatisticsDTO statistics = shopService.getShopStatistics(shop.getShopId());
            return Result.success(statistics);
        } catch (Exception e) {
            return Result.error("获取店铺统计信息失败：" + e.getMessage());
        }
    }
    
    /**
     * 审核店铺
     * 
     * @param shopId 店铺ID
     * @param status 审核状态：normal-通过，closed-拒绝
     * @return 审核结果
     */
    @PutMapping("/audit/{shopId}/{status}")
    public Result<String> auditShop(@PathVariable Integer shopId, @PathVariable String status) {
        try {
            shopService.auditShop(shopId, status);
            return Result.success("店铺审核成功");
        } catch (Exception e) {
            return Result.error("店铺审核失败：" + e.getMessage());
        }
    }
    
    /**
     * 关闭店铺
     * 
     * @param shopId 店铺ID
     * @return 关闭结果
     */
    @PutMapping("/close/{shopId}")
    public Result<String> closeShop(@PathVariable Integer shopId) {
        try {
            shopService.closeShop(shopId);
            return Result.success("店铺关闭成功");
        } catch (Exception e) {
            return Result.error("店铺关闭失败：" + e.getMessage());
        }
    }
    
    /**
     * 重新开店
     * 
     * @param shopId 店铺ID
     * @return 重新开店结果
     */
    @PutMapping("/reopen/{shopId}")
    public Result<String> reopenShop(@PathVariable Integer shopId) {
        try {
            shopService.reopenShop(shopId);
            return Result.success("店铺重新开店成功");
        } catch (Exception e) {
            return Result.error("店铺重新开店失败：" + e.getMessage());
        }
    }
}
