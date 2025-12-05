package org.taobao.exception;

/**
 * 账号已存在异常
 */
public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}