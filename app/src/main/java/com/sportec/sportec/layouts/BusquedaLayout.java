package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sportec.sportec.Informacion.Adapter.BusquedaAdapter;
import com.sportec.sportec.Informacion.Adapter.BusquedaNoticiaAdapter;
import com.sportec.sportec.Informacion.Adapter.BusquedaUsuarioAdapter;
import com.sportec.sportec.Informacion.Adapter.MiembroAdapter;
import com.sportec.sportec.Informacion.Adapter.NoticiaMainAdapter;
import com.sportec.sportec.Informacion.Adapter.ResultadoAdapter;
import com.sportec.sportec.Informacion.ConstantInterface;
import com.sportec.sportec.Informacion.Model.BusquedaModel;
import com.sportec.sportec.Informacion.Model.MiembroModel;
import com.sportec.sportec.Informacion.Model.NoticiaMainModel;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.Informacion.Usuario;
import com.sportec.sportec.MainActivity;
import com.sportec.sportec.R;
import com.sportec.sportec.fragments.NoticiaFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class BusquedaLayout extends AppCompatActivity {
    private GridView mGridView;

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;

    private EditText mEntryBusqueda;

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;

    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_busqueda);

        this.list = new ArrayList();

        this.mEntryBusqueda = (EditText) findViewById(R.id.busqueda_editText);

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();
    }

    public void metodoBusquedas() {
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();
        DatabaseReference ref = this.mDatabaseReference.child("noticia");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);

                    if (noticia.descripcion.contains(mEntryBusqueda.getText().toString())) {
                        list.add(new NoticiaMainModel(NoticiaMainModel.IMAGE_TYPE, noticia.titulo, noticia.foto, noticia.descripcion, noticia.dia, noticia.id));
                    } else if (noticia.titulo.contains(mEntryBusqueda.getText().toString())) {
                        list.add(new NoticiaMainModel(NoticiaMainModel.IMAGE_TYPE, noticia.titulo, noticia.foto, noticia.descripcion, noticia.dia, noticia.id));
                    }
                }
                BusquedaNoticiaAdapter adapter = new BusquedaNoticiaAdapter(list, BusquedaLayout.this, new ConstantInterface() {
                    @Override
                    public void onClick(View v, final int position) {
                        DatabaseReference ref = mDatabaseReference.child("noticia");
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);
                                    startActivity(new Intent(BusquedaLayout.this, MainActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusquedaLayout.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_busqueda);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boton_busqueda:
                this.list = new ArrayList();
                this.metodoBusquedas();
                break;
        }
    }
}
