package com.example.trabalhodesenvolvimentomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;
import com.example.trabalhodesenvolvimentomovel.modelo.Emprestimo;
import com.example.trabalhodesenvolvimentomovel.modelo.Livro;

import java.util.ArrayList;

public class EmprestimoDao extends Banco {
    private static final String TB_EMPRESTIMO = "tb_emprestimo";
    private static final String ID_EMPRESTIMO = "id_emprestimo";
    private static final String DATA_EMP = "data_emprestimo";
    private static final String DATA_DEV = "data_devolucao";
    private static final String LIVRO_ID = "livro_id";
    private static final String CLIENTE_ID = "cliente_id";
    private final ClienteDao clienteDao;
    private final LivroDao livroDao;

    public EmprestimoDao(Context context){
        super(context);
        this.clienteDao = new ClienteDao(context);
        this.livroDao = new LivroDao(context);
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

    public ArrayList<Emprestimo> selectAllEmprestimos() {
        String [] coluns = {ID_EMPRESTIMO, CLIENTE_ID, LIVRO_ID, DATA_EMP, DATA_DEV};

        Cursor cursor = getWritableDatabase().query(TB_EMPRESTIMO, coluns, null, null, null, null, null, null);

        ArrayList<Emprestimo> listEmprestimo = new ArrayList<Emprestimo>();

        while (cursor.moveToNext()) {
            Emprestimo e = new Emprestimo();
            Cliente cliente = clienteDao.buscarCliente(Integer.toString(cursor.getInt(1)));
            Livro livro = livroDao.buscarLivro(Integer.toString(cursor.getInt(2)));

            e.setId(cursor.getInt(0));
            e.setCliente(cliente);
            e.setLivro(livro);
            e.setData_emp(cursor.getString(3));
            e.setData_dev(cursor.getString(4));

            listEmprestimo.add(e);
        }

        return listEmprestimo;
    }

    public long excluirEmprestimo(Emprestimo emprestimo) {
        long retornoDB;

        String [] args = {String.valueOf(emprestimo.getId())};

        retornoDB = getWritableDatabase().delete(TB_EMPRESTIMO,ID_EMPRESTIMO+"=?", args);

        return retornoDB;
    }
}
