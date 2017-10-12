package com.baidoos.guitorio.blood4life.AreaGeneralPeople;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidoos.guitorio.blood4life.R;

import java.util.List;

/**
 * Created by ADAR on 10/11/2017.
 */

public class GenReqAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<GenRequest> genRequests;

    public GenReqAdapter(Activity activity, List<GenRequest> genRequests){
        this.activity = activity;
        this.genRequests = genRequests;
    }


    @Override
    public int getCount() {
        return genRequests.size();
    }

    @Override
    public Object getItem(int position) {
        return genRequests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.requests_gen_list_item_row, null);
        }

        GenRequest dt1 = genRequests.get(position); //new Student(id,name);

        TextView full_name = (TextView) convertView.findViewById(R.id.tvGenName);
        TextView blood_group = (TextView) convertView.findViewById(R.id.tvGenBloodGroup);
        TextView contact_no = (TextView) convertView.findViewById(R.id.tvGenContact_no);

        full_name.setText(dt1.getFull_name());
        blood_group.setText(dt1.getBlood_group());
        contact_no.setText(dt1.getContact_no());

        return convertView;
    }
}
