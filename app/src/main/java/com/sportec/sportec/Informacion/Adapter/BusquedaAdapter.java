package com.sportec.sportec.Informacion.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Model.BusquedaModel;
import com.sportec.sportec.Informacion.Model.ResultadoModel;
import com.sportec.sportec.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class BusquedaAdapter extends RecyclerView.Adapter {
    private ArrayList<BusquedaModel> dataSet;
    Context mContext;
    int total_types;


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView mDescripcion;
        private ImageView mImage;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.titulo = (TextView) itemView.findViewById(R.id.textview_busqueda_nombre);
            this.mDescripcion = (TextView) itemView.findViewById(R.id.textview_busqueda_descripcion);
            this.mImage = (ImageView) itemView.findViewById(R.id.imageview_busqueda_foto);
        }
    }


    public BusquedaAdapter(ArrayList<BusquedaModel>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case BusquedaModel.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_busqueda_item, parent, false);
                return new ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return BusquedaModel.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        BusquedaModel object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case BusquedaModel.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).titulo.setText(object.mPartido);
                    ((ImageTypeViewHolder) holder).mDescripcion.setText(object.mDescripcion);
                    Picasso.get().load(object.foto).into(((ImageTypeViewHolder) holder).mImage);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
