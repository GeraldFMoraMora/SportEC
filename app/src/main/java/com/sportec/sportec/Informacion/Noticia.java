package com.sportec.sportec.Informacion;

import com.sportec.sportec.R;

import java.util.Date;

/**
 * Created by GeraldMM on 04/05/2018.
 */

public class Noticia {

    public String mTitulo;
    public String mDescripcion;
    public Date mFecha;
    public int mImagen;

    public Noticia(String titulo, String descripcion, Date fecha, int  imagen){
        this.mTitulo=titulo;
        this.mDescripcion=descripcion;
        this.mFecha=fecha;
        this.mImagen=imagen;
    }
    public String getTitulo(){return this.mTitulo;}
    public String getDescripcion(){return this.mDescripcion;}
    public Date getFecha(){return this.mFecha;}
    public int getImagen(){return this.mImagen;}

    public long getId(){return mTitulo.hashCode();}

    public void setTitulo(String titulo){this.mTitulo=titulo;}
    public void setmDescripcion(String descripcion){this.mDescripcion=descripcion;}
    public void setmFecha(Date fecha){this.mFecha=fecha;}
    public void setmImagen(int imagen){this.mImagen=imagen;}

    public static Noticia[] ITEMS = {
            new Noticia("Uno","uno", new java.util.Date(), R.mipmap.grid1),
            new Noticia("Dos","dos", new java.util.Date(),R.mipmap.grid2),
            new Noticia("Tres","tres", new java.util.Date(),R.mipmap.grid3),
            new Noticia("Cuatro","cuatro", new java.util.Date(),R.mipmap.grid4)
    };
    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static Noticia getItem(int id) {
        for (Noticia item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
