package com.example.recyclerviewprac1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class placeToGoRecyclerViewAdapter extends RecyclerView.Adapter<placeToGoRecyclerViewAdapter.placeToGoViewHolder> {

    private List<placeToGo> placeToGoList;
    private Context placeToGocontext;

    private OnRowClickListener listener;

    public placeToGoRecyclerViewAdapter(List<placeToGo> placeToGoList, Context placeToGocontext, OnRowClickListener clickListener) {
        this.placeToGoList = placeToGoList;
        this.placeToGocontext = placeToGocontext;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public placeToGoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(placeToGocontext).inflate(R.layout.placetogo_row, parent, false);
        return new placeToGoViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull placeToGoViewHolder holder, int position) {
        holder.placeToGoImageView.setImageResource(placeToGoList.get(position).getImage());
        holder.nameTextView.setText(placeToGoList.get(position).getName());
        holder.descriptionTextView.setText(placeToGoList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return placeToGoList.size();
    }

    public class placeToGoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView placeToGoImageView;
        private TextView nameTextView, descriptionTextView;
        public OnRowClickListener onRowClickListener;

        public placeToGoViewHolder(@NonNull View itemView, OnRowClickListener onRowClickListener) {
            super(itemView);
            placeToGoImageView = itemView.findViewById(R.id.verticalImageView);
            nameTextView = itemView.findViewById(R.id.placeToGoNametextView);
            descriptionTextView = itemView.findViewById(R.id.placeToGoDescriptiontextView);
            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRowClickListener {
        void onItemClick(int position);
    }
}
