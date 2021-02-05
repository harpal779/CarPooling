package com.example.carpooling;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.carpooling.R.id.car;

public class Driver2 extends Driver1 {

    Button cancel,post;
    EditText CarName,Phone,Model,From,To,Date,Time,AvailableSeats,Price;
    Upload2 upload2;
    DatabaseReference database;
    FirebaseDatabase firebaseDatabase;




    @SuppressLint("WrongViewCast")
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
        CarName = findViewById(R.id.Car);
        Phone= findViewById(R.id.editTextPhone);
        Model = findViewById(R.id.Model);
        From = findViewById(R.id.From);
        To = findViewById(R.id.To);
        Date = findViewById(R.id.editTextDate);
        Time = findViewById(R.id.editTextTime);
        AvailableSeats = findViewById(R.id.Seats);
        Price = findViewById(R.id.editTextNumber);
        post = findViewById(R.id.button9);
        upload2 = new Upload2();
        database= firebaseDatabase.getInstance().getReference().child("Ride");


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload2.setCarname(CarName.getText().toString());
                upload2.setPhone(Phone.getText().toString());
                upload2.setModel(Model.getText().toString());
                upload2.setFrom(From.getText().toString());
                upload2.setTo(To.getText().toString());
                upload2.setDate(Date.getText().toString());
                upload2.setTime(Time.getText().toString());
                upload2.setAvailableSeats(AvailableSeats.getText().toString());
                upload2.setPrice(Price.getText().toString());
                String id = database.push().getKey();
                database.child(id).setValue(upload2);
                Toast.makeText(Driver2.this, "Data saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Driver2.this, Driver1.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void Cancel() {
        Intent intent=new Intent(Driver2.this,Driver1.class);
        startActivity(intent);
        finish();
    }
}
