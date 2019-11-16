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

    public Banco(Context context){
        super(context, BANCO, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CLIENTE);
    }
}