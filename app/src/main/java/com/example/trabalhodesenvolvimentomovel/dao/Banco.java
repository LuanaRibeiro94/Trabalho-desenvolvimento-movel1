package com.example.trabalhodesenvolvimentomovel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Banco extends SQLiteOpenHelper {
    private static final String BANCO = "DBBiblioteca.db";

    private static final String SQL_CLIENTE = "CREATE TABLE tb_cliente ( "
                                                + "id_cliente integer primary key autoincrement, "
                                                + "nome text, "
                                                + "cpf text, "
                                                + "email text, "
                                                + "telefone text );";

    private static final String SQL_LIVRO = "CREATE TABLE tb_livro ( "
                                                + "id_livro integer primary key autoincrement, "
                                                + "titulo text, "
                                                + "autor text, "
                                                + "editora text, "
                                                + "ano text );";

    public static final String SQL_EMPRESTIMO = "CREATE TABLE tb_emprestimo ( "
                                                + "id_emprestimo integer primary key autoincrement, "
                                                + "data_emprestimo date, "
                                                + "data_devolucao date, "
                                                + "livro_id integer, "
                                                + "cliente_id integer, "
                                                + "foreign key(livro_id) references tb_livro(id_livro), "
                                                + "foreign key(cliente_id) references tb_cliente(id_cliente));";

    public Banco(Context context){
        super(context, BANCO, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CLIENTE);
        db.execSQL(SQL_LIVRO);
        db.execSQL(SQL_EMPRESTIMO);
    }
}
