package com.baidoos.guitorio.blood4life.BloodDonorSearch;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodDonors extends AppCompatActivity {

    private ListView listView;
    private List<Donor> donorList;
    private DonorAdapter donorAdapter;

    private JSONArray jsonArray = null;

    private String blood_group, country, division, district;

    public static final String FULL_NAME = "full_name";
    public static final String BLOOD_GROUP = "blood_group";
    public static final String JSON_ARRAY = "result";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donors);
        setTitle("Blood Donors");

        /*progressDialog = new ProgressDialog(BloodDonors.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();*/

        Toast.makeText(BloodDonors.this, Config.ACTIVE_USER, Toast.LENGTH_SHORT).show();

        donorList = new ArrayList<Donor>();

        listView = (ListView) findViewById(R.id.listViewBLoodDonors);

        final Intent intent = getIntent();
        blood_group = intent.getExtras().getString("blood_group").toString();
        country = intent.getExtras().getString("country").toString();
        division = intent.getExtras().getString("division").toString();
        district = intent.getExtras().getString("district").toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SEARCH_BLOOD_DONOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //Snackbar.make(rootLayout,response.toString(),Snackbar.LENGTH_LONG).show();
                        //Toast.makeText(BloodDonors.this, response.toString(), Toast.LENGTH_SHORT).show();

                        //progressDialog.dismiss();

                        JSONObject jsonObject = null;

                        try {

                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                            for(int i = 0; i < jsonArray.length(); i++ ){
                                JSONObject object = jsonArray.getJSONObject(i);
                                Donor donor = new Donor();
                                donor.setFull_name(object.getString("full_name"));
                                donor.setBlood_group(object.getString("blood_group"));
                                donor.setContact_no(object.getString("contact_no"));
                                donorList.add(donor);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        donorAdapter = new DonorAdapter(BloodDonors.this,donorList);
                        listView.setAdapter(donorAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //progressDialog.dismiss();

                Toast.makeText(BloodDonors.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("blood_group", blood_group);
                params.put("country", country);
                params.put("division", division);
                params.put("district", district);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BloodDonors.this);
        requestQueue.add(stringRequest);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BloodDonors.this,DonorDetail.class);

                Donor me  = donorList.get(position);
                //Toast.makeText(BloodDonors.this, me.getFull_name(), Toast.LENGTH_SHORT).show();
                intent.putExtra("full_name",me.getFull_name());
                intent.putExtra("blood_group",me.getBlood_group());
                intent.putExtra("contact_no",me.getContact_no());
                intent.putExtra("country",country);
                intent.putExtra("division",division);
                intent.putExtra("district",district);

                //overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out);
                startActivity(intent, options.toBundle());

                //startActivity(inten);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BloodDonors.this,BloodDonorSearch.class);
        startActivity(intent);
    }
}



