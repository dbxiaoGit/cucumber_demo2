package com.x.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Created by Derek on 2017/12/16 0016
 */

public class MyFunction implements java.util.function.Function<WebDriver, WebElement> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private WebDriver driver;
    private  By by;
    public MyFunction(WebDriver driver,By by){
        this.driver = driver;
        this.by = by;
        logger.info("init MyFunction ({} ,{})",driver.toString(),by.toString());
    }

    @Override
    public WebElement apply(WebDriver driver) {
        logger.info("execute function apply ");
        return driver.findElement(by);
    }

    @Override
    public <V> Function<V, WebElement> compose(Function<? super V, ? extends WebDriver> before) {
        return null;
    }

    @Override
    public <V> Function<WebDriver, V> andThen(Function<? super WebElement, ? extends V> after) {
        return null;
    }
}
