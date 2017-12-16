package com.x.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Derek on 2017/12/16 0016
 */

public class ElementWait {
    Logger logger = LoggerFactory.getLogger(getClass());
    public WebElement getElement(WebDriver webDriver,By by){
        WebElement webElement = null;
        int findCounter = 0 ;
        do {
            findCounter++;
            try {
                logger.info("findCounter : {}",findCounter);
                webElement = webDriver.findElement(by);
            } catch (NoSuchElementException e) {
                logger.info("NoSuchElementException");
            }finally {
                if(webElement != null){
                    break;
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (findCounter <= 30);
        return webElement;
    }
}
