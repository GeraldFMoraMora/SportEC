package com.sportec.sportec.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Adapter.OpcionAdapter;
import com.sportec.sportec.Informacion.Adapter.ResultadoAdapter;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.R;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class ResultadoLayout extends AppCompatActivity {
    private GridView mGridView;

    private Intent mScreen;
    private Toolbar mToolbar;
    private ImageView mLogoNav;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultado);

        ArrayList<ResultadoModel> list= new ArrayList();
        list.add(new ResultadoModel(ResultadoModel.IMAGE_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",R.mipmap.grid4));
        list.add(new ResultadoModel(ResultadoModel.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.mipmap.grid1));
        list.add(new ResultadoModel(ResultadoModel.IMAGE_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.mipmap.grid2));
        list.add(new ResultadoModel(ResultadoModel.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.mipmap.grid3));

        ResultadoAdapter adapter = new ResultadoAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_resultado);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
}
