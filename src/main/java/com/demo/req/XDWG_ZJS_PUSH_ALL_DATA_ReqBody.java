package com.demo.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 宅急送-历史数据推送请求实体
 * date：2020-12-03
 * writer：sy05893
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XDWG_ZJS_PUSH_ALL_DATA_ReqBody implements Serializable {

    private static final long serialVersionUID = -1366722770354513468L;

    /**记录笔数**/
    @Min(value = 1,message = "记录笔数不能为空")
    private int totalNum;

    /**
     text中数据项的内容下见下面描述。
     本字段域的内容为：出入库记录（UTF-8编码）压缩后的Base64编码。
     压缩算法：gzip
     **/
    @NotBlank(message = "数据内容不能为空")
    private String text;

}
