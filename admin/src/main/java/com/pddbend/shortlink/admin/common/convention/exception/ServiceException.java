package com.pddbend.shortlink.admin.common.convention.exception;

import com.pddbend.shortlink.admin.common.convention.errcode.BaseErrorCode;
import com.pddbend.shortlink.admin.common.convention.errcode.IErrorCode;

import java.util.Optional;

/**
 * @Author: pddbend
 * @Date: 2023-11-15-下午2:21
 * @Description: 服务端异常
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}