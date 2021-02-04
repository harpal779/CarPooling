package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Driver1 extends AppCompatActivity {
Button postride;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver1);
        postride=findViewById(R.id.button5);
    }

    public void PostRide(View view) {

        Intent intent=new Intent(Driver1.this,Driver2.class);
        startActivity(intent);
        finish();



    }
}