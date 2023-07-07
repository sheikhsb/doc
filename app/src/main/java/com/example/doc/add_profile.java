package com.example.doc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class add_profile extends AppCompatActivity {

    TextView etName,etNumber,etAge;
    Spinner etRelationship,etGender;
    Button btnAddProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activtiy_add_profile);
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");

        init();

        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValid = true;
                if (etName.getText().toString().isEmpty())
                    etName.setError("Name cannot be blank");
                else if (etNumber.getText().toString().isEmpty())
                    etNumber.setError("Name cannot be blank");
                else if (etAge.getText().toString().isEmpty())
                    etAge.setError("Name cannot be blank");
                else
                {
                    String name,number,age,relationship,gender;
                    name = etName.getText().toString();
                    number = etNumber.getText().toString();
                    age = etAge.getText().toString();
                    relationship = etRelationship.getSelectedItem().toString();
                    gender = etGender.getSelectedItem().toString();


                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", name);
                    data.put("number",number);
                    data.put("age", age);
                    data.put("relationship", relationship);
                    data.put("gender", gender);


                    FirebaseDatabase.getInstance().
                            getReference()
                            .child("user").child("patients").child(user).child("profile")
                            .push()
                            .updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(add_profile.this, "Student Added Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent profile = new Intent(add_profile.this,profile.class);
                                    startActivity(profile);
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(add_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });








                }

            }
        });




    }
    void init()
    {
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etAge = findViewById(R.id.etAge);
        etRelationship = findViewById(R.id.etRelationship);
        etGender = findViewById(R.id.etGender);
        btnAddProfile = findViewById(R.id.btnAddProfile);

        ArrayAdapter<String> adapterRelationship = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterRelationship.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterRelationship.add("Mother");
        adapterRelationship.add("Father");
        adapterRelationship.add("Spouse");
        adapterRelationship.add("Daughter");
        adapterRelationship.add("Son");
        adapterRelationship.add("Other");
        etRelationship.setAdapter(adapterRelationship);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterGender.add("Male");
        adapterGender.add("Female");
        etGender.setAdapter(adapterGender);

    }
}