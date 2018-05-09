package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sportec.sportec.Informacion.Adapter.MiembroAdapter;
import com.sportec.sportec.Informacion.Deporte;
import com.sportec.sportec.Informacion.Model.MiembroModel;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.R;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class EquipoLayout extends AppCompatActivity{
    private Intent mScreen;
    private Long mId;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_equipo);

        mId = getIntent().getLongExtra("id",0);
        System.out.println("@@@@@@@@@@@@@"+mId.toString());

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();
        DatabaseReference ref = this.mDatabaseReference.child("deporte");

        //Query phoneQuery = ref.equalTo("usuario");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Deporte deporte = singleSnapshot.getValue(Deporte.class);
                    System.out.println(deporte.nombre);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        ArrayList<MiembroModel> list= new ArrayList();
        list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Messi Bolaños",R.mipmap.messi));
        list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Mia Kalifa",R.mipmap.mia));
        list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Tonny Tun Tun",R.mipmap.tony));
        list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.mipmap.grid3));

        MiembroAdapter adapter = new MiembroAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_miembro);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
}
