package org.taobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taobao.dto.BatchUpdateCartItemDTO;
import org.taobao.dto.CartItemAddDTO;
import org.taobao.dto.CartItemUpdateDTO;
import org.taobao.pojo.Result;
import org.taobao.pojo.CartItem;
import org.taobao.service.CartService;

import java.util.List;
import java.util.Map;

/**
 * 购物车Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     * 
     * @param cartItemAddDTO 购物车添加信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result<String> addCartItem(@RequestBody CartItemAddDTO cartItemAddDTO) {
        try {
            cartService.addCartItem(cartItemAddDTO);
            return Result.success("添加商品到购物车成功");
        } catch (Exception e) {
            return Result.error("添加商品到购物车失败：" + e.getMessage());
        }
    }

    /**
     * 批量更新购物车商品数量
     * 
     * @param batchUpdateCartItemDTO 批量更新购物车信息
     * @return 更新结果
     */
    @PutMapping("/update/quantity")
    public Result<String> batchUpdateCartItemQuantity(@RequestBody BatchUpdateCartItemDTO batchUpdateCartItemDTO) {
        try {
            // 遍历items数组，逐个更新商品数量
            for (Map<String, Object> item : batchUpdateCartItemDTO.getItems()) {
                Integer cartItemId = (Integer) item.get("cartItemId");
                Integer quantity = (Integer) item.get("quantity");

                // 创建CartItemUpdateDTO对象
                CartItemUpdateDTO cartItemUpdateDTO = new CartItemUpdateDTO();
                cartItemUpdateDTO.setCartItemId(cartItemId);
                cartItemUpdateDTO.setQuantity(quantity);

                // 调用现有服务方法更新商品数量
                cartService.updateCartItem(cartItemUpdateDTO);
            }
            return Result.success("批量更新购物车商品数量成功");
        } catch (Exception e) {
            return Result.error("批量更新购物车商品数量失败：" + e.getMessage());
        }
    }

    /**
     * 修改购物车商品规格（支持同时修改数量）
     * 
     * @param cartItemUpdateDTO 购物车更新信息
     * @return 更新结果
     */
    @PutMapping("/update/sku")
    public Result<String> updateCartItemSku(@RequestBody CartItemUpdateDTO cartItemUpdateDTO) {
        try {
            cartService.updateCartItem(cartItemUpdateDTO);
            return Result.success("修改购物车商品规格成功");
        } catch (Exception e) {
            return Result.error("修改购物车商品规格失败：" + e.getMessage());
        }
    }

    /**
     * 删除购物车商品
     * 
     * @param cartItemId 购物车项ID
     * @return 删除结果
     */
    @DeleteMapping("/{cartItemId}")
    public Result<String> deleteCartItem(@PathVariable Integer cartItemId) {
        try {
            cartService.deleteCartItem(cartItemId);
            return Result.success("删除购物车商品成功");
        } catch (Exception e) {
            return Result.error("删除购物车商品失败：" + e.getMessage());
        }
    }

    /**
     * 清空购物车
     * 
     * @return 清空结果
     */
    @DeleteMapping("/clear")
    public Result<String> clearCart() {
        try {
            cartService.clearCart();
            return Result.success("清空购物车成功");
        } catch (Exception e) {
            return Result.error("清空购物车失败：" + e.getMessage());
        }
    }

    /**
     * 获取购物车列表
     * 
     * @return 购物车列表
     */
    @GetMapping("/list")
    public Result<List<CartItem>> getCartList() {
        try {
            List<CartItem> cartList = cartService.getCartList();
            return Result.success(cartList);
        } catch (Exception e) {
            return Result.error("获取购物车列表失败：" + e.getMessage());
        }
    }
}