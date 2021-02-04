package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Driver2 extends AppCompatActivity {
Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver2);
        cancel=findViewById(R.id.button10);
    }

    public void Cancel(View view) {
        Intent intent=new Intent(Driver2.this,Driver1.class);
        startActivity(intent);
        finish();
    }
}