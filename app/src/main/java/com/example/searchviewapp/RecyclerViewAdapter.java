package com.example.searchviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    private List<FoodModel> allItems;
    private List<FoodModel> allItemsFilter;
    private Context context;

    public RecyclerViewAdapter(List<FoodModel> allItems, Context context) {
        this.allItems = allItems;
        this.context = context;
        allItemsFilter=new ArrayList<>(allItems);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodModel foodModel=allItems.get(position);
        holder.foodtextView.setText(foodModel.getFoodName());
        holder.foodimgView.setImageResource(foodModel.getFoodImg());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,foodModel.getFoodName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    @Override
    public Filter getFilter() {
        return allItemFilter;
    }

    private Filter allItemFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<FoodModel> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
               filteredList.addAll(allItemsFilter);
            }
            else{
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(FoodModel model:allItemsFilter){
                    if(model.getFoodName().toLowerCase().contains(filterPattern)){
                        filteredList.add(model);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allItems.clear();
            allItems.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodtextView;
        ImageView foodimgView;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimgView=itemView.findViewById(R.id.foodimgView);
            foodtextView=itemView.findViewById(R.id.foodtextView);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
