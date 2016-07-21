package com.uma.android.cmpi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.uma.android.cmpi.R;
import com.uma.android.cmpi.api.CMPAPIFilter;
import com.uma.android.cmpi.api.SearchListener;
import com.uma.android.cmpi.api.SearchManager;
import com.uma.android.cmpi.model.Commodity;
import com.uma.android.cmpi.util.RestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private TextView stateSearchView;
    private TextView districtSearchView;
    private ListView commodityListView;
    private List<Commodity> commodityList=new ArrayList<>();
    private CommodityListViewAdapter commodityListViewAdapter;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity_list);
        stateSearchView= (TextView) findViewById(R.id.search_state);
        districtSearchView= (TextView) findViewById(R.id.search_district);
        commodityListView= (ListView) findViewById(R.id.list_commodity);
        commodityListViewAdapter=new CommodityListViewAdapter(commodityList,getApplicationContext());
        commodityListView.setAdapter(commodityListViewAdapter);


        SearchManager.instance().setSearchListener(new SearchListener() {

            @Override
            public void onResults(final List<Commodity> results) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commodityListViewAdapter.addResults(results);
                    }
                });

            }
        });
        searchCommodityPrices();
    }



    private void searchCommodityPrices(){
        CMPAPIFilter filter=new CMPAPIFilter("Karnataka");
        String url=RestUtil.getURL(filter);
        SearchManager.instance().performSearch(url);
    }



}
