package com.example.trabalhodesenvolvimentomovel;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhodesenvolvimentomovel.dao.ClienteDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;

import java.util.ArrayList;

public class ListagemClientes extends AppCompatActivity {
    ListView listaClientes;
    Button btnNovoCadastro;
    Cliente cliente;
    ClienteDao clienteDao;
    ArrayList<Cliente> arrayListCliente;
    ArrayAdapter<Cliente> arrayAdapterCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_clientes);

        setTitle("Listagem");

        listaClientes = findViewById(R.id.listClientes);
        registerForContextMenu(listaClientes);

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListagemClientes.this, FormCliente.class);
                startActivity(i);
            }
        });

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clienteEnviado = (Cliente) arrayAdapterCliente.getItem(position);

                Intent i = new Intent(ListagemClientes.this, FormCliente.class);
                i.putExtra("cliente-enviado", clienteEnviado);
                startActivity(i);
            }
        });

        listaClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cliente = arrayAdapterCliente.getItem(position);
                return false;
            }
        });
    }

    public void populaLista() {
        clienteDao = new ClienteDao(ListagemClientes.this);

        arrayListCliente = clienteDao.selectAllClientes();
        clienteDao.close();

        if (listaClientes != null) {
            arrayAdapterCliente = new ArrayAdapter<Cliente>(ListagemClientes.this, android.R.layout.simple_list_item_1, arrayListCliente);

            listaClientes.setAdapter(arrayAdapterCliente);
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
            clienteDao = new ClienteDao(ListagemClientes.this);
            retornoDB = clienteDao.excluirCliente(cliente);
            clienteDao.close();

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
