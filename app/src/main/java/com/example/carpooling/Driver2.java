package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Driver2 extends AppCompatActivity {
Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver2);
        cancel=findViewById(R.id.button10);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });

    }

    public void Cancel() {
        Intent intent=new Intent(Driver2.this,Driver1.class);
        startActivity(intent);
        finish();
    }
}
