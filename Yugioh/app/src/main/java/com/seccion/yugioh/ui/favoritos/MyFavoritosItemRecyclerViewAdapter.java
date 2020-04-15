package com.seccion.yugioh.ui.favoritos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;
import com.seccion.yugioh.ui.cuadros_dialogo.ComprarDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFavoritosItemRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoritosItemRecyclerViewAdapter.ViewHolder> {

    private List<YugiohEntity> mValues;
    private Context ctx;
    private YugiohViewModel mViewModel;

    public MyFavoritosItemRecyclerViewAdapter(List<YugiohEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        mViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favoritos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.nombre_carta_favoritos.setText(holder.mItem.getNombre_carta());
        holder.precio_carta.setText("Precio: "+holder.mItem.getPrecio_carta());
        holder.tipo_carta_favoritos.setText("Tipo: "+holder.mItem.getTipo_carta());
        holder.descripcion_carta_favoritos.setText(holder.mItem.getDescripcion_carta());
        holder.ataque_carta_favoritos.setText("ATK: "+holder.mItem.getAtaque_carta());
        holder.defensa_carta_favoritos.setText("DEF: "+holder.mItem.getDefensa_carta());
        holder.estrellas_carta_favoritos.setRating(Integer.parseInt(holder.mItem.getEstrellas_carta()));
        Picasso.get().load(holder.mItem.getImagen_carta()).into(holder.imagen_carta_favoritos);

        holder.add_favorito_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.eliminarCartaByNombre(holder.nombre_carta_favoritos.getText().toString());
            }
        });

        holder.btnComprasFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Para realizar la compra de la carta pasamos todos los datos de la misma para almacenarla en el lista de cartas guardadas
                FragmentManager fragmentManager = ((AppCompatActivity)ctx).getSupportFragmentManager();
                ComprarDialogFragment comprar = new ComprarDialogFragment();
                Bundle arg = new Bundle();
                arg.putString("nombre_carta", holder.mItem.getNombre_carta());
                arg.putString("descripcion_carta", holder.mItem.getDescripcion_carta());
                arg.putString("tipo_carta", holder.mItem.getTipo_carta());
                arg.putString("ataque_carta", holder.mItem.getAtaque_carta());
                arg.putString("defensa_carta", holder.mItem.getDefensa_carta());
                arg.putString("estrellas_carta", holder.mItem.getEstrellas_carta());
                arg.putString("imagen_carta", holder.mItem.getImagen_carta());
                arg.putString("precio_carta", holder.mItem.getPrecio_carta());
                arg.putString("favorito_carta", "no");//->Este valor no esta conciderado en la base de datos extera, por eso la pasamos predefinada
                arg.putString("precio", holder.mItem.getPrecio_carta());
                comprar.setArguments(arg);
                comprar.show(fragmentManager, "ComprarDialogFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevaCarta(List<YugiohEntity> yugiohEntityList){
        this.mValues = yugiohEntityList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre_carta_favoritos;
        public final TextView precio_carta;
        public final TextView tipo_carta_favoritos;
        public final TextView descripcion_carta_favoritos;
        public final TextView ataque_carta_favoritos;
        public final TextView defensa_carta_favoritos;
        public final RatingBar estrellas_carta_favoritos;
        public final ImageView imagen_carta_favoritos;
        public final ImageView add_favorito_favoritos;
        public final Button btnComprasFavoritos;
        public YugiohEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre_carta_favoritos = view.findViewById(R.id.nombre_carta_favoritos);
            precio_carta = view.findViewById(R.id.precio_carta);
            tipo_carta_favoritos = view.findViewById(R.id.tipo_carta_favoritos);
            descripcion_carta_favoritos = view.findViewById(R.id.descripcion_carta_favoritos);
            ataque_carta_favoritos = view.findViewById(R.id.ataque_carta_favoritos);
            defensa_carta_favoritos = view.findViewById(R.id.defensa_carta_favoritos);
            estrellas_carta_favoritos = view.findViewById(R.id.estrellas_carta_favoritos);
            imagen_carta_favoritos = view.findViewById(R.id.imagen_carta_favoritos);
            add_favorito_favoritos = view.findViewById(R.id.add_favoritos_favoritos);
            btnComprasFavoritos = view.findViewById(R.id.btnComprarFavoritos);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre_carta_favoritos.getText() + "'";
        }
    }
}
