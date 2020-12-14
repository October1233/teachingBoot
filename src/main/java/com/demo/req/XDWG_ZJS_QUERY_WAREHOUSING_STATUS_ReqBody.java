package com.demo.req;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 宅急送-出库记录查询请求实体
 * date：2020-12-03
 * writer：sy05893
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_QUERY_WAREHOUSING_STATUS_ReqBody implements Serializable {

    private static final long serialVersionUID = -1366722770354513468L;

    /**出库时间**/
    @NotEmpty(message = "出库时间不能为空")
    private String operationTime;

    /**客户编号**/
    @NotEmpty(message = "客户编号不能为空")
    private String storeID;

    /**交易类型IN入库OUT出库**/
    @NotEmpty(message = "交易类型不能为空")
    private String transType;

    /**出入库单号**/
    @NotEmpty(message = "出入库单号不能为空")
    private String orderNo;

}
