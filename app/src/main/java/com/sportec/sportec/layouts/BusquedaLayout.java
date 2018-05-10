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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sportec.sportec.Informacion.Adapter.BusquedaAdapter;
import com.sportec.sportec.Informacion.Adapter.NoticiaMainAdapter;
import com.sportec.sportec.Informacion.Adapter.ResultadoAdapter;
import com.sportec.sportec.Informacion.ConstantInterface;
import com.sportec.sportec.Informacion.Model.BusquedaModel;
import com.sportec.sportec.Informacion.Model.NoticiaMainModel;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.MainActivity;
import com.sportec.sportec.R;
import com.sportec.sportec.fragments.NoticiaFragment;

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

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;

    private ArrayList<NoticiaMainModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_busqueda);

        this.list=new ArrayList();

        //ArrayList<BusquedaModel> list= new ArrayList();
        //list.add(new BusquedaModel(BusquedaModel.IMAGE_TYPE,"Barcelona - Real Madrid","https://firebasestorage.googleapis.com/v0/b/sportec-cf3d1.appspot.com/o/logos%2Fequipo_logo.png?alt=media&token=c1bc7833-8a71-44e7-ac2d-2637b7591ceb","1-1"));

        //BusquedaAdapter adapter = new BusquedaAdapter(list,this);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        //RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_resultado);
        //mRecyclerView.setLayoutManager(linearLayoutManager);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.setAdapter(adapter);

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();

    }
    public void metodoBusquedas(){
        DatabaseReference ref = this.mDatabaseReference.child("noticia");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);

                }
                NoticiaMainAdapter adapter = new NoticiaMainAdapter(list, BusquedaLayout.this, new ConstantInterface() {
                    @Override
                    public void onClick(View v, final int position) {
                        DatabaseReference ref = mDatabaseReference.child("noticia");
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                                    NoticiaMainModel noticia = singleSnapshot.getValue(NoticiaMainModel.class);

                                    if (String.valueOf(noticia.id.equals(String.valueOf(position)))=="true"){
                                        getSupportFragmentManager()
                                                .beginTransaction()
                                                .replace(R.id.main_activity_fragment,
                                                        NoticiaFragment.newInstance(noticia.titulo,noticia.foto, noticia.descripcion))
                                                .commit();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusquedaLayout.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.noticia_app_bar_main_recyclerview);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    public void onClick(View view){
        switch (view.getId()){

        }
    }
}
