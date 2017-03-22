package com.davidcs.poeablusa.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.davidcs.poeablusa.model.Periodo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by logonrm on 09/03/2017.
 */

public class PeriodoDao {
    private DBOpenHelper banco;

    public PeriodoDao(Context context){
        banco = new DBOpenHelper(context);
    }
    public static final String TABELA_PERIODOS="periodo";
    public static final String COLUNA_ID="id";
    public static final String COLUNA_PERIODO="periodo";

    public List<Periodo> getAll(){
        List<Periodo> periodos = new LinkedList<>();
        String query ="SELECT * FROM "+TABELA_PERIODOS;
        SQLiteDatabase db=banco.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);
        Periodo periodo=null;
        if(cursor.moveToFirst()){
            do{
                periodo = new Periodo();
                periodo.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));

                periodo.setPeriodo(cursor.getString(cursor.getColumnIndex(COLUNA_PERIODO)));
                periodos.add(periodo);
            }while(cursor.moveToNext());
        }
        return periodos;
    }

    public Periodo getBy(int id){
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_PERIODO};
        String where = "id = " + id;
        Cursor cursor = db.query(true, TABELA_PERIODOS, colunas, where, null, null,
                null, null, null);
        Periodo periodo = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            periodo = new Periodo();
            periodo.setPeriodo(cursor.getString(cursor.getColumnIndex(COLUNA_PERIODO)));
            periodo.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return periodo;
    }

    public PeriodoDao() {
    }
}
