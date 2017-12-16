package com.x.common;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

/**
 * Created by Derek on 2017/12/15 0015
 */


@RunWith(Cucumber.class)
@CucumberOptions(strict = true, monochrome = true, features = "src/test/resources/features",
        glue = "com.x",
        plugin = {"pretty", "html:target/cucumber-html-report;","json:target/cucumber.json" },
        tags = {})
public class DemoRunner extends AbstractTestNGCucumberTests {
    static{
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath);
        System.setProperty("webdriver.chrome.driver",basePath+"/src/main/resources/drivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",basePath+"/src/main/resources/drivers/geckodriver.exe");
    }
}