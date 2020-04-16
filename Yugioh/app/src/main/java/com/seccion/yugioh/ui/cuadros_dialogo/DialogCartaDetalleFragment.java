package com.seccion.yugioh.ui.cuadros_dialogo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;
import com.squareup.picasso.Picasso;

public class DialogCartaDetalleFragment extends DialogFragment {

    private View view;
    private ImageView cerrar_carta, imagen_carta;
    private TextView nombre_carta, tipo_carta, precio_carta, descripcion_carta, defensa_carta, ataque_carta;
    private String nombre_carta_recibida, tipo_carta_recibida, precio_carta_recibida, descripcion_carta_recibida, defensa_carta_recibida, ataque_carta_recibida, imagen_carta_recibida, estrellas_carta_recibida;
    private RatingBar estrellas_carta;
    private Bundle data;

    public static DialogCartaDetalleFragment getInstance(){
        return new DialogCartaDetalleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En el metodo onCreate vamos a pasarle un estilo personalizado para que ocupe la pantalla completa
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        data = getArguments();
        nombre_carta_recibida = data.getString("nombre_carta");
        tipo_carta_recibida = data.getString("tipo_carta");
        precio_carta_recibida = data.getString("precio_carta");
        descripcion_carta_recibida = data.getString("descripcion_carta");
        defensa_carta_recibida = data.getString("defensa_carta");
        ataque_carta_recibida = data.getString("ataque_carta");
        imagen_carta_recibida = data.getString("imagen_carta");
        estrellas_carta_recibida = data.getString("estrellas_carta");

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Create the AlertDialog object and return it
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.fragment_dialog_carta_detalle, null);
        nombre_carta = view.findViewById(R.id.nombre_carta);
        tipo_carta = view.findViewById(R.id.tipo_carta);
        precio_carta = view.findViewById(R.id.precio_carta);
        descripcion_carta = view.findViewById(R.id.descripcion_carta);
        defensa_carta = view.findViewById(R.id.defensa_carta);
        ataque_carta = view.findViewById(R.id.ataque_carta);
        estrellas_carta = view.findViewById(R.id.estrellas_carta);
        imagen_carta = view.findViewById(R.id.imagen_carta);

        nombre_carta.setText(nombre_carta_recibida);
        tipo_carta.setText("Tipo: "+tipo_carta_recibida);
        precio_carta.setText("Precio: "+precio_carta_recibida);
        descripcion_carta.setText(descripcion_carta_recibida);
        defensa_carta.setText("DEF: "+defensa_carta_recibida);
        ataque_carta.setText("ATK: "+ataque_carta_recibida);
        estrellas_carta.setRating(Float.valueOf(estrellas_carta_recibida));
        Picasso.get().load(imagen_carta_recibida).into(imagen_carta);

        cerrar_carta = view.findViewById(R.id.cerrar_carta);
        cerrar_carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

}
