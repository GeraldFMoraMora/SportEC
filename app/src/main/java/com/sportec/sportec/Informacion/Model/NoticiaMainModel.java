package com.sportec.sportec.Informacion.Model;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class NoticiaMainModel {
    public static final int IMAGE_TYPE=0;

    public int type;
    public String foto;
    public String titulo;
    public String descripcion;

    public NoticiaMainModel(){}
    public NoticiaMainModel(int type, String text, String foto,String descripcion)
    {
        this.type=type;
        this.foto=foto;
        this.titulo=text;
        this.descripcion=descripcion;
    }
}
