package com.example.trabalhodesenvolvimentomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhodesenvolvimentomovel.modelo.Cliente;

import java.util.ArrayList;

public class ClienteDao extends Banco {
    private static final String TB_CLIENTE = "tb_cliente";
    private static final String ID_CLIENTE = "id_cliente";
    private static final String NOME = "nome";
    private static final String CPF = "cpf";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";

    public ClienteDao(Context context){
        super(context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TB_CLIENTE;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarCliente(Cliente c) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, c.getNome());
        values.put(CPF, c.getCpf());
        values.put(EMAIL, c.getEmail());
        values.put(TELEFONE, c.getTelefone());

        retornoDB = getWritableDatabase().insert(TB_CLIENTE, null, values);

        return retornoDB;
    }

    public long alterarCliente(Cliente c) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, c.getNome());
        values.put(CPF, c.getCpf());
        values.put(EMAIL, c.getEmail());
        values.put(TELEFONE, c.getTelefone());

        String [] args = {String.valueOf(c.getId())};

        retornoDB = getWritableDatabase().update(TB_CLIENTE, values, "id_cliente=?", args);

        return retornoDB;
    }

    public long excluirCliente(Cliente c) {
        long retornoDB;

        String [] args = {String.valueOf(c.getId())};

        retornoDB = getWritableDatabase().delete(TB_CLIENTE,ID_CLIENTE+"=?", args);

        return retornoDB;
    }

    public ArrayList<Cliente> selectAllClientes(){
        String [] columns = {ID_CLIENTE, NOME, CPF, EMAIL, TELEFONE};

        Cursor cursor = getWritableDatabase().query(TB_CLIENTE, columns, null, null, null, null, "upper(nome)", null);

        ArrayList<Cliente> listCliente = new ArrayList<Cliente>();

        while (cursor.moveToNext()) {
            Cliente c = new Cliente();

            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCpf(cursor.getString(2));
            c.setEmail(cursor.getString(3));
            c.setTelefone(cursor.getString(4));

            listCliente.add(c);
        }

        return listCliente;
    }

    public Cliente buscarCliente(String id) {
        String [] columns = {ID_CLIENTE, NOME, CPF, EMAIL, TELEFONE};
        String where = "id_cliente = ?";
        String[] whereArgs = {id};
        Cursor cursor = getReadableDatabase().query(
            TB_CLIENTE,
            columns,
            where,
            whereArgs,
            null,
            null,
            null,
            null
        );

        Cliente cliente = new Cliente();

        if(cursor.moveToFirst()) {
            cliente.setId(cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setCpf(cursor.getString(2));
            cliente.setEmail(cursor.getString(3));
            cliente.setTelefone(cursor.getString(4));
        }

        return cliente;
    }
}
