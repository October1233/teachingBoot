package com.shiyue.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.shiyue.springboot.domain.ScreenConditions;
import com.shiyue.springboot.domain.ScreenRule;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Controller
@RequestMapping("filtrateRule")
public class FiltrateRuleController {

    private static Logger logger = LoggerFactory.getLogger(FiltrateRuleController.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @ResponseBody
    @RequestMapping("rule")
    public void returnRuleList(String parentCode,String type,HttpServletResponse response){
        List<Map> str = new ArrayList<>();
        Map<String,Object> map = new HashMap();
        map.put("code","注册天数");
        map.put("name","注册天数");
        str.add(map);
        Map<String,Object> map2 = new HashMap();
        map2.put("code","注册渠道");
        map2.put("name","注册渠道");
        str.add(map2);
        Map<String,Object> map3 = new HashMap();
        map3.put("code","平台首次授信");
        map3.put("name","平台首次授信");
        str.add(map3);
        Map<String,Object> map4 = new HashMap();
        map4.put("code","授信日期距离注册日期");
        map4.put("name","授信日期距离注册日期");
        str.add(map4);
        Map<String,Object> map5 = new HashMap();
        map5.put("code","授信渠道是否属于注册渠道");
        map5.put("name","授信渠道是否属于注册渠道");
        str.add(map5);
        Map<String,Object> map6 = new HashMap();
        map6.put("code","授信渠道");
        map6.put("name","授信渠道");
        str.add(map6);
        Map<String,Object> map7 = new HashMap();
        map7.put("code","借款日期距离注册日期");
        map7.put("name","借款日期距离注册日期");
        str.add(map7);
        Map<String,Object> map8 = new HashMap();
        map8.put("code","借款渠道是否属于注册渠道");
        map8.put("name","借款渠道是否属于注册渠道");
        str.add(map8);
        Map<String,Object> map9 = new HashMap();
        map9.put("code","借款渠道");
        map9.put("name","借款渠道");
        str.add(map9);
        String fromObject = "";
        logger.info(fromObject);
        renderString(response, fromObject, "text/json");
    }


    @RequestMapping("ruleDetail")
    public void returnRuleDetailList(String parentCode,String type,HttpServletResponse response){
        List<Map<String,Object>> str = new ArrayList<>();
        Supplier<Map<String,Object>> mapSupplier = HashMap::new;
        Map<String,Object> map = mapSupplier.get();
        map.put("code","=");
        map.put("name","=");
        Map<String,Object> map2 = mapSupplier.get();
        map2.put("code",">=");
        map2.put("name",">=");
        Map<String,Object> map3 = mapSupplier.get();
        map3.put("name",">");
        map3.put("code",">");
        Map<String,Object> map4 = mapSupplier.get();
        map4.put("name","<=");
        map4.put("code","<=");
        Map<String,Object> map5 = mapSupplier.get();
        map5.put("name","<");
        map5.put("code","<");
        Map<String,Object> map6 = mapSupplier.get();
        map6.put("name","!=");
        map6.put("code","!=");
        if ("平台首次授信".equals(parentCode) ||"授信渠道是否属于注册渠道".equals(parentCode) || "借款渠道是否属于注册渠道".equals(parentCode)){
            str.add(map);
        }else if ("注册渠道".equals(parentCode) || "授信渠道".equals(parentCode) || "借款渠道".equals(parentCode)){
            str.add(map);
            str.add(map6);
        }else if ("注册天数".equals(parentCode) ||"授信日期距离注册日期".equals(parentCode) || "借款日期距离注册日期".equals(parentCode)){
            str.add(map);
            str.add(map2);
            str.add(map3);
            str.add(map4);
            str.add(map5);
        }else {
            str.add(null);
        }
        String fromObject = "";
        logger.info(fromObject);
        renderString(response, fromObject, "text/json");
    }

    @RequestMapping("saveRuleList")
    public void saveRuleList(HttpServletResponse response, HttpServletRequest request,
                             String tmescCarInfoListJson) throws UnsupportedEncodingException {
    }

    @RequestMapping("saveRuleInfo")
    public void saveRuleInfo(HttpServletResponse response, HttpServletRequest request, String tmescCarInfoListJson) throws UnsupportedEncodingException {
        String tmescCarInfo = URLDecoder.decode(tmescCarInfoListJson, "UTF-8");
        JSONObject jsonObj = new JSONObject();
        JSONArray array = new JSONArray();
        Supplier<ScreenConditions> conditionsSupplier = ScreenConditions::new;
        String timeNew =  String.valueOf(System.currentTimeMillis());
        String ruleName = request.getParameter("ruleName");
        String ruleCode = request.getParameter("ruleCode");
        String[] loanProduct = request.getParameterValues("loanProduct");
        String dataType = request.getParameter("dataType");
        String start = request.getParameter("startTime");
        String end = request.getParameter("endTime");
        String reMark = request.getParameter("reMark");
        String ruleState = request.getParameter("ruleState");
        if (1 == 0) {
            jsonObj.put("ec", "M00001");
            jsonObj.put("em", "规则数不能为空");
            renderString(response, jsonObj.toString(), "text/json");
            return;
        }
        if (ruleName==null||"".equals(ruleName)||ruleCode==null||"".equals(ruleCode)||loanProduct==null||"".equals(loanProduct)||
                start==null||"".equals(start)||end==null||"".equals(end)) {
            JSONObject jsonObj1 = new JSONObject();
            jsonObj1.put("ec", "M00001");
            jsonObj1.put("em", "必须输入关键参数");
            renderString(response, jsonObj1.toString(), "text/json");
            return;
        }
        List<HashMap<String,Object>> orderTmescExtList = new ArrayList<>();
        logger.info("返回的信息list====="+orderTmescExtList);
        for (HashMap map:orderTmescExtList){
            ScreenConditions screenConditions =conditionsSupplier.get();
            screenConditions.setRuleName(ruleName);
            screenConditions.setRuleCode(ruleCode);
            screenConditions.setConditionsCode(String.valueOf(map.get("symbol")));
            screenConditions.setConditionsName(String.valueOf(map.get("ruleDetailName")));
            screenConditions.setRefValue(String.valueOf(map.get("ruleValue")));
        }
        ScreenRule screenRule = new ScreenRule();
        screenRule.setDataType(Integer.parseInt(dataType));
        try {
            screenRule.setStartTime(dateFormat.parse(start));
            screenRule.setStartTime(dateFormat.parse(end));
        }catch (Exception e){
            logger.error("时间格式化出现错误");
        }
        screenRule.setRuleName(ruleName);
        screenRule.setRuleCode(ruleCode);
        screenRule.setRemark(reMark);
        screenRule.setStatus(ruleState);
        String product = StringUtils.join(loanProduct, ",");
//        screenRule.setBusinessType(product);
        logger.info("---"+product);

    }

    @RequestMapping("formInfo")
    public void formInfo( HttpServletRequest request, String tmescCarInfoListJson,
                          HttpServletResponse response) throws UnsupportedEncodingException {

    }




    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException var5) {
            return null;
        }
    }

}
