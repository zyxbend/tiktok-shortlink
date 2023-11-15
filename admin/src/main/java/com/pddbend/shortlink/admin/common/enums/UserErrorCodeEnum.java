package com.pddbend.shortlink.admin.common.enums;

import com.pddbend.shortlink.admin.common.convention.errcode.IErrorCode;

/**
 * @Author: pddbend
 * @Date: 2023-11-15-下午1:35
 * @Description: 用户错误码
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_NOT_EXIST("B000200", "用户记录不存在"),
    USER_EXISTED("B000201", "用户记录已存在");


    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
