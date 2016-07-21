package com.uma.android.cmpi.util;

import android.util.Log;

import com.uma.android.cmpi.api.APIConstants;
import com.uma.android.cmpi.api.CMPAPIFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Umamaheshwar HS on 7/14/2016.
 */
public class RestUtil implements APIConstants {

    private static String TAG = RestUtil.class.getCanonicalName();

    public static String get(URL url) {
        Log.d(TAG,"URL is : "+url.toString());
        StringBuilder sb = new StringBuilder();
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return sb.toString();
    }

    public static String getURL() {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(QUERY_PARAM_DELIMITER);
        sb.append(RESOURCE_ID + "=");
        sb.append(RESOURCE_ID_VAL);
        sb.append(AND);
        sb.append(API_KEY + "=");
        sb.append(API_KEY_VAL);
        return sb.toString();
    }

    public static String getURL(CMPAPIFilter filter) {
        StringBuilder url = new StringBuilder(getURL());

        if (filter != null) {
            List<String> states=filter.getStates();
            if (states != null && !states.isEmpty()) {
                url.append(AND + FILTERS);
                url.append("[");
                url.append(STATE);
                url.append("]=");
                url.append(StringUtil.join(",",states));
            }

            List<String> districts=filter.getDistricts();
            if(districts!=null&&!districts.isEmpty()){
                url.append(AND + FILTERS);
                url.append("[");
                url.append(DISTRICT);
                url.append("]=");
                url.append(StringUtil.join(",",districts));
            }
            List<String> sortFields=filter.getSortFields();
            if(sortFields!=null&&!sortFields.isEmpty()){
                url.append(AND);
                url.append(SORT+"[");
                url.append(StringUtil.join(",",sortFields));
                url.append("]=asc");
            }

        }
        return url.toString();
    }
}
