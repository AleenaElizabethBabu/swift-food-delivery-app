package com.swift.delivery.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Toolbar toolbar;
    private Button logout, cancel;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        Intent i = getIntent();
        String username = i.getStringExtra("Name_marker");

        Toast.makeText(getApplicationContext(), "Hello " + username + ", Welcome to SWIFT", Toast.LENGTH_SHORT).show();

        // Set default fragment
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Fragment_container, fragment);
        fragmentTransaction.commit();

        // Set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        android.support.v4.app.Fragment fragment = null;

        if (id == R.id.nav_fastfood) {
            fragment = new FastFoodFragment();
        } else if (id == R.id.nav_seafood) {
            fragment = new SeaFragment();
        } else if (id == R.id.nav_italian) {
            fragment = new ItalianFragment();
        } else if (id == R.id.nav_continental) {
            fragment = new ContinentalFragment();
        } else if (id == R.id.nav_traditional) {
            fragment = new TraditionalFragment();
        } else if (id == R.id.nav_chinese) {
            fragment = new ChineseFragment();
        } else if (id == R.id.order_details_drawer) {
            Cursor check = mydb.Get_OrderDetails();
            if (check != null && check.getCount() > 0) {
                Intent intent = new Intent(getApplicationContext(), OrderPage.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "No details found because you didn't order something...", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.submit_order) {
            Cursor check = mydb.Get_OrderDetails();
            if (check != null && check.getCount() > 0) {
                fragment = new SubmitOrderFragment();
            } else {
                Toast.makeText(getApplicationContext(), "Sorry, you haven't ordered anything...", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.log_out) {
            openDialog();
        }

        if (fragment != null) {
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.Fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdesign);
        dialog.setTitle(R.string.dialog_popup);
        dialog.show();

        logout = (Button) dialog.findViewById(R.id.dialog_ok);
        cancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.delete_all();
                Toast.makeText(getApplicationContext(), "Hope you liked our service. Have a good day!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginOptionsPage.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "So you don't want to log out!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
