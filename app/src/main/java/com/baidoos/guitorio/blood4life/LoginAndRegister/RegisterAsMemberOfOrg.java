package com.baidoos.guitorio.blood4life.LoginAndRegister;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidoos.guitorio.blood4life.R;

public class RegisterAsMemberOfOrg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_member_of_org);

        Button button_cook = (Button) findViewById(R.id.button_cook);

        button_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder button_cook_action = new AlertDialog.Builder(RegisterAsMemberOfOrg.this);
                final EditText cookMl = new EditText(RegisterAsMemberOfOrg.this);
                cookMl.setInputType(InputType.TYPE_CLASS_NUMBER);

                button_cook_action.setTitle("kitchen_recipe_button_cook")
                        .setMessage("kitchen_recipe_button_cook_volume")
                        .setView(cookMl)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builderCooking = new AlertDialog.Builder(RegisterAsMemberOfOrg.this);
                                builderCooking.setTitle(cookMl.getText().toString())
                                        .setMessage("message");
                                builderCooking.show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


    }
}
