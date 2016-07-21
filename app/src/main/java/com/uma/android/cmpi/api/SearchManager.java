package com.uma.android.cmpi.api;

import android.util.Log;

import com.uma.android.cmpi.model.Commodity;
import com.uma.android.cmpi.util.FileUtil;
import com.uma.android.cmpi.util.JsonUtil;
import com.uma.android.cmpi.util.RestUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class SearchManager {
    public static String TAG = SearchManager.class.getCanonicalName();
    private static SearchManager instance;
    private Map<String, List<String>> states;
    private SearchListener searchListener;

    public synchronized static void create() {
        if (instance == null) {
            instance = new SearchManager();
        }
    }

    public static SearchManager instance() {
        return instance;
    }

    private SearchManager() {
        try {
            states = FileUtil.loadStatesDataAsMap();
        } catch (IOException e) {
            Log.e(TAG, "Exception while loading states.properties", e);
            e.printStackTrace();
        }
    }

    public SearchListener getSearchListener() {
        return searchListener;
    }

    public void setSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public Map<String, List<String>> getStates() {
        return states;
    }

    public void setStates(Map<String, List<String>> states) {
        this.states = states;
    }

    public void performSearch(String url) {
        new Thread(new SearchTask(url, new SearchListener() {
            @Override
            public void onResults(List<Commodity> results) {
               searchListener.onResults(results);
            }
        })).start();
    }


}

class SearchTask implements Runnable {
    private String url;
    private SearchListener searchListener;
    private int count = Integer.MAX_VALUE;

    public SearchTask(String url, SearchListener searchListener) {
        this.url = url;
        this.searchListener = searchListener;
    }


    @Override
    public void run() {
        try {
            int offset = 0;
            while (count > 0) {
                //Currently API returns maximum 100 rows. We need to send incremented offset value to return all the rows matching the filter criteria.
                String apiResponse = RestUtil.get(new URL(url + "&offset="
                        + offset));
                CMPAPIResponse response = JsonUtil.toObject(apiResponse,
                        CMPAPIResponse.class);
                if (response == null)
                    break;
                offset++;
                count = response.getCount();
                if (count > 0) {
                    searchListener.onResults(response.getRecords());
                }
                Log.d("SearchTask","Number of rows returned : "+count);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}