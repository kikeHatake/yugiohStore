package com.seccion.yugioh.ui.inicio;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seccion.yugioh.R;
import com.seccion.yugioh.Retrofit.Response.ResponseCartas;
import com.seccion.yugioh.Retrofit.ViewModel.CartasViewModel;

import java.util.List;

public class InicioCartasItemFragment extends Fragment {

    private int mColumnCount = 1;
    //Declaramos como variable global el recyclerView
    private RecyclerView recyclerView;
    //Declaramos como variable global nuestro adaptador
    private MyInicioCartasItemRecyclerViewAdapter adapter;
    //Creamos una lista gobal de nuestra respuesta del servidor que contiene el modelo de nuestro json
    private List<ResponseCartas> cartasList;
    //Creamos una instancia a nuestra clase ViewModel que nos permitira recuperar la informacion y enviarla al adapter
    private CartasViewModel cartasViewModel;

    public InicioCartasItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicializamos nuestra clase ViewModel
        cartasViewModel = ViewModelProviders.of(getActivity()).get(CartasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_cartas_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyInicioCartasItemRecyclerViewAdapter(cartasList, getActivity());
            recyclerView.setAdapter(adapter);
            loadCartas();
        }
        return view;
    }

    //En este metodo debemos mandar a llamar al metodo de getListaCartas que se encuentra en nuestra clase ViewModel
    //a este metodo le pasaremos un observador que nos permitira pasar los datos una vez cargados a nuestro adaptador
    private void loadCartas() {
        cartasViewModel.getListaCartas().observe(getActivity(), new Observer<List<ResponseCartas>>() {
            @Override
            public void onChanged(List<ResponseCartas> responseCartas) {
                //a la lista que creamos en esta clase debemos igualarla a la lista que noes devuelve como respuesta nuestro servidor
                cartasList = responseCartas;
                //Una vez que rescuperamos la lista de cartas de servidor la enviamos a un metodo dentro de nuestra clase adapter
                adapter.setData(cartasList);
            }
        });
    }

}
