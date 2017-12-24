package com.x.common;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by x on 2017/12/24.
 */

public class HTTPUtil extends BaseAction {
    private Logger logger = LoggerFactory.getLogger(getClass());
    protected String uri;
    protected String param;
    protected int statusCode;
    protected String result;

    public HTTPUtil() {
    }

    public HTTPUtil(String uri) {
        this.uri = uri;
    }

    public HTTPUtil(String uri, String param) {
        this.uri = uri;
        this.param = param;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String doGet() {
        logger.info("开始执行GET请求");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet doGet = new HttpGet(uri);
        doGet.setHeader("Content-type", "");
        logger.info("执行请求的URI为：" + doGet.getURI());
        try {
            CloseableHttpResponse response = httpClient.execute(doGet);
            try {
                if (response != null) {
                    statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode != 200) {
                        logger.error("request faild,status code：" + statusCode + response.getStatusLine());
                        result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                        logger.info("response content:" + result);
                    } else {
                        result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                        logger.info("response content：" + result);
                    }
                } else {
                    logger.info("response is null");
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.error("error:{}", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("error:{}", e);
            }
        }
        return result;
    }

    public String doPost() {
        logger.info("开始执行Post请求");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost dopost = new HttpPost(uri);
        dopost.setHeader("Content-type", "");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        try {
            dopost.setEntity(entity);
            logger.info("执行请求的URI为：" + dopost.getURI());
            logger.info("请求参数为：" + param);
            CloseableHttpResponse response = httpClient.execute(dopost);
            try {
                if (response != null) {
                    statusCode = response.getStatusLine().getStatusCode();

                    if (statusCode != 200) {
                        logger.error("request faild,status code：" + statusCode + response.getStatusLine());
                        result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                        logger.info("response content:" + result);
                    } else {
                        response.getEntity().getContentLength();
                        result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
                        logger.info("response content:" + result);
                    }
                } else {
                    logger.info("response is null");
                }
            } finally {
                response.close();
                dopost.releaseConnection();
            }
        } catch (Exception e) {
            logger.error("error:{}", e);
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                logger.error("error:{}", e);
            }
        }
        return result;
    }
}
