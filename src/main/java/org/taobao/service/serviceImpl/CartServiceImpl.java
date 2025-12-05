package org.taobao.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taobao.context.BaseContext;
import org.taobao.dto.CartItemAddDTO;
import org.taobao.dto.CartItemUpdateDTO;
import org.taobao.mapper.CartMapper;
import org.taobao.pojo.CartItem;
import org.taobao.service.CartService;

import java.util.Date;
import java.util.List;

/**
 * 购物车Service实现类
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addCartItem(CartItemAddDTO cartItemAddDTO) {
        // 从BaseContext中获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();

        // 检查购物车中是否已存在该商品
        CartItem existingItem = cartMapper.findByUserIdAndSkuId(userId, cartItemAddDTO.getSkuId());

        Date now = new Date();

        if (existingItem != null) {
            // 已存在，更新数量
            int newQuantity = existingItem.getQuantity() + cartItemAddDTO.getQuantity();
            cartMapper.updateQuantity(existingItem.getCartItemId(), newQuantity, now);
        } else {
            // 不存在，添加新购物车项
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setSkuId(cartItemAddDTO.getSkuId());
            cartItem.setQuantity(cartItemAddDTO.getQuantity());
            cartItem.setCreateTime(now);
            cartItem.setUpdateTime(now);
            cartMapper.insert(cartItem);
        }
    }

    @Override
    public void updateCartItem(CartItemUpdateDTO cartItemUpdateDTO) {
        // 更新购物车项数量
        cartMapper.updateQuantity(
                cartItemUpdateDTO.getCartItemId(),
                cartItemUpdateDTO.getQuantity(),
                new Date());
    }

    @Override
    public void deleteCartItem(Integer cartItemId) {
        // 删除购物车项
        cartMapper.deleteById(cartItemId);
    }

    @Override
    public void clearCart() {
        // 从BaseContext中获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();
        // 删除当前用户的所有购物车项
        cartMapper.deleteByUserId(userId);
    }

    @Override
    public List<CartItem> getCartList() {
        // 从BaseContext中获取当前用户ID
        Integer userId = BaseContext.getCurrentId().intValue();
        // 获取当前用户的购物车列表
        return cartMapper.findByUserId(userId);
    }
}