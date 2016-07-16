package com.uma.android.cmpi.util;

import android.content.res.AssetManager;

import com.uma.android.cmpi.app.AppConstants;
import com.uma.android.cmpi.app.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class FileUtil {

    public static Map<String,List<String>> loadStatesDataAsMap() throws IOException {
        Properties properties=loadProperties(AppConstants.STATES_FILE);
        Map<String,List<String>> stateMap=new TreeMap<>();
        for(String state:properties.stringPropertyNames()){
            List<String> districts=StringUtil.split(AppConstants.DELIMITER,properties.getProperty(state));
            stateMap.put(state,districts);
        }
        return stateMap;
    }

    public static Properties loadProperties(String file) throws IOException {
        Properties props=new Properties();
        AssetManager assetManager= MyApplication.getContext().getAssets();
        InputStream is=assetManager.open(file);
        props.load(is);
        return props;
    }
}
