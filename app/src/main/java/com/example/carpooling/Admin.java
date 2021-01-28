package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {
Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        b1=findViewById(R.id.button5);
        b2=findViewById(R.id.button6);
        b3=findViewById(R.id.button7);






    }

    public void Passenger(View view) {

        Intent intent=new Intent(Admin.this,Interface.class);
        startActivity(intent);
        finish();
    }


    public void Driver(View view) {
        Intent intent=new Intent(Admin.this,Interface.class);
        startActivity(intent);
        finish();
    }

    public void Logout(View view) {
        Intent intent=new Intent(Admin.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}