package com.example.trabalhodesenvolvimentomovel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhodesenvolvimentomovel.dao.ClienteDao;
import com.example.trabalhodesenvolvimentomovel.dao.EmprestimoDao;
import com.example.trabalhodesenvolvimentomovel.dao.LivroDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;
import com.example.trabalhodesenvolvimentomovel.modelo.Emprestimo;
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

    Emprestimo emprestimo, altEmprestimo;
    EmprestimoDao emprestimoDao;

    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_emprestimo);

        sCliente = findViewById(R.id.spnCliente);
        sLivro = findViewById(R.id.spnLivro);
        dataEmp = findViewById(R.id.edtDataEmp);
        dataDev = findViewById(R.id.edtDataDev);
        btVariavelE = findViewById(R.id.btnVariavelE);

        Intent i= getIntent();
        altEmprestimo = (Emprestimo) i.getSerializableExtra("emprestimo-enviado");

        emprestimoDao = new EmprestimoDao(FormEmprestimo.this);

        clienteDao = new ClienteDao(FormEmprestimo.this);
        listCliente = clienteDao.selectAllClientes();
        arrayAdapterCliente = new ArrayAdapter<Cliente>(this, android.R.layout.simple_spinner_item, listCliente);

        sCliente.setAdapter(arrayAdapterCliente);

        livroDao = new LivroDao(FormEmprestimo.this);
        listLivro = livroDao.selectAllLivro();
        arrayAdapterLivro = new ArrayAdapter<Livro>(this, android.R.layout.simple_spinner_item, listLivro);

        sLivro.setAdapter(arrayAdapterLivro);

        emprestimo = new Emprestimo();

        if(altEmprestimo != null ){
            btVariavelE.setText("Alterar");

            sCliente.setSelection(arrayAdapterCliente.getPosition(altEmprestimo.getCliente()));
            sLivro.setSelection(arrayAdapterLivro.getPosition(altEmprestimo.getLivro()));
            dataEmp.setText(altEmprestimo.getData_emp());
            dataDev.setText(altEmprestimo.getData_dev());
            emprestimo.setId(altEmprestimo.getId());
        } else {
            btVariavelE.setText("Salvar");
        }

        btVariavelE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cliente cliente = (Cliente) sCliente.getSelectedItem();
                Livro livro = (Livro) sLivro.getSelectedItem();

                emprestimo.setCliente(cliente);
                emprestimo.setLivro(livro);
                emprestimo.setData_emp(dataEmp.getText().toString());
                emprestimo.setData_dev(dataDev.getText().toString());

                if (btVariavelE.getText().toString().equals("Salvar")){
                    retornoDB = emprestimoDao.salvarEmprestimo(emprestimo);
                    emprestimoDao.close();

                    if ( retornoDB == -1 ) {
                        alert("Erro ao cadastrar");
                    } else {
                        alert("Cadastro realizado com sucesso");
                    }
                } else {
                    retornoDB = emprestimoDao.alterarEmprestimo(emprestimo);
                    emprestimoDao.close();

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
