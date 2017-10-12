package com.baidoos.guitorio.blood4life.AreaAdmin;

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

public class AdmReqAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<AdmRequest> admRequests;

    public AdmReqAdapter(Activity activity, List<AdmRequest> admRequests){
        this.activity = activity;
        this.admRequests = admRequests;
    }


    @Override
    public int getCount() {
        return admRequests.size();
    }

    @Override
    public Object getItem(int position) {
        return admRequests.get(position);
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
            convertView = inflater.inflate(R.layout.requests_adm_list_item_row, null);
        }

        AdmRequest dt1 = admRequests.get(position); //new Student(id,name);

        TextView full_name = (TextView) convertView.findViewById(R.id.tvAdmName);
        TextView blood_group = (TextView) convertView.findViewById(R.id.tvAdmBloodGroup);
        TextView contact_no = (TextView) convertView.findViewById(R.id.tvAdmContact_no);

        full_name.setText(dt1.getFull_name());
        blood_group.setText(dt1.getBlood_group());
        contact_no.setText(dt1.getContact_no());

        return convertView;
    }
}