package com.davidcs.poeablusa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.davidcs.poeablusa.model.Usuario;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by logonrm on 09/03/2017.
 */

public class UsuarioDao {

    private SQLiteDatabase db;
    private DBOpenHelper banco;
    private Context context;
    public UsuarioDao(Context context) {
        banco = new DBOpenHelper(context);
        this.context =context;
    }

    private static final String TABELA_USUARIO = "usuario";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_FRIO = "frio";
    private static final String COLUNA_CALOR = "calor";
    private static final String COLUNA_CHUVA = "chuva   ";



    public String add(Usuario usuario){

        long resultado;
        SQLiteDatabase db=banco.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("id",usuario.getId());
        values.put(COLUNA_NOME,usuario.getNome());
        values.put(COLUNA_FRIO,usuario.getFrio());
        values.put(COLUNA_CALOR,usuario.getCalor());
        values.put(COLUNA_CHUVA,usuario.getChuva());

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
        String rawQuery = "SELECT * FROM " +
                UsuarioDao.TABELA_USUARIO  +
                " ORDER BY " + UsuarioDao.COLUNA_NOME + " ASC";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Usuario usuario;
        if (cursor.moveToFirst()) {
            do {
                usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setFrio(cursor.getString(2));
                usuario.setCalor(cursor.getString(3));
                usuario.setChuva(cursor.getString(4));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

    public void deleteByID(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        db.delete(UsuarioDao.TABELA_USUARIO, "id = " + id, null);
        db.close();
    }

    public Usuario getByID(int id){
        SQLiteDatabase db = banco.getReadableDatabase();
        String rawQuery = "SELECT * FROM " +
                UsuarioDao.TABELA_USUARIO+" where id = " + id;
        Cursor cursor = db.rawQuery(rawQuery, null);
        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario = new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setFrio(cursor.getString(2));
                usuario.setCalor(cursor.getString(3));
                usuario.setChuva(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        db.close();
        return usuario;
    }

    public String editByID(Usuario usuario){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME,usuario.getNome());
        values.put(COLUNA_FRIO,usuario.getFrio());
        values.put(COLUNA_CALOR,usuario.getCalor());
        values.put(COLUNA_CHUVA,usuario.getChuva());
        resultado = db.update(UsuarioDao.TABELA_USUARIO, values, " id = "+ usuario.getId(), null);
        db.close();
        if (resultado == -1) {
            return "Erro ao editar registro";
        } else {
            return "Registro editado com sucesso";
        }
    }


}
