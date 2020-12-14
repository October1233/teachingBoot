//package com.demoPack.web;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.cmbc.openbank.api.utils.HttpClientUtil;
//import com.demoPack.util.*;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Demo {
//
//    public static void main(String[] args) {
//
//        {
//
//            try{
//                //String url="http://172.30.60.129:18060/gateway/accept";
//                String url="http://58.250.57.100:18090/gateway/accept";
//                String corpCode = "coopr_jinshan";//商户号
//                String aesKey = "B8OevT5wAL94xmRQGVm4Tw==";//密钥
//                String salt = "8aa16b3b-fbbd-424e-badf-788ccce0d1b1";//盐
//                JSONObject jsonObject = new JSONObject();
//                Map params = new HashMap();
//
//
//                // 设置请求头
//                ReqHead head = new ReqHead();
//                head.setBusiness_code(XdGateWayBusinessType.QUERY_CONTRACT_INFO.getCode());
//                head.setCorp_code(corpCode);
//                head.setTimestamp(1583977243816L);//调试时这个字段可以写成固定时间
//
//                // 设置请求的业务实体内容
//                XDGW_QUERY_CONTRACT_INFOReqBody reqBody = new XDGW_QUERY_CONTRACT_INFOReqBody();
//                reqBody.setCoContractCode("YKD2020032611192368946");
//                reqBody.setContractCode("YKD2020032611192368946");
//                reqBody.setOpenId("coopr_jinshan338758814168256512");
//
//                // 组装报文内容
//                jsonObject.put("head",JSON.toJSONString(head));
//                jsonObject.put("body",JSON.toJSONString(reqBody));
//
//                String originData = jsonObject.toJSONString();
//                String mac= MD5.encodeByMd5WithSalt(originData,salt);
//                String data= AES.encrypt(originData, AES.generateKey(Base64.decode(aesKey)));
//
//
//                params.put("corpCode",corpCode);
//                params.put("mac",mac);
//                params.put("data",data);
//
//                String result = HttpClientUtil.doPost(url,params);
//                JSONObject obj = JSONObject.parseObject(result);
//                String aesdata = obj.getString("data");
//                String orgData = AES.decrypt(aesdata,AES.generateKey(Base64.decode(aesKey)));
//                LOG.info("result:{}",orgData);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//    }
//}
