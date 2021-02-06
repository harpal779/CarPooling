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



    EditText password1,email1;
    Button button_id,b16;
    RecyclerView recyclerView1;
    FirebaseDatabase firebaseDatabase;
  private  DatabaseReference databasereference;
    Upload upload;
    String name ;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);
        firebaseAuth = FirebaseAuth.getInstance();
        b16 = findViewById(R.id.button16);
        password1 = findViewById(R.id.password);
        email1 = findViewById(R.id.email);
        upload = new Upload();
        button_id = findViewById(R.id.button_save);
        recyclerView1 = findViewById(R.id.recyclerview_main);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);


        button_id.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Register();

            }
        });
    }

    public void Register() {
        final String e = email1.getText().toString();
        final String p = password1.getText().toString();

        databasereference = firebaseDatabase.getInstance().getReference().child("Users");
        upload.setPassword(password1.getText().toString());
        upload.setEmail(email1.getText().toString());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        String id = databasereference.push().getKey();
        databasereference.child(id).setValue(upload);

        firebaseAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(AccountView.this, "Successfully registered", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(AccountView.this, "Sign up fail!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Upload>options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"),Upload.class)
                        .build();


        FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Upload, ViewHolder>(options) {
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

    private void showDeleteDataDialog(final String email){
        AlertDialog .Builder builder = new AlertDialog.Builder(AccountView.this);
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
