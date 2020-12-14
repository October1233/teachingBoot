package com.demo.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.req.XDWG_ZJS_BOOK_DELIVERY_ReqBody;
import com.demo.req.XDWG_ZJS_RECORD;

import com.demo.util.*;
import com.demo.util.Base64;
import lombok.extern.slf4j.Slf4j;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;

/**
 * 宅急送-预约出库
 * @date：2020-12-03
 * @author：XiaoYMax
 */
@Slf4j
public class XDWG_ZJS_BOOK_DELIVERY  {

    public static void main(String[] args) {

        try{

            String url="http://127.0.0.1:8083/gateway/bookDelivery";
//            String url="http://xdgw60129.msyidai.com/gateway/bookDelivery";
            String corpCode = "zhai_ji_song";//商户号
            String aesKey = "B8OevT5wAL94xmRQGVm4Tw==";//密钥
            String salt = "8aa16b3b-fbbd-424e-badf-788ccce0d1b1";//盐
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            UUID uuid = UUID.randomUUID();
            String msgId = uuid.toString().replace("-", "");
            Map<String,String> params = new HashMap<>();
            Supplier<XDWG_ZJS_RECORD> xdwgZjsRecordSupplier = XDWG_ZJS_RECORD::new;


            List<XDWG_ZJS_RECORD> xdwgZjsBookDeliveryReqBodyList = new ArrayList<>();
            //模拟列
            XDWG_ZJS_RECORD xdwgZjsBookDeliveryReqBody1 = xdwgZjsRecordSupplier.get();
            xdwgZjsBookDeliveryReqBody1.setProductSKU("aka123");
            xdwgZjsBookDeliveryReqBody1.setProductSKUDes("feidianwang1");
            xdwgZjsBookDeliveryReqBody1.setProductType("空调");
            xdwgZjsBookDeliveryReqBody1.setProductCount(10);
            xdwgZjsBookDeliveryReqBody1.setProductPrice(3333.00);
            XDWG_ZJS_RECORD xdwgZjsBookDeliveryReqBody2 = xdwgZjsRecordSupplier.get();
            xdwgZjsBookDeliveryReqBody2.setProductPrice(3333.00);
            xdwgZjsBookDeliveryReqBody2.setProductSKU("aka123");
            xdwgZjsBookDeliveryReqBody2.setProductSKUDes("feidianwang1");
            xdwgZjsBookDeliveryReqBody2.setProductType("空调");
            xdwgZjsBookDeliveryReqBody2.setProductCount(10);

            xdwgZjsBookDeliveryReqBodyList.add(xdwgZjsBookDeliveryReqBody1);
            xdwgZjsBookDeliveryReqBodyList.add(xdwgZjsBookDeliveryReqBody2);

            //封装请求体
            XDWG_ZJS_BOOK_DELIVERY_ReqBody xdwg_zjs_book_delivery = new XDWG_ZJS_BOOK_DELIVERY_ReqBody();
            xdwg_zjs_book_delivery.setOperationTime(simpleDateFormat.format(new Date()));
            xdwg_zjs_book_delivery.setStoreID("05893");
            xdwg_zjs_book_delivery.setStoreName("石越");
            xdwg_zjs_book_delivery.setWarehouseID("1597979648");
            xdwg_zjs_book_delivery.setWarehouseName("宅急送哈尔滨群力仓");
            xdwg_zjs_book_delivery.setOperationType("1");
            xdwg_zjs_book_delivery.setTotalCounts(10);
            xdwg_zjs_book_delivery.setTotalValues(1.00);
            xdwg_zjs_book_delivery.setOrderNO("8aa16b3bfbbd424ebadf788ccce0d1b1");
            xdwg_zjs_book_delivery.setProducts(xdwgZjsBookDeliveryReqBodyList);

            String xdzjs = JSON.toJSONString(xdwg_zjs_book_delivery);
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
