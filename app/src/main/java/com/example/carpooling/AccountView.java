package com.example.carpooling;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class AccountView extends Admin {



    EditText name_id,age,gender;
    Button button_id,b16;
    RecyclerView recyclerView1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    Upload upload;
    String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);
b16=findViewById(R.id.button16);
        name_id = findViewById(R.id.editText_name);
        age = findViewById(R.id.editText_age);
        gender = findViewById(R.id.editText_gender);
        upload = new Upload();
        button_id = findViewById(R.id.button_save);
        recyclerView1 = findViewById(R.id.recyclerview_main);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        database= firebaseDatabase.getInstance().getReference().child("Users");


        button_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload.setName(name_id.getText().toString());
                upload.setAge(age.getText().toString());
                upload.setGender(gender.getText().toString());

                String id = database.push().getKey();
                database.child(id).setValue(upload);
                Toast.makeText(AccountView.this, "Data saved", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Upload>options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                        .setQuery(database,Upload.class)
                        .build();


        FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Upload, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Upload model) {
                        holder.setData(getApplicationContext(),model.getName(),model.getAge(),model.getGender());

                        holder.setOnClickListener(new ViewHolder.Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {

                                name = getItem(position).getName();

                                showDeleteDataDialog(name);
                            }
                        });


                    }


                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.row,parent,false);

                        return new ViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView1.setAdapter(firebaseRecyclerAdapter);

    }

    private void showDeleteDataDialog(final String name){
        AlertDialog .Builder builder = new AlertDialog.Builder(AccountView.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you Sure to Delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = database.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(AccountView.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void admin(View view) {
        Intent intent=new Intent(AccountView.this,Admin.class);
        startActivity(intent);
        finish();
    }
}