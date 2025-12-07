package org.taobao.service;

import org.taobao.dto.CartItemAddDTO;
import org.taobao.dto.CartItemUpdateDTO;
import org.taobao.pojo.CartItem;

import java.util.List;

/**
 * 购物车Service接口
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * 
     * @param cartItemAddDTO 购物车添加信息
     */
    void addCartItem(CartItemAddDTO cartItemAddDTO);

    /**
     * 更新购物车商品（支持修改数量和规格）
     * 
     * @param cartItemUpdateDTO 购物车更新信息
     */
    void updateCartItem(CartItemUpdateDTO cartItemUpdateDTO);

    /**
     * 删除购物车商品
     * 
     * @param cartItemId 购物车项ID
     */
    void deleteCartItem(Integer cartItemId);

    /**
     * 清空购物车
     */
    void clearCart();

    /**
     * 获取购物车列表
     * 
     * @return 购物车列表
     */
    List<CartItem> getCartList();
}