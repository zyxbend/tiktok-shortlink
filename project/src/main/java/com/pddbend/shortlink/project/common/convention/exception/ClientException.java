package com.pddbend.shortlink.project.common.convention.exception;

/**
 * @Author: pddbend
 * @Date: 2023-11-15-下午2:20
 * @Description: 客户端异常
 */

import com.pddbend.shortlink.project.common.convention.errcode.BaseErrorCode;
import com.pddbend.shortlink.project.common.convention.errcode.IErrorCode;

public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
