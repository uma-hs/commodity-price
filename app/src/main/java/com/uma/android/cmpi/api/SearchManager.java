package com.uma.android.cmpi.api;

import android.util.Log;

import com.uma.android.cmpi.util.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class SearchManager {
    public static String TAG=SearchManager.class.getCanonicalName();
    private static SearchManager instance;
    private Map<String,List<String>> states;

    public synchronized static void create(){
        if(instance==null){
            instance=new SearchManager();
        }
    }

    public static SearchManager instance(){
        return instance;
    }
    private SearchManager(){
        try {
            states= FileUtil.loadStatesDataAsMap();
        } catch (IOException e) {
            Log.e(TAG,"Exception while loading states.properties",e);
            e.printStackTrace();
        }
    }
}
