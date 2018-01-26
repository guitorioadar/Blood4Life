package com.baidoos.guitorio.blood4life;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidoos.guitorio.blood4life.LoginAndRegister.Login;
import com.baidoos.guitorio.blood4life.LoginAndRegister.Registration;
import com.baidoos.guitorio.blood4life.LoginAndRegister.RegistrationInitial;
import com.baidoos.guitorio.blood4life.Profiles.ProfileUsers;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegistration,testButton;

    private String loginUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);

        /*testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AreaMember.class);
                startActivity(i);
            }
        });*/

        /*SharedPreferences secondPref = getApplicationContext().getSharedPreferences("secondPref", Context.MODE_PRIVATE);
        Toast.makeText(this, secondPref.getString("secondPref",null), Toast.LENGTH_SHORT).show();*/


    }


    public void btnLoginMain(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


    public void btnRegistrationMain(View view) {
        Intent i = new Intent(this, RegistrationInitial.class);
        startActivity(i);
    }


    /*public void btnTestActivity(View view) {
        Intent i = new Intent(this, AddDonor.class);
        startActivity(i);
    }*/

}
