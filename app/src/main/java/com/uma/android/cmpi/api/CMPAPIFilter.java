package com.uma.android.cmpi.api;

import java.util.List;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class CMPAPIFilter {
    private List<String> states;
    private List<String> districts;
    private List<String> sortFields;

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
    }

    public List<String> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<String> sortFields) {
        this.sortFields = sortFields;
    }
}
