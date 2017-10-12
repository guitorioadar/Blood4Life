package com.baidoos.guitorio.blood4life.BloodDonorSearch;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidoos.guitorio.blood4life.R;

public class DonorDetail extends AppCompatActivity {

    private TextView tvDonorName;

    private String full_name, blood_group, country, division, district,contact_no;

    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_detail);

        tvDonorName = (TextView) findViewById(R.id.tvDonorName);

        btnCall = (Button) findViewById(R.id.btnCall);


        Intent intent = getIntent();

        full_name = intent.getExtras().getString("full_name");
        blood_group = intent.getExtras().getString("blood_group");
        contact_no = intent.getExtras().getString("contact_no");
        country = intent.getExtras().getString("country");
        division = intent.getExtras().getString("division");
        district = intent.getExtras().getString("district");

        //Toast.makeText(DonorDetail.this, full_name, Toast.LENGTH_SHORT).show();

        tvDonorName.setText(full_name);
        btnCall.setText("Call "+full_name);

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
        Intent intent = new Intent(DonorDetail.this,BloodDonors.class);
        intent.putExtra("blood_group",blood_group);
        intent.putExtra("country",country);
        intent.putExtra("division",division);
        intent.putExtra("district",district);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.slide_out,R.anim.slide_in);
        startActivity(intent,options.toBundle());
    }
}

