package com.x.testcase;

import com.google.common.base.Function;
import com.x.common.ElementWait;
import com.x.common.MyFunction;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertEquals;

/**
 * Created by Derek on 2017/12/15 0015
 */


//@Component
@Scope("cucumber-glue")
public class UiTestStep {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EventFiringWebDriver webDriver;

    @Given("^打开网页(.+?)$")
    public void openBrowser(String url) {
        logger.info("openBrowser");
        webDriver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        webDriver.get(url);
    }

    @Given("^检查页面是否包含text为(.+?)的超链接(.+?)$")
    public void checkContains(String text,String href) {
        String xpath = "//a[@href='"+href+"']";
        logger.info("xpath : {}",xpath);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10L);
        WebElement webElement = null ;
        int waitRound = 0;
        boolean isFound = false;
        //等待1：
        /*
        while(true){
            waitRound++;
            logger.info("waitRound :{}",waitRound);
            try{
                webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            }catch(TimeoutException e){
                logger.info("TimeoutException");
                webDriver.navigate().refresh();
            }
            if(webElement != null || waitRound > 10 ){
                break;
            }
        }
        */

        //等待2：
        do{
            waitRound++;
            logger.info("waitRound :{}",waitRound);
            try {
                webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpath), 1));
                isFound = true;
            }catch (TimeoutException e){
                logger.info("TimeoutException");
                webDriver.navigate().refresh();
            }
        } while(!isFound && waitRound < 10);


        //等待3：
        /*
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);

        webElement = wait.until(new MyFunction(webDriver,By.xpath(xpath)));
        */

        //等待4：
        //webElement = new ElementWait().getElement(webDriver,By.xpath(xpath));
        String elementText = webDriver.findElement(By.xpath(xpath)).getText();
        logger.info("actual elementText  : {}",elementText);
        assertEquals(elementText,text);
    }

    @After("@ui_test")
    public void testAfter(Scenario s){
        //s.write("html:"+webDriver.getPageSource());
        //String base64String = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
        //s.write("img:<img src=\"data:image/png;base64,"+base64String+"\"/>");
        s.embed(((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES),"image/png");
        //webDriver.quit();
    }
}
