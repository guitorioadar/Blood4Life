package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidoos.guitorio.blood4life.R;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void btnGeneralPeople(View view) {
        Intent i = new Intent(this,RegisterGeneralPeople.class);
        startActivity(i);
    }

    public void btnMember(View view) {

        Intent j = new Intent(this,RegisterMember.class);
        startActivity(j);

    }

    public void btnAdmin(View view) {
        Intent k = new Intent(this,RegisterAdmin.class);
        startActivity(k);
    }
}

