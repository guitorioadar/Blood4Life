package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.BloodDonorSearch.Donor;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.MySingleton;
import com.baidoos.guitorio.blood4life.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationInitial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_initial);
        setTitle("RegistrationInitial");


    }

    public void OwnAccount(View view) {
        //Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,RegisterGeneralPeople.class);
        startActivity(intent);
    }

    public void RegisterOrganisation(View view) {
        Intent intent = new Intent(this,RegisterOrganization.class);
        startActivity(intent);
    }

    public void BecomeAMemberofTheOrg(View view) {
        /*Intent intent = new Intent(this,RegisterAsMemberOfOrg.class);
        startActivity(intent);*/


        final AlertDialog.Builder get_org_name = new AlertDialog.Builder(RegistrationInitial.this);
        final EditText et_get_org_unique_id = new EditText(RegistrationInitial.this);
        et_get_org_unique_id.setInputType(InputType.TYPE_CLASS_NUMBER);

        get_org_name.setTitle("Organization Id")
                .setMessage("Type the unique organization id which you want to be a member")
                .setView(et_get_org_unique_id)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        StringRequest stringRequest = new StringRequest(

                                Request.Method.POST,
                                Config.URL_ORG_NAME_GET,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {


                                        if (response.equals("No organisation is registered with that Id. Please try again")) {

                                            Toast.makeText(RegistrationInitial.this, response, Toast.LENGTH_SHORT).show();

                                        } else if (response.equals("Something went wrong... please try after sometimes or Contact with us")) {

                                            Toast.makeText(RegistrationInitial.this, response, Toast.LENGTH_SHORT).show();

                                        } else {


                                            //Toast.makeText(RegistrationInitial.this, response, Toast.LENGTH_SHORT).show();

                                            JSONObject jsonObject = null;

                                            try {

                                                jsonObject = new JSONObject(response);
                                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);

                                                    String org_name_json = object.getString("org_name");

                                                    //Toast.makeText(RegistrationInitial.this, org_name_json.toString(), Toast.LENGTH_SHORT).show();

                                                    AlertDialog.Builder org_name = new AlertDialog.Builder(RegistrationInitial.this);
                                                    org_name.setTitle(Html.fromHtml("** "+org_name_json+" **"+"<font color='#f16f7d'> is your Organisation?</font>"))
                                                            .setMessage("If the above is your organisation name then press yes...")
                                                            .setPositiveButton("Yes",

                                                                    new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            Intent intent = new Intent(RegistrationInitial.this, RegisterMember.class);
                                                                            startActivity(intent);
                                                                        }
                                                                    }

                                                            )
                                                            .setNegativeButton("No", null)
                                                            .show();

                                                    //builderCooking.show();

                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(RegistrationInitial.this, "Ki j hoy"+error.toString(), Toast.LENGTH_SHORT).show();

                                    }
                                }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();

                                params.put("org_unique_id",et_get_org_unique_id.getText().toString());

                                return params;
                            }
                        };

//                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

                        RequestQueue requestQueue = Volley.newRequestQueue(RegistrationInitial.this);
                        requestQueue.add(stringRequest);


                    }
                })
                .setNegativeButton("No", null)
                .show();


    }
}
