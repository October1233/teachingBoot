package com.demo.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.req.*;
import com.demo.util.AES;
import com.demo.util.Base64;
import com.demo.util.HttpClientUtil;
import com.demo.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPOutputStream;

/**
 * 宅急送-历史数据推送
 * @date：2020-12-03
 * @author：XiaoYMax
 */

@Slf4j
public class XDWG_ZJS_PUSH_ALL_DATA {


    /**
     * 一个可供参考的demo
     * @param args
     */
    public static void main(String[] args) {

        try{
            String text = "交易类型|出入库时间|货主ID|仓库ID|仓库名称|出入库类型|出入库单号|出入库产品SKU|出入库产品SKU描述|出入库产品种类|出入库产品数量|产品价格\n" +
                        "IN|2020-09-01 01:01:01|05893|10001|郑州格力1号仓-宅急送|采购入库|RCV20111500026|640267*3402020714|云米互联网变频空调Milano（柔风版）-1匹室内机||12|31741.23\n" +
                    "OUT|2020-09-01 01:01:01|05893|10001|郑州格力1号仓-宅急送|采购入库|RCV20111500026|640267*3402020714|云米互联网变频空调Milano（柔风版）-1匹室内机||12|31741.23\n" +
                    "IN|2020-09-01 01:01:01|05893|10001|郑州格力1号仓-宅急送|采购入库|RCV20111500026|640267*3402020714|云米互联网变频空调Milano（柔风版）-1匹室内机||12|31741.23";
//            String url="http://xdgw60129.msyidai.com/gateway/pushAllData";
            //请使用UTF-8格式
            String url="http://127.0.0.1:8083/gateway/pushAllData";
            String corpCode = "zhai_ji_song";//商户号
            String aesKey = "B8OevT5wAL94xmRQGVm4Tw==";//密钥
            String salt = "8aa16b3b-fbbd-424e-badf-788ccce0d1b1";//盐
            Map params = new HashMap();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            UUID uuid = UUID.randomUUID();
            String msgId = uuid.toString().replace("-", "");

            // 设置请求ti
            XDWG_ZJS_PUSH_ALL_DATA_ReqBody xdwgZjsPushAllDataReqBody = new XDWG_ZJS_PUSH_ALL_DATA_ReqBody();
            xdwgZjsPushAllDataReqBody.setTotalNum(3);
            xdwgZjsPushAllDataReqBody.setText(gzip(text));

            //封装请求体
            String xdzjs = JSON.toJSONString(xdwgZjsPushAllDataReqBody);
            String mac= MD5.encodeByMd5WithSalt(xdzjs,salt);
            String data= AES.encrypt(xdzjs, AES.generateKey(Base64.decode(aesKey)));
            params.put("corpCode",corpCode);
            params.put("tradeTime",simpleDateFormat.format(new Date()));
            params.put("msgId",msgId);
            params.put("mac",mac);
            params.put("data",data);
            log.info("请求:【{}】",params);
            String result = HttpClientUtil.doPost(url,params);
            log.info("响应:【{}】",result);
            JSONObject obj = JSONObject.parseObject(result);
            String aesdata = obj.getString("data");
            String orgData = AES.decrypt(aesdata,AES.generateKey(Base64.decode(aesKey)));
            log.info("解密:【{}】",orgData);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 一个可供参考的压缩gzip方法
     *
     * @param primStr
     * @return
     * @throws Exception
     */

    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return out.toString();
    }

    @Test
    public void bytes(){
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy");
        LocalDate localDate = LocalDate.now();
//        resultList.subList(pageParam.getPageSize() * (pageParam.getPageNum() - 1), ((pageParam.getPageSize() * pageParam.getPageNum()) > Integer.valueOf(resultList.size()) ? Integer.valueOf(resultList.size()) : (pageParam.getPageSize() * pageParam.getPageNum())));
        System.out.println(localDate.format(dtf3));
        Byte aByte = new Byte("1");
        System.out.println(aByte.equals(new Byte("1")));
    }
}
