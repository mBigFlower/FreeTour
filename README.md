# InitApp
卸甲归田，沉淀一下 ---- 且看菜鸟是如何一步步成长，来完成整套app的

“自己写 --> 用轮子 --> 分析轮子，善用轮子 --> 做大牛，造轮子”

## 目录

- [工具](https://github.com/mBigFlower/InitApp/tree/master/blog/setting)
- [设计模式](https://github.com/mBigFlower/InitApp/tree/master/blog/design_patterns)
- [Java性能优化tips](https://github.com/mBigFlower/InitApp/tree/master/blog/optimization)
 
<h1 id=this> 关于InitApp </h1>

目前已经有了想法。先把架构搭一下。第一次真正意义上搭建自己的架构，名字就叫**Tomorrow**，以后敲代码就引入该module即可。

## 更新日志

#### 开始工程前的Setting[2016-04-??](#setting)
#### 关于整个工程的框架[2016-05-08](#architecture)

<h2 id=setting> 开始工程前的Setting[2016-04-??] </h2>

去看[**工具**](https://github.com/mBigFlower/InitApp/tree/master/blog/setting)吧，里面会提到Studio、Gradle、插件以及一些实用的工具。
如果你是高玩，请跳过这一步

<h2 id=architecture> 框架 </h2>

搭建一下“架构”吧，这个App我打算接触一些新鲜玩意儿，边学边弄，
目前初步打算使用 RxJava与Retrofit、Dagger2和MVP（对于Retrofit持观望态度），GO ~

参考的工程有

- [**MVP-Dagger2-Retrofit**](https://github.com/thinkSky1206/MVP-Dagger2-Retrofit)、
- [**RxJoke**](https://github.com/JDDJJ/RxJoke)、
- [**PhotoNoter**](https://github.com/yydcdut/PhotoNoter)

三个工程循序渐进，很有收获=。=

[0512 22:44]
说实话以前一直用的时MVC，最多把Model和View分离开来。最近研究这个Dagger+MVP简直懵逼。所以我在这里奉劝大家：一步一个脚印。

具体的我会单拎出来一个工程来分别写MVC、MVP与MVP+Dagger2，小白篇，[详情点这里](https://github.com/mBigFlower/ArchitectureLearning)。

### MVP

先来说说MVP的特点吧：低耦合、抽象、单一职责。。。各个层级之间相互独立，方便修改与测试。

官方已经有很棒的demo了，[看这里](https://github.com/googlesamples/android-architecture)，
在里面点击不同分支即可查看不同的架构，这里我们主要看MVP分支

### 通过Gradle导入必要库

#### Butterknife

好东西不多说。这里要提一句，别忘了这个插件：Butterknife Zelezny，谁用谁知道。

#### RxJava和RxAndroid - 多线程

这个东西玩了一圈下来，我的感觉就是异步！而且这个线程切换起来真是方便

#### Retrolambda -- 简洁

扔物线曾这么评价过 Retrolambda ：

1. 可读性差，且容易忽略一些技术细节（如 与RxJava相关的）
2. Retrolambda是非官方的，以后可能会出现兼容性问题

用过lambda以后真的会觉得很爽，那么你只会一点点皮毛，也会觉得代码干净了很多。什么？可读性差？我记得有个朋友这么评价男人时间短：
管她呢，反正老子爽了。

#### Dagger2 - 解耦

- [详解Dagger2](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0519/2892.html)
- [当复仇者联盟遇上Dragger2、RxJava和Retrofit的巧妙结合](http://www.devtf.cn/?p=565)

1. 独立于组件之外
2. 方便测试
3. 便于大工程，便于团队协作

我不得不说这个玩意儿有点难懂，原谅我以前没用过注解。慢慢来。

#### leakCanary

"Automatically show a notification when an activity memory leak is detected in your debug build."



# 相关文章

1. [从零开始的Android新项目1 - 架构搭建篇](http://blog.zhaiyifan.cn/2016/03/14/android-new-project-from-0-p1/)


//-------------------------------------------------

华丽的分割线 下面的内容是想到就mark下来的，尚未添加内容

//-------------------------------------------------

### 自动更新

框图：http://naotu.baidu.com/file/002044554d93744cf9433d8dfe0d7e2b?token=83201e9f4315262a


## 其他

### 缓存Achace

### 学习安卓的网站

- [掘金](http://gold.xitu.io/)
- [安卓开发技术周报](http://www.androidweekly.cn/) Android开发技术周报是由@脉脉不嘚語 维护更新，截止到现在，已经发布了28 期，分享的都是高质量的文章教程，代码库，工具，新闻视频，设计等。
- [Weekly](http://androidweekly.net/) 是由一群国外知名的Android 开发者维护，分享的内容不仅仅只有干货，而且还有最新的技术分享，也是Android 开发圈子最早，最有名的周报。如果想看中文版，请移步知乎专栏——《Android Weekly - 知乎专栏》。
- [慕课网](http://www.imooc.com/) 质量挺高的，初学者必备
- **[当然还有其他](http://tikitoo.me/2016/04/26/android-worth-subscribe-daily-weekly/)**

