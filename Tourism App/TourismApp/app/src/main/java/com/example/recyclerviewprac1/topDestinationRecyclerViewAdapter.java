package com.example.recyclerviewprac1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class topDestinationRecyclerViewAdapter extends RecyclerView.Adapter<topDestinationRecyclerViewAdapter.DestinationViewHolder> {

    private List<topDestination> topDestinationsList;
    private Context topDestinationcontext;

    public topDestinationRecyclerViewAdapter(List<topDestination> topDestinationsList, Context topDestinationcontext) {
        this.topDestinationsList = topDestinationsList;
        this.topDestinationcontext = topDestinationcontext;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(topDestinationcontext).inflate(R.layout.topdestination_row, parent, false);
        return new DestinationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        holder.topDestinationImageView.setImageResource(topDestinationsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return topDestinationsList.size();
    }

    public class DestinationViewHolder extends RecyclerView.ViewHolder {
        private ImageView topDestinationImageView;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            topDestinationImageView = itemView.findViewById(R.id.horizontalImageView);
        }
    }
}
