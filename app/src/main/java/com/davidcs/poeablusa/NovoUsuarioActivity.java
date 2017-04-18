package com.davidcs.poeablusa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davidcs.poeablusa.dao.UsuarioDao;
import com.davidcs.poeablusa.model.Usuario;

public class NovoUsuarioActivity extends AppCompatActivity {
    public static int CODE_NOVO_USUARIO= 1002;
    public final static int CODE_EDITA_USUARIO = 333;

    private TextInputLayout tilNomeUsuario;
    private EditText edtNome;
    private EditText edtFrio;
    private EditText edtCalor;
    private EditText edtChuva;
    private TextView txNome;
    private TextView txFrio;
    private TextView txCalor;
    private TextView txChuva;
    private TextInputLayout tilFrio;
    private TextInputLayout tilCalor;
    private TextInputLayout tilChuva;
    private SharedPreferences id_usuarios;
    private int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        tilNomeUsuario =(TextInputLayout)findViewById(R.id.tilNomeUsuario);
        edtNome =(EditText)findViewById(R.id.edtNome);
        edtFrio =(EditText)findViewById(R.id.edtFrio);
        edtCalor =(EditText)findViewById(R.id.edtCalor);
        edtChuva=(EditText)findViewById(R.id.edtChuva);
        txNome =(TextView) findViewById(R.id.txNome);
        tilFrio =(TextInputLayout)findViewById(R.id.tilFrio);
        txFrio =(TextView)findViewById(R.id.txFrio);
        txChuva =(TextView)findViewById(R.id.txChuva);
        txCalor =(TextView)findViewById(R.id.txCalor);
        tilCalor =(TextInputLayout)findViewById(R.id.tilCalor);
        tilChuva =(TextInputLayout)findViewById(R.id.tilChuva);
        id_usuarios = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id = id_usuarios.getInt("ID",0);

        if(id != 0){
            editar();
        }
    }

    public void cadastrar(View v){
        UsuarioDao usuarioDao = new UsuarioDao(this);

        Usuario usuario = new Usuario();
        usuario.setNome(String.valueOf(tilNomeUsuario.getEditText().getText()));
        usuario.setFrio(String.valueOf(tilFrio.getEditText().getText()));
        usuario.setCalor(String.valueOf(tilCalor.getEditText().getText()));
        usuario.setChuva(String.valueOf(tilChuva.getEditText().getText()));
        if(id==0) {
            usuarioDao.add(usuario);
            id_usuarios.edit().putInt("ID",0).apply();
            retornaParaTelaAnteriorPosEditar();
        }else{
            usuario.setId(id);
            usuarioDao.editByID(usuario);
            retornarParaTelaAnterior();
        }
    }

    private void retornarParaTelaAnterior() {
        Intent intentMessage=new Intent();
        setResult(CODE_NOVO_USUARIO, intentMessage);
        finish();
    }

    public void retornaParaTelaAnteriorPosEditar() {
        Intent intentMessage = new Intent();
        setResult(CODE_EDITA_USUARIO, intentMessage);
        finish();
    }

    public void editar(){
        UsuarioDao usuarioDao = new UsuarioDao(this);
        Usuario usuario;
        usuario = usuarioDao.getByID(id);
        tilNomeUsuario.getEditText().setText(usuario.getNome());
        tilFrio.getEditText().setText(usuario.getFrio());
        tilCalor.getEditText().setText(usuario.getCalor());
        tilChuva.getEditText().setText(usuario.getChuva());
        id_usuarios.edit().putInt("ID", 0).apply();
    }

}
