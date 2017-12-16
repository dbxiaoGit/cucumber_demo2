package com.x.common;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Derek on 2017/12/15 0015
 */

public class Configurer {
    public static Properties prop ;
    static{
        prop = new Properties();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/application.properties");
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            prop.load(inputStreamReader);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStreamReader != null ){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testProp(){
        System.out.println(prop.getProperty("browser.type"));
    }
}
