package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Driver1 extends AppCompatActivity {
Button postride,Ridelist,logout,edit,chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver1);
        postride=findViewById(R.id.button5);
        Ridelist=findViewById(R.id.button11);
        logout=findViewById(R.id.button8);
        edit=findViewById(R.id.button12);
        chat=findViewById(R.id.button27);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat();
            }
        });


    }

    private void chat() {
        Intent intent=new Intent(Driver1.this,MainActivity2.class);
        startActivity(intent);
        finish();
    }

    private void back() {

        Intent intent=new Intent(Driver1.this,editprofile.class);
        startActivity(intent);
        finish();
    }

    public void PostRide(View view) {

        Intent intent=new Intent(Driver1.this,Driver2.class);
        startActivity(intent);
        finish();



    }

    public void Ridelist(View view) {
        Intent intent=new Intent(Driver1.this,Driver3.class);
        startActivity(intent);
        finish();

    }

    public void logoutt(View view) {
        Intent intent=new Intent(Driver1.this,Login.class);
        startActivity(intent);
        finish();
    }
}
