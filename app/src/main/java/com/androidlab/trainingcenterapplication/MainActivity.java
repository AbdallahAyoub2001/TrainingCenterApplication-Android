package com.androidlab.trainingcenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    int flag = 0;
    EditText Email, Password;
    Button signIn, signUp;
    CheckBox rememberMe;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.et_Email);
        Password = findViewById(R.id.et_Password);
        signIn = findViewById(R.id.bt_signIn);
        signUp = findViewById(R.id.bt_signUp);
        rememberMe = findViewById(R.id.cb_rememberMe);

        rememberMe.setOnClickListener(view -> {
            if(rememberMe.isChecked()){
                sharedPrefManager.saveEmail("emailAddress", Email.getText().toString());
                flag = 1;
            }
        });
        signIn.setOnClickListener(view -> {
            String email = Email.getText().toString();
            String password = Password.getText().toString();

            if (validate(email, password)) {

            }

        });

        signUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(flag == 1){
            Email.setText(sharedPrefManager.readEmail("emailAddress","noValue"));
        }
    }

    private boolean validate(String email, String password){
        if (email.isEmpty()) {
            Email.setError("Email is required");
            Email.requestFocus();
        } else if (!isValidEmail(email)) {
            Email.setError("Invalid email");
            Email.requestFocus();
        }
        if (password.isEmpty()) {
            Password.setError("Password is required");
            Password.requestFocus();
        } else if (password.length() < 8) {
            Password.setError("Password must be at least 8 characters long");
            Password.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}