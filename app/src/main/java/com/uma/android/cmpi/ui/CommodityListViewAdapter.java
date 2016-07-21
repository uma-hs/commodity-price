package com.uma.android.cmpi.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.uma.android.cmpi.R;
import com.uma.android.cmpi.model.Commodity;

import java.util.List;

public class CommodityListViewAdapter extends BaseAdapter implements Filterable {
    private List<Commodity> commodities;
    private List<Commodity> filterList;
    private Filter valueFilter;
    private Context context;

    public CommodityListViewAdapter(List<Commodity> commodities, Context context) {
        this.commodities = commodities;
        this.filterList = commodities;
        this.valueFilter = new ValueFilter();
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.commodity_list_row, null);
        TextView commodity_tv = (TextView) view.findViewById(R.id.commodity);
        TextView market_tv = (TextView) view.findViewById(R.id.market);
        Commodity commodity = commodities.get(position);

        commodity_tv.setText(commodity.getCommodity());
        market_tv.setText(commodity.getMarket());

        return view;
    }


    @Override
    public Filter getFilter() {
        return valueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }

    public void addResults(List<Commodity> results){
        if(null==results||results.isEmpty())
            return;
        commodities.addAll(results);
        filterList.addAll(results);
        notifyDataSetChanged();
    }
}