package test8;

import android.content.Context;

/**
 * Created by 明明大美女 on 2016/4/11.
 */
public class LoginManager {

    // 在这个Manager里，创建一个状态，默认未登录
    private UserState mState = new LogoutState();

    // 这里是一个单例， 你当然可以写的专业点，但是这里我们。。。 don't care it
    public static LoginManager sLoginManager = new LoginManager();

    public static LoginManager getLoginManager() {
        return sLoginManager;
    }

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
