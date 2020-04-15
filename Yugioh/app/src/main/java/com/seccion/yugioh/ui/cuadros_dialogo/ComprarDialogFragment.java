package com.seccion.yugioh.ui.cuadros_dialogo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;

public class ComprarDialogFragment extends DialogFragment {

    private View view;
    private TextView mensajeCompra, precio_txt, saldo_txt;
    private Bundle data;
    private Double precio;
    private String nombre_carta, descripcion_carta, tipo_carta, ataque_carta, defensa_carta, estrellas_carta, imagen_carta, precio_carta, favorito_carta;

    public static ComprarDialogFragment newInstance()   {
        return new ComprarDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = this.getArguments();
        nombre_carta = data.getString("nombre_carta");
        descripcion_carta = data.getString("descripcion_carta");
        tipo_carta = data.getString("tipo_carta");
        ataque_carta = data.getString("ataque_carta");
        defensa_carta = data.getString("defensa_carta");
        estrellas_carta = data.getString("estrellas_carta");
        imagen_carta = data.getString("imagen_carta");
        precio_carta = data.getString("precio_carta");
        favorito_carta = data.getString("favorito_carta");
        //Este precio se usa para validar si el usuario puede o no comprar las cartas
        precio = Double.valueOf(data.getString("precio"));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle("¿Quieres comprar esta carta?");
        //builder.setMessage("");
        builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Double saldo_usuario = Double.valueOf(saldo_txt.getText().toString());
                        if (saldo_usuario > precio){
                            //Si el saldo del usuario es suficiente se agrega a la lista de cartas compradas
                            //Metodo para guardar la carta en la lista de compradas
                            YugiohViewModel mViewModel;
                            mViewModel = ViewModelProviders.of(getActivity()).get(YugiohViewModel.class);
                            mViewModel.insertarCartaComprada(new YugiogUserEntity(nombre_carta, descripcion_carta,
                                    tipo_carta, ataque_carta, defensa_carta, estrellas_carta, imagen_carta, precio_carta, favorito_carta));
                            //Mostramos un mensaje de confirmacion al usuario
                            FragmentManager fragmentManager = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
                            AprobadoDialogFragment aprobadoDialogFragment = new AprobadoDialogFragment();
                            aprobadoDialogFragment.show(fragmentManager, "AprobadoDialogFragment");
                        }else {
                            //Si no se puede comprar se le notifica al usuario
                            FragmentManager fragmentManager = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
                            CancelarDialogFragment cancelarDialogFragment = new CancelarDialogFragment();
                            cancelarDialogFragment.show(fragmentManager, "CancelarDialogFragment");
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.fragment_comprar_dialog, null);
        mensajeCompra = view.findViewById(R.id.mensajeCompra);
        precio_txt = view.findViewById(R.id.precio_txt);
        saldo_txt = view.findViewById(R.id.saldo_txt);
        precio_txt.setText(String.valueOf(precio));
        builder.setView(view);
        return builder.create();

    }
}
