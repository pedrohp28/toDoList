package com.example.tarefarecyclerview.model;

public class ItemModel {
    private String nome;
    private String descricao;

    public ItemModel() {
    }

    public ItemModel(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
