package com.seccion.yugioh.ui.cartas_usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;
import com.seccion.yugioh.ui.cuadros_dialogo.DialogCartaDetalleFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCartasUsuariosItemRecyclerViewAdapter extends RecyclerView.Adapter<MyCartasUsuariosItemRecyclerViewAdapter.ViewHolder> {

    private List<YugiogUserEntity> mValues;
    private Context ctx;
    private YugiohViewModel mViewModel;

    public MyCartasUsuariosItemRecyclerViewAdapter(List<YugiogUserEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cartas_usuarios_item, parent, false);
        mViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre_carta_comprada.setText(holder.mItem.getNombre_carta());
        holder.tipo_carta_comprada.setText("Tipo: "+holder.mItem.getTipo_carta());
        holder.descripcion_carta_comprada.setText(holder.mItem.getDescripcion_carta());
        holder.ataque_carta_comprada.setText("ATK: "+holder.mItem.getAtaque_carta());
        holder.defensa_carta_comprada.setText("DEF: "+holder.mItem.getDefensa_carta());
        holder.estrellas_carta_comprada.setRating(Float.valueOf(holder.mItem.getEstrellas_carta()));
        Picasso.get().load(holder.mItem.getImagen_carta()).into(holder.imagen_carta_comprada);

        holder.btnComprarcomprada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)ctx).getSupportFragmentManager();
                DialogCartaDetalleFragment cartaDetalle = new DialogCartaDetalleFragment();
                Bundle arg = new Bundle();
                arg.putString("nombre_carta", holder.mItem.getNombre_carta());
                arg.putString("descripcion_carta", holder.mItem.getDescripcion_carta());
                arg.putString("tipo_carta", holder.mItem.getTipo_carta());
                arg.putString("ataque_carta", holder.mItem.getAtaque_carta());
                arg.putString("defensa_carta", holder.mItem.getDefensa_carta());
                arg.putString("estrellas_carta", holder.mItem.getEstrellas_carta());
                arg.putString("imagen_carta", holder.mItem.getImagen_carta());
                arg.putString("precio_carta", holder.mItem.getPrecio_carta());
                cartaDetalle.setArguments(arg);
                cartaDetalle.show(fragmentManager, "DialogCartaDetalleFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null){
            return mValues.size();
        }else {
            return 0;
        }
    }

    public void setData(List<YugiogUserEntity> userEntityList){
        mValues = userEntityList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imagen_carta_comprada;
        public final TextView nombre_carta_comprada;
        public final TextView tipo_carta_comprada;
        public final TextView descripcion_carta_comprada;
        public final TextView ataque_carta_comprada;
        public final TextView defensa_carta_comprada;
        public final RatingBar estrellas_carta_comprada;
        public final Button btnComprarcomprada;


        public YugiogUserEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imagen_carta_comprada = view.findViewById(R.id.imagen_carta_comprada);
            nombre_carta_comprada = view.findViewById(R.id.nombre_carta_comprada);
            tipo_carta_comprada = view.findViewById(R.id.tipo_carta_comprada);
            descripcion_carta_comprada = view.findViewById(R.id.descripcion_carta_comprada);
            ataque_carta_comprada = view.findViewById(R.id.ataque_carta_comprada);
            defensa_carta_comprada = view.findViewById(R.id.defensa_carta_comprada);
            estrellas_carta_comprada = view.findViewById(R.id.estrellas_carta_comprada);
            btnComprarcomprada = view.findViewById(R.id.btnComprarcomprada);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre_carta_comprada.getText() + "'";
        }
    }
}
