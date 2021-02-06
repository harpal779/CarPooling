package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Interface extends AppCompatActivity {
Button b2,b21,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        b2=findViewById(R.id.button2);
        b21=findViewById(R.id.button21);
        b4=findViewById(R.id.button4);

        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booked();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(Interface.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void booked() {
        Intent intent = new Intent(Interface.this, Passenger1.class);
        startActivity(intent);
        finish();
    }

    public void Home(View view) {
        Toast.makeText(Interface.this, "Logout Successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Interface.this, Passenger2.class);
        startActivity(intent);
        finish();

    }
}
