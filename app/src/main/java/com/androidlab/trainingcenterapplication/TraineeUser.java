package com.androidlab.trainingcenterapplication;

import android.media.Image;
import android.widget.ImageView;

public class TraineeUser {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    //private String confirmPassword;
    private ImageView photo;
    private String mobileNumber;
    private String address;

    public TraineeUser(String email, String firstName, String lastName, String password,
                       ImageView photo, String mobileNumber, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        //this.confirmPassword = confirmPassword;
        this.photo = photo;
        this.mobileNumber = mobileNumber;
        this.address = address;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
