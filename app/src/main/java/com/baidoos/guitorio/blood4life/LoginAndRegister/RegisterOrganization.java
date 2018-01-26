package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterOrganization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organization);
        setTitle("RegisterOrganization");



    }

    public void GotToOrganisationDetail(View view) {

        final EditText editText = (EditText) findViewById(R.id.et_org_name);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Config.URL_ORG_NAME_CHECK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("Success")){

                            Intent intent = new Intent(RegisterOrganization.this,RegisterOrganisationDetail.class);
                            intent.putExtra("org_name",editText.getText().toString());
                            startActivity(intent);

                        }else{
                            Toast.makeText(RegisterOrganization.this, "Already Exist. Choose another name", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("org_name",editText.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
