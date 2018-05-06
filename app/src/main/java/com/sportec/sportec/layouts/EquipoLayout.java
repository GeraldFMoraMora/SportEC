package com.sportec.sportec.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.sportec.sportec.Informacion.Adapter.MiembroAdapter;
import com.sportec.sportec.Informacion.Model.MiembroModel;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.R;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class EquipoLayout extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_equipo);


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
