package org.taobao.service;

import org.taobao.dto.ShopCreateDTO;
import org.taobao.dto.ShopQueryDTO;
import org.taobao.dto.ShopStatisticsDTO;
import org.taobao.dto.ShopUpdateDTO;
import org.taobao.pojo.Shop;

import java.util.List;

/**
 * 店铺Service接口
 */
public interface ShopService {
    /**
     * 根据店铺ID查询店铺
     * 
     * @param shopId 店铺ID
     * @return 店铺信息
     */
    Shop getShopById(Integer shopId);

    /**
     * 根据商家ID查询店铺
     * 
     * @param merchantId 商家ID
     * @return 店铺信息
     */
    Shop getShopByMerchantId(Integer merchantId);

    /**
     * 创建店铺
     * 
     * @param merchantId    商家ID
     * @param shopCreateDTO 店铺创建信息
     */
    void createShop(Integer merchantId, ShopCreateDTO shopCreateDTO);

    /**
     * 更新店铺信息
     * 
     * @param shopId        店铺ID
     * @param shopUpdateDTO 店铺更新信息
     */
    void updateShop(Integer shopId, ShopUpdateDTO shopUpdateDTO);

    /**
     * 根据商家ID更新店铺信息
     * 
     * @param merchantId    商家ID
     * @param shopUpdateDTO 店铺更新信息
     */
    void updateShopByMerchantId(Integer merchantId, ShopUpdateDTO shopUpdateDTO);
    
    /**
     * 获取店铺列表
     * 
     * @param shopQueryDTO 查询条件
     * @return 店铺列表
     */
    List<Shop> getShopList(ShopQueryDTO shopQueryDTO);
    
    /**
     * 获取店铺总数
     * 
     * @param shopQueryDTO 查询条件
     * @return 店铺总数
     */
    Integer getShopCount(ShopQueryDTO shopQueryDTO);
    
    /**
     * 获取店铺统计信息
     * 
     * @param shopId 店铺ID
     * @return 店铺统计信息
     */
    ShopStatisticsDTO getShopStatistics(Integer shopId);
    
    /**
     * 审核店铺
     * 
     * @param shopId 店铺ID
     * @param status 审核状态：normal-通过，closed-拒绝
     */
    void auditShop(Integer shopId, String status);
    
    /**
     * 关闭店铺
     * 
     * @param shopId 店铺ID
     */
    void closeShop(Integer shopId);
    
    /**
     * 重新开店
     * 
     * @param shopId 店铺ID
     */
    void reopenShop(Integer shopId);
}
