package com.swift.delivery.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class LoginOptionsPage extends AppCompatActivity {
    Button signIn, signUp, userInfo, orderDetails;
    DatabaseHelper myDB;
    LinearLayout layout1, layout2;
    Animation upToDown, downToUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options_page);

        // Initialize buttons
        signIn = (Button) findViewById(R.id.signin);
        signUp = (Button) findViewById(R.id.signup);
        userInfo = (Button) findViewById(R.id.userinfo);
        orderDetails = (Button) findViewById(R.id.order_details);

        // Initialize layouts
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);

        // Load animations
        upToDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downToUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        // Apply animations to layouts
        layout1.setAnimation(upToDown);
        layout2.setAnimation(downToUp);

        // Initialize database helper
        myDB = new DatabaseHelper(this);

        // Set up button click listeners
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInPage = new Intent(getApplicationContext(), SignInPage.class);
                startActivity(signInPage);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpPage = new Intent(getApplicationContext(), SignUpPage.class);
                startActivity(signUpPage);
                finish();
            }
        });

        userInfo();
        orderData();
    }

    private void userInfo() {
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.GetData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No data found");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("Id: ").append(res.getString(0)).append("\n");
                    buffer.append("Username: ").append(res.getString(1)).append("\n");
                    buffer.append("Password: ").append(res.getString(2)).append("\n");
                }
                showMessage("User Information", buffer.toString());
            }
        });
    }

    private void orderData() {
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.Get_OrderDetails();
                if (res.getCount() == 0) {
                    showMessage("Error", "No data found");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("Order Id: ").append(res.getString(0)).append("\n");
                    buffer.append("Item Name: ").append(res.getString(1)).append("\n");
                    buffer.append("Quantity: ").append(res.getString(2)).append("\n");
                    buffer.append("Price: ").append(res.getString(3)).append("\n");
                }
                showMessage("Order Details", buffer.toString());
            }
        });
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
