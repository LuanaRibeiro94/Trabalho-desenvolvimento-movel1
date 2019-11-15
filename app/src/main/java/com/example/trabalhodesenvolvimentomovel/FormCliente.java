package com.example.trabalhodesenvolvimentomovel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhodesenvolvimentomovel.dao.ClienteDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;


public class FormCliente extends AppCompatActivity {
    EditText nome;
    EditText cpf;
    EditText email;
    EditText telefone;
    Button variavel;

    Cliente cliente, altCliente;
    ClienteDao clienteDao;

    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);

        Intent i= getIntent();
        altCliente = (Cliente) i.getSerializableExtra("cliente-enviado");
        cliente = new Cliente();
        clienteDao = new ClienteDao(FormCliente.this);

        nome = findViewById(R.id.edtNome);
        cpf = findViewById(R.id.edtCpf);
        email = findViewById(R.id.edtEmail);
        telefone = findViewById(R.id.edtTelefone);
        variavel = findViewById(R.id.btnVariavel);

        if(altCliente != null ){
            variavel.setText("Alterar");
            nome.setText(altCliente.getNome());
            cpf.setText(altCliente.getCpf()+"");
            email.setText(altCliente.getEmail());
            telefone.setText(altCliente.getTelefone());

            cliente.setId(altCliente.getId());
        } else {
            variavel.setText("Salvar");
        }

        variavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente.setNome(nome.getText().toString());
                cliente.setCpf(cpf.getText().toString());
                cliente.setEmail(email.getText().toString());
                cliente.setTelefone(telefone.getText().toString());

                if (variavel.getText().toString().equals("Salvar")){
                    retornoDB = clienteDao.salvarCliente(cliente);
                    clienteDao.close();

                    if ( retornoDB == -1 ) {
                        alert("Erro ao cadastrar");
                    } else {
                        alert("Cadastro realizado com sucesso");
                    }
                } else {
                    retornoDB = clienteDao.alterarCliente(cliente);
                    clienteDao.close();

                    if (retornoDB == -1) {
                        alert("Erro ao cadastrar");
                    } else {
                        alert("Atualização realizada com sucesso");
                    }
                }

                finish();
            }
        });
    }
    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}