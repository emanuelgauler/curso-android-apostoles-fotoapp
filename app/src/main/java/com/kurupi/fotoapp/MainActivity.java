package com.kurupi.fotoapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int SOLICITUD_TOMAR_FOTO = 1;
    private RecyclerView.Adapter adaptador_lista_de_fotos;
    private List<DatoCapturado> mi_lista_de_fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mi_lista_de_fotos = new ArrayList<>();
        adaptador_lista_de_fotos = new AdaptadorParaListaDeFotos(mi_lista_de_fotos);

        RecyclerView view = findViewById(R.id.recurso_lista_de_fotos);
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view.setAdapter(adaptador_lista_de_fotos);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    lanzar_camara();
                } catch (ActivityNotFoundException ex) {
                    Snackbar.make(view, ex.getMessage(), Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.d("SACANDO FOTO", String.valueOf(data.getExtras().isEmpty()));
        if (SOLICITUD_TOMAR_FOTO == requestCode && RESULT_OK == resultCode) {
            Bitmap foto = recuperar_foto_del_dato(data);
            agregar_a_la_lista_la_foto(foto);
        }
    }

    private void agregar_a_la_lista_la_foto(Bitmap foto) {
        mi_lista_de_fotos
                .add(DatoCapturado
                        .crear(new Date(), foto)
                );
        adaptador_lista_de_fotos.notifyDataSetChanged();
    }


    private Bitmap recuperar_foto_del_dato(Intent data) {
        Log.d("RECUPERANDO FOTO", data.getExtras().get("data").toString());
        try {
            Object foto_capturada = data.getExtras().get("data");
            if ( foto_capturada != null) {
                return (Bitmap) foto_capturada;
            }
        } catch (Exception ex) {
            Snackbar.make(findViewById(R.id.fab), ex.getMessage(), Snackbar.LENGTH_SHORT)
                    .show();
        }

        return null;
    }

    private void lanzar_camara() {
        Intent despachar_camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(despachar_camara, SOLICITUD_TOMAR_FOTO);
    }
}
