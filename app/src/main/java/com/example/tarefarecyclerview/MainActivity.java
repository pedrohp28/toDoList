package com.example.tarefarecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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


    //
    EditText edtTarefa;
    EditText edtDescricao;
    Button btnSalvar;
    Button btnListaTarefas;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTarefa = findViewById(R.id.edtTarefa);
        edtDescricao = findViewById(R.id.edtDescricao);
        btnSalvar = findViewById(R.id.btnSalvarTarefa);
        btnListaTarefas = findViewById(R.id.btnListaTarefas);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarTarefa();
            }
        });

        btnListaTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListaTarefasActivity();
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
        Toast.makeText(getApplicationContext(), "Tarefa salva!", Toast.LENGTH_LONG).show();
    }

    private void openListaTarefasActivity() {
        Intent intent = new Intent(this, ListaTarefasActivity.class);
        startActivity(intent);
    }
}