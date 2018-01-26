package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.Profiles.ProfileUsers;
import com.baidoos.guitorio.blood4life.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterOrganisationDetail extends AppCompatActivity {


    private TextView tv_org_admin_org_name;

    private EditText et_org_admin_Full_Name,
            et_org_admin_user_name,
            et_org_admin_password,
            et_org_admin_confirm_password,
            et_org_admin_contact_no,

            et_org_email,
            et_org_alternative_contact_no,
            et_org_Main_address,
            et_org_objective,
            et_org_activities;

    private Button tv_org_admin_org_register_org;

    private Spinner spn_org_country;

    private ArrayAdapter spnAdapterCountry;

    private String country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organisation_detail);
        setTitle("RegisterOrganisationDetail");


        et_org_admin_Full_Name = (EditText) findViewById(R.id.et_org_admin_Full_Name);
        et_org_admin_user_name = (EditText) findViewById(R.id.et_org_admin_user_name);
        et_org_admin_password = (EditText) findViewById(R.id.et_org_admin_password);
        et_org_admin_confirm_password = (EditText) findViewById(R.id.et_org_admin_confirm_password);
        et_org_admin_contact_no = (EditText) findViewById(R.id.et_org_admin_contact_no);


        et_org_email = (EditText) findViewById(R.id.et_org_email);
        et_org_alternative_contact_no = (EditText) findViewById(R.id.et_org_alternative_contact_no);
        et_org_Main_address = (EditText) findViewById(R.id.et_org_Main_address);
        et_org_objective = (EditText) findViewById(R.id.et_org_objective);
        et_org_activities = (EditText) findViewById(R.id.et_org_activities);

        tv_org_admin_org_name = (TextView) findViewById(R.id.tv_org_admin_org_name);

        spn_org_country = (Spinner) findViewById(R.id.spn_org_country);


        Intent intent = getIntent();
        String org_name = intent.getStringExtra("org_name");

        Toast.makeText(this, org_name, Toast.LENGTH_SHORT).show();

        tv_org_admin_org_name.setText(org_name);

        //================================ Country =============================

        spnAdapterCountry = ArrayAdapter.createFromResource(RegisterOrganisationDetail.this,R.array.Country,android.R.layout.simple_spinner_item);
        spnAdapterCountry.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_org_country.setAdapter(spnAdapterCountry);

        spn_org_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterOrganisationDetail.this, "Please Select Country...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Country =============================


    }

    public void RegisterOrganisationButton(View view) {

        //startActivity(new Intent(this, ProfileUsers.class));


        if (et_org_admin_Full_Name.getText().toString().isEmpty()) {
            Toast.makeText(RegisterOrganisationDetail.this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }  else if (et_org_admin_user_name.getText().toString().isEmpty()) {
            Toast.makeText(RegisterOrganisationDetail.this, "Enter Username...", Toast.LENGTH_SHORT).show();
        } else if (et_org_admin_contact_no.getText().toString().isEmpty()) {
            Toast.makeText(RegisterOrganisationDetail.this, "Please Add Contact Number", Toast.LENGTH_SHORT).show();
        }else if (!et_org_admin_password.getText().toString().equals(et_org_admin_confirm_password.getText().toString())) {
            Toast.makeText(RegisterOrganisationDetail.this, "Password doesn't match...", Toast.LENGTH_SHORT).show();
        } else if (et_org_email.getText().toString().isEmpty() || Patterns.EMAIL_ADDRESS.matcher(et_org_email.getText().toString()).matches()) {
            Toast.makeText(RegisterOrganisationDetail.this, "Invalid Email...", Toast.LENGTH_SHORT).show();
        }else if (country.equals("Select Country")) {
            Toast.makeText(RegisterOrganisationDetail.this, "Please your country...", Toast.LENGTH_SHORT).show();
        }  else if (et_org_Main_address.getText().toString().isEmpty()) {
            Toast.makeText(RegisterOrganisationDetail.this, "Please insert main Address...", Toast.LENGTH_SHORT).show();
        }    else {


            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Config.URL_ORG_REGISTRATION_DETAIL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //Toast.makeText(RegisterOrganisationDetail.this, response, Toast.LENGTH_SHORT).show();
                            if(response.equals("Success")){
                                startActivity(new Intent(getApplicationContext(),Login.class));
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("org_name",tv_org_admin_org_name.getText().toString());
                    params.put("org_email",et_org_email.getText().toString());
                    params.put("org_admin_full_name",et_org_admin_Full_Name.getText().toString());
                    params.put("org_admin_user_name",et_org_admin_user_name.getText().toString());
                    params.put("org_admin_password",et_org_admin_password.getText().toString());
                    params.put("org_admin_contact_no",et_org_admin_contact_no.getText().toString());
                    params.put("org_alternative_contact_no",et_org_alternative_contact_no.getText().toString());
                    params.put("org_main_address",et_org_Main_address.getText().toString());
                    params.put("org_country",country);
                    params.put("org_objective",et_org_objective.getText().toString());
                    params.put("org_activities",et_org_activities.getText().toString());


                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }
}
