package com.baidoos.guitorio.blood4life.AreaMember;

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

public class MemReqAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<MemRequest> memRequests;

    public MemReqAdapter(Activity activity, List<MemRequest> memRequests){
        this.activity = activity;
        this.memRequests = memRequests;
    }


    @Override
    public int getCount() {
        return memRequests.size();
    }

    @Override
    public Object getItem(int position) {
        return memRequests.get(position);
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
            convertView = inflater.inflate(R.layout.requests_mem_list_item_row, null);
        }

        MemRequest dt1 = memRequests.get(position); //new Student(id,name);

        TextView full_name = (TextView) convertView.findViewById(R.id.tvMemName);
        TextView blood_group = (TextView) convertView.findViewById(R.id.tvMemBloodGroup);
        TextView contact_no = (TextView) convertView.findViewById(R.id.tvMemContact_no);

        full_name.setText(dt1.getFull_name());
        blood_group.setText(dt1.getBlood_group());
        contact_no.setText(dt1.getContact_no());

        return convertView;
    }
}