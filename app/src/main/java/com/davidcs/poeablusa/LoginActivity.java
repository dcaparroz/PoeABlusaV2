package com.davidcs.poeablusa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.davidcs.poeablusa.dao.LoginDao;
import com.davidcs.poeablusa.model.Login;


public class LoginActivity extends AppCompatActivity {

    public static final String KEY_APP_PREFERENCES ="APP_PREFERENCES";
    public static final String KEY_LOGIN ="login";

    private EditText etLogin;
    private EditText  etSenha;
    private CheckBox cbManter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        cbManter =(CheckBox) findViewById(R.id.cbManter);
        if(isConectado())
            iniciarApp();
    }

    public void logar(View view){
        if(isLoginValido()){
            if(cbManter.isChecked()){
                manterConectado();
            }
            iniciarApp();
        }else{
            Toast.makeText(this, "Usuáro ou senha inválidos",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLoginValido() {
        LoginDao dao = new LoginDao(getApplicationContext());
        String login =etLogin.getText().toString();
        String senha =etSenha.getText().toString();
        if(login.equals("fiap")&&senha.equals("123")){
            return true;
        }else{
            for(Login l:dao.getAll()){
                if(login.equals(l.getNome()) && senha.equals(l.getSenha())){
                return true;}}
        }
        return false;
    }

    private void manterConectado() {
        String login =etLogin.getText().toString();
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor =pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }

    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES,MODE_PRIVATE);
        String login =shared.getString(KEY_LOGIN,"");
        if(login.equals(""))
            return false;
        else
            return true;
    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
