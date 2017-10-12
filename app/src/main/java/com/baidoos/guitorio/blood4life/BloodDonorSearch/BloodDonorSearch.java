package com.baidoos.guitorio.blood4life.BloodDonorSearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.baidoos.guitorio.blood4life.AreaAdmin.AreaAdmin;
import com.baidoos.guitorio.blood4life.AreaGeneralPeople.AreaGeneralPeople;
import com.baidoos.guitorio.blood4life.AreaMember.AreaMember;
import com.baidoos.guitorio.blood4life.Config;
import com.baidoos.guitorio.blood4life.LoginAndRegister.Registration;
import com.baidoos.guitorio.blood4life.R;

public class BloodDonorSearch extends AppCompatActivity {


    private FrameLayout rootLayout;

    private ArrayAdapter spnAdapterBloodGroup,spnAdapterCountry,spnAdapterDivision,spnAdapterDistrict;

    private Spinner spnBloodGroup,spnCountry,spnDivision,spnDistrict,spnAvailableStatus;

    private String blood_group,country,division,district;

    private String active_user;


    private Button btnSearch;



    /*private final String USER_GENERAL_PEOPLE = "General Peole";
    private final String USER_ADMIN = "Admin";
    private final String USER_MEMBER = "Member";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donor_search);
        setTitle("Search Blood");


        Toast.makeText(BloodDonorSearch.this, Config.ACTIVE_USER, Toast.LENGTH_SHORT).show();

        /*Intent intent = getIntent();
        active_user = intent.getExtras().getString("active_user");
        Toast.makeText(BloodDonorSearch.this, active_user, Toast.LENGTH_SHORT).show();*/

        //rootLayout = (FrameLayout) findViewById(R.id.blood_donor_search_xml);

        spnBloodGroup = (Spinner) findViewById(R.id.spnr_blood_group);
        spnCountry = (Spinner) findViewById(R.id.spnr_country);
        spnDivision = (Spinner) findViewById(R.id.spnr_division);
        spnDistrict = (Spinner) findViewById(R.id.spnr_district);


        //================================  Blood Group================================

        spnAdapterBloodGroup = ArrayAdapter.createFromResource(BloodDonorSearch.this,R.array.blood_group,android.R.layout.simple_spinner_item);
        spnAdapterBloodGroup.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBloodGroup.setAdapter(spnAdapterBloodGroup);

        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(BloodDonorSearch.this, parent.getItemAtPosition(position).toString()+" is selected", Toast.LENGTH_SHORT).show();

                blood_group = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(blood_group==parent.getItemAtPosition(0))
                    Toast.makeText(BloodDonorSearch.this, "Please Select One...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================  Blood Group================================


        //================================ Country =============================

        spnAdapterCountry = ArrayAdapter.createFromResource(BloodDonorSearch.this,R.array.Country,android.R.layout.simple_spinner_item);
        spnAdapterCountry.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnCountry.setAdapter(spnAdapterCountry);

        spnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getItemAtPosition(position).toString();
                //Toast.makeText(BloodDonorSearch.this, country, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodDonorSearch.this, "Please Select Country...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Country =============================


        //================================ Division =============================

        spnAdapterDivision = ArrayAdapter.createFromResource(BloodDonorSearch.this,R.array.Divisions,android.R.layout.simple_spinner_item);
        spnAdapterDivision.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnDivision.setAdapter(spnAdapterDivision);

        spnDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                division = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodDonorSearch.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ Division =============================


        //================================ District =============================

        spnAdapterDistrict = ArrayAdapter.createFromResource(BloodDonorSearch.this,R.array.Districts,android.R.layout.simple_spinner_item);
        spnAdapterDistrict.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnDistrict.setAdapter(spnAdapterDistrict);

        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(BloodDonorSearch.this, "Please Select Division...", Toast.LENGTH_SHORT).show();
            }
        });

        //================================ District =============================


        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(blood_group.equals("Select Blood Group")){
                    Toast.makeText(BloodDonorSearch.this, "Please Select A Blood Group", Toast.LENGTH_SHORT).show();
                }else if(country.equals("Select Country")) {
                    Toast.makeText(BloodDonorSearch.this, "Please Select a Country", Toast.LENGTH_SHORT).show();
                }else if(division.equals("Select Division") && !district.equals("Select District")){
                    Toast.makeText(BloodDonorSearch.this, "Please Select Division", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(BloodDonorSearch.this, BloodDonors.class);
                    intent.putExtra("blood_group", blood_group);
                    intent.putExtra("country", country);
                    intent.putExtra("division", division);
                    intent.putExtra("district", district);
                    startActivity(intent);
                }
            }
        });




    }

    @Override
    public void onBackPressed() {
        if(Config.ACTIVE_USER.equals(Config.USER_GENERAL_PEOPLE)){
            Intent intent = new Intent(BloodDonorSearch.this, AreaGeneralPeople.class);
            startActivity(intent);
        }else if(Config.ACTIVE_USER.equals(Config.USER_ADMIN)){
            Intent intent = new Intent(BloodDonorSearch.this, AreaAdmin.class);
            startActivity(intent);
        }else if (Config.ACTIVE_USER.equals(Config.USER_MEMBER)){
            Intent intent = new Intent(BloodDonorSearch.this, AreaMember.class);
            startActivity(intent);
        }else if (Config.ACTIVE_USER.isEmpty()){
            Intent intent = new Intent(BloodDonorSearch.this, Registration.class);
            startActivity(intent);
        }else{
            Toast.makeText(BloodDonorSearch.this, "Get Register", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void donorSearch() {

        if (blood_group.equals("Select Blood Group")) {
            Toast.makeText(this, "Please Select Blood Group", Toast.LENGTH_SHORT).show();
        } else if (country.equals("Select Country")) {
            Toast.makeText(this, "Please select country...", Toast.LENGTH_SHORT).show();
        } else if (division.equals("Select Division")) {
            Toast.makeText(this, "Please select division...", Toast.LENGTH_SHORT).show();
        } else if (district.equals("Select District")) {
            Toast.makeText(this, "Please select district...", Toast.LENGTH_SHORT).show();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_SEARCH_BLOOD_DONOR,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //Toast.makeText(BloodDonorSearch.this, response, Toast.LENGTH_SHORT).show();
                            //Snackbar.make(rootLayout,response.toString(),Snackbar.LENGTH_LONG).show();
                            Snackbar snackbar = Snackbar.make(rootLayout, response.toString(), Snackbar.LENGTH_LONG).setAction("Action", null);
                            //snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(BloodDonorSearch.this, error.toString(), Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("blood_group", blood_group);
                    params.put("country", country);
                    params.put("division", division);
                    params.put("district", district);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(BloodDonorSearch.this);
            requestQueue.add(stringRequest);

        }
    }*/
}
