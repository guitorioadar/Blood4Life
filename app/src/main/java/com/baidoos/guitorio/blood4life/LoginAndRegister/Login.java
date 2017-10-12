package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.AreaAdmin.AreaAdmin;
import com.baidoos.guitorio.blood4life.AreaGeneralPeople.AreaGeneralPeople;
import com.baidoos.guitorio.blood4life.AreaMember.AreaMember;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.MainActivity;
import com.baidoos.guitorio.blood4life.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private final String USER_GENERAL_PEOPLE = "General People";
    private final String USER_ADMIN = "Admin";
    private final String USER_MEMBER = "Member";




    private String user_name,password;


    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.et_user_name);
        editTextPassword = (EditText) findViewById(R.id.et_password);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        final ProgressDialog progressdialoge = new ProgressDialog(Login.this);
        progressdialoge.setMessage("Loading....");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_name = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();

                progressdialoge.show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();

                                /*Intent intent = new Intent(Login.this,TestActivity.class);
                                startActivity(intent);*/

                                if (response.equals(USER_GENERAL_PEOPLE)){
                                    Intent intentGen = new Intent(Login.this, AreaGeneralPeople.class);
                                    //intentGen.putExtra("active_user",USER_GENERAL_PEOPLE);
                                    Config.ACTIVE_USER = USER_GENERAL_PEOPLE;
                                    startActivity(intentGen);
                                    progressdialoge.dismiss();
                                }else if (response.equals(USER_ADMIN)){
                                    Intent intentAdm = new Intent(Login.this, AreaAdmin.class);
                                    //intentAdm.putExtra("active_user",USER_ADMIN);
                                    Config.ACTIVE_USER = USER_ADMIN;
                                    startActivity(intentAdm);
                                    progressdialoge.dismiss();
                                }else if (response.equals(USER_MEMBER)){
                                    Intent intentMem = new Intent(Login.this, AreaMember.class);
                                    //intentMem.putExtra("active_user",USER_MEMBER);
                                    Config.ACTIVE_USER = USER_MEMBER;
                                    startActivity(intentMem);
                                    progressdialoge.dismiss();
                                }else{
                                    Toast.makeText(Login.this, "Please try again", Toast.LENGTH_SHORT).show();
                                    progressdialoge.dismiss();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_name",user_name);
                        params.put("password",password);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);


            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

