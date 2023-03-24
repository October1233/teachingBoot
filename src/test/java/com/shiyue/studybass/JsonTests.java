package com.shiyue.studybass;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTests {


    @Test
    public void getJson(){
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("projectId","73add9cf-fa1f-4f38-9366-5a88d043dc30");
        map.put("grade",6);
        list.add(map);
        Map map11 = new HashMap();
        map11.put("projectId","73add9cf-fa1f-4f38-9366-5a88d043dc30");
        map11.put("grade",7);
//        list.add(map11);
        System.out.println(JSONObject.toJSONString(list));

        List<Map> list2 = new ArrayList<>();
        Map map2 = new HashMap();
        map2.put("professionalEmphasisId","d92cbbfa-2e27-454a-b0e2-a1964b8d75d0");
        list2.add(map2);
        Map map22 = new HashMap();
        map22.put("professionalEmphasisId","f9982d55-ccb3-42ae-992b-a4b5966e0808");
        list2.add(map22);
        System.out.println(JSONObject.toJSONString(list2));

    }


    @Test
    public void tag(){
        List<Map> list2 = new ArrayList<>();
        Map map2 = new HashMap();
        map2.put("id","64fb0798-71ca-4812-8d2b-c414979f73af");
        list2.add(map2);
        System.out.println(JSONObject.toJSONString(list2));
    }



    @Test
    public void getJson2(){
        List<Map<String,Object>> roomlist = new ArrayList<>();
        Map<String,Object> roomMap = new HashMap<>();

        List<Map<String,Object>> photolist = new ArrayList<>();
        Map<String,Object> photoMap = new HashMap<>();
        Map<String,Object> photoMap2 = new HashMap<>();

        roomMap.put("dwellType","1");
        roomMap.put("dwellCost","303");
        roomMap.put("dwellCostType","1");
        roomMap.put("dwellCount","30");

//        photoMap.put("attachmentId","00007654-71dd-40e5-85f4-9a148f9dccf7");
//        photoMap.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
//        photoMap.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
//        photoMap.put("thumbnailPath","");
//        photoMap.put("thumbnailId","");
//        photoMap.put("thumbnailImg","");
//        photolist.add(photoMap);
//        photoMap2.put("attachmentId","00007b70-5c2e-4800-84a1-5c1eefc39b44");
//        photoMap2.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
//        photoMap2.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
//        photoMap2.put("thumbnailPath","");
//        photoMap2.put("thumbnailId","");
//        photoMap2.put("thumbnailImg","");
//        photolist.add(photoMap2);

        roomMap.put("repositorySupplierPhotos",photolist);
        roomlist.add(roomMap);

        System.out.println(JSONObject.toJSONString(roomlist));

    }



    @Test
    public void getJson3(){
        List<Map<String,Object>> roomlist = new ArrayList<>();
        Map<String,Object> roomMap = new HashMap<>();

        List<Map<String,Object>> photolist = new ArrayList<>();
        Map<String,Object> photoMap = new HashMap<>();
        Map<String,Object> photoMap2 = new HashMap<>();

        roomMap.put("seminarType","1");
        roomMap.put("seminarGalleryful","600");
        roomMap.put("seminarCount","6");
        roomMap.put("seminarCostType","1");
        roomMap.put("seminarCost","303");

        photoMap.put("attachmentId","00007654-71dd-40e5-85f4-9a148f9dccf7");
        photoMap.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
        photoMap.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
        photoMap.put("thumbnailPath","");
        photoMap.put("thumbnailId","");
        photoMap.put("thumbnailImg","");
        photolist.add(photoMap);
        photoMap2.put("attachmentId","00007b70-5c2e-4800-84a1-5c1eefc39b44");
        photoMap2.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
        photoMap2.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
        photoMap2.put("thumbnailPath","");
        photoMap2.put("thumbnailId","");
        photoMap2.put("thumbnailImg","");
        photolist.add(photoMap2);

        Map<String,Object> roomMap2 = new HashMap<>();

        List<Map<String,Object>> photolist2 = new ArrayList<>();
        Map<String,Object> photoMap3 = new HashMap<>();
        Map<String,Object> photoMap4 = new HashMap<>();

        roomMap2.put("seminarType","1");
        roomMap2.put("seminarGalleryful","600");
        roomMap2.put("seminarCount","6");
        roomMap2.put("seminarCostType","1");
        roomMap2.put("seminarCost","303");

        photoMap3.put("attachmentId","00007654-71dd-40e5-85f4-9a148f9dccf7");
        photoMap3.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
        photoMap3.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
        photoMap3.put("thumbnailPath","");
        photoMap3.put("thumbnailId","");
        photoMap3.put("thumbnailImg","");
        photolist2.add(photoMap3);
        photoMap4.put("attachmentId","00007b70-5c2e-4800-84a1-5c1eefc39b44");
        photoMap4.put("path","default/M00/05/59/Ci7mTV_xGaiAMeGRAADclyvxd8s614.jpg");
        photoMap4.put("name","certificate_bf402470-8b65-430a-9699-c778c7e76d81_1607599029885.jpg");
        photoMap4.put("thumbnailPath","");
        photoMap4.put("thumbnailId","");
        photoMap4.put("thumbnailImg","");
        photolist2.add(photoMap4);

        roomMap.put("images",photolist);
        roomMap2.put("images",photolist2);
        roomlist.add(roomMap);
        roomlist.add(roomMap2);




        System.out.println(JSONObject.toJSONString(roomlist));

    }


    @Test
    public void getJson4(){
        List<Map> list2 = new ArrayList<>();
        Map map2 = new HashMap();
        map2.put("id","2f80a1d7-65ad-4471-83c2-b5a97b17de2c");
        list2.add(map2);
        Map map22 = new HashMap();
        map22.put("id","4fac9cea-3cca-407e-89e5-ce08344760c5");
        list2.add(map22);
        System.out.println(JSONObject.toJSONString(list2));

    }


    @Test
    public void getJson5(){
        List<Map> list2 = new ArrayList<>();
        Map map2 = new HashMap();
        map2.put("attachmentId","2f80a1d7-65ad-4471-83c2-b5a97b17de2c");
        map2.put("name","小文档");
        map2.put("type","文档");
        map2.put("attachType","1");
        list2.add(map2);
        Map map22 = new HashMap();
        map22.put("attachmentId","2f80a1d7-65ad-4471-83c2-b5a97b17de2c");
        map22.put("name","大图");
        map22.put("type","图片");
        map22.put("attachType","2");
        list2.add(map22);
        System.out.println(JSONObject.toJSONString(list2));

    }


}
