package org.taobao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.taobao.pojo.CartItem;

import java.util.List;

/**
 * 购物车Mapper接口
 */
@Mapper
public interface CartMapper {
    /**
     * 根据用户ID和SKU ID查询购物车项
     * 
     * @param userId 用户ID
     * @param skuId  SKU ID
     * @return 购物车项
     */
    @Select("SELECT cart_item_id AS cartItemId, user_id AS userId, sku_id AS skuId, quantity, create_time AS createTime, update_time AS updateTime FROM cart_item WHERE user_id = #{userId} AND sku_id = #{skuId}")
    CartItem findByUserIdAndSkuId(Integer userId, Integer skuId);

    /**
     * 添加购物车项
     * 
     * @param cartItem 购物车项信息
     */
    @Insert("INSERT INTO cart_item (user_id, sku_id, quantity, create_time, update_time) VALUES (#{userId}, #{skuId}, #{quantity}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "cartItemId")
    void insert(CartItem cartItem);

    /**
     * 更新购物车项数量
     * 
     * @param cartItemId 购物车项ID
     * @param quantity   商品数量
     * @param updateTime 更新时间
     */
    @Update("UPDATE cart_item SET quantity = #{quantity}, update_time = #{updateTime} WHERE cart_item_id = #{cartItemId}")
    void updateQuantity(Integer cartItemId, Integer quantity, java.util.Date updateTime);

    /**
     * 根据购物车项ID删除购物车项
     * 
     * @param cartItemId 购物车项ID
     */
    @Delete("DELETE FROM cart_item WHERE cart_item_id = #{cartItemId}")
    void deleteById(Integer cartItemId);

    /**
     * 根据用户ID删除所有购物车项
     * 
     * @param userId 用户ID
     */
    @Delete("DELETE FROM cart_item WHERE user_id = #{userId}")
    void deleteByUserId(Integer userId);

    /**
     * 根据用户ID获取购物车列表
     * 
     * @param userId 用户ID
     * @return 购物车列表
     */
    @Select("SELECT cart_item_id AS cartItemId, user_id AS userId, sku_id AS skuId, quantity, create_time AS createTime, update_time AS updateTime FROM cart_item WHERE user_id = #{userId} ORDER BY update_time DESC")
    List<CartItem> findByUserId(Integer userId);
}