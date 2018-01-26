package com.baidoos.guitorio.blood4life.Profiles;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.baidoos.guitorio.blood4life.R;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    private EditText etUserName, etUserFullName, etUserBloodGropu, etUserStatus, etUserEmail, etUserContact, etUserAddress;

    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        etUserEmail = (EditText) findViewById(R.id.et_user_email_id);
        etUserContact = (EditText) findViewById(R.id.et_user_contact_number);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);




        /*etUserName = (EditText) findViewById(R.id.et_user_user_name);
        etUserFullName = (EditText) findViewById(R.id.et_name_of_the_user);
        etUserBloodGropu = (EditText) findViewById(R.id.et_user_blood_group);
        etUserStatus = (EditText) findViewById(R.id.et_user_status);
        etUserEmail = (EditText) findViewById(R.id.et_user_email_id);
        etUserContact = (EditText) findViewById(R.id.et_user_contact_number);
        etUserAddress = (EditText) findViewById(R.id.et_user_address);*/

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPref", 0);

        etUserEmail.setHint(pref.getString("email",null));
        etUserContact.setHint(pref.getString("contact_no",null));


        /*etUserName.setText(pref.getString("user_name",null));
        etUserFullName.setText(pref.getString("full_name",null));
        etUserBloodGropu.setText(pref.getString("blood_group",null));
        etUserEmail.setText(pref.getString("email",null));
        etUserContact.setText(pref.getString("contact_no",null));
        etUserAddress.setText(pref.getString("permanent_address",null));
        etUserStatus.setText(pref.getString("available_status",null));*/


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest =  new StringRequest(Request.Method.POST, Config.URL_UPDATE_PROFILE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(EditProfile.this, "Success", Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(EditProfile.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String,String>();

                        params.put("user_name",pref.getString("user_name",null));
                        params.put("email",etUserEmail.getText().toString());
                        params.put("contact_no",etUserContact.getText().toString());

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(EditProfile.this);
                requestQueue.add(stringRequest);
            }
        });

    }
}
