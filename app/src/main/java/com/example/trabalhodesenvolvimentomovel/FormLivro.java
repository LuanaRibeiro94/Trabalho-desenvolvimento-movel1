package com.example.trabalhodesenvolvimentomovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhodesenvolvimentomovel.dao.LivroDao;
import com.example.trabalhodesenvolvimentomovel.modelo.Livro;

public class FormLivro extends AppCompatActivity {
    EditText titulo;
    EditText autor;
    EditText editora;
    EditText ano;
    Button variavelL;

    Livro livro, altLivro;
    LivroDao livroDao;

    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livro);

        Intent i= getIntent();
        altLivro = (Livro) i.getSerializableExtra("livro-enviado");
        livro = new Livro();
        livroDao = new LivroDao(FormLivro.this);

        titulo = findViewById(R.id.edtTitulo);
        autor = findViewById(R.id.edtAutor);
        editora = findViewById(R.id.edtEditora);
        ano = findViewById(R.id.edtAnoPublicacao);
        variavelL = findViewById(R.id.btnVariavelL);

        if(altLivro != null ){
            variavelL.setText("Alterar");
            titulo.setText(altLivro.getTitulo());
            autor.setText(altLivro.getAutor()+"");
            editora.setText(altLivro.getEditora());
            ano.setText(altLivro.getAno());

            livro.setId(altLivro.getId());
        } else {
            variavelL.setText("Salvar");
        }

        variavelL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro.setTitulo(titulo.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setEditora(editora.getText().toString());
                livro.setAno(ano.getText().toString());

                if (variavelL.getText().toString().equals("Salvar")){
                    retornoDB = livroDao.salvarLivro(livro);
                    livroDao.close();

                    if ( retornoDB == -1 ) {
                        alert("Erro ao cadastrar");
                    } else {
                        alert("Cadastro realizado com sucesso");
                    }
                } else {
                    retornoDB = livroDao.alterarLivro(livro);
                    livroDao.close();

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
