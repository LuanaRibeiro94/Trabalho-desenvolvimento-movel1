package com.example.trabalhodesenvolvimentomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhodesenvolvimentomovel.modelo.Emprestimo;

public class EmprestimoDao extends Banco {
    private static final String TB_EMPRESTIMO = "tb_emprestimo";
    private static final String ID_EMPRESTIMO = "id_emprestimo";
    private static final String DATA_EMP = "data_emprestimo";
    private static final String DATA_DEV = "data_devolucao";
    private static final String LIVRO_ID = "livro_id";
    private static final String CLIENTE_ID = "cliente_id";

    public EmprestimoDao(Context context){
        super(context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TB_EMPRESTIMO;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarEmprestimo(Emprestimo e) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(CLIENTE_ID, e.getCliente().getId());
        values.put(LIVRO_ID, e.getLivro().getId());
        values.put(DATA_EMP, e.getData_emp());
        values.put(DATA_DEV, e.getData_dev());

        retornoDB = getWritableDatabase().insert(TB_EMPRESTIMO, null, values);

        return retornoDB;
    }
}
