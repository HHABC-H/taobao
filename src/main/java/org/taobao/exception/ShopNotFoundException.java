package org.taobao.exception;

/**
 * 店铺不存在异常
 */
public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String message) {
        super(message);
    }
}
