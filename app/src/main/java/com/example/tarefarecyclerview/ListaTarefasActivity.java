package com.example.tarefarecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tarefarecyclerview.adapter.ItemAdapter;
import com.example.tarefarecyclerview.model.ItemModel;
import com.example.tarefarecyclerview.vh.ItemVH;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaTarefasActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ItemAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference userRef;
    String usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefas);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);
        buscarItens();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHandler(0, ItemTouchHelper.RIGHT));
        helper.attachToRecyclerView(recyclerView);
    }

    private  void buscarItens() {

        usuarioId = FirebaseAuth.getInstance().getUid();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("Usuario").child(usuarioId).child("Tarefas");
        Query tarefasQuery = userRef.orderByChild("nome");

        tarefasQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ItemModel> itens = new ArrayList<>();
                for (DataSnapshot dados : snapshot.getChildren()) {

                    ItemModel item = dados.getValue(ItemModel.class);
                    item.setKey(dados.getKey());
                    itens.add(item);
                }
                adapter.setItems(itens);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro na busca" + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback{
        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getBindingAdapterPosition();
            int to = target.getBindingAdapterPosition();

            Collections.swap(adapter.getTarefas(), from, to);
            adapter.notifyItemMoved(from, to);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            String key = adapter.getTarefa(viewHolder.getBindingAdapterPosition()).getKey();
            userRef = database.getReference("Usuario").child(usuarioId).child("Tarefas").child(key);
            userRef.removeValue();
            adapter.getTarefas().remove(viewHolder.getBindingAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getBindingAdapterPosition());
            usuarioId = FirebaseAuth.getInstance().getUid();
            database = FirebaseDatabase.getInstance();
        }
    }
}