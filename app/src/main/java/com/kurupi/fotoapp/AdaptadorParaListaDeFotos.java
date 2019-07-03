package com.kurupi.fotoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

class AdaptadorParaListaDeFotos extends RecyclerView.Adapter<AdaptadorParaListaDeFotos.VistaDeFoto> {

    private List<DatoCapturado> mi_lista_de_fotos;

    AdaptadorParaListaDeFotos(List<DatoCapturado> lista_de_fotos) {
        this.mi_lista_de_fotos = lista_de_fotos;
    }

    @NonNull
    @Override
    public VistaDeFoto onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.esquema_de_foto, viewGroup, false);
        return new VistaDeFoto(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaDeFoto vistaDeFoto, int position) {
        vistaDeFoto.cambiar_dato_capturado(mi_lista_de_fotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mi_lista_de_fotos.size();
    }


    public class VistaDeFoto extends RecyclerView.ViewHolder {

        public VistaDeFoto(@NonNull View itemView) {
            super(itemView);
        }

        private TextView texto_de_la_fecha() {
            return itemView.findViewById(R.id.recurso_texto_hora_de_captura);
        }

        private ImageView vista_de_foto() {
            return itemView.findViewById(R.id.foto_capturada);
        }

        public void cambiar_dato_capturado(DatoCapturado captura) {
            vista_de_foto().setImageBitmap(captura.su_imagen_de_captura());
            texto_de_la_fecha().setText(fecha_formateada(captura));
        }

        private String fecha_formateada(DatoCapturado captura) {
            return new SimpleDateFormat("yyyy MM dd")
                    .format(captura.su_fecha_de_captura());
        }
    }
}
