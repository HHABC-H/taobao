package org.taobao.exception;

/**
 * 地址不存在异常
 */
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException() {
        super("地址不存在");
    }
}
