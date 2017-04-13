package com.davidcs.poeablusa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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
import com.davidcs.poeablusa.dao.UsuarioDao;
import com.davidcs.poeablusa.model.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ListaAndroidAdapter adapter;
    private RecyclerView rvUsuarios;
    private FloatingActionButton fabDel;
    private FloatingActionButton fabEdt;
    private SharedPreferences id_usuario;
    private int temp_id;
    private TextView id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UsuarioDao usuarioDao = new UsuarioDao(this.getApplicationContext());
        id_usuario = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        temp_id= -1;
        rvUsuarios =(RecyclerView) findViewById(R.id.rvUsuarios);
        fabDel = (FloatingActionButton) findViewById(R.id.fabDel);
        fabEdt = (FloatingActionButton) findViewById(R.id.fabEdt);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_usuario.edit().putInt("ID",0).apply();
                startActivityForResult(new Intent(MainActivity.this,
                                NovoUsuarioActivity.class),
                        NovoUsuarioActivity.CODE_NOVO_USUARIO);
            }
        });

        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp_id != -1){
                    usuarioDao.deleteByID(temp_id);
                    carregaUsuario();
                    fabDel.setVisibility(View.INVISIBLE);
                    fabEdt.setVisibility(View.INVISIBLE);
                }
            }
        });

        fabEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp_id != -1){
                    id_usuario.edit().putInt("ID", temp_id).apply();
                    startActivityForResult(new Intent(MainActivity.this,
                                    NovoUsuarioActivity.class),
                            NovoUsuarioActivity.CODE_EDITA_USUARIO);
                }
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

        ItemClickSupport.addTo(rvUsuarios).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                id = (TextView) v.findViewById(R.id.id);
                int i = countItemSelected();
                if(!isItemSelected() && i == 0){
                    v.setSelected(true);
                    temp_id = Integer.parseInt(id.getText().toString());
                    fabDel.setVisibility(View.VISIBLE);
                    fabEdt.setVisibility(View.VISIBLE);
                } else if(v.isSelected()){
                    v.setSelected(false);
                    temp_id = -1;
                    fabDel.setVisibility(View.INVISIBLE);
                    fabEdt.setVisibility(View.INVISIBLE);
                }
            }
        });
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Cancelado",
                    Toast.LENGTH_LONG).show();
        } else if(requestCode == NovoUsuarioActivity.CODE_NOVO_USUARIO) {
            fabDel.setVisibility(View.INVISIBLE);
            fabEdt.setVisibility(View.INVISIBLE);
            carregaUsuario();
        }else if(requestCode == NovoUsuarioActivity.CODE_EDITA_USUARIO) {
            fabDel.setVisibility(View.INVISIBLE);
            fabEdt.setVisibility(View.INVISIBLE);
            carregaUsuario();
    }
    }

    private void carregaUsuario() {
        UsuarioDao usuarioDAO = new UsuarioDao(this);
        List<Usuario> usuarios  = usuarioDAO.getAll();
        setUpUsuario(usuarios);

    }

    private void setUpUsuario(List<Usuario> lista) {
        adapter = new ListaAndroidAdapter(this, lista);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        rvUsuarios.setAdapter(adapter);
    }

    private boolean isItemSelected(){
        for (int i = 0; i < rvUsuarios.getAdapter().getItemCount(); i++) {
            if(rvUsuarios.getChildAt(i).isSelected()){
                return true;
            }
        }
        return false;
    }

    private int countItemSelected(){
        int s = 0;
        for (int i = 0; i < rvUsuarios.getAdapter().getItemCount(); i++) {
            if(rvUsuarios.getChildAt(i).isSelected()){
                s =+ 1;
            }
        }
        return s;
    }
}
