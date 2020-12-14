package com.demo.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.req.XDWG_ZJS_QUERY_WAREHOUSING_STATUS_ReqBody;
import com.demo.req.XDWG_ZJS_REQ;
import com.demo.util.AES;
import com.demo.util.Base64;
import com.demo.util.HttpClientUtil;
import com.demo.util.MD5;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 宅急送-出库记录查询
 * @date：2020-12-03
 * @author：XiaoYMax
 */
@Slf4j
public class XDWG_ZJS_QUERY_WAREHOUSING_STATUS{
    public static void main(String[] args) {

        try{
//            String url="http://127.0.0.1:8083/gateway/queryWarehousingStatus";

//            String url="http://xdgw60129.msyidai.com/gateway/queryWarehousingStatus";
            String url="http://127.0.0.1:8083/gateway/queryWarehousingStatus";
            String corpCode = "zhai_ji_song";//商户号
            String aesKey = "B8OevT5wAL94xmRQGVm4Tw==";//密钥
            String salt = "8aa16b3b-fbbd-424e-badf-788ccce0d1b1";//盐
            Map<String,String> params = new HashMap<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            UUID uuid = UUID.randomUUID();
            String msgId = uuid.toString().replace("-", "");

            // 设置请求ti
            XDWG_ZJS_QUERY_WAREHOUSING_STATUS_ReqBody xdwgZjsQueryWarehousingStatusReqBody = new XDWG_ZJS_QUERY_WAREHOUSING_STATUS_ReqBody();
            xdwgZjsQueryWarehousingStatusReqBody.setOperationTime("2020-09-01 01:01:01");
            xdwgZjsQueryWarehousingStatusReqBody.setOrderNo("RCV2011150002_6");
            xdwgZjsQueryWarehousingStatusReqBody.setStoreID("05893");
            xdwgZjsQueryWarehousingStatusReqBody.setTransType("IN");

            //封装请求体
            String xdzjs = JSON.toJSONString(xdwgZjsQueryWarehousingStatusReqBody);
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

}
