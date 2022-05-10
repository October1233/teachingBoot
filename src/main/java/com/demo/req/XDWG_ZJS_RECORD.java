package com.demo.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_RECORD implements Serializable {

    private static final long serialVersionUID = -1366722770354513468L;

    /**出库产品SKU**/
    @NotNull(message = "出库单号不能为空")
    private String productSKU;

    /**出库产品SKU描述(型号)**/
    private String productSKUDes;

    /**出库产品种类**/
    @NotNull(message = "出库产品种类不能为空")
    private String productType;

    /**出库产品数量**/
    @Min(value = 1,message = "出库产品数量必须大于0")
    private int productCount;

    /**产品价格**/
    @DecimalMin(value = "0.01",message = "产品价格必须大于0")
    private double productPrice;


}
