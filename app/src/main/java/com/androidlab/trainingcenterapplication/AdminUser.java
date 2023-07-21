package com.androidlab.trainingcenterapplication;

import android.media.Image;
import android.widget.ImageView;

public class AdminUser {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private ImageView photo;

    public AdminUser(String email, String firstName, String lastName, String password, ImageView photo) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        //this.confirmPassword = confirmPassword;
        this.photo = photo;

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

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public ImageView getPhoto() {
        return photo;
    }
}
