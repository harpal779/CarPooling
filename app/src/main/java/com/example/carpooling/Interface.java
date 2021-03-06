package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Interface extends AppCompatActivity {
Button b2,b21,b4,b22;
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
                booked() ;
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        b22=findViewById(R.id.button22);
    }

    private void logout() {
        Intent intent = new Intent(Interface.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void booked() {
        Toast.makeText(Interface.this, "Please Contact with Driver Phone Number To Book Ride ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Interface.this, SendMessage.class);
        startActivity(intent);
        finish();
    }

    public void Home(View view) {
        Toast.makeText(Interface.this, "Please Contact with Driver Phone Number To Book Ride ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Interface.this, Passenger2.class);
        startActivity(intent);
        finish();

    }

    public void edit(View view) {
        Toast.makeText(Interface.this, "Please Contact with Driver Phone Number To Book Ride ", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Interface.this, editprofile.class);
        startActivity(intent);
        finish();
    }
}
