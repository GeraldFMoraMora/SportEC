package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
import com.sportec.sportec.Informacion.Usuario;
import com.sportec.sportec.R;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class EquipoLayout extends AppCompatActivity{
    private Intent mScreen;
    private Long mId;
    private String mNombreEquipo;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private TextView mNombreEquipoView;

    private ArrayList<MiembroModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_equipo);

        this.mId = getIntent().getLongExtra("id",0);

        this.setmNombreDeporte();
        this.mNombreEquipoView = (TextView) findViewById(R.id.nombre_equipo_layout_equipo_textview);

        this.mDatabase = FirebaseDatabase.getInstance();
        this.mDatabaseReference = mDatabase.getReference();
        DatabaseReference ref = this.mDatabaseReference.child("deporte");

        //Query phoneQuery = ref.equalTo("usuario");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Deporte deporte = singleSnapshot.getValue(Deporte.class);
                    if(mNombreEquipo.equals(deporte.nombre)){
                        System.out.println(deporte.nombre);
                        mNombreEquipoView.setText(mNombreEquipo);
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        this.list= new ArrayList();
        ref = this.mDatabaseReference.child("usuario");
        //Query phoneQuery = ref.equalTo("usuario");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Usuario user = singleSnapshot.getValue(Usuario.class);
                    System.out.println(user.mNombre);
                    list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,user.mNombre,user.mFoto.toString()));
                }
                MiembroAdapter adapter = new MiembroAdapter(list,EquipoLayout.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EquipoLayout.this, OrientationHelper.VERTICAL, false);

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_miembro);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Messi Bolaños",R.mipmap.messi));
        //list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Mia Kalifa",R.mipmap.mia));
        //list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Tonny Tun Tun",R.mipmap.tony));
        //list.add(new MiembroModel(MiembroModel.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.mipmap.grid3));



    }

    /**
     * Este metodo obtiene el id del objeto seleccionado y le asigna un nombre para ser identificado de manera
     * sencilla.
     */
    private void setmNombreDeporte(){
        switch (mId.toString()){
            case "0":
                this.mNombreEquipo="Artes marciales";
                break;
            case "1":
                this.mNombreEquipo="Atletismo";
                break;
            case "2":
                this.mNombreEquipo="Badminton";
                break;
            case "3":
                this.mNombreEquipo="Balon mano";
                break;
            case "4":
                this.mNombreEquipo="Baseball";
                break;
            case "5":
                this.mNombreEquipo="Basketball";
                break;
            case "6":
                this.mNombreEquipo="Ciclismo";
                break;
            case "7":
                this.mNombreEquipo="Levantamiento de pesas";
                break;
            case "8":
                this.mNombreEquipo="Futball";
                break;
            case "9":
                this.mNombreEquipo="Kayak";
                break;
            case "10":
                this.mNombreEquipo="Ping pong";
                break;
            case "11":
                this.mNombreEquipo="Esgrima";
                break;
            case "12":
                this.mNombreEquipo="Tennis";
                break;
            case "13":
                this.mNombreEquipo="Volleyball";
                break;
        }
    }
}
