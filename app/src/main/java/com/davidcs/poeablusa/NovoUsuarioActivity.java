package com.davidcs.poeablusa;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.davidcs.poeablusa.dao.PeriodoDao;
import com.davidcs.poeablusa.dao.TemperaturaDao;
import com.davidcs.poeablusa.dao.UsuarioDao;
import com.davidcs.poeablusa.model.Periodo;
import com.davidcs.poeablusa.model.Temperatura;
import com.davidcs.poeablusa.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class NovoUsuarioActivity extends AppCompatActivity {
    public static int CODE_NOVO_USUARIO= 1002;
    private TextInputLayout tilNomeUsuario;
    private TextInputLayout tilFrio;
    private TextInputLayout tilCalor;
    private TextInputLayout tilChuva;
    private Spinner spPeriodo;
    private List<Periodo> periodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        tilNomeUsuario =(TextInputLayout)findViewById(R.id.tilNomeUsuario);
        tilFrio =(TextInputLayout)findViewById(R.id.tilFrio);
        tilCalor =(TextInputLayout)findViewById(R.id.tilCalor);
        tilChuva =(TextInputLayout)findViewById(R.id.tilChuva);
        spPeriodo=(Spinner)findViewById(R.id.spPeriodo);


        PeriodoDao periodoDao = new PeriodoDao(this);
        periodos = periodoDao.getAll();

        ArrayAdapter<Periodo> adapter = new ArrayAdapter<Periodo>(getApplicationContext(),
                R.layout.periodo_spinner_item,periodos);
        adapter.setDropDownViewResource(R.layout.periodo_spinner_item);
        spPeriodo.setAdapter(adapter);

    }

    public void cadastrar(View v){
        UsuarioDao usuarioDao = new UsuarioDao(this);
        TemperaturaDao temperaturaDao = new TemperaturaDao(this);
        Usuario usuario = new Usuario();
        usuario.setNome(String.valueOf(tilNomeUsuario.getEditText().getText()));
        Temperatura temperatura = new Temperatura(
                Integer.parseInt(String.valueOf(tilFrio.getEditText().getText())),
                Integer.parseInt(String.valueOf(tilCalor.getEditText().getText())),
                Integer.parseInt(String.valueOf(tilChuva.getEditText().getText())));
     //   usuario.setPeriodo((Periodo)spPeriodo.getSelectedItem());
        String idTemp =temperaturaDao.add(temperatura);
        if(idTemp != "-1"){
            temperatura.setId(Integer.parseInt(idTemp));
        }else{
            retornarParaTelaAnterior();
        }

        usuario.setPeriodo((Periodo) spPeriodo.getSelectedItem());
        usuario.setTemperatura(temperatura);
        usuarioDao.add(usuario);
        retornarParaTelaAnterior();
    }

    private void retornarParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(CODE_NOVO_USUARIO, intentMessage);
        finish();
    }
}
