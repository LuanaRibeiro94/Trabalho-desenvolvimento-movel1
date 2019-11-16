package com.example.trabalhodesenvolvimentomovel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ListagemLivros extends AppCompatActivity {
    Button btnNovoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_livros);

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListagemLivros.this, FormLivro.class);
                startActivity(i);
            }
        });
    }
}
