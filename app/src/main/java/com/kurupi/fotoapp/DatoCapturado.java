package com.kurupi.fotoapp;

import android.graphics.Bitmap;

import java.util.Date;

public class DatoCapturado {
    private Date mi_fecha_de_captura;
    private Bitmap mi_imagen_de_captura;

    public DatoCapturado(Date fecha_de_captura, Bitmap imagen_de_captura) {
        this.mi_fecha_de_captura = fecha_de_captura;
        this.mi_imagen_de_captura = imagen_de_captura;
    }

    public static DatoCapturado crear(Date fecha_de_captura, Bitmap image_de_captura) {
        return new DatoCapturado(fecha_de_captura, image_de_captura);
    }

    public Date su_fecha_de_captura() {
        return mi_fecha_de_captura;
    }

    public Bitmap su_imagen_de_captura() {
        return mi_imagen_de_captura;
    }
}
