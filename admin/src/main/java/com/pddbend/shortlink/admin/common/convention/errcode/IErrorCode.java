package com.pddbend.shortlink.admin.common.convention.errcode;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午4:37
 * @Description: 平台错误代码
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}