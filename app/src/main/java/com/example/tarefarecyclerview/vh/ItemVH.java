package com.example.tarefarecyclerview.vh;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefarecyclerview.R;
import com.example.tarefarecyclerview.model.ItemModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemVH extends RecyclerView.ViewHolder{

    public TextView nomeTextView;
    public TextView descricaoTextView;
    public TextView key;
    FirebaseDatabase database;
    DatabaseReference userRef;
    String usuarioId;
    public ItemVH(@NonNull View itemView) {
        super(itemView);

        nomeTextView = itemView.findViewById(R.id.nomeTextView);
        descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
        key = itemView.findViewById(R.id.txtKey);

//        ItemModel item =new ItemModel();
//        item.setKey(key.getText().toString());
//        item.setNome(nomeTextView.getText().toString());
//        item.setDescricao(descricaoTextView.getText().toString());
    }
}
