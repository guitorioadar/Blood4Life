package com.baidoos.guitorio.blood4life.Profiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidoos.guitorio.blood4life.LoginAndRegister.Login;
import com.baidoos.guitorio.blood4life.R;

public class ProfileUsers extends AppCompatActivity {

    private ProfileUserDetailPOJO profileUserDetailPOJO;


    private TextView tvUserName, tvUserFullName, tvUserBloodGropu, tvUserStatus, tvUserEmail, tvUserContact, tvUserAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_users);



        tvUserName = (TextView) findViewById(R.id.tv_user_user_name);
        tvUserFullName = (TextView) findViewById(R.id.tv_name_of_the_user);
        tvUserBloodGropu = (TextView) findViewById(R.id.tv_user_blood_group);
        tvUserStatus = (TextView) findViewById(R.id.tv_user_status);
        tvUserEmail = (TextView) findViewById(R.id.tv_user_email_id);
        tvUserContact = (TextView) findViewById(R.id.tv_user_contact_number);
        tvUserAddress = (TextView) findViewById(R.id.tv_user_address);

        profileUserDetailPOJO = new ProfileUserDetailPOJO();

        //Toast.makeText(this, profileUserDetailPOJO.getUser_address(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, profileUserDetailPOJO.getUser_email(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, profileUserDetailPOJO.getName_of_the_user(), Toast.LENGTH_SHORT).show();

        /*tvUserName.setText(profileUserDetailPOJO.getUsername());
        tvUserFullName.setText(profileUserDetailPOJO.getName_of_the_user());
        tvUserBloodGropu.setText(profileUserDetailPOJO.getUser_blood_group());
        tvUserStatus.setText(profileUserDetailPOJO.getAvailable_status());
        tvUserEmail.setText(profileUserDetailPOJO.getUser_email());
        tvUserContact.setText(profileUserDetailPOJO.getUser_email());
        tvUserAddress.setText(profileUserDetailPOJO.getUser_address());*/

        SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPref", 0);


        tvUserName.setText(pref.getString("user_name",null));
        tvUserFullName.setText(pref.getString("full_name",null));
        tvUserBloodGropu.setText(pref.getString("blood_group",null));
        tvUserEmail.setText(pref.getString("email",null));
        tvUserContact.setText(pref.getString("contact_no",null));
        tvUserAddress.setText(pref.getString("permanent_address",null));
        tvUserStatus.setText(pref.getString("available_status",null));



        /*SharedPreferences secondPref = getApplicationContext().getSharedPreferences("secondPref", Context.MODE_PRIVATE);

        tvUserEmail.setText(secondPref.getString("secondPref",null));*/


        //Toast.makeText(this, pref.getString("user_type",null), Toast.LENGTH_SHORT).show();


    }

    public void editProfile(View view) {

        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();*/

        startActivity(new Intent(this,EditProfile.class));

    }

    public void singOut(View view) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("UserPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        startActivity(new Intent(ProfileUsers.this, Login.class));

    }
}
