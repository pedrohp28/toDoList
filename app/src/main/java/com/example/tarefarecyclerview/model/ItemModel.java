package com.example.tarefarecyclerview.model;

public class ItemModel {
    private String key;
    private String nome;
    private String descricao;

    public ItemModel() {
    }

    public ItemModel(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "key='" + key + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
