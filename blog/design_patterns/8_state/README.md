# 第8章 随遇而安--状态模式

## 概述

### 书中的话

“通过多态的形式去除重复、杂乱的if-else语句----状态模式的精髓”

“状态模式的关键点在于 不同的状态下对于同一行为有不同的响应”


### 我的理解

字面来看，无非state，如果你的code中，会通过判断state来进行不同的处理。

使用状态模式，弃用传统的if、switch判断，把每个判断的部分放到不同状态的类里面去实现，使得逻辑更加清晰


## 优劣

### 优点

“将繁琐的状态判断 转换成结构清晰的状态类族，在**避免代码膨胀**的同时也保证了**可扩展性**与**可维护性**”

### 缺点

“增加 类和对象 的个数”

## 操作

下面我以书中的一个登录的例子来说下实现

### 概述

在一个app中，普遍存在两种状态：登录与未登录（有可能涉及到未绑定手机号），对于不同的状态，
用户有着不同的权限。这时很多地方就需要进行判断，反正以前我都是在每个地方使用if来判断，直到我遇到了状态模式

### 代码

先上代码

首先，我们需要一个接口来定义我们的状态

    /**
     * 我们进行下面操作时，需要判断状态
     * doSome1, doSome2
     * 至于传递进来的参数，随意，开心就好
     */
    public interface UserState {
        void doSome1(Context context);
        void doSome2(Context context);
    }
    
然后，我们暂且定义两种状态，登录与未登录，来继承这个接口。

    // 登录状态
    public class LoginedState implements UserState {
        @Override
        public void doSome1(Context context) {
            // 登录后，doSome1 应该做的事儿
        }
    
        @Override
        public void doSome2(Context context) {
    
        }
    }
    // 未登录状态
    public class LogoutState implements UserState {
        @Override
        public void doSome1(Context context) {
            // 未登录，doSome1 应该做的事儿
        }
    
        @Override
        public void doSome2(Context context) {
    
        }
    }
    
进行到这里，准备工作完成，当时我看到这里的时候，第一感觉是：怎么这么多类？用得着这样吗？
上面其实只是山楂，下面即将登场的类是木签子，让我们来穿一串糖葫芦=。=

    /**
     * 
     */
    public class LoginManager {
    
        // 在这个Manager里，创建一个状态，默认未登录
        private UserState mState = new LogoutState();
    
        // 这里是一个单例， 你当然可以写的专业点，但是这里我们。。。 don't care it
        public static LoginManager sLoginManager = new LoginManager();
    
        public static LoginManager getLoginManager() {
            return sLoginManager;
        }
        // 当我们的登录状态改变后，就执行这个函数。
        public void setState(UserState aState) {
            this.mState = aState;
        }
    
        // 下面是我们的需要判断的操作，这里可以和UserState接口里的方法一样
        public void doSome1(Context context){
            mState.doSome1(context);
        }
        public void doSome2(Context context){
            mState.doSome2(context);
        }
    }

好了，核心代码就上面那些，一共四个类，两种状态的类，一个公共的接口，一个我们来使用的类，使用方法如下：
（我们调用的永远是LoginManager.class）

我们的LoginManager里只有一个mState，当状态改变时，我们相当于改变了这个mState，时而把mState赋值为LoginedState，
时而把mState赋值为LogoutState。这样，虽然我们调用的都是

    LoginManager.getLoginManager().doSome1(getContext());
    
但是，因为mState的不同，执行的代码是不同的。我们看到，这样之后，不再需要判断。直接调用就好，至于不同状态执行的不同的操作，
我们会在不同的状态的类（LoginedState、LogoutState）中去执行，这样代码好看了很多

