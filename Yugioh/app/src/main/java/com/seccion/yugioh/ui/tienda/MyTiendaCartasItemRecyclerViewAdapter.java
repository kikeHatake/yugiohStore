package com.seccion.yugioh.ui.tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.seccion.yugioh.Common.MyApp;
import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;
import com.seccion.yugioh.Retrofit.Response.ResponseCartas;
import com.seccion.yugioh.Retrofit.Response.ResponseTiendaCartas;
import com.seccion.yugioh.Retrofit.ViewModel.CartasViewModel;
import com.seccion.yugioh.ui.cuadros_dialogo.ComprarDialogFragment;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyTiendaCartasItemRecyclerViewAdapter extends RecyclerView.Adapter<MyTiendaCartasItemRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<ResponseTiendaCartas> mValues;
    //Variables para cartas favoritos
    private YugiohViewModel mFavViewModel;

    //Variables de prueba para comparar
    private CartasViewModel mViewModel;
    private List<YugiohEntity> pruebaLista;

    //Variable para favoritos
    private ArrayList<String> nombreCartaFavorita;

    public MyTiendaCartasItemRecyclerViewAdapter(final List<ResponseTiendaCartas> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        /*Inicia el bloque de codigo para validar si es o no favorita*/
        pruebaLista = new ArrayList<>();
        nombreCartaFavorita = new ArrayList<>(); //->Este arreglo se iguala a la lista de favoritos para poder validar la info
        mFavViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
        mFavViewModel.getAllFavYugioh().observe((AppCompatActivity)ctx, new Observer<List<YugiohEntity>>() {
            @Override
            public void onChanged(List<YugiohEntity> yugiohEntityList) {
                pruebaLista = yugiohEntityList; //-> Cargamos la informacion que se encuentre en nuestra base de datos de favoritos de forma local
                Log.d("HIDEO","onAttachedToRecyclerView: La lista se ha cargado");
                if (pruebaLista.size() > 0){ //-> Si el alegro se no esta vacio lo recorremos
                    for (int i = 0; i < pruebaLista.size(); i++){
                        Log.d("HIDEO","onAttachedToRecyclerView: Las cartas en tu lista de favoritos son: "+pruebaLista.get(i).getNombre_carta());
                        nombreCartaFavorita.add(pruebaLista.get(i).getNombre_carta()); //-> le pasamos toda la informacion al arreglo que creamos para validar la informacion
                    }
                }else {
                    Log.d("HIDEO","onAttachedToRecyclerView: La lista esta vacia");
                }
                notifyDataSetChanged();
            }
        });
        /*Termina el bloque para cargar la lista de favoritos*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tienda_cartas_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.nombre_carta_tienda.setText(holder.mItem.getNombreCarta());
        holder.precio_carta.setText("Precio: "+holder.mItem.getPrecioCarta());
        holder.tipo_carta_tienda.setText("Tipo de carta: "+holder.mItem.getTipoCarta());
        holder.descripcion_carta_tienda.setText(holder.mItem.getDescripcionCarta());
        holder.ataque_carta_tienda.setText("ATK: "+holder.mItem.getAtaqueCarta());
        holder.defensa_carta_tienda.setText("DF: "+holder.mItem.getDefensaCarta());
        holder.estrellas_carta_tienda.setRating(Integer.parseInt(holder.mItem.getEstrellasCarta()));
        Picasso.get().load(holder.mItem.getImagenCarta()).into(holder.imagen_carta_tienda);

        /*Este bloque de codigo valida si la carta ya estaba agregada o no a la lista de favoritos
        * si se comple la condicion se pintara un corazon relleno*/
        if (nombreCartaFavorita.size() > 0) {
            if (nombreCartaFavorita.contains(holder.nombre_carta_tienda.getText().toString())){
                Log.d("HIDEO","Esta carta ya esta en favoritos");
                holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_black_24dp);
            }else {
                Log.d("HIDEO","Esta carta no esta en favoritos");
                holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }else {
            Log.d("HIDEO","No tienes cartas en favoritos");
            holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        /*Termina el bloque de codigo para validar si esta o no en la lista de favoritos*/

        holder.add_favorito_tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //El primer If valida si nuestro arreglo de cartas favoritas no esta vacio, si esta vacio guarda el valor automaticamente
                if (nombreCartaFavorita.size() > 0){
                    Log.d("HIDEO","Ya hay cartas guardadas como favoritas");
                    //Si entramos en esta condicion validamos si el nombre de la carta que queremos guardar en favoritos ya esta en la lista
                    if (nombreCartaFavorita.contains(holder.nombre_carta_tienda.getText().toString())) {
                        //Si el nombre estaba registrado anteriormente el corazon se despinta y se elimina de la lista de favoritos
                        Log.d("HIDEO","Esta carta ya esta en favoritos");
                        mFavViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
                        mFavViewModel.eliminarYugioh(position); //-> Para eliminar la informacion usamos la posicion del RecyclerView que se mando como parametro
                        holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        nombreCartaFavorita.remove(holder.nombre_carta_tienda.getText().toString());
                        Toast.makeText(ctx, "Se ha removido de tus favoritos", Toast.LENGTH_SHORT).show();

                    }else {
                        //Si el nombre de la carta no concuerda con ninguno anteriormente agregado se elimina y se pinta el corazon
                        Log.d("HIDEO","Esta carta aun no esta en favoritos");
                        mFavViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
                        mFavViewModel.insertarYugioh(new YugiohEntity(position, holder.mItem.getNombreCarta(), holder.mItem.getDescripcionCarta(),
                                holder.mItem.getTipoCarta(), holder.mItem.getAtaqueCarta(), holder.mItem.getDefensaCarta(), holder.mItem.getEstrellasCarta(),
                                holder.mItem.getImagenCarta(), holder.mItem.getPrecioCarta(), "si"));
                        holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_black_24dp);
                        nombreCartaFavorita.add(holder.nombre_carta_tienda.getText().toString());
                        Toast.makeText(ctx, "Agregado a tu lista de deseos", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //Si la lista de vaoritos esta vacia se agrega el valor sin preguntar
                    Log.d("HIDEO","No hay cartas guardadas como favoritas");
                    mFavViewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(YugiohViewModel.class);
                    mFavViewModel.insertarYugioh(new YugiohEntity(position, holder.mItem.getNombreCarta(), holder.mItem.getDescripcionCarta(),
                            holder.mItem.getTipoCarta(), holder.mItem.getAtaqueCarta(), holder.mItem.getDefensaCarta(), holder.mItem.getEstrellasCarta(),
                            holder.mItem.getImagenCarta(), holder.mItem.getPrecioCarta(), "si"));
                    holder.add_favorito_tienda.setImageResource(R.drawable.ic_favorite_black_24dp);
                    nombreCartaFavorita.add(holder.nombre_carta_tienda.getText().toString());
                    Toast.makeText(ctx, "Agregado a tu lista de deseos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.btnCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para realizar la compra de la carta pasamos todos los datos de la misma para almacenarla en el lista de cartas guardadas
                FragmentManager fragmentManager = ((AppCompatActivity)ctx).getSupportFragmentManager();
                ComprarDialogFragment comprar = new ComprarDialogFragment();
                Bundle arg = new Bundle();
                arg.putString("nombre_carta", holder.mItem.getNombreCarta());
                arg.putString("descripcion_carta", holder.mItem.getDescripcionCarta());
                arg.putString("tipo_carta", holder.mItem.getTipoCarta());
                arg.putString("ataque_carta", holder.mItem.getAtaqueCarta());
                arg.putString("defensa_carta", holder.mItem.getDefensaCarta());
                arg.putString("estrellas_carta", holder.mItem.getEstrellasCarta());
                arg.putString("imagen_carta", holder.mItem.getImagenCarta());
                arg.putString("precio_carta", holder.mItem.getPrecioCarta());
                arg.putString("favorito_carta", "no");//->Este valor no esta conciderado en la base de datos extera, por eso la pasamos predefinada
                arg.putString("precio", holder.mItem.getPrecioCarta());
                comprar.setArguments(arg);
                comprar.show(fragmentManager, "ComprarDialogFragment");
            }
        });

    }

    @Override
    public int getItemCount() {
        //Si nuestra lista mValues no null, retornamos el tamaño de nuestra lista, de lo contrario retornamos 0
        if (mValues != null){
            return mValues.size();
        }else {
            return 0;
        }
    }

    //Creamos un metodo donde igualemos nuestra variable mValue con una variable que recibe informacion desde nuestro fragmento
    //El fragmento es vinculado a nuestro Recyclerview
    public void setData(List<ResponseTiendaCartas> tiendaCartas){
        mValues = tiendaCartas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre_carta_tienda;
        public final TextView precio_carta;
        public final TextView tipo_carta_tienda;
        public final TextView descripcion_carta_tienda;
        public final TextView ataque_carta_tienda;
        public final TextView defensa_carta_tienda;
        public final RatingBar estrellas_carta_tienda;
        public final ImageView imagen_carta_tienda;
        public final ImageView add_favorito_tienda;
        public final Button btnCompras;
        public ResponseTiendaCartas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre_carta_tienda = view.findViewById(R.id.nombre_carta_tienda);
            precio_carta = view.findViewById(R.id.precio_carta);
            tipo_carta_tienda = view.findViewById(R.id.tipo_carta_tienda);
            descripcion_carta_tienda = view.findViewById(R.id.descripcion_carta_tienda);
            ataque_carta_tienda = view.findViewById(R.id.ataque_carta_tienda);
            defensa_carta_tienda = view.findViewById(R.id.defensa_carta_tienda);
            estrellas_carta_tienda = view.findViewById(R.id.estrellas_carta_tienda);
            imagen_carta_tienda = view.findViewById(R.id.imagen_carta_tienda);
            add_favorito_tienda = view.findViewById(R.id.add_favoritos_tienda);
            btnCompras = view.findViewById(R.id.btnComprar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombre_carta_tienda.getText() + "'";
        }
    }
}