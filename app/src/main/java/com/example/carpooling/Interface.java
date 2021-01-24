package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Interface extends AppCompatActivity {
Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        b2=findViewById(R.id.button2);
    }

    public void Home(View view) {
        Toast.makeText(Interface.this, "Logout Successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Interface.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}