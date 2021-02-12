package com.example.carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpooling.ViewHolder.Clicklistener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Passenger2 extends AppCompatActivity {
Button back,logout;
    RecyclerView recyclerView1;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    private FirebaseAuth firebaseAuth;
    private String auth,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger2);
        back=findViewById(R.id.button19);
        logout=findViewById(R.id.button20);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView1 = findViewById(R.id.recyclerView2);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        auth=firebaseAuth.getCurrentUser().getUid();
        databasereference = firebaseDatabase.getInstance().getReference().child("Ride");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });



    }

    private void logout() {
        Intent intent = new Intent(Passenger2.this, Login.class);
        startActivity(intent);
        finish();

    }

    private void back() {

        Intent intent = new Intent(Passenger2.this, Interface.class);
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
                    protected void onBindViewHolder(@NonNull final ViewHolder2 holder, int position, @NonNull final Upload2 model) {
                        holder.setData(getApplicationContext(),model.getCarname(),model.getPhone(),model.getModel(),model.getFrom(),model.getTo(),model.getDate(),model.getTime(),model.getAvailableSeats(),model.getPrice());
                        holder.setOnClickListener(new Clicklistener() {
                            @Override
                            public void onItemlongClick(View view, int position) {
                                DatabaseReference ds = firebaseDatabase.getInstance().getReference("Ride");
                                ds.removeValue();

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




                }
