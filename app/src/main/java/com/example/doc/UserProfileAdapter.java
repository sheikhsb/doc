package com.example.doc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.Holder> {

    ArrayList<UserProfile> profiles;

    public UserProfileAdapter(ArrayList<UserProfile> data)
    {
        profiles = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.single_layout_profile, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvLetter.setText("" + profiles.get(position).getName().charAt(0));
        holder.tvName.setText(profiles.get(position).getName());
        holder.tvRelationship.setText(profiles.get(position).getRelationship());


    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {

        TextView tvLetter, tvName, tvRelationship;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvLetter = itemView.findViewById(R.id.tvLetter);
            tvName = itemView.findViewById(R.id.tvName);
            tvRelationship = itemView.findViewById(R.id.tvRelationship);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}