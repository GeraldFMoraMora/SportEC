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
import com.sportec.sportec.Informacion.Adapter.OpcionAdapter;
import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.R;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class OpcionLayout extends AppCompatActivity {
    private GridView mGridView;

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_opcion);

        GridView gridview = (GridView) findViewById(R.id.gridview_layout_opcion);
        gridview.setAdapter(new OpcionAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(OpcionLayout.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
