package com.example.trabalhodesenvolvimentomovel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListagemEmprestimos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_emprestimos);
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
}
