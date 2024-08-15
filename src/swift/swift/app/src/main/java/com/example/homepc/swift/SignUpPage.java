package com.example.homepc.restauranteatitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText signupName, signupPassword;
    Button addAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        myDB = new DatabaseHelper(this);
        signupName = findViewById(R.id.signup_id);
        signupPassword = findViewById(R.id.signup_password);
        addAccount = findViewById(R.id.add_account);

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signupName.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();

                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Username and Password fields can't be left blank", Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Username field can't be left blank", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Password field can't be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = myDB.Add_Account(username, password);
                    if (isInserted) {
                        Toast.makeText(SignUpPage.this, "User Added Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignInPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpPage.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
