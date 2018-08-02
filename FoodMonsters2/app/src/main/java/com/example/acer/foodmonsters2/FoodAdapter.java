package com.example.acer.foodmonsters2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<JsonFood> jsonFood1 = new ArrayList<JsonFood>();
    int id;
    Context context;

    public FoodAdapter(Context mainActivity, ArrayList<JsonFood> jsonFood1) {
        this.context = mainActivity;
        this.jsonFood1 = jsonFood1;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        id = R.layout.list_items;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(id, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        holder.tv.setText(jsonFood1.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return jsonFood1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.id_1);
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(context, ItemListActivity.class);
                    JsonFood jf = jsonFood1.get(getAdapterPosition());
                    intent.putExtra("ingredients", jf.getIngredients());
                    intent.putExtra("steps", jf.getSteps());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
