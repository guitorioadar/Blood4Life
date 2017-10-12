package com.baidoos.guitorio.blood4life.TestActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidoos.guitorio.blood4life.AreaGeneralPeople.AreaGeneralPeople;
import com.baidoos.guitorio.blood4life.R;

import static android.R.attr.button;

public class TestActivity extends AppCompatActivity {

    private String string1,string2;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("Test Activity");

        string1 = "String 1";
        string2 = "String 2";

        button = (Button) findViewById(R.id.btntextParse);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this,AreaGeneralPeople.class);
                /*intent.putExtra("string1",string1);
                intent.putExtra("string2",string2);*/
                startActivity(intent);
            }
        });

    }

}
