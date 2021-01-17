package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.carpooling.R.id.button;

public class MainActivity extends AppCompatActivity {
private TextView tv;
private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) this.findViewById(R.id.textView);
        tv.setSelected(true);
        b1=findViewById(button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }

    private void Signup() {

        Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }






}