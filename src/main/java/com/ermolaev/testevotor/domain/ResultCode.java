package com.ermolaev.testevotor.domain;

/**
 * @author Ivan Ermolaev
 * @date 13:53 09-11-2016.
 */
public enum ResultCode  {

    OK(0),
    USER_ALREADY_EXIST(1),
    INTERNAL_ERROR(2);

    ResultCode(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return this.code;
    }
}
