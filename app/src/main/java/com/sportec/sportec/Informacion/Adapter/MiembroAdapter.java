package com.sportec.sportec.Informacion.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Model.MiembroModel;
import com.sportec.sportec.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class MiembroAdapter extends RecyclerView.Adapter {
    private ArrayList<MiembroModel> dataSet;
    Context mContext;
    int total_types;


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView mImage;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.titulo = (TextView) itemView.findViewById(R.id.textview_miembro_nombre);
            this.mImage = (ImageView) itemView.findViewById(R.id.imageview_miembro_foto);
        }
    }


    public MiembroAdapter(ArrayList<MiembroModel>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case MiembroModel.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_miembro_item, parent, false);
                return new ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return MiembroModel.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        MiembroModel object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case MiembroModel.IMAGE_TYPE:
                    ((ImageTypeViewHolder) holder).titulo.setText(object.text);
                    Context mContext1=((ImageTypeViewHolder) holder).mImage.getContext();
                    int idFoto= mContext1.getResources().getIdentifier(object.foto,"mipmap",mContext1.getPackageName());
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
