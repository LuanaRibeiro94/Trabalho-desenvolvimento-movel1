package com.example.trabalhodesenvolvimentomovel.modelo;

import java.io.Serializable;

public class Emprestimo implements Serializable {

    private int id;
    private Cliente cliente;
    private Livro livro;
    private String data_emp;
    private String data_dev;

    public Emprestimo() {
        this.cliente = new Cliente();
        this.livro = new Livro();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_emp() {
        return data_emp;
    }

    public void setData_emp(String data_emp) {
        this.data_emp = data_emp;
    }

    public String getData_dev() {
        return data_dev;
    }

    public void setData_dev(String data_dev) {
        this.data_dev = data_dev;
    }

    @Override
    public String toString() {
        return "Nome: " + this.cliente.getNome() + " Livro: " + this.livro.getTitulo();
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return this.livro;
    }
}
