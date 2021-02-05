package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Passenger1 extends AppCompatActivity {
Button home,back,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger1);
        home=findViewById(R.id.button18);
        back=findViewById(R.id.button18);
        logout=findViewById(R.id.button18);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {
        Intent intent = new Intent(Passenger1.this, Login.class);
        startActivity(intent);
        finish();

    }

    private void home() {
        Intent intent = new Intent(Passenger1.this, Interface.class);
        startActivity(intent);
        finish();
    }

    private void back() {
        Intent intent = new Intent(Passenger1.this, Passenger2.class);
        startActivity(intent);
        finish();
    }
}
