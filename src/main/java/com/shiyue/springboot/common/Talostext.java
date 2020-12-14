//
//import com.alibaba.fastjson.JSON;
//import com.qudian.pay.talos.sdk.TalosSDKClient;
//import com.qudian.pay.talos.sdk.bo.CommonResp;
//import com.qudian.pay.talos.sdk.bo.FileInfoResp;
//import net.sf.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
///**
// * Talos测试类
// * @author yejingchun
// * @since 2019-05-15 17:37
// */
//public class TalosTest {
//
//    static TalosSDKClient talosSDKClient = new TalosSDKClient();
//    private static final String date = "20190523";
//    private static final String fileType = "20";
//
//    public static void main(String[] args) {
//        try {
//            if(!talosSDKClient.isInit()) {
//                talosSDKClient.init("classpath:talos/app.properties", "classpath:talos/talosPriKey");
//            }
//            //System.out.println("上传文件");
//            CommonResp<String> putFileResp = putFile();
//           // System.out.println("putFileResp: "+JSONObject.toJSONString(putFileResp));
//
//           // System.out.println("查询文件：");
//            CommonResp<List<FileInfoResp>> testlistPageKeyPath = testlistPageKeyPath();
//            JSONObject page = testlistPageKeyPath.getExtraData("page");
//            Integer totalPage = page.getInteger("totalPage");
//           // System.out.println("总页数："+totalPage);
//            for(FileInfoResp fileInfoResp : testlistPageKeyPath.getData()) {
//                //System.out.println("获取文件");
//                byte[] fileBytes = testgetFile(fileInfoResp.getKeyPath());
//               // System.out.println("文件内容："+new String(fileBytes, "gbk"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //分页获取信息列表
//    public static CommonResp<List<FileInfoResp>> testlistPageKeyPath() {
//        int page = 1;
//        int pageSize =10;
//        CommonResp<List<FileInfoResp>> listPageKeyPath = talosSDKClient.listPageFileInfo(date, fileType, page, pageSize );
//        //System.out.println(JSON.toJSONString(listPageKeyPath));
//        return listPageKeyPath;
//    }
//    //下载文件   key地址
//    public static byte[] testgetFile(String key) throws Exception {
//        return talosSDKClient.getFile(key);
//    }
//
//    //上传文件
//    public static CommonResp<String> putFile() throws IOException {
//        String fileName = "放款明细.txt";
//        byte[] file;
//        try (FileInputStream inputStream =
//                     new FileInputStream(
//                             new File("/Users/qudian/Downloads/放款明细.txt"));
//             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            byte[] buf = new byte[4096];
//            int n;
//            while((n = inputStream.read(buf))!=-1){
//                outputStream.write(buf, 0, n);
//            }
//            file = outputStream.toByteArray();
//        }
//        return talosSDKClient.putFile(date, fileType, fileName, file);
//    }
//}