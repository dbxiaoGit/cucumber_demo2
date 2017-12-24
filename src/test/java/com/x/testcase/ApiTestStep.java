package com.x.testcase;

import com.alibaba.fastjson.JSONPath;
import com.x.common.HTTPUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by x on 2017/12/24.
 */

public class ApiTestStep extends HTTPUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void alterJsonBody(String jsonPath,String value) {
        logger.info("jsonPath:" + jsonPath);
        logger.info("修改前param:{}" , param);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(param);
        JSONPath.set(jsonObject, jsonPath, value);
        param = jsonObject.toJSONString();
        logger.info("修改后param:{}" , param);
    }

    @After("@api_test")
    public void testAfter(Scenario s){
        s.embed(uri.getBytes(),"text/plain");
        s.embed(param.getBytes(),"application/json");
        s.embed(result.getBytes(),"application/json");
    }
}
