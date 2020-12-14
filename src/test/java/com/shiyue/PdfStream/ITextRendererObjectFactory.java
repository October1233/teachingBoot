//package com.shiyue.PdfStream;
//
//import com.itextpdf.text.pdf.BaseFont;
//import com.lowagie.text.DocumentException;
//import org.apache.commons.pool.BasePoolableObjectFactory;
//import org.apache.commons.pool.impl.GenericObjectPool;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.io.IOException;
//import java.util.ResourceBundle;
//
///**
// * ITextRenderer对象工厂,需要加载中文字体集(大小10M),故增加对象池
// */
//public class ITextRendererObjectFactory extends BasePoolableObjectFactory {
//    private static GenericObjectPool itextRendererObjectPool = null;
//
//    @Override
//    public Object makeObject() throws Exception {
//        ITextRenderer renderer = createTextRenderer();
//        return renderer;
//    }
//
//    /**
//     * 获取对象池,使用对象工厂 后提供性能支持 500线程 迭代10
//     * @Title: getObjectPool
//     * @Description: 获取对象池
//     * @return GenericObjectPool
//     */
//    public static GenericObjectPool getObjectPool() {
//        synchronized (ITextRendererObjectFactory.class) {
//            if (itextRendererObjectPool == null) {
//                itextRendererObjectPool = new GenericObjectPool(new ITextRendererObjectFactory());
//                GenericObjectPool.Config config = new GenericObjectPool.Config();
//                config.lifo = false;
//                config.maxActive = 15;
//                config.maxIdle = 5;
//                config.minIdle = 1;
//                config.maxWait = 5 * 100;
//                itextRendererObjectPool.setConfig(config);
//            }
//        }
//
//        return itextRendererObjectPool;
//    }
//
//    /**
//     * 初始化
//     *
//     * @Title: initTextRenderer
//     * @Description:
//     * @return
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static synchronized ITextRenderer createTextRenderer() throws DocumentException, IOException {
//        ITextRenderer renderer = new ITextRenderer();
//        ITextFontResolver fontResolver = renderer.getFontResolver();
//        addFonts(fontResolver);
//        return renderer;
//    }
//
//    /**
//     * 添加字体
//     *
//     * @Title: addFonts
//     * @Description:
//     * @param fontResolver
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static ITextFontResolver addFonts(ITextFontResolver fontResolver) throws DocumentException, IOException {
//        ResourceBundle resource = ResourceBundle.getBundle("template");
//        fontResolver.addFont(resource.getString("font_path"), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        return fontResolver;
//    }
//}
