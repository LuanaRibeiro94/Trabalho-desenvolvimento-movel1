package com.example.trabalhodesenvolvimentomovel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.trabalhodesenvolvimentomovel.dao.EmprestimoDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Emprestimo;

import java.util.ArrayList;

public class ListagemEmprestimos extends AppCompatActivity {
    ListView listaEmprestimos;
    EmprestimoDao emprestimoDao;
    ArrayList<Emprestimo> arrayListEmprestimo;
    ArrayAdapter<Emprestimo> arrayAdapterEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_emprestimos);

        listaEmprestimos = findViewById(R.id.listEmprestimos);
        registerForContextMenu(listaEmprestimos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listagem_emprestimos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuFecharApp: {
                finish();
                break;
            }
            case R.id.menuCliente: {
                Intent i = new Intent(this, ListagemClientes.class);
                startActivityForResult(i, 1);
                break;
            }
            case R.id.menuLivro: {
                Intent i = new Intent(this, ListagemLivros.class);
                startActivityForResult(i, 1);
                break;
            }
            case R.id.menuEmprestimo: {
                Intent i = new Intent(this, FormEmprestimo.class);
                startActivityForResult(i, 1);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    public void populaLista() {
        emprestimoDao = new EmprestimoDao(ListagemEmprestimos.this);

        arrayListEmprestimo = emprestimoDao.selectAllEmprestimos();
        emprestimoDao.close();

        if (listaEmprestimos != null) {
            arrayAdapterEmprestimo = new ArrayAdapter<Emprestimo>(
                    ListagemEmprestimos.this,
                    android.R.layout.simple_list_item_1,
                    arrayListEmprestimo
            );

            listaEmprestimos.setAdapter(arrayAdapterEmprestimo);
        }
    }
}
