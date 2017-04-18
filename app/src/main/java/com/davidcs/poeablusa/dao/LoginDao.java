package com.davidcs.poeablusa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.davidcs.poeablusa.model.Login;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrador on 11/04/2017.
 */

public class LoginDao {

    private SQLiteDatabase db;
    private DBOpenHelper banco;
    private Context context;
    public LoginDao(Context context) {
        banco = new DBOpenHelper(context);
        this.context = context;
    }
    private static final String TABELA_USUARIO = "login";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "usuario";
    private static final String COLUNA_SENHA = "senha";

    public String add(Login login){
        LoginDao dao = new LoginDao(context);

        long resultado;
        SQLiteDatabase db=banco.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUNA_NOME,login.getNome());
        values.put(COLUNA_SENHA, login.getSenha());
        resultado =db.insert(TABELA_USUARIO, null, values);
        db.close();
        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
             return "Registro inserido com sucesso";
               }
        }

    public List<Login> getAll() {
        List<Login> usuarios = new LinkedList<>();
        String rawQuery = "SELECT * FROM " +
                TABELA_USUARIO;
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Login usuario = null;
        if (cursor.moveToFirst()) {
            do {
                usuario = new Login();
                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setSenha(cursor.getString(2));
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        db.close();
        return usuarios;
    }

    }
