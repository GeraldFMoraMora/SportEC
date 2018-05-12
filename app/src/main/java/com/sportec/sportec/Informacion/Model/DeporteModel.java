package com.sportec.sportec.Informacion.Model;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class DeporteModel {
    public static final int IMAGE_TYPE = 0;

    public int type;
    public String foto;
    public String nombre;

    public DeporteModel() {
    }

    public DeporteModel(int type, String partido, String foto) {
        this.type = type;
        this.foto = foto;
    }
}
