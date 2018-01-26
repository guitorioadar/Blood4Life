package com.baidoos.guitorio.blood4life.AreaGeneralPeople;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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

public class AreaGeneralPeople extends AppCompatActivity {


    private Button btnGenBloodDonorSearch,btnGenBloodRequest,btnGenAddBloodDonor;
    private String active_user;

    private ListView listView;
    private List<GenRequest> requestList;
    private GenReqAdapter genReqAdapter;

    private JSONArray jsonArray = null;

    private final String JSON_ARRAY = "result";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_general_people);
        setTitle("General People");

        requestList = new ArrayList<GenRequest>();

        listView = (ListView) findViewById(R.id.listViewGeneral);



        /*Intent intent = getIntent();
        active_user = intent.getExtras().getString("active_user");
        Toast.makeText(AreaGeneralPeople.this, active_user, Toast.LENGTH_SHORT).show();*/

        //Toast.makeText(AreaGeneralPeople.this, Config.ACTIVE_USER, Toast.LENGTH_SHORT).show();


        btnGenBloodDonorSearch = (Button) findViewById(R.id.btn_Gen_Blood_Donor_Search);
        //btnGenBloodRequest = (Button) findViewById(R.id.btn_Gen_Blood_Request);
        btnGenAddBloodDonor = (Button) findViewById(R.id.btn_Gen_Add_Blood_Donor);


        btnGenBloodDonorSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnGenBloodDonorSearch = new Intent(AreaGeneralPeople.this, BloodDonorSearch.class);
                startActivity(intentbtnGenBloodDonorSearch);
            }
        });

        /*btnGenBloodRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnGenBloodRequest = new Intent(AreaGeneralPeople.this, BloodRequestsCreate.class);
                startActivity(intentbtnGenBloodRequest);
            }
        });*/

        btnGenAddBloodDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtnGenAddBloodDonor = new Intent(AreaGeneralPeople.this, AddDonor.class);
                startActivity(intentbtnGenAddBloodDonor);
            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_GEN_BLOOD_REQUEST_VIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //Toast.makeText(AreaGeneralPeople.this, response.toString(), Toast.LENGTH_LONG).show();

                        JSONObject jsonObject = null;

                        try{

                            jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray(JSON_ARRAY);
                            for(int i = 0; i < jsonArray.length(); i++ ){
                                JSONObject object = jsonArray.getJSONObject(i);
                                GenRequest request = new GenRequest();
                                request.setFull_name(object.getString("patient_name"));
                                request.setBlood_group(object.getString("blood_group"));
                                request.setContact_no(object.getString("contact_number"));
                                requestList.add(request);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        genReqAdapter = new GenReqAdapter(AreaGeneralPeople.this,requestList);
                        listView.setAdapter(genReqAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AreaGeneralPeople.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(AreaGeneralPeople.this);
        requestQueue.add(stringRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AreaGeneralPeople.this,GenReqDetail.class);

                GenRequest rq  = requestList.get(position);
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

    private void showGeneralBloodRequest() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.menu_add_blood_request).setTitle(Html.fromHtml("<font color='#ff3824'>Add Blood Request</font>"));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_add_blood_request) {

            //Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
            //return true;

            Intent intentbtnGenBloodRequest = new Intent(AreaGeneralPeople.this, BloodRequestsCreate.class);
            startActivity(intentbtnGenBloodRequest);

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
