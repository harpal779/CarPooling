package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Driver3 extends AppCompatActivity {
Button h,l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver3);
        h=findViewById(R.id.button13);
        l=findViewById(R.id.button14);
    }

    public void Homee(View view) {
        Intent intent=new Intent(Driver3.this,Driver1.class);
        startActivity(intent);
        finish();
    }

    public void Logoutt(View view) {
        Intent intent=new Intent(Driver3.this,Login.class);
        startActivity(intent);
        finish();
    }
}