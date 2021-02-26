package com.example.carpooling;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static com.example.carpooling.R.id.car;

public class Driver2 extends Driver1 {

    Button cancel,post;
    EditText CarName,Phone,Model,From,To,Date,Time,AvailableSeats,Price;
    Upload2 upload2;
    DatabaseReference database,database1;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatePickerDialog picker;
    private FirebaseAuth auth;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver2);
        cancel = findViewById(R.id.button10);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
        CarName = findViewById(R.id.Car);
        Phone = findViewById(R.id.editTextPhone);
        Model = findViewById(R.id.Model);
        From = findViewById(R.id.From);
        To = findViewById(R.id.To);
        Date = findViewById(R.id.editTextDate);
        Time = findViewById(R.id.editTextTime);
        AvailableSeats = findViewById(R.id.Seats);
        Price = findViewById(R.id.editTextNumber);
        post = findViewById(R.id.button9);
        upload2 = new Upload2();
        auth = FirebaseAuth.getInstance();
        database = firebaseDatabase.getInstance().getReference("Ride");
        database1 = firebaseDatabase.getInstance().getReference("All");


        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calender = Calendar.getInstance();
                final int year = calender.get(Calendar.YEAR);
                final int month = calender.get(Calendar.MONTH);
                final int date = calender.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Driver2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int date) {
                        Date.setText(SimpleDateFormat.getDateInstance().format(calender.getTime()));
                    }

                }, year, month, date);
                datePickerDialog.show();
            }
        });


        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calender = Calendar.getInstance();
                final int hours = calender.get(Calendar.HOUR);
                final int minute = calender.get(Calendar.MINUTE);

                TimePickerDialog PickerDialog = new TimePickerDialog(Driver2.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calender = Calendar.getInstance();
                        calender.set(Calendar.HOUR,hourOfDay);
                        calender.set(Calendar.MINUTE,minute);
                        calender.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time=format.format(calender.getTime());
                        Time.setText(time);



                    }
                },hours,minute,false);
                PickerDialog.show();
            }
                                });




        post.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                final String Carname = CarName.getText().toString();
                final String phone = Phone.getText().toString();
                final String model = Model.getText().toString();
                final String from = From.getText().toString();
                final String to = To.getText().toString();
                final String date = Date.getText().toString();
                final String time = Time.getText().toString();
                final String seats = AvailableSeats.getText().toString();
                final String price = Price.getText().toString();






                if(TextUtils.isEmpty(Carname))
{
    CarName.setError("Enter Carname");
    return;
}
                else if(TextUtils.isEmpty(phone))
                {
                    Phone.setError("Enter Phone");
                    return;
                }
                else if(TextUtils.isEmpty(model))
                {
                    Model.setError("Enter Model");
                    return;
                } else if(TextUtils.isEmpty(from))
                {
                    From.setError("Enter from");
                    return;
                } else if(TextUtils.isEmpty(to))
                {
                    To.setError("Enter to");
                    return;
                } else if(TextUtils.isEmpty(date))
                {
                    Date.setError("Enter Date");
                    return;
                } else if(TextUtils.isEmpty(time))
                {
                    Time.setError("Enter time");
                    return;
                } else if(TextUtils.isEmpty(seats))
                {
                    AvailableSeats.setError("Enter Seats");
                    return;
                } else if(TextUtils.isEmpty(price))
                {
                    Price.setError("Enter price");
                    return;
                }

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
                String id1 = database1.push().getKey();
                database.child(auth.getCurrentUser().getUid()).child(id).setValue(upload2);
                database1.child(id1).setValue(upload2);
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
