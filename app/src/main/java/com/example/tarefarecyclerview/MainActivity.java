package com.example.tarefarecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarefarecyclerview.adapter.ItemAdapter;
import com.example.tarefarecyclerview.model.ItemModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    List<ItemModel> itemList;
    EditText edtTarefa;
    EditText edtDescricao;
    Button btnSalvar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        edtTarefa = findViewById(R.id.edtTarefa);
        edtDescricao = findViewById(R.id.edtDescricao);
        btnSalvar = findViewById(R.id.btnSalvarTarefa);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        buscarTarefas();
        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarTarefa();
            }
        });
    }

    private void salvarTarefa() {
        String tarefa = edtTarefa.getText().toString();
        String descricao = edtDescricao.getText().toString();

        ItemModel item = new ItemModel(tarefa, descricao);

        String usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Usuario");
        userRef.child(usuarioId).child("Tarefas").push().setValue(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList.add(item);
        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
    }

    private void buscarTarefas() {
        String usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Usuario");
        DatabaseReference minhasTarefas = userRef.child(usuarioId).child("Tarefas");

        Query tarefasQuery = minhasTarefas.orderByChild("nome");

        tarefasQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dados: snapshot.getChildren()) {

                    ItemModel item = dados.getValue(ItemModel.class);
                    itemList.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro na busca" + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}