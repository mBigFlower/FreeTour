package com.flowerfat.initapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.flowerfat.initapp.model.TourSettingItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 明明大美女 on 2016/9/28.
 * <p>
 * SP 保存TourSetting界面，最初的那些条目
 * <p>
 * 如果本地没有保存这个SP，则调用setup
 */

public class InitTourSettingItems {

    public static void setup(Context context) {
        // 1.第一次进入 从assets中加载json文件，并将copy到个人喜好（File？SP？）
        String settingList = SpManager.getInstance().getString(SpManager.SP_TOUR_SETTING_LIST);
        if (settingList == null) {
            RxAssets(context);
        }
    }

    /**
     * 初始化本地SP内容
     *
     * @param context
     */
    public static void initLocalData(Context context) {
        String settingData = getAsset(context);
        TourSettingItem[] list = new Gson().fromJson(settingData, TourSettingItem[].class);
        List<String> titleList = new ArrayList<>();
        for (TourSettingItem item : list) {
            titleList.add(item.getTitle());
            SpManager.getInstance().put(item.getTitle(), GsonUtil.toJson(item.getItems()));
        }
        SpManager.getInstance().put(SpManager.SP_TOUR_SETTING_LIST, GsonUtil.toJson(titleList));
    }

    /**
     * 初始化本地SP内容， 使用RxJava
     *
     * @param context
     */
    public static void RxAssets(Context context) {
        Observable.just("")
                .map(s -> getAsset(context))
                .map(s -> GsonUtil.g().fromJson(s, TourSettingItem[].class))
                .flatMap(new Func1<TourSettingItem[], Observable<TourSettingItem>>() {
                    @Override
                    public Observable<TourSettingItem> call(TourSettingItem[] item) {
                        return Observable.from(item);
                    }
                })
                .doOnNext(item -> SpManager.getInstance().put(item.getTitle(), GsonUtil.toJson(item.getItems())))
                .subscribeOn(Schedulers.io())
                .subscribe();
        PackageManager pk ;
    }

    /**
     * TODO 这个应该放到别的地方
     * @param context
     * @return
     */
    public static String getAsset(Context context) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open("TourSettingBaseItems.json");
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            return new String(buffer, "utf-8");

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
    }

}
