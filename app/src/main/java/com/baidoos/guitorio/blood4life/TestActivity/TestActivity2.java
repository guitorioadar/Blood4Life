package com.baidoos.guitorio.blood4life.TestActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidoos.guitorio.blood4life.R;

public class TestActivity2 extends AppCompatActivity {

    private String string1,string2;

    private TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        string1 = intent.getExtras().getString("string1").toString();
        string2 = intent.getExtras().getString("string2").toString();

        textView1.setText(string1);
        textView2.setText(string2);

    }
}
