package com.davidcs.poeablusa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.davidcs.poeablusa.adpater.ListaAndroidAdapter;
import com.davidcs.poeablusa.dao.TemperaturaDao;
import com.davidcs.poeablusa.dao.UsuarioDao;
import com.davidcs.poeablusa.model.Temperatura;
import com.davidcs.poeablusa.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   // private TextView tvUsuarios;
    private ListaAndroidAdapter adapter;
    private RecyclerView rvUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   tvUsuarios = (TextView) findViewById(R.id.tvUsuarios);
        rvUsuarios =(RecyclerView) findViewById(R.id.rvUsuarios);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,
                                NovoUsuarioActivity.class),
                        NovoUsuarioActivity.CODE_NOVO_USUARIO);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        carregaUsuario();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Cancelado",
                    Toast.LENGTH_LONG).show();
        } else if(requestCode == NovoUsuarioActivity.CODE_NOVO_USUARIO) {
            carregaUsuario();
        }
    }

    private void carregaUsuario() {

        UsuarioDao usuarioDAO = new UsuarioDao(this);
        List<Usuario> usuarios  = usuarioDAO.getAll();
        setUpUsuario(usuarios);

        /*tvUsuarios.setText("");
        UsuarioDao usuarioDao = new UsuarioDao(this);
        StringBuilder sb;
        List<Usuario> usuarios = usuarioDao.getAll();
        for(Usuario t : usuarios) {
            TemperaturaDao temperaturaDao = new TemperaturaDao(this);
            Temperatura temperatura;
            temperatura = temperaturaDao.getBy(t.getTemperatura().getId());
            sb= new StringBuilder(tvUsuarios.getText());
            sb.append("\n");
            sb.append(t.getNome());
            sb.append(" - ");
            sb.append(temperatura.getFrio());
            sb.append(" - ");
            sb.append(temperatura.getCalor());
            sb.append(" - ");
            sb.append(temperatura.getChuva());
            sb.append(" - ");
            sb.append(t.getPeriodo());
            tvUsuarios.setText(sb.toString());
        }*/
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpUsuario(List<Usuario> lista) {
        adapter = new ListaAndroidAdapter(this, lista);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        rvUsuarios.setAdapter(adapter);
    }
}
