package com.corp.sso.utils;


import com.corp.oa.util.G;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基于 HttpClient 的请求
 * @author wqj
 */
public class HttpUtil {

    private static HttpClient client = null;
    private static Integer DEFAULT_CONN_TIMEOUT = 30000;
    private static Integer DEFAULT_READ_TIMEOUT = 30000;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String get(String url, Map<String, Object> params) throws Exception {

        if (params == null || params.isEmpty()) {
            return get(url);
        }
        //将参数进行拼接
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry: params.entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String urlStr = builder.toString();
        if (!G.isNvl(urlStr)) {
            String urlencoded = urlStr.substring(0, urlStr.length() - 1);
            url = url + "?" + urlencoded;
        }

        return get(url);
    }

    /**
     * 发送一个 GET 请求
     * @param url 地址
     * @return
     * @throws Exception
     */
    public static String get(String url) throws Exception {
        return get(url, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }
    /**
     * 发送一个 GET 请求
     * @param url 地址
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return
     * @throws Exception
     */
    public static String get(String url, Integer connTimeout, Integer readTimeout) throws Exception {
        HttpGet get = new HttpGet(url);
        return invokeNet(url, get, connTimeout, readTimeout);
    }

    /**
     * 发送一个 POST 请求
     * @param url 地址
     * @param body 请求体 json stringify
     * @return
     * @throws Exception
     */
    public static String post(String url, String body) throws Exception {
        return post(url, body, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    /**
     * 发送一个 POST 请求
     * @param url 地址
     * @param body 请求体 json stringify
     * @param headers 请求头
     * @return
     * @throws Exception
     */
    public static String post(String url, String body, Map<String, String> headers) throws Exception {
        HttpPost post = new HttpPost(url);
        if (!G.isNvl(body)) {
            // 请求体编码
            HttpEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
        }
        if (headers != null && !headers.isEmpty()) {
            // 添加请求头
            for (Map.Entry<String, String> entry: headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return invokeNet(url, post, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    /**
     * 发送一个 POST 请求
     * @param url 地址
     * @param body 请求体 json stringify
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return
     * @throws Exception
     */
    public static String post(String url, String body, Integer connTimeout, Integer readTimeout) throws Exception {
        HttpPost post = new HttpPost(url);
        if (!G.isNvl(body)) {
            HttpEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
        }
        return invokeNet(url, post, connTimeout, readTimeout);
    }

    /**
     * 提交form表单
     * @param url 地址
     * @param params 参数
     * @param headers 请求头
     * @return
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params,
                                  Map<String, String> headers) throws Exception {
        return postForm(url, params, headers, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }
    /**
     * 提交form表单
     * @param url 地址
     * @param params 参数
     * @param headers 请求头
     * @param connTimeout 建立链接超时时间,毫秒.
     * @param readTimeout 响应超时时间,毫秒.
     * @return
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers,
                                  Integer connTimeout, Integer readTimeout ) throws Exception {
        HttpPost post = new HttpPost(url);
        if (params != null && !params.isEmpty()) {
            // 参数编码 &key=value x-www-form-urlencoded
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry: entrySet) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
            post.setEntity(entity);
        }
        if (headers != null && !headers.isEmpty()) {
            // 添加请求头
            for (Map.Entry<String, String> entry: headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return invokeNet(url, post, connTimeout, readTimeout);
    }

    /**
     * 请求总入口
     * @param url 地址
     * @param http
     * @param connTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    private static String invokeNet(String url, HttpRequestBase http,
                                    Integer connTimeout, Integer readTimeout) throws Exception {
        HttpClient client = null;
        String result = null;
        try {
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            http.setConfig(customReqConf.build());

            HttpResponse res = null;
            if (url.startsWith("https")) {
                // 执行https请求
                client = createSSLInsecureClient();
                res = client.execute(http);
            } else {
                // 执行http请求
                client = HttpUtil.client;
                res = client.execute(http);
            }
            HttpEntity entity = res.getEntity();

            result = IOUtils.toString(entity.getContent(), "UTF-8");
        } finally {
            http.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * https请求 SSL加密
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {

        try {
            SSLContext sllContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sllContext, new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException e) {
            throw e;
        }
    }

}
