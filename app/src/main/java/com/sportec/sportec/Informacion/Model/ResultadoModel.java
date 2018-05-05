package com.sportec.sportec.Informacion.Model;

/**
 * Created by GeraldMM on 05/05/2018.
 */

public class ResultadoModel {
    public static final int IMAGE_TYPE=0;

    public int type;
    public int data;
    public String text;

    public ResultadoModel(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
    }
}
