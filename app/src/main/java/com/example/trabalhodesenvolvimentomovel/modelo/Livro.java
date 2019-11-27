package com.example.trabalhodesenvolvimentomovel.modelo;

import java.io.Serializable;

public class Livro implements Serializable {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private String ano;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString(){

        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Livro)) {
            return false;
        }

        Livro livro = (Livro) o;

        return livro.id == this.id;
    }
}
