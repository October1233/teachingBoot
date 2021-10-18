//package com.shiyue.thread;
//
//import com.cmbc.openbank.api.io.response.CustomerMspassCreat_userResponseV1_0;
//import com.cmbc.openbank.api.request.AbstractCmbcRequest;
//import com.cmbc.openbank.api.request.BusiParam;
//
//import java.util.Map;
//
//    //
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//    public class testc extends AbstractCmbcRequest<CustomerMspassCreat_userResponseV1_0> {
//        public testc() {
//        }
//
//        public Class<CustomerMspassCreat_userResponseV1_0> getResponseClass() {
//            return CustomerMspassCreat_userResponseV1_0.class;
//        }
//
//        public boolean isNeedEncrypt() {
//            return true;
//        }
//
//        public Class<? extends BusiParam> getBusiParamClass() {
//            return testc.CustomerMspassCreat_userRequestBusiParam.class;
//        }
//
//        public Map<String, String> getExtraParameters() {
//            return null;
//        }
//
//        public String getMethod() {
//            return "customer.mapss.LgnISVCreateUserAccept";
//        }
//
//        public String getVersion() {
//            return "V1.0";
//        }
//
//        public String getRequestType() {
//            return "POST";
//        }
//
//        public static class CustomerMspassCreat_userRequestBusiParam implements BusiParam {
//            private String req_seq;
//            private String trans_date;
//            private String trans_time;
//            private String phone;
//            private String sign_type = "0";
//            private String sms_code;
//            private String service_code;
//            private String type = "0";
//            private String merchant_no;
//            private String comp_id;
//            private String app_id;
//
//            public CustomerMspassCreat_userRequestBusiParam() {
//            }
//
//            public String getReq_seq() {
//                return this.req_seq;
//            }
//
//            public void setReq_seq(String req_seq) {
//                this.req_seq = req_seq;
//            }
//
//            public String getTrans_date() {
//                return this.trans_date;
//            }
//
//            public void setTrans_date(String trans_date) {
//                this.trans_date = trans_date;
//            }
//
//            public String getTrans_time() {
//                return this.trans_time;
//            }
//
//            public void setTrans_time(String trans_time) {
//                this.trans_time = trans_time;
//            }
//
//            public String getPhone() {
//                return this.phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//
//            public String getSign_type() {
//                return this.sign_type;
//            }
//
//            public void setSign_type() {
//                this.sign_type = "0";
//            }
//
//            public String getSms_code() {
//                return this.sms_code;
//            }
//
//            public void setSms_code(String sms_code) {
//                this.sms_code = sms_code;
//            }
//
//            public String getService_code() {
//                return this.service_code;
//            }
//
//            public void setService_code(String service_code) {
//                this.service_code = service_code;
//            }
//
//            public String getType() {
//                return this.type;
//            }
//
//            public String getMerchant_no() {
//                return this.merchant_no;
//            }
//
//            public void setMerchant_no(String merchant_no) {
//                this.merchant_no = merchant_no;
//            }
//
//            public String getComp_id() {
//                return this.comp_id;
//            }
//
//            public void setComp_id(String comp_id) {
//                this.comp_id = comp_id;
//            }
//
//            public String getApp_id() {
//                return this.app_id;
//            }
//
//            public void setApp_id(String app_id) {
//                this.app_id = app_id;
//            }
//
//            testc param = new testc();
//
//
//
//
//        }
//    }
//
