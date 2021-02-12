package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class editprofile extends AppCompatActivity {
    FirebaseUser user;
    Button b,b25;
    EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        b = findViewById(R.id.button24);
        b25 = findViewById(R.id.button25);

t=findViewById(R.id.enterpassword);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change() ;
            }
        });
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });


    }

    private void back() {
        Intent intent=new Intent(editprofile.this,Interface.class);
        startActivity(intent);
        finish();
    }

    private void change() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            user.updatePassword(t.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(editprofile.this, "Successfully", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(editprofile.this, "Not Successfully", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }















}