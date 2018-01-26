package com.baidoos.guitorio.blood4life.AreaMember;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.AddDonors.AddDonor;
import com.baidoos.guitorio.blood4life.BloodDonorSearch.BloodDonorSearch;
import com.baidoos.guitorio.blood4life.BloodRequest.BloodRequestsCreate;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.MainActivity;
import com.baidoos.guitorio.blood4life.Profiles.ProfileUsers;
import com.baidoos.guitorio.blood4life.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AreaMember extends AppCompatActivity {

    private Button btnMemBloodDonorSearch,btnMemBloodRequest,btnMemAddBloodDonor;
    private String active_user;

    private ListView listView;
    private List<MemRequest> requestList;
    private MemReqAdapter memReqAdapter;

    private JSONArray jsonArray = null;

    private final String JSON_ARRAY = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_member);
        setTitle("Member");


        requestList = new ArrayList<MemRequest>();

        listView = (ListView) findViewById(R.id.listViewMember);

        /*Intent intent = getIntent();
        active_user = intent.getExtras().getString("active_user");
        Toast.makeText(AreaMember.this, active_user, Toast.LENGTH_SHORT).show();*/


        Toast.makeText(AreaMember.this, Config.ACTIVE_USER, Toast.LENGTH_SHORT).show();


        btnMemBloodDonorSearch = (Button) findViewById(R.id.btn_Mem_Blood_Donor_Search);
        btnMemBloodRequest = (Button) findViewById(R.id.btn_Mem_Blood_Request);
        btnMemAddBloodDonor = (Button) findViewById(R.id.btn_Mem_Add_Blood_Donor);


        btnMemBloodDonorSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnMemBloodDonorSearch = new Intent(AreaMember.this, BloodDonorSearch.class);
                startActivity(intentbtnMemBloodDonorSearch);
            }
        });

        btnMemBloodRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnMemBloodRequest = new Intent(AreaMember.this, BloodRequestsCreate.class);
                startActivity(intentbtnMemBloodRequest);
            }
        });

        btnMemAddBloodDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnMemAddBloodDonor = new Intent(AreaMember.this, AddDonor.class);
                startActivity(intentbtnMemAddBloodDonor);
            }
        });



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_GEN_BLOOD_REQUEST_VIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //Toast.makeText(AreaMemeralPeople.this, response.toString(), Toast.LENGTH_LONG).show();

                        JSONObject jsonObject = null;

                        try{

                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                            for(int i = 0; i < jsonArray.length(); i++ ){
                                JSONObject object = jsonArray.getJSONObject(i);
                                MemRequest request = new MemRequest();
                                request.setFull_name(object.getString("patient_name"));
                                request.setBlood_group(object.getString("blood_group"));
                                request.setContact_no(object.getString("contact_number"));
                                requestList.add(request);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        memReqAdapter = new MemReqAdapter(AreaMember.this,requestList);
                        listView.setAdapter(memReqAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AreaMember.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(AreaMember.this);
        requestQueue.add(stringRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AreaMember.this,MemReqDetail.class);

                MemRequest rq  = requestList.get(position);
                //Toast.makeText(BloodDonors.this, me.getFull_name(), Toast.LENGTH_SHORT).show();
                intent.putExtra("full_name",rq.getFull_name());
                intent.putExtra("blood_group",rq.getBlood_group());
                intent.putExtra("contact_no",rq.getContact_no());
                /*intent.putExtra("country",country);
                intent.putExtra("division",division);
                intent.putExtra("district",district);*/

                //overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out);
                startActivity(intent, options.toBundle());

                //startActivity(inten);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_add_blood_request){
            startActivity(new Intent(AreaMember.this,BloodRequestsCreate.class));
        }if (id == R.id.userProfile){
            startActivity(new Intent(this, ProfileUsers.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
