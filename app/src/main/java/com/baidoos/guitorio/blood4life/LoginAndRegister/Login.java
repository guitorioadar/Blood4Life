package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.baidoos.guitorio.blood4life.Profiles.ProfileUserDetailPOJO;
import com.baidoos.guitorio.blood4life.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private final String USER_GENERAL_PEOPLE = "General People";
    private final String USER_ADMIN = "Admin";
    private final String USER_MEMBER = "Member";




    private String user_name,password;


    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button btnLogin;



    private ProgressDialog progressDialog;


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

                //Toast.makeText(Login.this, user_name+","+password, Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressdialoge.dismiss();

                                //Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();

                                ProfileUserDetailPOJO profileUserDetailPOJO = new ProfileUserDetailPOJO();

                                JSONObject jsonObject = null;

                                try{

                                    jsonObject = new JSONObject(response);
                                    JSONArray array  = jsonObject.getJSONArray("result");

                                    for (int i = 0; i < array.length(); i++)
                                    {
                                        JSONObject object = array.getJSONObject(i);

                                        //String type = object.getString("user_type");

                                        //Toast.makeText(Login.this, type, Toast.LENGTH_SHORT).show();



                                        profileUserDetailPOJO.setUser_typre(object.getString("user_type"));
                                        profileUserDetailPOJO.setAvailable_status(object.getString("available_status"));
                                        profileUserDetailPOJO.setName_of_the_user(object.getString("full_name"));
                                        profileUserDetailPOJO.setUsername(object.getString("user_name"));
                                        profileUserDetailPOJO.setUser_email(object.getString("email"));
                                        profileUserDetailPOJO.setUser_contact(object.getString("contact_no"));
                                        profileUserDetailPOJO.setUser_blood_group(object.getString("blood_group"));
                                        profileUserDetailPOJO.setUser_address(object.getString("permanent_address"));

                                        //Toast.makeText(Login.this, profileUserDetailPOJO.getUser_typre(), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(Login.this, profileUserDetailPOJO.getUser_email(), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(Login.this, profileUserDetailPOJO.getName_of_the_user(), Toast.LENGTH_SHORT).show();

                                        SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPref", 0); // 0 - for private mode

                                        SharedPreferences.Editor editor = pref.edit();

                                        editor.clear();

                                        editor.putString("user_type", profileUserDetailPOJO.getUser_typre());
                                        editor.putString("available_status", profileUserDetailPOJO.getAvailable_status());
                                        editor.putString("user_name", profileUserDetailPOJO.getUsername());
                                        editor.putString("email", profileUserDetailPOJO.getUser_email());
                                        editor.putString("contact_no", profileUserDetailPOJO.getUser_contact());
                                        editor.putString("blood_group", profileUserDetailPOJO.getUser_blood_group());
                                        editor.putString("permanent_address", profileUserDetailPOJO.getUser_address());
                                        editor.putString("full_name", profileUserDetailPOJO.getName_of_the_user());

                                        editor.putString("LoggedIn","LoggedIn");

                                        editor.commit();




                                        /*SharedPreferences secondPref = getApplicationContext().getSharedPreferences("secondPref", Context.MODE_PRIVATE);

                                        SharedPreferences.Editor secondeditor = secondPref.edit();
                                        secondeditor.putString("secondPref","Second Pref Name");

                                        secondeditor.commit();*/



                                    }

                                    /*jsonObject = new JSONObject(response);

                                    JSONArray jsonArray = jsonObject.getJSONArray("result");

                                    JSONObject object = jsonArray.getJSONObject(0);

                                    String type = object.getString("user_type");

                                    Toast.makeText(Login.this, type, Toast.LENGTH_SHORT).show();*/



                                }catch (JSONException e){
                                    Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }

                                //Toast.makeText(RegistrationInitial.this, org_name_json.toString(), Toast.LENGTH_SHORT).show();

                                /*Intent intent = new Intent(Login.this,TestActivity.class);
                                startActivity(intent);*/

                                if (profileUserDetailPOJO.getUser_typre().equals(USER_GENERAL_PEOPLE)){
                                    Intent intentGen = new Intent(Login.this, AreaGeneralPeople.class);
                                    //intentGen.putExtra("active_user",USER_GENERAL_PEOPLE);
                                    Config.ACTIVE_USER = USER_GENERAL_PEOPLE;
                                    startActivity(intentGen);

                                }else if (profileUserDetailPOJO.getUser_typre().equals(USER_ADMIN)){
                                    Intent intentAdm = new Intent(Login.this, AreaAdmin.class);
                                    //intentAdm.putExtra("active_user",USER_ADMIN);
                                    Config.ACTIVE_USER = USER_ADMIN;
                                    startActivity(intentAdm);
                                }else if (profileUserDetailPOJO.getUser_typre().equals(USER_MEMBER)){
                                    Intent intentMem = new Intent(Login.this, AreaMember.class);
                                    //intentMem.putExtra("active_user",USER_MEMBER);
                                    Config.ACTIVE_USER = USER_MEMBER;
                                    startActivity(intentMem);
                                }else{
                                    Toast.makeText(Login.this, "Please try again", Toast.LENGTH_SHORT).show();
                                    progressdialoge.dismiss();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressdialoge.dismiss();
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                        //error.printStackTrace();
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

    public void SignUpButton(View view) {

        startActivity(new Intent(this,RegistrationInitial.class));

    }

    public void tvForgetPassword(View view) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        final EditText et_get_email = new EditText(this);
        et_get_email.setInputType(InputType.TYPE_CLASS_TEXT);

        alertBuilder.setTitle("Recover your password")
                    .setMessage("Write down your mail id and check your email")
                    .setView(et_get_email)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        /*if(Pattern.matches(null,et_get_email.getText().toString())){
                            Toast.makeText(Login.this, "Mathces", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }*/

                        progressDialog = new ProgressDialog(Login.this);
                        progressDialog.setMessage("Sending Email");
                        progressDialog.setCancelable(false);
                        progressDialog.show();



                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SEND_EMAIL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        progressDialog.dismiss();

                                        final String s = response.substring(response.length()-21,response.length());

                                        if(s.equals("Message has been sent")){
                                            Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                                        }else {

                                            Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();

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
                                Map<String,String> params = new HashMap<String,String>();

                                params.put("user_mail",et_get_email.getText().toString());

                                return params;
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                        requestQueue.add(stringRequest);

                    }
                })
                .setNegativeButton("Cancle", null)
                .show();


    }
}

