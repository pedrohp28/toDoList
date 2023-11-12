package com.example.tarefarecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefarecyclerview.R;
import com.example.tarefarecyclerview.model.ItemModel;
import com.example.tarefarecyclerview.vh.ItemVH;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<ItemModel> lista = new ArrayList<>();

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<ItemModel> items) {
        lista.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemModel item = null;
        this.onBindViewHolder(holder, position, item);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, ItemModel item){
        ItemVH vh = (ItemVH) holder;
        ItemModel i = item == null ? lista.get(position) : item;
        vh.nomeTextView.setText(i.getNome());
        vh.descricaoTextView.setText(i.getDescricao());
        vh.key.setText(i.getKey());
    }

    public ArrayList<ItemModel> getTarefas() {
        return lista;
    }
    public ItemModel getTarefa(int position) {
        return lista.get(position);
    }
    public void clear() {
        lista.clear();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
