package com.gaurav.stockmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gaurav.stockmanage.adapter.Proadapter;
import com.gaurav.stockmanage.model.Productmodel;

import java.util.ArrayList;

public class viewActivity extends AppCompatActivity {
    private ArrayList<Productmodel> productArrayList;
    private DBhandler dBhandler;
    private Proadapter productAdapter;
    private RecyclerView productRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        productArrayList = new ArrayList<>();
        dBhandler = new DBhandler(viewActivity.this);


        productArrayList = dBhandler.read();


        productAdapter = new Proadapter(productArrayList,viewActivity.this);
        productRV = findViewById(R.id.recycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewActivity.this,RecyclerView.VERTICAL,false);
        productRV.setLayoutManager(linearLayoutManager);

        productRV.setAdapter(productAdapter);


    }
}