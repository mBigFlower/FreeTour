package com.flowerfat.initapp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 明明大美女 on 2016/10/11.
 */

public class GsonUtil {
    private static Gson mGson;

    public static Gson g() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    public static String toJson(Object o) {
        return g().toJson(o);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJsonHashMap(String json) {
        T target = (T) g().fromJson(json, new TypeToken<HashMap>() {
        }.getType());
        return target;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJsonList(String json) {
        T target = (T) g().fromJson(json, new TypeToken<List>() {
        }.getType());
        return target;
    }

}
