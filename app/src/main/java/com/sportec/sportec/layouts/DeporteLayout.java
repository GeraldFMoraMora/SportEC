package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.Informacion.NoticiaAdapter;
import com.sportec.sportec.R;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class DeporteLayout extends AppCompatActivity{
    private GridView mGridView;
    private NoticiaAdapter mNoticiaAdapter;

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_deporte);

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
