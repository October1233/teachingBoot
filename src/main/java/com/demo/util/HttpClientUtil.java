package com.demo.util;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.util.*;
import java.util.zip.GZIPInputStream;


public class HttpClientUtil {
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    private static final int DEFAULT_REQUEST_TIMEOUT = 30 * 1000;

    // default charset is UTF-8
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final HttpClient client;

    static {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        BasicHttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, false);

        HttpConnectionParams.setConnectionTimeout(params, DEFAULT_REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, DEFAULT_REQUEST_TIMEOUT);

        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
        client = new DefaultHttpClient(cm, params);
    }

    private static String getResponseBodyAsString(final HttpResponse response, final String defaultCharset) {
        InputStream instream = null;
        InputStreamReader inReader = null;
        String ret = "";
        try {
            instream = getResponseBodyAsStream(response);
            final HttpEntity entity = response.getEntity();
            if (entity.getContentLength() > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            }
            int i = (int) entity.getContentLength();
            if (i < 0) {
                i = 512;
            }
            inReader = new InputStreamReader(instream, Charset.forName(defaultCharset));
            CharArrayBuffer buffer = new CharArrayBuffer(i);
            char[] tmp = new char[512];
            int l;
            while ((l = inReader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            ret = buffer.toString();
        } catch (Exception e) {
            logger.error("getResponseBodyAsString is error", e);
        } finally {
            if (inReader != null) {
                try {
                    inReader.close();
                } catch (Exception e) {
                }
            }
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {

                }
            }
        }
        return ret;
    }

    private static InputStream getResponseBodyAsStream(final HttpResponse response) throws IOException {
        final HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        Header gzipHeader = response.getFirstHeader("Content-Encoding");
        if (gzipHeader != null && gzipHeader.getValue().equalsIgnoreCase("gzip")) {
            instream = new GZIPInputStream(instream);
        }
        return instream;
    }

    public static String doPost(String reqUrl, Map<String, String> params) {
        logger.info("\nrequest url:\n" + reqUrl + "\nrequest params:\n" + JSONObject.toJSONString(params));
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (params != null) {
            Set<String> keys = params.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = i.next();
                String value = params.get(key);
                if (key == null || key.trim().length() == 0 || value == null || value.trim().length() == 0) {
                    continue;
                }
                pairs.add(new BasicNameValuePair(key, value));
            }
        }
        HttpPost httppost = null;
        try {
            httppost = new HttpPost(reqUrl);
            UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httppost.setEntity(p_entity);
            HttpResponse response = executeHttpMethod(httppost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("request is error by url:" + reqUrl);
                httppost.abort();// 释放连接
                return null;
            }
            String responseStr = getResponseBodyAsString(response, DEFAULT_CHARSET);
            logger.info("\nresponse data:\n" + responseStr);
            return responseStr;
        } catch (Exception e) {
            logger.error("request is error by url:" + reqUrl, e);
            if (httppost != null) {
                try {
                    httppost.abort();
                } catch (Exception ee) {
                }
            }
        }
        return null;
    }

    public static HttpResponse executeHttpMethod(HttpRequestBase method) {
        method.setHeader("Connection", "Keep-Alive");
        method.setHeader("Accept-Encoding", "gzip, deflate");
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, DEFAULT_REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, DEFAULT_REQUEST_TIMEOUT);
        method.setParams(httpParameters);
        try {
            HttpResponse response = client.execute(method);
            return response;
        } catch (Exception e) {
            logger.error("executeHttpMethod is error", e);
        }
        return null;
    }
}
