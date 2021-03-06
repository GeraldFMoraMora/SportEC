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

    public Noticia(String titulo, String descripcion, Date fecha, int imagen) {
        this.mTitulo = titulo;
        this.mDescripcion = descripcion;
        this.mFecha = fecha;
        this.mImagen = imagen;
    }

    public String getTitulo() {
        return this.mTitulo;
    }

    public String getDescripcion() {
        return this.mDescripcion;
    }

    public Date getFecha() {
        return this.mFecha;
    }

    public int getImagen() {
        return this.mImagen;
    }

    public long getId() {
        return mTitulo.hashCode();
    }

    public void setTitulo(String titulo) {
        this.mTitulo = titulo;
    }

    public void setmDescripcion(String descripcion) {
        this.mDescripcion = descripcion;
    }

    public void setmFecha(Date fecha) {
        this.mFecha = fecha;
    }

    public void setmImagen(int imagen) {
        this.mImagen = imagen;
    }

}
