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