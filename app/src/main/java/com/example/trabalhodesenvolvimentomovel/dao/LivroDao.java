package com.example.trabalhodesenvolvimentomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhodesenvolvimentomovel.modelo.Livro;

import java.util.ArrayList;

public class LivroDao extends Banco {
    private static final String TB_LIVRO = "tb_livro";
    private static final String ID_LIVRO = "id_livro";
    private static final String TITULO = "titulo";
    private static final String AUTOR = "autor";
    private static final String EDITORA = "editora";
    private static final String ANO = "ano";

    public LivroDao(Context context){
        super(context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TB_LIVRO;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarLivro(Livro l) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(TITULO, l.getTitulo());
        values.put(AUTOR, l.getAutor());
        values.put(EDITORA, l.getEditora());
        values.put(ANO, l.getAno());

        retornoDB = getWritableDatabase().insert(TB_LIVRO, null, values);

        return retornoDB;
    }

    public long alterarLivro(Livro l) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(TITULO, l.getTitulo());
        values.put(AUTOR, l.getAutor());
        values.put(EDITORA, l.getEditora());
        values.put(ANO, l.getAno());

        String [] args = {String.valueOf(l.getId())};

        retornoDB = getWritableDatabase().update(TB_LIVRO, values, "id_livro=?", args);

        return retornoDB;
    }

    public long excluirLivro(Livro l) {
        long retornoDB;

        String [] args = {String.valueOf(l.getId())};

        retornoDB = getWritableDatabase().delete(TB_LIVRO,ID_LIVRO+"=?", args);

        return retornoDB;
    }

    public ArrayList<Livro> selectAllLivro(){
        String [] coluns = {ID_LIVRO, TITULO, AUTOR, EDITORA, ANO};

        Cursor cursor = getWritableDatabase().query(TB_LIVRO, coluns, null, null, null, null, "upper(titulo)", null);

        ArrayList<Livro> listLivro = new ArrayList<Livro>();

        while (cursor.moveToNext()) {
            Livro l = new Livro();

            l.setId(cursor.getInt(0));
            l.setTitulo(cursor.getString(1));
            l.setAutor(cursor.getString(2));
            l.setEditora(cursor.getString(3));
            l.setAno(cursor.getString(4));

            listLivro.add(l);
        }

        return listLivro;
    }
}
