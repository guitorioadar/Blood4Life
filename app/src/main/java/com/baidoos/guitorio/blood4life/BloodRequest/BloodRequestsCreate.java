package com.baidoos.guitorio.blood4life.BloodRequest;

import android.app.DatePickerDialog;
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

public class BloodRequestsCreate extends AppCompatActivity {


    private TextView tv_blood_req_date_of_donation;

    private EditText et_blood_req_patient_name,et_blood_req_reason,et_blood_req_contact_number,et_blood_req_relation,et_blood_req_quantity_bag,et_blood_req_already_managed_quantity_bag;

    private Spinner spn_blood_req_gender,spn_blood_req_blood_group,spn_blood_req_country,spn_blood_req_division,spn_blood_req_district;

    private ArrayAdapter spnAdapterBloodGroup,spnAdapterGender,spnAdapterCountry,spnAdapterDivision,spnAdapterDistrict;

    private Button btn_blood_req_submit_req;

    private DatePickerDialog datePickerDialog;

    private String date_of_donation,patient_name,gender,blood_group,blood_req_reason,contact_number,relation,quantity_bag,managed_quantity_bag,country,division,district;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_requests_create);
        setTitle("Make a Blood Request");


        tv_blood_req_date_of_donation = (TextView) findViewById(R.id.tv_blood_req_date_of_donation);


        et_blood_req_patient_name = (EditText) findViewById(R.id.et_blood_req_patient_name);
        et_blood_req_reason = (EditText) findViewById(R.id.et_blood_req_reason);
        et_blood_req_contact_number = (EditText) findViewById(R.id.et_blood_req_contact_number);
        et_blood_req_relation = (EditText) findViewById(R.id.et_blood_req_relation);
        et_blood_req_quantity_bag = (EditText) findViewById(R.id.et_blood_req_quantity_bag);
        et_blood_req_already_managed_quantity_bag = (EditText) findViewById(R.id.et_blood_req_already_managed_quantity_bag);


        spn_blood_req_gender = (Spinner) findViewById(R.id.spn_blood_req_gender);
        spn_blood_req_blood_group = (Spinner) findViewById(R.id.spn_blood_req_blood_group);
        spn_blood_req_country = (Spinner) findViewById(R.id.spn_blood_req_country);
        spn_blood_req_division = (Spinner) findViewById(R.id.spn_blood_req_division);
        spn_blood_req_district = (Spinner) findViewById(R.id.spn_blood_req_district);


        btn_blood_req_submit_req = (Button) findViewById(R.id.btn_blood_req_submit_req);


        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(BloodRequestsCreate.this, R.array.blood_group, android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_blood_req_blood_group.setAdapter(spnAdapterBloodGroup);
        spn_blood_req_blood_group.setPrompt("Blood Group");

        spn_blood_req_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterGeneralPeople.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (blood_group == parent.getItemAtPosition(0))
                    Toast.makeText(BloodRequestsCreate.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================


        //================================ Gender =====================================

        spnAdapterGender = ArrayAdapter.createFromResource(BloodRequestsCreate.this, R.array.gender, android.R.layout.simple_spinner_item);
        spnAdapterGender.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_blood_req_gender.setAdapter(spnAdapterGender);

        spn_blood_req_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodRequestsCreate.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Gender =====================================


        //================================ Country =============================

        spnAdapterCountry = ArrayAdapter.createFromResource(BloodRequestsCreate.this, R.array.Country, android.R.layout.simple_spinner_item);
        spnAdapterCountry.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_blood_req_country.setAdapter(spnAdapterCountry);

        spn_blood_req_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodRequestsCreate.this, "Please Select Country...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Country =============================


        //================================ Division =============================

        spnAdapterDivision = ArrayAdapter.createFromResource(BloodRequestsCreate.this, R.array.Divisions, android.R.layout.simple_spinner_item);
        spnAdapterDivision.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_blood_req_division.setAdapter(spnAdapterDivision);

        spn_blood_req_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                division = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodRequestsCreate.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================


        //================================ District =============================

        spnAdapterDistrict = ArrayAdapter.createFromResource(BloodRequestsCreate.this, R.array.Districts, android.R.layout.simple_spinner_item);
        spnAdapterDistrict.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_blood_req_district.setAdapter(spnAdapterDistrict);

        spn_blood_req_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodRequestsCreate.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================


        //================================ Date of Donation ==============================

        tv_blood_req_date_of_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(BloodRequestsCreate.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                tv_blood_req_date_of_donation.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });

        //================================ Date of Donation ==============================


        btn_blood_req_submit_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodRequest();
            }
        });


    }

    private void bloodRequest() {

        date_of_donation = tv_blood_req_date_of_donation.getText().toString();
        patient_name = et_blood_req_patient_name.getText().toString();
        blood_req_reason = et_blood_req_reason.getText().toString();
        contact_number = et_blood_req_contact_number.getText().toString();
        relation = et_blood_req_relation.getText().toString();
        quantity_bag = et_blood_req_quantity_bag.getText().toString();
        managed_quantity_bag = et_blood_req_already_managed_quantity_bag.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_ADD_BLOOD_REQUEST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(BloodRequestsCreate.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("date_of_donation", date_of_donation);
                params.put("patient_name", patient_name);
                params.put("gender", gender);
                params.put("blood_group", blood_group);
                params.put("blood_req_reason", blood_req_reason);
                params.put("contact_number", contact_number);
                params.put("relation", relation);
                params.put("quantity_bag", quantity_bag);
                params.put("managed_quantity_bag", managed_quantity_bag);
                params.put("country", country);
                params.put("division", division);
                params.put("district", district);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(BloodRequestsCreate.this);
        requestQueue.add(stringRequest);

    }
}
