# 工具 0416

正所谓工欲善其事，必先利其器。器大才能活儿好，下面就是一些敲代码前的准备工作，望三思而行

## git

这东西不安装天理难容啊，没啥好说的

## Charles

简单来说是个抓包的工具，很好用，比如json数据可以直接格式化，看起来很方便。

“但你如果仅仅停留在抓包层面，就有点low了”，这是之前的一位测试朋友对我说的。

基本使用方法可以看这篇文章：[http://drops.wooyun.org/tips/2423](http://drops.wooyun.org/tips/2423)

具体还可以做什么其他的？当时我也没问，目前理解可以 **模拟网速**，可测试弱网环境下，APP与服务端的交互。

## Studio

### 下载

[AndroidDevTools](http://www.androiddevtools.cn/index.html)

这个网站不多说了吧，应该能满足绝大部分开发者了。至于翻墙，还是花点钱吧，省心。

### Settings

[Android Studio advanced configuration](http://liukun.engineer/2016/04/10/Android-Studio-advanced-configuration/)

这种文章其实很好搜到，我想说的是导出与导入你的Settings

Alt+F 就能看到**Import Settings**和**Export Settings** 把导出的Settings存到网盘，以后用得到

### 插件

插件这东西谁用谁知道啊，我下面随便说点，具体大家去搜吧，很容易搜到哪些实用且怎么使用

1. Ctrl+Alt+S 或者 Alt+F后点Settings
2. 选中Plugins, 然后选择 Browser...

- Sexy Editor 这个插件绝笔炫酷，但是用久了会卡。。。 （刚刚我搜了一下，怎么没找到。。。）
- ButterKnife Zelezny 配合ButterKnife使用，一键导入所有的BindView，简直不要太爽
- GsonFormat 服务器返回的Json数据，直接用它生成对应实体类
- [Material Design Icon](https://github.com/konifar/android-material-design-icon-generator-plugin) 图片生成器，DIY神器，省去了手动打开Iconfront 
- Markdown 顾名思义，可有可无

## Gradle

当初从Eclipse转Studio的时候，就好像临时转校，在新学校碰到了个高傲的副校长，至今跟他也不怎么熟！！！


