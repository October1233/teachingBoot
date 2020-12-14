//package com.shiyue.PdfStream;
//
//import com.itextpdf.text.pdf.BaseFont;
//import com.jeeplus.common.Encodes;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import org.w3c.dom.Document;
//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.BufferedWriter;
//import java.io.ByteArrayInputStream;
//import java.io.OutputStream;
//import java.io.StringWriter;
//import java.util.Locale;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//public class HtmlGenerator {
//
//
//
//    /**
//     *
//     * 描述：freemarker生成html字符串
//     *
//     * @param template
//     * @param variables
//     * @return
//     * @throws Exception
//     * @see
//     */
//    public static String generate(String template, Map<String, Object> variables) throws Exception {
//        Configuration config = FreeMarkers.buildConfiguration("classpath:/templates");
//        Template tp = config.getTemplate(template, new Locale("Zh_cn"), "UTF-8");
//        StringWriter stringWriter = new StringWriter();
//        BufferedWriter writer = new BufferedWriter(stringWriter);
//        tp.process(variables, writer);
//        String htmlStr = stringWriter.toString();
//        writer.flush();
//        writer.close();
//        return htmlStr;
//    }
//
//    public static void generate(String htmlStr, OutputStream out) throws Exception {
//        ITextRenderer renderer = null;
//        try {
//            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = builder.parse(new ByteArrayInputStream(htmlStr.getBytes("UTF-8")));
//            renderer = (ITextRenderer) ITextRendererObjectFactory.getObjectPool().borrowObject();
//            ITextFontResolver fontResolver = renderer.getFontResolver();
//            ResourceBundle resource = ResourceBundle.getBundle("template");
//            fontResolver.addFont(resource.getString("font_path"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            renderer.setDocument(doc, null);
//            renderer.layout();
//            renderer.createPDF(out);
//        } catch (Exception e) {
//            System.out.println("生成pdf发生异常:");
//            e.printStackTrace();
//            ITextRendererObjectFactory.getObjectPool().invalidateObject(renderer);
//            renderer = null;
//            throw e;
//        } finally {
//            if (out != null)
//                out.close();
//
//            if (renderer != null) {
//                try {
//                    ITextRendererObjectFactory.getObjectPool().returnObject(renderer);
//                } catch (Exception ex) {
//                    System.out.println("itextRender对象池发生异常:");
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//    public static void generate(String htmlStr, HttpServletResponse response) throws Exception {
//        ITextRenderer renderer = null;
//        ServletOutputStream out = response.getOutputStream();
//        try {
//            String fileName = "证书下载" + DateUtils.getDate("yyyyMMddHHmmss") + ".pdf";
//            response.reset();
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
//            response.setHeader("FileName", Encodes.urlEncode(fileName));
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Expose-Headers", "FileName");
//
//            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = builder.parse(new ByteArrayInputStream(htmlStr.getBytes("UTF-8")));
//            renderer = (ITextRenderer) ITextRendererObjectFactory.getObjectPool().borrowObject();
//            ITextFontResolver fontResolver = renderer.getFontResolver();
//            ResourceBundle resource = ResourceBundle.getBundle("template");
//            fontResolver.addFont(resource.getString("font_path"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            renderer.setDocument(doc, null);
//            renderer.layout();
//            renderer.createPDF(out);
//        } catch (Exception e) {
//            System.err.println("生成pdf发生异常:");
//            e.printStackTrace();
//            ITextRendererObjectFactory.getObjectPool().invalidateObject(renderer);
//            renderer = null;
//            throw e;
//        } finally {
//            if (out != null)
//                out.close();
//
//            if (renderer != null) {
//                try {
//                    ITextRendererObjectFactory.getObjectPool().returnObject(renderer);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//
//    }
//}
