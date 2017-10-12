package com.baidoos.guitorio.blood4life.AddDonors;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddDonor extends AppCompatActivity {


    private EditText et_first_name,et_add_donor_weight,et_add_donor_contact_number,et_add_donor_permanent_address;

    private Spinner spn_add_blood_group,spn_add_donor_gender,spn_add_donor_country,spn_add_donor_division,spn_add_donor_district;

    private ArrayAdapter spnAdapterBloodGroup,spnAdapterGender,spnAdapterCountry,spnAdapterDivision,spnAdapterDistrict;

    private TextView datePicker_add_donor_date_of_birth;

    private String full_name,blood_group,gender,date_of_birth,contact_no,country,division,district,permanent_address,email,user_name,password,confirm_password,available_status,last_blood_donate;

    private Button btn_add_Blood_donor;

    private DatePickerDialog datePickerDialog;

    private String user_type = Config.USER_GENERAL_PEOPLE;

    private ProgressDialog progressdialoge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);
        setTitle("Add Blood BloodDonors");


        et_first_name = (EditText) findViewById(R.id.et_first_name);



        //et_add_donor_weight = (EditText) findViewById(R.id.et_add_donor_weight);
        et_add_donor_contact_number = (EditText) findViewById(R.id.et_add_donor_contact_number);
        et_add_donor_permanent_address = (EditText) findViewById(R.id.et_add_donor_permanent_address);

        spn_add_blood_group = (Spinner) findViewById(R.id.spn_add_blood_group);
        spn_add_donor_gender = (Spinner) findViewById(R.id.spn_add_donor_gender);
        spn_add_donor_country = (Spinner) findViewById(R.id.spn_add_donor_country);
        spn_add_donor_division = (Spinner) findViewById(R.id.spn_add_donor_division);
        spn_add_donor_district = (Spinner) findViewById(R.id.spn_add_donor_district);

        datePicker_add_donor_date_of_birth = (TextView) findViewById(R.id.datePicker_add_donor_date_of_birth);

        btn_add_Blood_donor = (Button) findViewById(R.id.btn_add_Blood_donor);




        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(AddDonor.this,R.array.blood_group,android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_add_blood_group.setAdapter(spnAdapterBloodGroup);

        spn_add_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterGeneralPeople.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(blood_group==parent.getItemAtPosition(0))
                    Toast.makeText(AddDonor.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================



        //================================ Gender =====================================

        spnAdapterGender = ArrayAdapter.createFromResource(AddDonor.this,R.array.gender,android.R.layout.simple_spinner_item);
        spnAdapterGender.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_add_donor_gender.setAdapter(spnAdapterGender);

        spn_add_donor_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddDonor.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Gender =====================================


        //================================ Country =============================

        spnAdapterCountry = ArrayAdapter.createFromResource(AddDonor.this,R.array.Country,android.R.layout.simple_spinner_item);
        spnAdapterCountry.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_add_donor_country.setAdapter(spnAdapterCountry);

        spn_add_donor_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddDonor.this, "Please Select Country...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Country =============================


        //================================ Division =============================

        spnAdapterDivision = ArrayAdapter.createFromResource(AddDonor.this,R.array.Divisions,android.R.layout.simple_spinner_item);
        spnAdapterDivision.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_add_donor_division.setAdapter(spnAdapterDivision);

        spn_add_donor_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                division = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddDonor.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================


        //================================ District =============================

        spnAdapterDistrict = ArrayAdapter.createFromResource(AddDonor.this,R.array.Districts,android.R.layout.simple_spinner_item);
        spnAdapterDistrict.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_add_donor_district.setAdapter(spnAdapterDistrict);

        spn_add_donor_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddDonor.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================




        //================================ Date of Birth ==============================

        datePicker_add_donor_date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddDonor.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                datePicker_add_donor_date_of_birth.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                            }
                        },mYear,mMonth,mDay);

                datePickerDialog.show();

            }
        });

        //================================ Date of Birth ==============================



        btn_add_Blood_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBloodDonor();
            }
        });


    }

    private void AddBloodDonor() {

        progressdialoge = new ProgressDialog(AddDonor.this);
        progressdialoge.setMessage("Loading....");

        progressdialoge.show();

        full_name = et_first_name.getText().toString();
        date_of_birth = datePicker_add_donor_date_of_birth.getText().toString();
        contact_no = et_add_donor_contact_number.getText().toString();
        permanent_address = et_add_donor_permanent_address.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_ADD_BLOOD_DONOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AddDonor.this, response, Toast.LENGTH_SHORT).show();
                        progressdialoge.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put("user_type",user_type);
                params.put("full_name",full_name);
                params.put("blood_group",blood_group);
                params.put("gender",gender);
                params.put("country",country);
                params.put("division",division);
                params.put("district",district);
                params.put("date_of_birth",date_of_birth);
                params.put("contact_no",contact_no);
                params.put("permanent_address",permanent_address);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddDonor.this);
        requestQueue.add(stringRequest);


    }


}
