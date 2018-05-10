package com.sportec.sportec.Informacion.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.sportec.sportec.Informacion.Model.NoticiaMainModel;
import com.sportec.sportec.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class NoticiaMainAdapter extends RecyclerView.Adapter {
    private ArrayList<NoticiaMainModel> dataSet;
    Context mContext;
    int total_types;

    private Context mContext1;


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView mTitulo;
        ImageView mImage;
        TextView mDescripcion;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.mDescripcion = (TextView) itemView.findViewById(R.id.noticia_descripcion_layout_card_textview);
            this.mTitulo = (TextView) itemView.findViewById(R.id.noticia_titulo_layout_card_textview);
            this.mImage = (ImageView) itemView.findViewById(R.id.noticia_foto_layout_card_imageview);
        }
    }


    public NoticiaMainAdapter(ArrayList<NoticiaMainModel>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case NoticiaMainModel.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_noticia_item, parent, false);
                return new ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return NoticiaMainModel.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        NoticiaMainModel object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case NoticiaMainModel.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).mTitulo.setText(object.titulo);
                    ((ImageTypeViewHolder) holder).mDescripcion.setText(object.descripcion);
                    mContext1=((ImageTypeViewHolder) holder).mImage.getContext();
                    int idFoto= mContext1.getResources().getIdentifier(object.foto,"mipmap",mContext1.getPackageName());
                    //((ImageTypeViewHolder) holder).mImage.setImageResource(idFoto);
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
