package com.baidoos.guitorio.blood4life.Profiles;

/**
 * Created by ADAR on 1/12/2018.
 */

public class ProfileUserDetailPOJO {

    String user_typre;
    String available_status;
    String name_of_the_user;
    String username;
    String user_email;
    String user_contact;
    String user_blood_group;
    String user_address;


    public ProfileUserDetailPOJO() {

    }


/*
    public ProfileUserDetailPOJO(String user_typre, String available_status, String name_of_the_user, String username, String user_email, String user_contact, String user_blood_group, String user_address) {
        this.user_typre = user_typre;
        this.available_status = available_status;
        this.name_of_the_user = name_of_the_user;
        this.username = username;
        this.user_email = user_email;
        this.user_contact = user_contact;
        this.user_blood_group = user_blood_group;
        this.user_address = user_address;
    }
*/

    public String getUser_typre() {
        return user_typre;
    }

    public void setUser_typre(String user_typre) {
        this.user_typre = user_typre;
    }

    public String getAvailable_status() {
        return available_status;
    }

    public void setAvailable_status(String available_status) {
        this.available_status = available_status;
    }

    public String getName_of_the_user() {
        return name_of_the_user;
    }

    public void setName_of_the_user(String name_of_the_user) {
        this.name_of_the_user = name_of_the_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getUser_blood_group() {
        return user_blood_group;
    }

    public void setUser_blood_group(String user_blood_group) {
        this.user_blood_group = user_blood_group;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
