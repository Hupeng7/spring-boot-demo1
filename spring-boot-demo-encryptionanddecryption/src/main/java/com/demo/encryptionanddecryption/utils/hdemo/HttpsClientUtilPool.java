package com.demo.encryptionanddecryption.utils.hdemo;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HttpsClientUtilPool
 */
public class HttpsClientUtilPool {

    private static Logger logger = LoggerFactory.getLogger(HttpsClientUtilPool.class);
    private static final int DEFAULT_MAX_CONNECTION_PER_ROUTE = 100;
    private static final int TOTAL_MAX_CONNECTION = 500;
    private static final int DEFAULT_REQUEST_TIMEOUT = 1000;
    private static HttpsClientUtilPool inst;
    private CloseableHttpClient httpClient;

    private HttpsClientUtilPool() {
        HttpClientBuilder b = HttpClientBuilder.create();
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception e) {
            sslContext = SSLContexts.createSystemDefault();
            logger.error("sslContext error:", e);
        }
        b.setSSLContext(sslContext);
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connMgr.setMaxTotal(TOTAL_MAX_CONNECTION);
        connMgr.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTION_PER_ROUTE);
        b.setConnectionManager(connMgr);
        b.setRetryHandler(new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException arg0, int retryTimes, HttpContext arg2) {
                if (retryTimes >= 3) {
                    return false;
                }
                if (arg0 instanceof UnknownHostException || arg0 instanceof ConnectTimeoutException
                        || arg0 instanceof NoHttpResponseException || arg0 instanceof NoRouteToHostException
                        || arg0 instanceof SocketException) {
                    logger.info("restTemplate setRetryHandler,>>>>>>>>>>:{},{}", arg0.getClass(), arg0.getMessage());
                    if (arg0 instanceof SocketException) {
                         return arg0.getMessage().contains("Connection");
                    }
                    return true;
                }
                return false;
            }
        });
        httpClient = b.build();
    }

    public static HttpsClientUtilPool getInst() {
        if (inst == null) {
            synchronized (HttpsClientUtilPool.class) {
                if (inst == null) {
                    inst = new HttpsClientUtilPool();
                }
            }
        }
        return inst;
    }

    /**
     * PostForm请求 aliyun
     * 
     */
    public String doPostFormQX(String url, List<NameValuePair> params, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
                if (StringUtils.isBlank(result) && response.getStatusLine() != null) {
                    logger.error("error:{}", response.getStatusLine());
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String postJsonCWX(String url, String jsonParam, Map<String, String> headerMap, int connectTimeOut,
            int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            for (Entry<String, String> var : headerMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(jsonParam, "UTF-8");
            httpPost.setEntity(entity);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * PostForm请求 aliyun
     * 
     */
    public String doPostFormALI(String url, Map<String, String> paramsMap, Map<String, String> headerMap,
            int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            for (Entry<String, String> var : headerMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
                if (StringUtils.isBlank(result) && response.getStatusLine() != null) {
                    JSONObject resultJsonObj = new JSONObject();
                    resultJsonObj.put("code", response.getStatusLine().getStatusCode());
                    resultJsonObj.put("message", "hz补充httpsCode");
                    result = resultJsonObj.toString();
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }
    
    /**
     * PostForm请求
     * 
     */
    public String doPostFormCWX(String url, Map<String, String> paramsMap, Map<String, String> headerMap,
            int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            for (Entry<String, String> var : headerMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String postJsonYS(String url, String jsonParam, String acctId, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.addHeader("X_GLAD_ACCT_ID", acctId);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(jsonParam, "UTF-8");
            httpPost.setEntity(entity);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * 
     * post的Json请求 yxt
     * 
     * @param url           地址
     * @param headers       请求头header参数
     * @param jsonParam     请求参数
     * @return result
     */
    public String postJsonYXT(String url, Map<String, Object> headers, String jsonParam, int connectTimeOut,
            int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            if (headers != null && headers.size() > 0) {
                for (Entry<String, Object> param : headers.entrySet()) {
                    httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            if (jsonParam != null && jsonParam.trim().length() != 0) {
                StringEntity uefe = new StringEntity(jsonParam, ContentType.APPLICATION_JSON);
                uefe.setContentType("text/json");
                httpPost.setEntity(uefe);
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * PostForm请求
     *
     */
    public String doPostForm(String url, Map<String, String> paramsMap, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * PostForm请求 子柒
     *
     */
    public String doPostFormZIQI(String url, Map<String, String> paramsMap, Map<String, String> headersMap,
            int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            for (Entry<String, String> varh : headersMap.entrySet()) {
                httpPost.addHeader(varh.getKey(), varh.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostJsonPool(String url, String jsonStr, String charset, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(jsonStr, charset);
            httpPost.setEntity(entity);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);

            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostJson(String url, String jsonStr, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(jsonStr, "UTF-8");
            httpPost.setEntity(entity);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostMapPool(String url, String jsonStr, int connectTimeOut, int readTimeOut) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost request = new HttpPost(url);
            request.setHeader(HttpHeaders.REFERER, "referer");
            request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
            request.setEntity(new StringEntity(jsonStr, Charsets.UTF_8));
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            request.setConfig(requestConfig);
            // 获取返回值
            response = httpClient.execute(request);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error(" response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doGetPool(String url, int connectTimeOut, int readTimeOut) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpGet.setConfig(requestConfig);
            // 通过请求对象获取响应对象
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error(" response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * talkingdata
     */
    public String doGetPoolTalkData(String url, String headerKey, int connectTimeOut, int readTimeOut) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);// 这里发送get请求
            if (StringUtils.isNotBlank(headerKey)) {
                httpGet.setHeader("X-Access-Token", headerKey);// 钛镕分配
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut)
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);
            // 通过请求对象获取响应对象
            response = httpClient.execute(httpGet);
            logger.info("response.getStatusLine().getStatusCod:{}", response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", response.getStatusLine().getStatusCode());
                jsonObj.put("responseBody", EntityUtils.toString(response.getEntity(), "utf-8"));
                result = jsonObj.toString();
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doGetPool4TR(String url, List<NameValuePair> params, String headerKey, int connectTimeOut,
            int readTimeOut) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            URI uri = new URIBuilder(url).setParameters(params).build();
            HttpGet httpGet = new HttpGet(uri);// 这里发送get请求
            httpGet.setHeader("tfapi-key", headerKey);// 钛镕分配
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut)
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);
            // 通过请求对象获取响应对象
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostPool4XA(String url, String jsonStr, String charset, int connectTimeOut, int readTimeOut,
            String xaKey) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("xa-key", xaKey);
            StringEntity entity = new StringEntity(jsonStr, charset);
            httpPost.setEntity(entity);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);

            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * post 表单请求
     */
    public String doPostFormPool(String url, Map<String, String> paramsMap, String charset, int connectTime,
            int readTime) {
        String result = null;
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            result = doPostFormPool4List(url, params, charset, connectTime, readTime);
        } catch (Exception ex) {
            logger.error("response.close error:{}", url, ex);
        }
        return result;
    }

    /**
     * post 表单请求，入参：List<NameValuePair>
     */
    public String doPostFormPool4List(String url, List<NameValuePair> params, String charset, int connectTime,
            int readTime) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTime).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    /**
     * curl -X POST 'http://182.92.66.213:19000/api/main/v2?apiKey=130003&custOrderId=bb9a31219bc54c8db7c412c6373ddcd5&custUserId=21' -H 'content-type: application/x-www-form-urlencoded' -d 'primaryParameter=cellphoneNum%3Dbafc727a964549362b9ea6a3b0d3b07e217dac52b0916442b674c1e2745caea6%26carrier%3DUNICOM%26encryptType%3Dsha256&auxiliaryParameter=0&reqTime=1734344761075&sign=9623E5F1B08342C4A51952CBEA4AF977'
     */
    public String doPostFormSdtc(String url, String requestBody, String charset, int connectTime, int readTime) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            // httpPost.addHeader("Connection", "Keep-Alive");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTime).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    // mob
    public String doPostJsonPoolHeadersMap(String url, String jsonStr, String charset, int connectTimeOut,
            int readTimeOut, Map<String, String> headersMap) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            for (Entry<String, String> var : headersMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            StringEntity entity = new StringEntity(jsonStr, charset);
            httpPost.setEntity(entity);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    // ZhongZC
    public String doPostJsonPoolHeader(String url, String jsonStr, String charset, int connectTimeOut, int readTimeOut,
            String authorization) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", authorization);
            StringEntity entity = new StringEntity(jsonStr, charset);
            httpPost.setEntity(entity);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("url = {}, error = {}, exception: ", url, ex.toString(), ex);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String postJsonHeaderCommon(String url, String jsonParam, Map<String, String> headerMap, int connectTimeOut,
            int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            for (Entry<String, String> var : headerMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(jsonParam, "UTF-8");
            httpPost.setEntity(entity);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start,
                    url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostAliyun(String url, Map<String, String> paramsMap, Map<String, String> headerMap, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> var : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(var.getKey(), var.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            for (Entry<String, String> var : headerMap.entrySet()) {
                httpPost.addHeader(var.getKey(), var.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    public String doPostAliyunV2(String url, String path, Map<String, String> headers, Map<String, String> querys, int connectTimeOut, int readTimeOut) {
        HttpPost httpPost = null;
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost = new HttpPost(buildUrl(url, path, querys));
            for (Entry<String, String> e : headers.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeOut)
                    .setConnectionRequestTimeout(DEFAULT_REQUEST_TIMEOUT).setSocketTimeout(readTimeOut).build();
            httpPost.setConfig(requestConfig);

            long start = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            logger.info(" >>>>> totalTime = {}, url = {}", System.currentTimeMillis() - start, url);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("url = {}, error = {}, exception: ", url, e.toString(), e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                logger.error("response.close error:{}", url, ex);
            }
        }
        return result;
    }

    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
    	StringBuilder sbUrl = new StringBuilder();
    	sbUrl.append(host);
    	if (!StringUtils.isBlank(path)) {
    		sbUrl.append(path);
        }
    	if (null != querys) {
    		StringBuilder sbQuery = new StringBuilder();
        	for (Entry<String, String> query : querys.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (!StringUtils.isBlank(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (!StringUtils.isBlank(query.getValue())) {
        				sbQuery.append("=");
        				sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append("?").append(sbQuery);
        	}
        }
    	
    	return sbUrl.toString();
    }


    public static void main(String[] args) {

        // String requestJson = "{\"account\":\"test_cf\",\"pwd\":\"xxx\",\"data\":{\"mobile\":\"18751961288\",\"name\":\"张三\",\"idNo\":\"320103199504072123\"}}";
        // String requestUrl = "http://106.15.27.88:30253/mobile/cmcc/factor3_excellent";

        // String logOut = HttpsClientUtilPool.getInst().doPostJsonPool(requestUrl, requestJson, "UTF-8", 1000, 5000);
        // if(StringUtils.isEmpty(logOut)){
        //     logger.info("异常, logOut = {}", logOut);
        // } else {
        //      logger.info("正常, logOut = {}", logOut);
        // }

        String url = "https://edu.market.alicloudapi.com";
	    String path = "/education/check";
        Map<String, String> headers = new HashMap<String, String>();
        String appcode = "809191b325cc428c88971e73ef370623";
	    headers.put("Authorization", "APPCODE " + appcode);

        String fileIndex = "7b9788e6bf004f14a55cc97649607f90";
        Map<String, String> paramMap = new HashMap<String, String>();
	    paramMap.put("fileIndex", fileIndex);
	    paramMap.put("userAuthed", "true");
	    paramMap.put("userName", "xxx");
	    paramMap.put("identifyNum", "xxx");

        String rawData = HttpsClientUtilPool.getInst().doPostAliyunV2(url, path, headers, paramMap, 3000, 5000);
        logger.info("正常, logOut = {}", rawData);
    }


}
