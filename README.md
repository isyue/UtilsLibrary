# 项目介绍
## 分支介绍
- v_1.0 Java分支
- v_2.0 代码转为Kotlin
> 收集一些常用的代码片段，进行整理。  
[Git上别人整理的链接](https://github.com/Blankj/AndroidUtilCode)
- utilslibrary 工具类包，没有资源文件，使用gradle命令进行jar构建使用
- uilibrary UI工具包，拥有资源文件，使用gradle自带命令进行aar包的构建
#### 参与规则
> **此工具类原则：**  
- 功能集合必须用一个类包含  
- 单一功能不能超过5个类，并且多余两个类文件必须分包  
- 类中功能不能包含在线依赖库，或者本地不能直接运行的继承等
- 代码不允许调用外部资源，一切已内部调用为先
- 代码所关联的类不允许超过三个，超过三个算第三方模板
- 代码的自定义View不允许高于三个文件关联，包括图片资源，风格等
- XML样式资源需要附带运行后的图样资源

> **不满足以上规则的，可移步至一下两个库进行开发**  
 [第三方开发模板及框架使用](https://gitee.com/xmqian/JAR_List)  
 [功能模块集合](https://gitee.com/xmqian/CollectApplication)

#### 软件架构
查看库文件的用法及作用，请移步至[Wiki文档](https://gitee.com/xmqian/UtilsLibrary/wikis/%E4%B8%BB%E9%A1%B5%E8%AF%B4%E6%98%8E)

#### 使用说明

1. [java代码工具路径](https://gitee.com/xmqian/UtilsLibrary/tree/master/utilslibrary/src/main/java/regpang/utilslibrary)
2. [XML布局路径](https://gitee.com/xmqian/UtilsLibrary/tree/master/utilslibrary/src/main/res/layout)
3. [Drawable资源路径](https://gitee.com/xmqian/UtilsLibrary/tree/master/utilslibrary/src/main/res/drawable)

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

