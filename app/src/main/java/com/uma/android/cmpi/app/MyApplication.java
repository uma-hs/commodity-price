package com.uma.android.cmpi.app;

import android.app.Application;
import android.content.Context;

import com.uma.android.cmpi.api.SearchManager;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class MyApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        SearchManager.create();
    }
    public static Context getContext(){
        return mContext;
    }
}
