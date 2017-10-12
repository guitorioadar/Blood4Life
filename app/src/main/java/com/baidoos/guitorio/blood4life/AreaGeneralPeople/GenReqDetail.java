package com.baidoos.guitorio.blood4life.AreaGeneralPeople;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidoos.guitorio.blood4life.R;

public class GenReqDetail extends AppCompatActivity {

    private TextView genReqDetail_patient_name,genReqDetail_blood_group;

    private String full_name,blood_group,contact_no;

    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_req_detail);

        genReqDetail_patient_name = (TextView) findViewById(R.id.genReqDetail_patient_name);
        genReqDetail_blood_group = (TextView) findViewById(R.id.genReqDetail_blood_group);
        btnCall = (Button) findViewById(R.id.genReqDetail_btnCall);

        Intent intent = getIntent();

        full_name = intent.getExtras().getString("full_name");
        blood_group = intent.getExtras().getString("blood_group");
        contact_no = intent.getExtras().getString("contact_no");

        genReqDetail_patient_name.setText(full_name);
        genReqDetail_blood_group.setText(blood_group);


        btnCall.setText("Call to "+full_name);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dialPhone(contact_no);

                /*Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                if (ActivityCompat.checkSelfPermission(DonorDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);*/
            }
        });

    }

    private void dialPhone(String phnNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phnNumber)));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GenReqDetail.this, AreaGeneralPeople.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in, R.anim.slide_out);
        startActivity(intent, options.toBundle());
    }
}
