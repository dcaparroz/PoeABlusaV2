package com.davidcs.poeablusa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.davidcs.poeablusa.dao.LoginDao;
import com.davidcs.poeablusa.model.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new BuscaDados().execute("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }
    private class BuscaDados extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream is = conn.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(is));

                    StringBuilder result = new StringBuilder();
                    String linha;

                    while((linha = buffer.readLine()) != null){
                        result.append(linha);
                    }
                    conn.disconnect();
                    return result.toString();

                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s == null){
                Toast.makeText(SplashScreenActivity.this, "Deu ruim!", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject json = new JSONObject(s);
                    Login login = new Login(json.getString("usuario"),
                            json.getString("senha"));
                    LoginDao db = new LoginDao(getApplicationContext());
                    db.add(login);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    }

