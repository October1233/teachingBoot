package com.demoPack.Exception;


import com.demoPack.code.ZjsApiCodes;

/**
 * @author Q-p
 * 宅急送Api异常
 */
public class ZjsApiException extends RuntimeException {


    private static final long serialVersionUID = -206586734391896498L;
    private ZjsApiCodes status;

    public ZjsApiException() {
        super();
    }

    public ZjsApiException(ZjsApiCodes status) {
        super(status.getMessage());
        this.status = status;
    }

    /**
     * @param status 异常代码
     * @param info	异常辅助信息
     */
    public ZjsApiException(ZjsApiCodes status,String info) {
        super(new StringBuffer(status.getMessage()).append("#_#").append(info).toString());
        this.status = status;
    }

    public ZjsApiException(ZjsApiCodes status,Throwable t) {
        super(status.getMessage(),t);
        this.status = status;
    }


    public ZjsApiCodes getStatus() {
        return status;
    }

    public void setStatus(ZjsApiCodes status) {
        this.status = status;
    }

}

