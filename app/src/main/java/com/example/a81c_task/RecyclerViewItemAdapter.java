package com.example.a81c_task;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewItemAdapter.MyViewHolder> {
    private ArrayList<UserData> itemList;

    public RecyclerViewItemAdapter(ArrayList<UserData> itemList)
    {
        this.itemList = itemList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView urlView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            urlView = itemView.findViewById(R.id.urlLinkTextView);
        }
    }
    @NonNull
    @Override
    public RecyclerViewItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemAdapter.MyViewHolder holder, int position) {
        String url = itemList.get(position).getUserLink();
        holder.urlView.setText(url);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

