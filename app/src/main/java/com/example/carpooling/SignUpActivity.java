package com.example.carpooling;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailId, passwordId, passwordId2;
    private RadioButton type;
    private Button SignUpButton;
    private TextView SignIntext;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    Upload upload3;



    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email1);
        passwordId = findViewById(R.id.password1);
        passwordId2 = findViewById(R.id.password2);
        SignUpButton = findViewById(R.id.register);
        upload3 = new Upload();

        progressDialog = new ProgressDialog(this);
        SignIntext = findViewById(R.id.signInTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        SignIntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });




    }

    public void Register() {
        final String email = emailId.getText().toString();
        final String password1 = passwordId.getText().toString();
        String password2 = passwordId2.getText().toString();


        if (TextUtils.isEmpty(email)) {
            emailId.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password1)) {
            passwordId.setError("Enter your password");
            return;
        } else if (TextUtils.isEmpty(password2)) {
            passwordId2.setError("Confirm your password");
            return;
        } else if (!password1.equals(password2)) {
            passwordId2.setError("Different password");
            return;
        } else if (password1.length() < 4) {
            passwordId.setError("Length should be > 4");
            return;
        } else if (!isVallidEmail(email)) {
            emailId.setError("invalid email");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        databaseReference = firebaseDatabase.getInstance().getReference().child("Users");
        upload3.setEmail(emailId.getText().toString());
        upload3.setPassword(passwordId.getText().toString());
        String id = databaseReference.push().getKey();
        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(upload3);

                    Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignUpActivity.this, "Sign up fail!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }


    private Boolean isVallidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
