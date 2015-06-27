package com.example.pallavshah.geospot;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class New extends ActionBarActivity{

    TextView txt1,txtLocation;

    MainActivity main;

    GPSService s;
    Button btn1,btn2,btn3;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new);

        final Intent intent = getIntent();
        final String value = intent.getStringExtra("key");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        txt1 = (TextView)findViewById(R.id.txt1);
        txtLocation = (TextView)findViewById(R.id.txtLocation);


        String fontPath = "fonts/PLYMOUTH.TTF";

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        txt1.setTypeface(tf);
        btn1.setTypeface(tf);
        btn2.setTypeface(tf);
        btn3.setTypeface(tf);

            txtLocation.setText(""+ value);
                 txtLocation.setTypeface(tf);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv= new Intent(getApplicationContext(),ListViewImagesActivity.class);
                iv.putExtra("key",value);

                startActivity(iv);



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


