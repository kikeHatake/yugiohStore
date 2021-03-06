package com.seccion.yugioh.ui.tienda;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seccion.yugioh.R;
import com.seccion.yugioh.Retrofit.Response.ResponseTiendaCartas;
import com.seccion.yugioh.Retrofit.ViewModel.TiendaCartasViewModel;

import java.util.List;

public class TiendaCartasItemFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    //En esta clase debemos inicializar una variable de tipi ViewModel
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private MyTiendaCartasItemRecyclerViewAdapter adapter;
    private TiendaCartasViewModel mViewModel;
    //Tambien debemos de crear una lista del tipo de ResponseTiendaCartas
    private List<ResponseTiendaCartas> tiendaCartas;
    //Integramos el metodo Swipe
    SwipeRefreshLayout swipeRefreshLayout;


    public TiendaCartasItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //La variable ViewModel la debemos inicializar en el metodo onCreate
        mViewModel = ViewModelProviders.of(getActivity()).get(TiendaCartasViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tienda_cartas_item_list, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_view_tienda);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list_tienda);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        //Al adapter debemos de pasarle la nueva lista que creamos y el contexto de la clase con getActivity
        adapter = new MyTiendaCartasItemRecyclerViewAdapter(tiendaCartas, getActivity());
        recyclerView.setAdapter(adapter);
        loadCartasTienda();

        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void loadCartasTienda() {
        mViewModel.getListaTienda().observe(getActivity(), new Observer<List<ResponseTiendaCartas>>() {
            @Override
            public void onChanged(List<ResponseTiendaCartas> responseTiendaCartas) {
                tiendaCartas = responseTiendaCartas;
                adapter.setData(tiendaCartas);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mViewModel.getListaNuevaTienda().observe(getActivity(), new Observer<List<ResponseTiendaCartas>>() {
            @Override
            public void onChanged(List<ResponseTiendaCartas> responseTiendaCartas) {
                tiendaCartas = responseTiendaCartas;
                adapter.setData(tiendaCartas);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
