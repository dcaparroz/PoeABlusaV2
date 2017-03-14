package com.davidcs.poeablusa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.davidcs.poeablusa.model.Temperatura;
import com.davidcs.poeablusa.model.Usuario;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by logonrm on 09/03/2017.
 */

public class UsuarioDao {

    private SQLiteDatabase db;
    private DBOpenHelper banco;
    public UsuarioDao(Context context) {
        banco = new DBOpenHelper(context);
    }

    private static final String TABELA_USUARIO = "usuario";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_TEMPERATURA_ID = "temperatura_id";


    public String add(Usuario usuario){
        long resultado;
        SQLiteDatabase db=banco.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUNA_NOME,usuario.getNome());
        values.put(COLUNA_TEMPERATURA_ID, usuario.getTemperatura().getId());
        resultado =db.insert(TABELA_USUARIO, null, values);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new LinkedList<>();
        String rawQuery = "SELECT t.*,t.nome FROM " +
                UsuarioDao.TABELA_USUARIO + " t INNER JOIN " +
                TemperaturaDao.TABELA_TEMPERATURAS +
                " c ON t." + UsuarioDao.COLUNA_TEMPERATURA_ID + "=c." +
                TemperaturaDao.COLUNA_ID +
                " ORDER BY " + UsuarioDao.COLUNA_NOME + " ASC";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setTemperatura(new Temperatura(cursor.getInt(1),
                        cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

}
