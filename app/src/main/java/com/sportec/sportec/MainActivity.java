package com.sportec.sportec;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.Informacion.NoticiaAdapter;
import com.sportec.sportec.fragments.DeporteFavoritoFragment;
import com.sportec.sportec.fragments.DeporteFragment;
import com.sportec.sportec.fragments.FormularioResgistroFragment;
import com.sportec.sportec.fragments.NoticiaFragment;
import com.sportec.sportec.fragments.SessionFragment;
import com.sportec.sportec.layouts.DeporteLayout;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        NoticiaFragment.OnFragmentInteractionListener,
        SessionFragment.OnFragmentInteractionListener,
        FormularioResgistroFragment.OnFragmentInteractionListener,
        DeporteFavoritoFragment.OnFragmentInteractionListener,
        DeporteFragment.OnFragmentInteractionListener{

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private GridView mGridView;
    private NoticiaAdapter mNoticiaAdapter;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        this.mLogoNav= (ImageView) findViewById(R.id.nav_logo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            this.mScreen=new Intent(this, DeporteLayout.class);
            startActivity(this.mScreen);


            this.mLogoNav.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
            this.mLogoNav.setVisibility(View.VISIBLE);
            this.showPrincipalFragment();

        } else if (id == R.id.nav_slideshow) {
            this.mLogoNav.setVisibility(View.INVISIBLE);
            this.showSessionFragment();

        } else if (id == R.id.nav_manage) {
            this.showRegistroFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_share) {
            showDeporteFavoritoFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_send) {
            this.showDeporteFragment();
            this.mLogoNav.setVisibility(View.VISIBLE);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showPrincipalFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        NoticiaFragment.newInstance(""))
                .commit();
    }
    private void showSessionFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        SessionFragment.newInstance(""))
                .commit();
    }
    private void showRegistroFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        FormularioResgistroFragment.newInstance(""))
                .commit();
    }
    private void showDeporteFavoritoFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        DeporteFavoritoFragment.newInstance(""))
                .commit();
    }
    private void showDeporteFragment() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment,
                        DeporteFragment.newInstance(""))
                .commit();
    }
    public static Noticia[] ITEMS = {
            new Noticia("Uno","uno", new java.util.Date(), R.mipmap.grid1),
            new Noticia("Dos","dos", new java.util.Date(),R.mipmap.grid2),
            new Noticia("Tres","tres", new java.util.Date(),R.mipmap.grid3),
            new Noticia("Cuatro","cuatro", new java.util.Date(),R.mipmap.grid4)
    };
    public static Noticia getItem(int id) {
        for (Noticia item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
