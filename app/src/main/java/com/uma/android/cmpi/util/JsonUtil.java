package com.uma.android.cmpi.util;

import com.google.gson.Gson;

/**
 * Created by Umamaheshwar HS on 7/14/2016.
 */
public class JsonUtil {
    private static Gson gson=new Gson();

    public static <T> T toObject(String json, Class<T> tClass){
        return gson.fromJson(json,tClass);
    }

}
