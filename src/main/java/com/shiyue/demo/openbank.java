//package com.shiyue.demo;
//
//import com.cmbc.openbank.api.client.DefaultCmbcClient;
//import com.cmbc.openbank.api.exception.CmbcApiException;
//import com.cmbc.openbank.api.io.request.CustomerMapssSend_sign_smsRequestV1_0;
//import com.cmbc.openbank.api.io.request.CustomerMspassCreat_userRequestV1_0;
//import com.cmbc.openbank.api.io.request.CustomerMspassQuery_sign_infoRequestV1_0;
//import com.cmbc.openbank.api.io.request.CustomerMspassQuery_sign_infoRequestV1_0.CustomerMspassQuery_sign_infoRequestBusiParam;
//import com.cmbc.openbank.api.response.CmbcResponse;
//
//
///**
// * 短信验证码发送
// * @date 2020-04-17
// */
//public class TestCustomerMapssSend_sign_smsV1_0 {
//
//    // 公共字段示例值
//    private static final String APP_ID = "01201911271125078724";// 商户应用对应的APPID 必输
//    private static String PUBLIC_SERVICE_URL = "https://openapi.cmbc.com.cn/gw/obp/public";// 网关服务地址 必输
//    private static final String CERTIFICATE_PATH_STR = "D:/certificate/ban/01201911271125078724/";// 证书本地目录
//    private static final String PRIVATE_KEY = CERTIFICATE_PATH_STR + "T77012019112711232100000710.sm2";// 商户私钥 必输
//    private static final String PRIVATE_KEY_PWD ="cmbc@95568";// 商户私钥密码 必输
//    private static final String CMBC_PUBLIC_KEY =CERTIFICATE_PATH_STR + "T77Bank.cer";// 对端公钥 必输
//    private static final String CUSTOMER_NO = "T77012019112711232100000710";// 商户号 必输
//    private static final String AUTH_TOKEN = null;// 子商户号 非必输
//    private static final String NOTIFY_URL = null;// 异步通知回调地址 非必输
//    private static final String CUST_ID = null;// 子商户id 非必输
//    private static final String CUST_SIGN = null;// 子商户签名 非必输
//
//    public static void main(String[] args) throws Exception {
//        testCustomerMapssSend_sign_smsV1_0();
//    }
//
//    public static void testCustomerMapssSend_sign_smsV1_0() throws CmbcApiException {
//        // 使用缺省参数构造client对象
//        DefaultCmbcClient client = new DefaultCmbcClient(APP_ID,PRIVATE_KEY,PRIVATE_KEY_PWD,CMBC_PUBLIC_KEY,CUSTOMER_NO,AUTH_TOKEN,NOTIFY_URL,CUST_ID,CUST_SIGN);
//        // 设置请求对象request
//        CustomerMapssSend_sign_smsRequestV1_0 request = new CustomerMapssSend_sign_smsRequestV1_0();
//        // 设置请求路径
//        request.setServiceUrl(PUBLIC_SERVICE_URL);
//        // 设置业务参数，每个Request请求实现类都有一个RequestBiz内部类用来设置业务参数
//        CustomerMapssSend_sign_smsRequestV1_0.CustomerMapssSend_sign_smsRequestBusiParam busiParam = new CustomerMapssSend_sign_smsRequestV1_0.CustomerMapssSend_sign_smsRequestBusiParam();
//        busiParam.setReq_seq("18516962852");
//        busiParam.setTrans_date("20201212");
//        busiParam.setTrans_time("121212");
//        busiParam.setPhone("15902225555");
//        busiParam.setType("06");
//        busiParam.setService_code("");
//        request.setBusiParam(busiParam);
//        // request组装完成，开始发起调用
//        CmbcResponse cmbcResponse = client.execute(request);
//        // 判断调用是否成功，进行后续业务处理
//        if (cmbcResponse.isSuccess()) {
//            // 业务成功
//            System.out.println("业务成功,returnMsg-->"+cmbcResponse.toString());
//            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
//            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
//        } else {
//            // 业务失败
//            System.out.println("业务失败,returnMsg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
//            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
//        }
//    }
//}
//
///**
// * 通行证签约
// * @date 2020-04-17
// */
//public class TestCustomerMspassCreat_userV1_0 {
//
//    // 公共字段示例值
//    private static final String APP_ID = "01201911271125078724";// 商户应用对应的APPID 必输
//    private static String PUBLIC_SERVICE_URL = "https://openapi.cmbc.com.cn/gw/obp/public";// 网关服务地址 必输
//    private static final String CERTIFICATE_PATH_STR = "D:/certificate/ban/01201911271125078724/";// 证书本地目录
//    private static final String PRIVATE_KEY = CERTIFICATE_PATH_STR + "T77012019112711232100000710.sm2";// 商户私钥 必输
//    private static final String PRIVATE_KEY_PWD ="cmbc@95568";// 商户私钥密码 必输
//    private static final String CMBC_PUBLIC_KEY =CERTIFICATE_PATH_STR + "T77Bank.cer";// 对端公钥 必输
//    private static final String CUSTOMER_NO = "T77012019112711232100000710";// 商户号 必输
//    private static final String AUTH_TOKEN = null;// 子商户号 非必输
//    private static final String NOTIFY_URL = null;// 异步通知回调地址 非必输
//    private static final String CUST_ID = null;// 子商户id 非必输
//    private static final String CUST_SIGN = null;// 子商户签名 非必输
//    public static void main(String[] args) throws Exception {
//        testCustomerMspassCreat_userV1_0();
//    }
//
//    public static void testCustomerMspassCreat_userV1_0() throws CmbcApiException {
//        // 使用缺省参数构造client对象
//        DefaultCmbcClient client = new DefaultCmbcClient(APP_ID,PRIVATE_KEY,PRIVATE_KEY_PWD,CMBC_PUBLIC_KEY,CUSTOMER_NO,AUTH_TOKEN,NOTIFY_URL,CUST_ID,CUST_SIGN);
//        // 设置请求对象request
//        CustomerMspassCreat_userRequestV1_0 request = new CustomerMspassCreat_userRequestV1_0();
//        // 设置请求路径
//        request.setServiceUrl(PUBLIC_SERVICE_URL);
//        // 设置业务参数，每个Request请求实现类都有一个RequestBiz内部类用来设置业务参数
//        CustomerMspassCreat_userRequestV1_0.CustomerMspassCreat_userRequestBusiParam busiParam = new CustomerMspassCreat_userRequestV1_0.CustomerMspassCreat_userRequestBusiParam();
//
//        busiParam.setReq_seq("18516962853");
//        busiParam.setTrans_date("20200112");
//        busiParam.setTrans_time("121212");
//        //实名测试数据
//        busiParam.setPhone("15902222555");
//        busiParam.setSign_type("1");
//        busiParam.setSms_code("044544");
//        busiParam.setName("开放注册三");
//        busiParam.setAccount("6226173300199296");
//        busiParam.setId_type("ZR01");
//        busiParam.setId_no("110101197405200086");
//        //若实名测试数据
//        busiParam.setPhone("15902223551");
//        busiParam.setSign_type();
//        busiParam.setSms_code("029121");
//        busiParam.setService_code("");
//        request.setBusiParam(busiParam);
//        // request组装完成，开始发起调用
//        CmbcResponse cmbcResponse = client.execute(request);
//        // 判断调用是否成功，进行后续业务处理
//        if (cmbcResponse.isSuccess()) {
//            // 业务成功
//            System.out.println("业务成功,returnMsg-->"+cmbcResponse.toString());
//            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
//            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
//        } else {
//            // 业务失败
//            System.out.println("业务失败,returnMsg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
//            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
//            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
//        }
//    }
//}
//
/////**
//// * 通行证签约信息查询
//// * @date 2020-04-17
//// */
////public class TestCustomerMspassQuery_sign_infoV1_0 {
////
////    // 公共字段示例值
////    private static final String APP_ID = "01201911271125078724";// 商户应用对应的APPID 必输
////    private static String PUBLIC_SERVICE_URL = "https://openapi.cmbc.com.cn/gw/obp/public";// 网关服务地址 必输
////    private static final String CERTIFICATE_PATH_STR = "D:/certificate/ban/01201911271125078724/";// 证书本地目录
////    private static final String PRIVATE_KEY = CERTIFICATE_PATH_STR + "T77012019112711232100000710.sm2";// 商户私钥 必输
////    private static final String PRIVATE_KEY_PWD ="cmbc@95568";// 商户私钥密码 必输
////    private static final String CMBC_PUBLIC_KEY =CERTIFICATE_PATH_STR + "T77Bank.cer";// 对端公钥 必输
////    private static final String CUSTOMER_NO = "T77012019112711232100000710";// 商户号 必输
////    private static final String AUTH_TOKEN = null;// 子商户号 非必输
////    private static final String NOTIFY_URL = null;// 异步通知回调地址 非必输
////    private static final String CUST_ID = null;// 子商户id 非必输
////    private static final String CUST_SIGN = null;// 子商户签名 非必输
////    public static void main(String[] args) throws Exception {
////        testCustomerMspassQuery_sign_infoV1_0();
////    }
////
////    public static void testCustomerMspassQuery_sign_infoV1_0() throws CmbcApiException {
////        // 使用缺省参数构造client对象
////        DefaultCmbcClient client = new DefaultCmbcClient(APP_ID,PRIVATE_KEY,PRIVATE_KEY_PWD,CMBC_PUBLIC_KEY,CUSTOMER_NO,AUTH_TOKEN,NOTIFY_URL,CUST_ID,CUST_SIGN);
////        // 设置请求对象request
////        CustomerMspassQuery_sign_infoRequestV1_0 request = new CustomerMspassQuery_sign_infoRequestV1_0();
////        // 设置请求路径
////        request.setServiceUrl(PUBLIC_SERVICE_URL);
////        // 设置业务参数，每个Request请求实现类都有一个RequestBiz内部类用来设置业务参数
////        CustomerMspassQuery_sign_infoRequestBusiParam busiParam = new CustomerMspassQuery_sign_infoRequestBusiParam();
////        busiParam.setReq_seq("18516962853");
////        busiParam.setTrans_date("20200112");
////        busiParam.setTrans_time("121212");
////        busiParam.setPhone("15551555005");
////        busiParam.setServiceCode("");
////        request.setBusiParam(busiParam);
////        // request组装完成，开始发起调用
////        CmbcResponse cmbcResponse = client.execute(request);
////        // 判断调用是否成功，进行后续业务处理
////        if (cmbcResponse.isSuccess()) {
////            // 业务成功
////            System.out.println("业务成功,returnMsg-->"+cmbcResponse.toString());
////            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
////            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
////            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
////        } else {
////            // 业务失败
////            System.out.println("业务失败,returnMsg-->"+cmbcResponse.getReturn_msg());
////            System.out.println("return_code-->"+cmbcResponse.getReturn_code());
////            System.out.println("return_msg-->"+cmbcResponse.getReturn_msg());
////            System.out.println("response_busi-->"+cmbcResponse.getResponse_busi());
////        }
