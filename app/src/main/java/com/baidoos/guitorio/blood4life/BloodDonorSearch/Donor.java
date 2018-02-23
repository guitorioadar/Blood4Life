package com.baidoos.guitorio.blood4life.BloodDonorSearch;

/**
 * Created by ADAR on 10/11/2017.
 */

public class Donor {String full_name;
    String blood_group;
    String contact_no;

    public Donor(){

    }

    public Donor(String full_name, String blood_group, String contact_no) {
        this.full_name = full_name;
        this.blood_group = blood_group;
        this.contact_no = contact_no;
    }



    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
