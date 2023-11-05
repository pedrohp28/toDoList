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

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    // Lista de itens a serem exibidos na RecyclerView.
    private List<ItemModel> itemList;

    // Contexto da atividade que está usando o adaptador.
    private Context context;

    // Construtor do adaptador.
    public ItemAdapter(Context context, List<ItemModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    // Método chamado quando a RecyclerView precisa criar um novo ViewHolder.
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla (converte) o layout XML do item em uma View.
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

        // Retorna um novo ViewHolder associado à View inflada.
        return new ItemViewHolder(view);
    }

    // Método chamado para associar os dados de um item a um ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Obtém o objeto ItemModel correspondente à posição na lista.
        ItemModel item = itemList.get(position);

        // Define os dados do item nos elementos de interface do ViewHolder.
        holder.nomeTextView.setText(item.getNome());
        holder.descricaoTextView.setText(item.getDescricao());
    }

    // Método chamado para determinar o número total de itens na lista.
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Classe interna que representa um ViewHolder.
//    Um ViewHolder é usado para otimizar o desempenho da RecyclerView, evitando a necessidade
//    de buscar elementos de interface repetidamente durante a rolagem. Em vez disso, os elementos
//    de interface são vinculados ao ViewHolder uma única vez, durante a criação, e reutilizados
//    para cada item exibido na lista, tornando a exibição mais eficiente.
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        // Elementos de interface no layout do item.
        TextView nomeTextView;
        TextView descricaoTextView;

        // Construtor do ViewHolder.
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            // Associa os elementos de interface às variáveis do ViewHolder.
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
        }
    }
}
