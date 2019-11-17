package com.example.trabalhodesenvolvimentomovel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhodesenvolvimentomovel.dao.ClienteDao;
import com.example.trabalhodesenvolvimentomovel.dao.LivroDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;
import com.example.trabalhodesenvolvimentomovel.modelo.Livro;

import java.util.ArrayList;

public class FormEmprestimo extends AppCompatActivity {
    Spinner sCliente;
    Spinner sLivro;
    EditText dataEmp;
    EditText dataDev;
    Button btVariavelE;

    ArrayList<Cliente> listCliente;
    ClienteDao clienteDao;
    ArrayAdapter<Cliente> arrayAdapterCliente;

    ArrayList<Livro> listLivro;
    LivroDao livroDao;
    ArrayAdapter<Livro> arrayAdapterLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_emprestimo);

        sCliente = findViewById(R.id.spnCliente);
        sLivro = findViewById(R.id.spnLivro);
        dataEmp = findViewById(R.id.edtDataEmp);
        dataDev = findViewById(R.id.edtDataDev);
        btVariavelE = findViewById(R.id.btnVariavelE);

        clienteDao = new ClienteDao(FormEmprestimo.this);
        listCliente = clienteDao.selectAllClientes();
        arrayAdapterCliente = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, listCliente);

        sCliente.setAdapter(arrayAdapterCliente);

        livroDao = new LivroDao(FormEmprestimo.this);
        listLivro = livroDao.selectAllLivro();
        arrayAdapterLivro = new ArrayAdapter<Livro>(this, android.R.layout.simple_spinner_item, listLivro);

        sLivro.setAdapter(arrayAdapterLivro);
    }
}
