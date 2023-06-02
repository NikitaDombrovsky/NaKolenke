package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.models.Catalog;

import java.util.List;

public class CatalogAdapterT extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Object> listRecyclerItem;

    public CatalogAdapterT(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView id, category, name, description, price, time_result, preparation, bio;
        public ItemViewHolder(View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.id);
            category=(TextView) itemView.findViewById(R.id.category);
            name=(TextView) itemView.findViewById(R.id.name);
            description=(TextView) itemView.findViewById(R.id.description);
            price=(TextView) itemView.findViewById(R.id.price);
            time_result=(TextView) itemView.findViewById(R.id.time_result);
            preparation=(TextView) itemView.findViewById(R.id.preparation);
            bio=(TextView) itemView.findViewById(R.id.bio);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Создаем представление из Layout
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        return new ItemViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder _holder = (ItemViewHolder) holder;
        Catalog catalog = (Catalog) listRecyclerItem.get(position);

        _holder.name.setText(catalog.getName());

        _holder.id.setText(catalog.getId());
        _holder.category.setText(catalog.getCategory());
        _holder.name.setText(catalog.getName());
        _holder.description.setText(catalog.getDescription());
        _holder.price.setText(catalog.getPrice());
        _holder.time_result.setText(catalog.getTime_result());
        _holder.preparation.setText(catalog.getPreparation());
        _holder.bio.setText(catalog.getBio());
        // Пример реализации setOnClickListener для этот представления
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Отобразить в тосте название выбранного элементаж
                Toast.makeText(context, catalog.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }


}