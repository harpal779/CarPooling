package com.example.carpooling;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Driver3 extends AppCompatActivity {
    Button h, l;
    RecyclerView recyclerView1;
    FirebaseDatabase Database;
    FirebaseUser user;
    String name;
    DatabaseReference databasereference,data;
    FirebaseAuth firebaseAuth;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver3);
        h = findViewById(R.id.button13);
        l = findViewById(R.id.button14);
        recyclerView1 = findViewById(R.id.recyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));



      databasereference = FirebaseDatabase.getInstance().getReference("Ride").child(FirebaseAuth.getInstance().getCurrentUser().getUid());



    }

    public void Homee(View view) {
        Intent intent = new Intent(Driver3.this, Driver1.class);
        startActivity(intent);
        finish();
    }

    public void Logoutt(View view) {
        Intent intent = new Intent(Driver3.this, Login.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Upload2> options1 =
                new FirebaseRecyclerOptions.Builder<Upload2>()
                        .setQuery(databasereference,Upload2.class)

                        .build();


        FirebaseRecyclerAdapter<Upload2,ViewHolder2> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Upload2, ViewHolder2>(options1) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder2 holder, int position, @NonNull Upload2 model) {
                        holder.setData(getApplicationContext(),model.getCarname(),model.getPhone(),model.getModel(),model.getFrom(),model.getTo(),model.getDate(),model.getTime(),model.getAvailableSeats(),model.getPrice());
                        holder.setOnClickListener(new ViewHolder.Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {
                                name = getItem(position).getPhone();
                                showDeleteDataDialog(name);
                            }
                        });
                    }



                    @NonNull
                    @Override
                    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.row,parent,false);

                        return new ViewHolder2(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        recyclerView1.setAdapter(firebaseRecyclerAdapter);

    }

    private void showDeleteDataDialog(final String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(Driver3.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you Sure to Delete this Data");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = databasereference.orderByChild("phone").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            ds.getRef().child("Ride").removeValue();
                            Toast.makeText(Driver3.this, "Data deleted", Toast.LENGTH_SHORT).show();

                        }
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

}
