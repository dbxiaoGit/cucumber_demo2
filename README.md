# cucumber_demo2
网上东拼西凑的东西



             /**
              * Created by ${USER} on ${DATE}.  
              */
 
 
             #!/usr/bin/env python
             # -*- coding: utf-8 -*-

             __author__ = '${USER}'



 
 
            mvn test verify -Dcucumber.options="--tags @ui_test"
            mvn test verify -Dcucumber.options="--name api"

           mvn  clean test verify -Dcucumber.options="--tags @api_test"
windows下jenkins构建命令用%%引用choice中的变量
>仅需要配一个Cucumber reports
>高级 Json Reports Path
>>target/
>File Include Pattern
>>cucumber.json