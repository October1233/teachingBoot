package com.demoPack.domain;

import java.io.Serializable;

public class ZjsReqHead implements Serializable {

    private static final long serialVersionUID = 1L;
    /**合作商户代码*/
    private String corp_code = null;
    /**时间戳**/
    private Long timestamp = null;
    /**业务类型*/
    private String business_code = null;

    public String getCorp_code() {
        return corp_code;
    }

    public void setCorp_code(String corp_code) {
        this.corp_code = corp_code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBusiness_code() {
        return business_code;
    }

    public void setBusiness_code(String business_code) {
        this.business_code = business_code;
    }
}
