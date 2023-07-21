package com.androidlab.trainingcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {
    // static String Role;
    Button admin, trainee, instructor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        admin = findViewById(R.id.bt_admin);
        trainee = findViewById(R.id.bt_trainee);
        instructor = findViewById(R.id.bt_instructor);

        admin.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, AdminSignUpActivity.class));
            finish();
        });

        trainee.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, TraineeSignUpActivity.class));
            finish();
        });

        instructor.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, InstructorSignUpActivity.class));
            finish();
        });

    }
}