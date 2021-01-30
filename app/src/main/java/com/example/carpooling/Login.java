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
    private EditText emailId, passwordId;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private RadioButton dri,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth = FirebaseAuth.getInstance();
        dri=findViewById(R.id.driver);
        pass=findViewById(R.id.driver1);
        emailId = findViewById(R.id.email1);
        passwordId = findViewById(R.id.password1);
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
        String email = emailId.getText().toString();
        String password = passwordId.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailId.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password)) {
            passwordId.setError("Enter your password");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && (dri.isChecked()) ) {

                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Driver1.class);
                        startActivity(intent);
                        finish();
                    }
               else if (task.isSuccessful() && (pass.isChecked()) ) {
                    {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Interface.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if (task.isSuccessful() && (pass.isChecked()==false) && (dri.isChecked()==false) ) {
                    {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Admin.class);
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