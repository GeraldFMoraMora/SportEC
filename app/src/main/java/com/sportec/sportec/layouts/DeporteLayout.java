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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sportec.sportec.Informacion.Adapter.DeporteAdapter;
import com.sportec.sportec.Informacion.Deporte;
import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.Informacion.Usuario;
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

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    private String mId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_deporte);

        GridView gridview = (GridView) findViewById(R.id.gridview_layout_deporte);
        gridview.setAdapter(new DeporteAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DeporteLayout.this, "" + id,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeporteLayout.this, EquipoLayout.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

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
