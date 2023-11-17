package com.example.tarefarecyclerview.vh;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tarefarecyclerview.R;
import com.example.tarefarecyclerview.model.ItemModel;

public class ItemVH extends RecyclerView.ViewHolder{

    public TextView nomeTextView;
    public TextView descricaoTextView;
    public ItemVH(@NonNull View itemView) {
        super(itemView);

        nomeTextView = itemView.findViewById(R.id.nomeTextView);
        descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
    }
}
