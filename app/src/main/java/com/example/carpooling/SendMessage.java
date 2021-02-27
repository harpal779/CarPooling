package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessage extends AppCompatActivity {

    EditText phone,messsage;
Button b23,b27;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

phone=findViewById(R.id.editTextPhone2);
messsage=findViewById(R.id.editTextTextPersonName);
b23=findViewById(R.id.button23);
b27=findViewById(R.id.button27);
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    private void back() {
        Intent intent=new Intent(SendMessage.this,Interface.class);
        startActivity(intent);
        finish();
    }


    public void send(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            Message();
            Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();

        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void Message() {
String Phone = phone.getText().toString().trim();
String Message = messsage.getText().toString().trim();


if(!phone.getText().toString().equals("")|| !messsage.getText().toString().equals("") ){



        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Phone,null,Message,null,null);
        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();

    }
else {
    Toast.makeText(this,"Please enter detail",Toast.LENGTH_SHORT).show();

}





}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
            if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Message();
            }
            else
            {
                Toast.makeText(this,"Please request permission",Toast.LENGTH_SHORT).show();

            }

        }
    }


}