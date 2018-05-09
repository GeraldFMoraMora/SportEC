package com.sportec.sportec.Informacion;

/**
 * Created by GeraldMM on 08/05/2018.
 */

public class Deporte {
    private String mFoto;
    private String mNombre;
    public String[] mAficionado;
    public String[] mEquipo;

    public Deporte(){}
    public Deporte(String foto, String nombre, String[] aficionado, String[] equipo){
        this.mFoto=foto;
        this.mNombre=nombre;
        this.mAficionado=aficionado;
        this.mEquipo=equipo;
    }
}
