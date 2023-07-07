package com.example.doc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {

    private DatabaseReference profileRef;
    private ValueEventListener valueEventListener;

    private RecyclerView recyclerView;
    private UserProfileAdapter userProfileAdapter;
    private ArrayList<UserProfile> profileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activtiy_profile);


        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");


        recyclerView = findViewById(R.id.rvList);


        userProfileAdapter = new UserProfileAdapter(profileList);


        recyclerView.setAdapter(userProfileAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        profileRef = FirebaseDatabase.getInstance().getReference().child("user").child("patients").child(user).child("profile");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserProfile userProfile = snapshot.getValue(UserProfile.class);
                    if(userProfile == null)
                        return;

                    profileList.add(userProfile);
                }


                userProfileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(profile.this, "Error retrieving data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        profileRef.addValueEventListener(valueEventListener);



    }
    public void addProfile(View view)
    {
        Intent addProfileActivity = new Intent(profile.this, add_profile.class);
        startActivity(addProfileActivity);
        finish();
    }
}