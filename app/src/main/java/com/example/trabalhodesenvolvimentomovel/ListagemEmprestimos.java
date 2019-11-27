package com.example.trabalhodesenvolvimentomovel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trabalhodesenvolvimentomovel.dao.EmprestimoDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Emprestimo;

import java.util.ArrayList;

public class ListagemEmprestimos extends AppCompatActivity {
    ListView listaEmprestimos;
    EmprestimoDao emprestimoDao;
    Emprestimo emprestimo;
    ArrayList<Emprestimo> arrayListEmprestimo;
    ArrayAdapter<Emprestimo> arrayAdapterEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_emprestimos);

        listaEmprestimos = findViewById(R.id.listEmprestimos);
        registerForContextMenu(listaEmprestimos);

        listaEmprestimos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Emprestimo emprestimoEnviado = (Emprestimo) arrayAdapterEmprestimo.getItem(position);

            Intent i = new Intent(ListagemEmprestimos.this, FormEmprestimo.class);
            i.putExtra("emprestimo-enviado", emprestimoEnviado);
            startActivity(i);
            }
        });

        listaEmprestimos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            emprestimo = arrayAdapterEmprestimo.getItem(position);
            return false;
            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add("Deletar registro");

        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
            long retornoDB;
            emprestimoDao = new EmprestimoDao(ListagemEmprestimos.this);
            retornoDB = emprestimoDao.excluirEmprestimo(emprestimo);
            emprestimoDao.close();

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

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
