package com.sportec.sportec.Informacion.Model;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class BusquedaModel {
    public static final int IMAGE_TYPE=0;

    public int type;
    public String foto;
    public String mPartido;
    public String mDescripcion;

    public BusquedaModel(){}
    public BusquedaModel(int type, String partido, String foto, String descripcion)
    {
        this.type=type;
        this.foto=foto;
        this.mPartido=partido;
        this.mDescripcion=descripcion;
    }
}
