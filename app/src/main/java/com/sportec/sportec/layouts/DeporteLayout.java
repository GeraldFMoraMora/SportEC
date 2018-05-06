package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sportec.sportec.Informacion.Adapter.DeporteAdapter;
import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.R;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class DeporteLayout extends AppCompatActivity{
    private GridView mGridView;

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_deporte);

        GridView gridview = (GridView) findViewById(R.id.gridview_layout_deporte);
        gridview.setAdapter(new DeporteAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DeporteLayout.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static Noticia[] ITEMS = {
            new Noticia("Uno","uno", new java.util.Date(), R.mipmap.logo_artesmarciales),
            new Noticia("Dos","dos", new java.util.Date(),R.mipmap.logo_atletismo),
            new Noticia("Tres","tres", new java.util.Date(),R.mipmap.logo_badminton),
            new Noticia("Cuatro","cuatro", new java.util.Date(),R.mipmap.logo_balonmano)
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
