package com.example.carpooling;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class AccountView extends Admin {


    Button b16;
    RecyclerView recyclerView1;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databasereference;
    Upload upload;
    String name;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);
        firebaseAuth = FirebaseAuth.getInstance();
        b16 = findViewById(R.id.button16);
        recyclerView1 = findViewById(R.id.recyclerview_main);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        databasereference = FirebaseDatabase.getInstance().getReference("Users");

    }

        @Override
        protected void onStart() {
            super.onStart();

            FirebaseRecyclerOptions<Upload> options1 =
                    new FirebaseRecyclerOptions.Builder<Upload>()
                            .setQuery(databasereference,Upload.class)

                            .build();


            FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Upload, ViewHolder>(options1) {
                        @Override
                        protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Upload model) {
                            holder.setData(getApplicationContext(),model.getPassword(),model.getEmail());
                            holder.setOnClickListener(new ViewHolder.Clicklistener() {
                                @Override
                                public void onItemlongClick(View view, int position) {
                                    name = getItem(position).getPassword();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(AccountView.this);
            builder.setTitle("Delete");
            builder.setMessage("Are you Sure to Delete this Data");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Query query1 = databasereference.orderByChild("email").equalTo(name);
                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                ds.getRef().removeValue();
                                Toast.makeText(AccountView.this, "Data deleted", Toast.LENGTH_SHORT).show();

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

