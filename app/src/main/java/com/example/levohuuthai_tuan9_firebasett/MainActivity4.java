package com.example.levohuuthai_tuan9_firebasett;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {
    ImageView imgXanh, imgDo, imgVang;
    Button btnFinish;
    private DatabaseReference mDatabase;
    int sad, normal, happy;
    String name = "ThaiLe";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imgXanh = findViewById(R.id.imgXanh);
        imgDo = findViewById(R.id.imgDo);
        imgVang = findViewById(R.id.imgVang);
        btnFinish = findViewById(R.id.btnFinish);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        email = MainActivity2.email;
        name = MainActivity3.name;
        Query query = mDatabase.orderByChild("Email").equalTo(email);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    name = ds.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
/*
        mDatabase.child(name).child("Cảm xúc").child("Normal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                normal = Integer.parseInt(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        imgVang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal++;
                mDatabase.child(name).child("Cảm xúc").child("Normal").setValue(normal);
                mDatabase.child(name).push().child("Email").setValue(email);
            }
        });
        /*
        mDatabase.child(name).child("Cảm xúc").child("Sad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sad = Integer.parseInt(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        imgDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sad++;
                mDatabase.child(name).child("Cảm xúc").child("Sad").setValue(sad);
                
            }
        });




    }
}