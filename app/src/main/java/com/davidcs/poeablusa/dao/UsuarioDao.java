package com.davidcs.poeablusa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.davidcs.poeablusa.model.Periodo;
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
    private Context context;
    public UsuarioDao(Context context) {
        banco = new DBOpenHelper(context);
        this.context =context;
    }

    private static final String TABELA_USUARIO = "usuario";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_TEMPERATURA_ID = "temperatura_id";
    private static final String COLUNA_PERIODO_ID = "periodo_id";


    public String add(Usuario usuario){
        UsuarioDao dao = new UsuarioDao(context);

        long resultado;
        SQLiteDatabase db=banco.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUNA_NOME,usuario.getNome());
        values.put(COLUNA_TEMPERATURA_ID, usuario.getTemperatura().getId());
        values.put(COLUNA_PERIODO_ID,usuario.getPeriodo().getId());
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
                usuario.setNome(cursor.getString(3));
                usuario.setTemperatura(new Temperatura(cursor.getInt(1)));
                PeriodoDao periodoDao= new PeriodoDao(context);
                usuario.setPeriodo(periodoDao.getBy(cursor.getInt(2)));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        return usuarios;
    }

}
