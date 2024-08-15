package com.example.homepc.restauranteatitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Start LoginOptionsPage activity
        Intent intent = new Intent(this, LoginOptionsPage.class);
        startActivity(intent);
        finish(); // Close the Splash activity
    }
}
