package com.uma.android.cmpi.api;

import com.uma.android.cmpi.model.Commodity;

import java.util.List;

/**
 * Created by Umamaheshwar HS on 7/14/2016.
 */
public class CMPAPIResponse {
    private String _help;
    private int count;
    private boolean success;
    private List<Commodity> records;

    public String get_help() {
        return _help;
    }

    public void set_help(String _help) {
        this._help = _help;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Commodity> getRecords() {
        return records;
    }

    public void setRecords(List<Commodity> records) {
        this.records = records;
    }
}
