package com.sportec.sportec.Informacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.R;

/**
 * Created by GeraldMM on 04/05/2018.
 */

public class NoticiaAdapter extends BaseAdapter {

    private Context mContext;

    public NoticiaAdapter(Context context){
        this.mContext=context;
    }
    @Override
    public int getCount() {
        return Noticia.ITEMS.length;
    }

    @Override
    public Noticia getItem(int position) {
        return Noticia.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        return Noticia.getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.app_bar_main,viewGroup,false);
        }
        ImageView imagenNoticia = (ImageView) view.findViewById(R.id.imagen_coche);
        TextView tituloNoticia = (TextView) view.findViewById(R.id.nombre_coche);

        final Noticia item = Noticia.getItem(position);
        imagenNoticia.setImageResource(item.getImagen());
        tituloNoticia.setText(item.getTitulo());

        return view;
    }
}
