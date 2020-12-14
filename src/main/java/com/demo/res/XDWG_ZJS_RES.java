package com.demo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_RES implements Serializable {

    /**商户号**/
    private String corpCode;

    /**交易时间**/
    private String tradeTime;

    /**响应码**/
    private String respCode;

    /**业务相应参数**/
    private XDWG_ZJS_RES_DATA data;

    /**签名串**/
    private String mac;

}
