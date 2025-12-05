package org.taobao.exception;

/**
 * 店铺名称已存在异常
 */
public class ShopNameAlreadyExistsException extends RuntimeException {
    public ShopNameAlreadyExistsException(String message) {
        super(message);
    }
}
