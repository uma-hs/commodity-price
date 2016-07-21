package com.uma.android.cmpi.api;

import com.uma.android.cmpi.model.Commodity;

import java.util.List;

/**
 * Created by Umamaheshwar HS on 7/17/2016.
 */
public interface SearchListener {
    public void onResults(List<Commodity> results);
}
