# language: zh-CN
功能:一个小demo

  @smoke
  场景大纲: 一个ui的demo
    * 打开网页<url>
    * 检查页面是否包含text为<text>的超链接<href>
    例子:
    |url|text|href|
    |https://www.baidu.com/|关于百度|http://home.baidu.com|
    |http://www.qq.com/|关于腾讯|    http://www.tencent.com/ |
    |http://www.163.com/|About NetEase|http://corp.163.com/|