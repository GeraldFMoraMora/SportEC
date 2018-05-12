package com.sportec.sportec.Informacion;

/**
 * Created by GeraldMM on 08/05/2018.
 */

public class Usuario {
    public static final int IMAGE_TYPE = 0;
    public int type;
    public String mNombre;
    public String mCorreo;
    public String mFoto;

    public Usuario() {
    }

    public Usuario(int type, String nombre, String correo, String foto) {
        this.mNombre = nombre;
        this.mCorreo = correo;
        this.mFoto = foto;
    }
}
