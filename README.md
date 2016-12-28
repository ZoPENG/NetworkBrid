# NetworkBrid
工程前台页面使用 jsp
视图层/控制层使用 springMVC
持久层使用 hibernate

common 包下放 共同文件
        1、异常类
        2、工具
        3、..
core 包下放 业务层/持久层 类文件
mvc  包下放 视图层/控制层 类文件

webapp 视图文件
        1、css
        2、img
        3、plugins
        4、WEB-INF
            1) web.xml 配置
            2) view 视图
                ① common 共同样式/页面
                ② network_admin 后台管理
                ③ network_bird 前台页面

以上暂定，根据业务后期修改结构
