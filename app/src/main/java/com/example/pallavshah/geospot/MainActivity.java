package com.example.pallavshah.geospot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView txtLocation;
    private TextView txt1;

    GPSService mGPSService;
    Button btn1,btn2,btn3;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        txt1 = (TextView) findViewById(R.id.txt1);
        String fontPath = "fonts/PLYMOUTH.TTF";

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        txtLocation = (TextView) findViewById(R.id.txtLocation);
        txt1 = (TextView) findViewById(R.id.txt1);

        txt1.setTypeface(tf);
        btn1.setTypeface(tf);
        btn2.setTypeface(tf);
        btn3.setTypeface(tf);

        address = "";
        mGPSService = new GPSService(getApplicationContext());
        mGPSService.getLocation();

        if (mGPSService.isLocationAvailable == false) {


            Toast.makeText(getApplicationContext(), "Your location or internet is not available, please Enable your location & internet settings.", Toast.LENGTH_LONG).show();

            finish();
        }
                   else{

            // Getting location co-ordinates
            double latitude = mGPSService.getLatitude();
            double longitude = mGPSService.getLongitude();
            // Toast.makeText(getApplicationContext(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

            address = mGPSService.getLocationAddress();

           

            txtLocation.setText("" + address);
            txtLocation.setTypeface(tf);

        }

       // Toast.makeText(getApplicationContext(), "Your address is: " + address, Toast.LENGTH_SHORT).show();


        mGPSService.closeGPS();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




              Intent i = new Intent(getApplicationContext(),New.class);
                i.putExtra("key",address);
                startActivity(i);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}