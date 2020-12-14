package com.demo.res;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 宅急送-预约出库响应实体
 * date：2020-12-03
 * writer：sy05893
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_RES_DATA implements Serializable {


    /**商户号**/
    private String corpCode;

    /**响应码**/
    private String orgMsgId;

    /**出库时间**/
    private String tradeTime;

    /**
     * 返回状态
     * S允许出入库
     * E禁止出入库
     * EE货主不存在
     **/
    private String status;

    /**失败原因描述**/
    private String failMsg;




}
