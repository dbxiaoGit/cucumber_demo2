package com.x.testcase;

import com.alibaba.fastjson.JSONPath;
import com.x.common.HTTPUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Created by x on 2017/12/24.
 */

public class ApiTestStep extends HTTPUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Given("^指定请求url为(.+?)$")
    public void setRequestUrl(String url) throws Throwable{
        logger.info("setRequestUrl : {}",url);
        this.uri = url;
    }

    @Given("^指定请求方法为(.+?)$")
    public void setRequestMethod(String requestMethod) throws Throwable{
        logger.info("setRequestMethod : {}",requestMethod);
        this.requestMethod = requestMethod;
    }

    @Given("^初始化请求参数")
    public void setRequestParam(String requestParam) throws Throwable{
        logger.info("setRequestParam : {}",requestParam);
        this.param = requestParam;
    }

    @Given("^修改参数(.+?)的值为(.+?)$")
    public void alterJsonBody(String jsonPath,String value) {
        logger.info("jsonPath:" + jsonPath);
        logger.info("修改前param:{}" , param);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(param);
        JSONPath.set(jsonObject, jsonPath, value);
        param = jsonObject.toJSONString();
        logger.info("修改后param:{}" , param);
    }

    @Given("^执行请求")
    public void excuteReuest() throws Throwable{
        logger.info("excuteReuest");
        sendRequest();
    }

    @Given("^检查jsonPath为(.+?)是否为(.+?)$")
    public void checkResponse(String jsonPath,String expectedValue) throws Throwable{
        logger.info("checkResponse,jsonPath:{},expectedValue:{}",jsonPath,expectedValue);
        Object actual = com.alibaba.fastjson.JSONPath.read(result,jsonPath);
        setCheckPoint(actual,expectedValue,"jsonpath断言");
        check();
    }



    @After("@api_test")
    public void testAfter(Scenario s){
        s.embed(uri.getBytes(Charset.forName("utf-8")),"text/plain");
        s.embed(param.getBytes(Charset.forName("utf-8")),"application/json");
        s.embed(result.getBytes(Charset.forName("utf-8")),"application/json");
    }
}
