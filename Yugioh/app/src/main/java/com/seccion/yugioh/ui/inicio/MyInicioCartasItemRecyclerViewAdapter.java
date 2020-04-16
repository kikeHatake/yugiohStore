package com.seccion.yugioh.ui.inicio;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seccion.yugioh.R;
import com.seccion.yugioh.Retrofit.Response.ResponseCartas;
import com.seccion.yugioh.Retrofit.ViewModel.CartasViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

import java.util.List;

public class MyInicioCartasItemRecyclerViewAdapter extends RecyclerView.Adapter<MyInicioCartasItemRecyclerViewAdapter.ViewHolder> {

    //Creamos una variable de tipo Context
    private Context ctx;
    //A nuestra variable mValues que era de tipo List de la clase Dummy le pasamos una lista de nuestra clase ResponseCartas
    private List<ResponseCartas> mValues;
    //Creamos una instancia de nuestra clase CartasViewModel
    private CartasViewModel mViewModel;

    public MyInicioCartasItemRecyclerViewAdapter(List<ResponseCartas> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inicio_cartas_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre_carta.setText(holder.mItem.getNombreCarta());
        holder.tipo_carta.setText("Tipo: "+holder.mItem.getTipoCarta());
        holder.descripcion_carta.setText(holder.mItem.getDescripcionCarta());
        holder.ataque_carta.setText("ATK "+holder.mItem.getAtaqueCarta());
        holder.defensa_carta.setText("DEF "+holder.mItem.getDefensaCarta());
        Picasso.get().load(holder.mItem.getImagenCarta()).into(holder.imagen_carta);
        holder.estrellas_carta.setRating(Integer.parseInt(holder.mItem.getEstrellasCarta()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //Hay que validar si nuestra lista esta vacia o no para evitar errores
    @Override
    public int getItemCount() {
        if (mValues != null){
            return mValues.size();
        }else {
            return 0;
        }
    }

    //Creamos el metodo que declaramos en la clase item y la lista que recibamos de la clase item se la enviamos a nuestra lista
    //mValues para mostrar y enlistar los resutados
    public void setData(List<ResponseCartas> cartasList){
        this.mValues = cartasList;
        //Actualizamos el contenido de nuestro RecyclerView
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre_carta;
        public final TextView tipo_carta;
        public final TextView descripcion_carta;
        public final TextView ataque_carta;
        public final TextView defensa_carta;
        public final ImageView imagen_carta;
        public final RatingBar estrellas_carta;
        //Cambiamos el mItem para que sea de tipo RepsonseCartas
        public ResponseCartas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre_carta = view.findViewById(R.id.nombre_carta);
            tipo_carta = view.findViewById(R.id.tipo_carta);
            descripcion_carta = view.findViewById(R.id.descripcion_carta);
            ataque_carta = view.findViewById(R.id.ataque_carta);
            defensa_carta = view.findViewById(R.id.defensa_carta);
            imagen_carta = view.findViewById(R.id.imagen_carta);
            estrellas_carta = view.findViewById(R.id.estrellas_carta);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre_carta.getText() + "'";
        }
    }
}
