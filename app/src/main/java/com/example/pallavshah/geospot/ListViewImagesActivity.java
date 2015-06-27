package com.example.pallavshah.geospot;

/**
 * Created by PALLAV SHAH on 28-06-2015.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewImagesActivity extends ActionBarActivity {


    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final Intent in = getIntent();
        final String v = in.getStringExtra("key");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView txtLocation = (TextView)findViewById(R.id.txtLocation);
        TextView txtDeals = (TextView)findViewById(R.id.txtDeals);
        txtLocation.setText(""+ v);

        String fontPath = "fonts/PLYMOUTH.TTF";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtLocation.setTypeface(tf);
        txtDeals.setTypeface(tf);


        ArrayList<ItemDetails> image_details = GetSearchResults();

        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));

        lv1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                ItemDetails obj_itemDetails = (ItemDetails)o;
               // Toast.makeText(ListViewImagesActivity.this, 'You have chosen:' + ' ' + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<ItemDetails> GetSearchResults(){
        ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

        ItemDetails item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(1);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(2);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(3);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(4);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(5);
        results.add(item_details);

        item_details = new ItemDetails();
        item_details.setName("Shoes");
        item_details.setItemDescription("special Deals");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(6);
        results.add(item_details);


        return results;
    }
}