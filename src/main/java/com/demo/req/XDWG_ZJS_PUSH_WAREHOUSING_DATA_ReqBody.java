package com.demo.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * 宅急送-出库记录请求实体
 * date：2020-12-03
 * writer：sy05893
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_PUSH_WAREHOUSING_DATA_ReqBody implements Serializable {

    private static final long serialVersionUID = -1366722770354513468L;

    /**交易类型IN入库OUT出库**/
    @NotBlank(message = "交易类型不能为空")
    private String transType;

    /**出库时间**/
    @NotBlank(message = "出库时间不能为空")
    private String operationTime;

    /**客户编码**/
    @NotBlank(message = "客户编码不能为空")
    private String storeID;

    /**客户名称**/
    @NotBlank(message = "客户名称不能为空")
    private String storeName;

    /**仓库id**/
    @NotBlank(message = "仓库id不能为空")
    private String warehouseID;

    /**仓库名称**/
    @NotBlank(message = "仓库名称不能为空")
    private String warehouseName;

    /**出库单号**/
    @NotBlank(message = "出入库单号不能为空")
    private String orderNO;

    /**出库类型**/
    @NotBlank(message = "出库类型不能为空")
    private String operationType;

    /**记录数**/
    @Min(value = 1,message = "记录数必须大于0")
    private int totalCounts;

    /**出库总价值**/
    @DecimalMin(value = "0.01",message = "出库总价值必须大于0")
    private double totalValues;

    /**出库信息**/
    @Valid
    @Size(min = 1, message = "数组至少需要一个货品")
    private List<XDWG_ZJS_RECORD> products;

}
