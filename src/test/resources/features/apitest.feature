# language: zh-CN

@all_test
@api_test
功能:api功能测试

  背景:请求参数初始化
    * 指定请求url为http://127.0.0.1:8001/getPeopleInfoByJson
    * 指定请求方法为post
    * 初始化请求参数
    """
    {"name":"张三"}
    """

  场景大纲: 一个api功能测试的demo
    * 修改参数<alterJsonPath>的值为<alterValue>
    * 执行请求
    * 检查jsonPath为<jsonPath>是否为<expectValue>
    例子:
      | alterJsonPath | alterValue | jsonPath | expectValue |
      | $.name        | 张三         | $[0].role   | 前端开发          |
      | $.name        | 李四         | $[0].role   | 后端开发          |
      | $.name        | 王五         | $[0].role   | 测试          |