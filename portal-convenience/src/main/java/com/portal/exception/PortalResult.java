package com.portal.exception;

public class PortalResult {
    private Integer code;
    private String msg;
    private ErrorCode errorCode;

    public PortalResult(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.errorCode = errorCode;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
