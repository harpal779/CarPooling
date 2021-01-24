package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button login,home;
    private EditText emailEt, passwordEt;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private RadioButton dr,pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth = FirebaseAuth.getInstance();
        dr=findViewById(R.id.driver);
        pas=findViewById(R.id.driver1);
        emailEt = findViewById(R.id.email1);
        passwordEt = findViewById(R.id.password1);
        home=findViewById(R.id.home);
        progressDialog = new ProgressDialog(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login1();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home1();
            }
        });


    }

    private void home1() {
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void login1() {
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEt.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password)) {
            passwordEt.setError("Enter your password");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && (dr.isChecked()) ) {

                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
               else if (task.isSuccessful() && (pas.isChecked()) ) {
                    {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Interface.class);
                        startActivity(intent);
                        finish();
                    }
                }

                else {
                    Toast.makeText(Login.this, "Sign In fail!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}