package com.pddbend.shortlink.admin.common.convention.exception;

import com.pddbend.shortlink.admin.common.convention.errcode.BaseErrorCode;
import com.pddbend.shortlink.admin.common.convention.errcode.IErrorCode;

/**
 * @Author: pddbend
 * @Date: 2023-11-15-下午2:21
 * @Description: 远程服务调用异常
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}