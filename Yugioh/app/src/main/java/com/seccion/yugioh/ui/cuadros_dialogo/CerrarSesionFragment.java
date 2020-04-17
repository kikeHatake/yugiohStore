package com.seccion.yugioh.ui.cuadros_dialogo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seccion.yugioh.MainActivity;
import com.seccion.yugioh.R;

public class CerrarSesionFragment extends DialogFragment {

    private View view;

    public static CerrarSesionFragment getInstance(){
        return new CerrarSesionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle("Estatus");
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                SharedPreferences preferenceManager = getContext().getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferenceManager.edit();
                editor.remove("user_login");
                editor.remove("user_passwor");
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        // Create the AlertDialog object and return it
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.fragment_cerrar_sesion, null);
        builder.setView(view);
        return builder.create();
    }
}
