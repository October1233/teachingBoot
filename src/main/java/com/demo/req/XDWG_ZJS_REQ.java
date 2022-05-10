package com.demo.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_REQ {

    /**合作方编码**/
    @NotNull(message = "合作方编码不能为空")
    private String corpCode;

    /**交易流水号**/
    @NotNull(message = "交易流水号不能为空")
    private String msgId;

    /**交易时间**/
    @NotNull(message = "交易时间不能为空")
    private String tradeTime;

    /**业务报文数据加密后字符串**/
    @NotNull(message = "业务报文数据不能为空")
    private String data;

    /**签名串**/
    @NotNull(message = "签名串不能为空")
    private String mac;
}
