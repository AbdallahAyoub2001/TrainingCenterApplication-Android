package com.androidlab.trainingcenterapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminSignUpActivity extends AppCompatActivity {

    private EditText etEmail, etFirstName, etLastName, etPassword, etConfirmPassword;
    Button btnSignUp;
    FloatingActionButton attachPhoto;
    ImageView userPhoto;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        userPhoto = findViewById(R.id.personalPhoto);

        btnSignUp = findViewById(R.id.btn_signUp);
        attachPhoto = findViewById(R.id.attachPhoto);

        //Intent openGallery;
        attachPhoto.setOnClickListener(view -> {
                Intent openGallery = new Intent(Intent.ACTION_PICK);
                openGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 1);
        });

        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (validate(email, firstName, lastName, password, confirmPassword, userPhoto)) {
                AdminUser admin = new AdminUser(email, firstName, lastName, password, userPhoto);

                DataBaseHelper dataBaseHelper =new DataBaseHelper(AdminSignUpActivity.this,"DB_TRAINING_CENTER",null,1);
                try {
                    dataBaseHelper.insertAdmin(admin);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                startActivity(new Intent(AdminSignUpActivity.this, AdminHomeViewActivity.class));
                finish();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                assert data != null;
                userPhoto.setImageURI(data.getData());
            }
        }
    }

    private boolean validate(String email, String firstName, String lastName, String password, String confirmPassword, ImageView photo) {
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        } else if (!isValidEmail(email)) {
            etEmail.setError("Invalid email");
            etEmail.requestFocus();
            return false;
        }
        if (firstName.isEmpty()) {
            etFirstName.setError("First name is required");
            etFirstName.requestFocus();
            return false;
        } else if(firstName.length() < 3){
            etFirstName.setError("First name should be at least 3 characters length");
            etFirstName.requestFocus();
            return false;
        }
        if (lastName.isEmpty()) {
            etLastName.setError("Last name is required");
            etLastName.requestFocus();
            return false;
        } else if(lastName.length() < 3){
            etFirstName.setError("Last name should be at least 3 characters length");
            etFirstName.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        } else if (password.length() < 8) {
            etPassword.setError("Password must be at least 8 characters long");
            etPassword.requestFocus();
            return false;
        } else if (!isValidPassword(password)) {
            etPassword.setError("Password must contain at least one number, one lowercase letter, and one uppercase letter.");
            etPassword.requestFocus();
            return false;
        } else if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }
        if(photo == null){
            Toast toast =Toast.makeText(AdminSignUpActivity.this, "Please Attach your photo",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;

    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String str) {
        // Check if the string contains at least one number.
        if (!str.matches(".*\\d.*")) {
            return false;
        }
        // Check if the string contains at least one lowercase letter.
        if (!str.matches(".*[a-z].*")) {
            return false;
        }
        // Check if the string contains at least one uppercase letter.
        if (!str.matches(".*[A-Z].*")) {
            return false;
        }
        // The string contains at least one number, one lowercase letter, and one uppercase letter.
        return true;
    }
}