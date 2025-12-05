package org.taobao.exception;

/**
 * 账号被锁定异常
 */
public class AccountLockedException extends RuntimeException {
    public AccountLockedException(String message) {
        super(message);
    }
}