package org.taobao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.dto.ShopQueryDTO;
import org.taobao.dto.ShopStatisticsDTO;
import org.taobao.pojo.Shop;

import java.util.List;

/**
 * 店铺Mapper接口
 */
@Mapper
public interface ShopMapper {
    /**
     * 根据店铺ID查询店铺
     * 
     * @param shopId 店铺ID
     * @return 店铺信息
     */
    @Select("SELECT shop_id AS shopId, merchant_id AS merchantId, shop_name AS shopName, shop_description AS shopDescription, shop_logo AS shopLogo, shop_banner AS shopBanner, status, create_time AS createTime, update_time AS updateTime FROM shop WHERE shop_id = #{shopId}")
    Shop findById(Integer shopId);

    /**
     * 根据商家ID查询店铺
     * 
     * @param merchantId 商家ID
     * @return 店铺信息
     */
    @Select("SELECT shop_id AS shopId, merchant_id AS merchantId, shop_name AS shopName, shop_description AS shopDescription, shop_logo AS shopLogo, shop_banner AS shopBanner, status, create_time AS createTime, update_time AS updateTime FROM shop WHERE merchant_id = #{merchantId}")
    Shop findByMerchantId(Integer merchantId);

    /**
     * 根据店铺名称查询店铺
     * 
     * @param shopName 店铺名称
     * @return 店铺信息
     */
    @Select("SELECT shop_id AS shopId, merchant_id AS merchantId, shop_name AS shopName, shop_description AS shopDescription, shop_logo AS shopLogo, shop_banner AS shopBanner, status, create_time AS createTime, update_time AS updateTime FROM shop WHERE shop_name = #{shopName}")
    Shop findByShopName(String shopName);

    /**
     * 插入店铺信息
     * 
     * @param shop 店铺信息
     */
    @Insert("INSERT INTO shop (merchant_id, shop_name, shop_description, shop_logo, shop_banner, status, create_time, update_time) VALUES (#{merchantId}, #{shopName}, #{shopDescription}, #{shopLogo}, #{shopBanner}, #{status}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "shopId")
    void insert(Shop shop);

    /**
     * 更新店铺信息
     * 
     * @param shop 店铺信息
     */
    @Update("UPDATE shop SET shop_name = #{shopName}, shop_description = #{shopDescription}, shop_logo = #{shopLogo}, shop_banner = #{shopBanner}, status = #{status}, update_time = #{updateTime} WHERE shop_id = #{shopId}")
    void update(Shop shop);

    /**
     * 根据商家ID更新店铺信息
     * 
     * @param shop 店铺信息
     */
    @Update("UPDATE shop SET shop_name = #{shopName}, shop_description = #{shopDescription}, shop_logo = #{shopLogo}, shop_banner = #{shopBanner}, status = #{status}, update_time = #{updateTime} WHERE merchant_id = #{merchantId}")
    void updateByMerchantId(Shop shop);

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
}
