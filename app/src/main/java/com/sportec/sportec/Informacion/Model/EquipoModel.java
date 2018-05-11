package com.sportec.sportec.Informacion.Model;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class EquipoModel {
    public static final int IMAGE_TYPE=0;

    public int type;
    public String foto;
    public String nombre;
    public String deporte;

    public EquipoModel(){}
    public EquipoModel(int type, String foto, String nombre,String deporte)
    {
        this.type=type;
        this.foto=foto;
        this.deporte=deporte;
    }
}
