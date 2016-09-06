# 第12章 解决、解耦的钥匙--观察者模式

我就问你Rx系列火不火？啥子RxJava、RxAndroid，归根到底都离不开观察者模式

我想大家对**订阅**应该很熟悉，比如Weekly，有很多人都订阅了Weekly，它会每周给订阅者们发邮件。
这就是简单的观察者模式，但这个和解耦有锤子关系？额。。。

## 使用

- observer：观察者：敌不动，我不动。
- observable：被观察者：那么多人看着呢，我得悠着点


## 优劣

### 优点

- “两者是抽象耦合，应对业务变化”
- “增加系统灵活性、可扩展性”

### 缺点

“需要考虑开发效率和运行效率的问题，程序中包括一个被观察者、多个观察者，开发和调试等内容会比较复杂，而且在Java中
消息的通知默认是顺序执行，一个观察者卡顿，会影响整体的执行效率，此时一般采用异步的方式”

对于上述的缺点，提到了异步，不晓得为啥现在说起异步我就想到RxJava，奶奶的明明不一样。

## 杂谈

一开始接触观察者模式的时候，observer、observable、subject总是傻傻分不清楚，英文不好是硬伤。
不过偶然想到dota里面的OB视角（裁判？）顺势我就记下了ob是观察的意思。

## 后续

其实在ListView中，当我们要更新数据时，往往调用

    adapter.notifyDataSetChanged();

不晓得你们有没有看过这code的源码，其内部调用了DataSetObservalbe的notifyChanged函数。
而这个函数又会执行观察者(AdapterDataSetObserver)的onChanged方法，onChanged中计算了ListView的条目，然后调用了ListView的重新布局，刷新界面。

其中的观察者就是在setAdapter时建立的。
