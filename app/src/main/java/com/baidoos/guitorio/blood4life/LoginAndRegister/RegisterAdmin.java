package com.baidoos.guitorio.blood4life.LoginAndRegister;

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

public class RegisterAdmin extends AppCompatActivity {

    //private String URL_REG_ADMIN = "http://192.168.75.1/Blood4Life/register.php";

    private String user_type="Admin";

    private String full_name,blood_group,gender,date_of_birth,contact_no,country,division,district,permanent_address,email,user_name,password,confirm_password,available_status,last_blood_donate;

    private EditText etFullname,etContactNo,etPermanentAddress,etEmail,etUserName,etPassword,etConfirmPassword;

    private TextView tvDateOfBirth;

    private Spinner spnBloodGroup,spnGender,spnCountry,spnDivision,spnDistrict,spnAvailableStatus;

    private ArrayAdapter spnAdapterBloodGroup,spnAdapterGender,spnAdapterCountry,spnAdapterDivision,spnAdapterDistrict,spnAdapterAvailableStatus;

    private Button btnRegisterGeneralPeople;



    private DatePickerDialog datePickerDialogDateOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);


        spnBloodGroup = (Spinner) findViewById(R.id.spn_Blood_Group);
        spnGender = (Spinner) findViewById(R.id.spn_gender);
        spnCountry = (Spinner) findViewById(R.id.spn_country);
        spnDivision = (Spinner) findViewById(R.id.spn_division);
        spnDistrict = (Spinner) findViewById(R.id.spn_district);
        spnAvailableStatus = (Spinner) findViewById(R.id.spn_available_status);

        etFullname = (EditText) findViewById(R.id.et_Full_Name);
        etContactNo = (EditText) findViewById(R.id.et_contact_no);
        etPermanentAddress = (EditText) findViewById(R.id.et_permanent_address);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);

        tvDateOfBirth = (TextView) findViewById(R.id.et_date_of_birth);



        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.blood_group,android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBloodGroup.setAdapter(spnAdapterBloodGroup);

        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterGeneralPeople.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(blood_group==parent.getItemAtPosition(0))
                    Toast.makeText(RegisterAdmin.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================

        //================================ Gender =====================================

        spnAdapterGender = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.gender,android.R.layout.simple_spinner_item);
        spnAdapterGender.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnGender.setAdapter(spnAdapterGender);

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, gender, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterAdmin.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Gender =====================================

        //================================ Date of Birth ==============================

        tvDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialogDateOfBirth = new DatePickerDialog(RegisterAdmin.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                tvDateOfBirth.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                            }
                        },mYear,mMonth,mDay);

                datePickerDialogDateOfBirth.show();

            }
        });

        //================================ Date of Birth ==============================

        //================================ Country =============================

        spnAdapterCountry = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.Country,android.R.layout.simple_spinner_item);
        spnAdapterCountry.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnCountry.setAdapter(spnAdapterCountry);

        spnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                //Toast.makeText(RegisterGeneralPeople.this, country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterAdmin.this, "Please Select Country...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Country =============================

        //================================ Division =============================

        spnAdapterDivision = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.Divisions,android.R.layout.simple_spinner_item);
        spnAdapterDivision.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnDivision.setAdapter(spnAdapterDivision);

        spnDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                division = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterAdmin.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================

        //================================ District =============================

        spnAdapterDistrict = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.Districts,android.R.layout.simple_spinner_item);
        spnAdapterDistrict.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnDistrict.setAdapter(spnAdapterDistrict);

        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterAdmin.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================

        //================================ Available Status =============================


        spnAdapterAvailableStatus = ArrayAdapter.createFromResource(RegisterAdmin.this,R.array.available_status,android.R.layout.simple_spinner_item);
        spnAdapterAvailableStatus.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnAvailableStatus.setAdapter(spnAdapterAvailableStatus);

        spnAvailableStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                available_status = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(RegisterAdmin.this, "Please Select Status...", Toast.LENGTH_SHORT).show();
            }
        });


        //================================ Available status =============================


        btnRegisterGeneralPeople = (Button) findViewById(R.id.btn_Register_General_People);
        btnRegisterGeneralPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regGeneralPeople();

            }
        });



    }

    private void regGeneralPeople() {


        full_name = etFullname.getText().toString();
        contact_no = etContactNo.getText().toString();
        permanent_address = etPermanentAddress.getText().toString();
        email = etEmail.getText().toString();
        user_name = etUserName.getText().toString();
        password = etPassword.getText().toString();
        confirm_password = etConfirmPassword.getText().toString();

        date_of_birth = tvDateOfBirth.getText().toString();



        if(full_name.isEmpty()){
            Toast.makeText(RegisterAdmin.this, "Please enter your name", Toast.LENGTH_SHORT).show();
        }else if(blood_group.equals("Select Blood Group")){
            Toast.makeText(RegisterAdmin.this, "Please Select Blood Group", Toast.LENGTH_SHORT).show();
        }else if(gender.equals("Select Gender")){
            Toast.makeText(RegisterAdmin.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        }else if(date_of_birth.isEmpty()){
            Toast.makeText(RegisterAdmin.this, "Please Select Date of Birth", Toast.LENGTH_SHORT).show();
        }else if(contact_no.isEmpty()){
            Toast.makeText(RegisterAdmin.this, "Please Add Contact Number", Toast.LENGTH_SHORT).show();
        }else if(country.equals("Select Country")){
            Toast.makeText(RegisterAdmin.this, "Please your country...", Toast.LENGTH_SHORT).show();
        }else if(division.equals("Select Division")){
            Toast.makeText(RegisterAdmin.this, "Please select your division...", Toast.LENGTH_SHORT).show();
        }else if(district.equals("Select District")){
            Toast.makeText(RegisterAdmin.this, "Please select your district...", Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty()){
            Toast.makeText(RegisterAdmin.this, "Invalid Email...", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(confirm_password)){
            Toast.makeText(RegisterAdmin.this, "Password doesn't match...", Toast.LENGTH_SHORT).show();
        }else if(user_name.isEmpty()){
            Toast.makeText(RegisterAdmin.this, "Enter Username...", Toast.LENGTH_SHORT).show();
        }else if(available_status.equals("Select Available Status")){
            Toast.makeText(RegisterAdmin.this, "Please select if you are now available or not to donate blood", Toast.LENGTH_LONG).show();
        }

        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REG_ADMIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(RegisterAdmin.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterAdmin.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("user_type",user_type);
                    params.put("full_name", full_name);
                    params.put("blood_group", blood_group);
                    params.put("gender",gender);
                    params.put("date_of_birth",date_of_birth);
                    params.put("contact_no",contact_no);
                    params.put("country",country);
                    params.put("division",division);
                    params.put("district",district);
                    params.put("permanent_address",permanent_address);
                    params.put("email",email);
                    params.put("user_name",user_name);
                    params.put("password",password);
                    params.put("available_status",available_status);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisterAdmin.this);
            requestQueue.add(stringRequest);


        }

    }


}
