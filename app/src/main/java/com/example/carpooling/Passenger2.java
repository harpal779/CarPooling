package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Passenger2 extends AppCompatActivity {
Button back,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger2);
        back=findViewById(R.id.button19);
        logout=findViewById(R.id.button20);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
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
        Intent intent = new Intent(Passenger2.this, Login.class);
        startActivity(intent);
        finish();

    }

    private void back() {

        Intent intent = new Intent(Passenger2.this, Interface.class);
        startActivity(intent);
        finish();

    }
}
