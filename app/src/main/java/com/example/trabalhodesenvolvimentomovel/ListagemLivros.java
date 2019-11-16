package com.example.trabalhodesenvolvimentomovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.trabalhodesenvolvimentomovel.dao.LivroDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Livro;

import java.util.ArrayList;

public class ListagemLivros extends AppCompatActivity {
    ListView listaLivros;
    Button btnNovoCadastro;
    Livro livro;
    LivroDao livroDao;
    ArrayList<Livro> arrayListLivro;
    ArrayAdapter<Livro> arrayAdapterLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_livros);

        listaLivros = findViewById(R.id.listLivros);
        registerForContextMenu(listaLivros);

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListagemLivros.this, FormLivro.class);
                startActivity(i);
            }
        });

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livroEnviado = (Livro) arrayAdapterLivro.getItem(position);

                Intent i = new Intent(ListagemLivros.this, FormLivro.class);
                i.putExtra("livro-enviado", livroEnviado);
                startActivity(i);
            }
        });

        listaLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                livro = arrayAdapterLivro.getItem(position);
                return false;
            }
        });
    }

    public void populaLista() {
        livroDao = new LivroDao(ListagemLivros.this);

        arrayListLivro = livroDao.selectAllLivro();
        livroDao.close();

        if (listaLivros != null) {
            arrayAdapterLivro = new ArrayAdapter<Livro>(ListagemLivros.this, android.R.layout.simple_list_item_1, arrayListLivro);

            listaLivros.setAdapter(arrayAdapterLivro);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        populaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add("Deletar registro");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                long retornoDB;
                livroDao = new LivroDao(ListagemLivros.this);
                retornoDB = livroDao.excluirLivro(livro);
                livroDao.close();

                if (retornoDB == -1 ){
                    alert("Erro de exclusão");
                } else {
                    alert("Registro excluído com sucesso!");
                }
                populaLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
