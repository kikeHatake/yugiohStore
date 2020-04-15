package com.seccion.yugioh.ui.cartas_usuario;

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

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class CartasUsuariosItemFragment extends Fragment {

    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private MyCartasUsuariosItemRecyclerViewAdapter adapter;
    private List<YugiogUserEntity> yugiogUserEntityList;
    private YugiohViewModel mViewModel;

    public CartasUsuariosItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartas_usuarios_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            yugiogUserEntityList = new ArrayList<>();
            adapter = new MyCartasUsuariosItemRecyclerViewAdapter(yugiogUserEntityList, getActivity());
            recyclerView.setAdapter(adapter);
            loadDatos();
        }
        return view;
    }

    private void loadDatos() {
        mViewModel = ViewModelProviders.of(getActivity()).get(YugiohViewModel.class);
        mViewModel.getAllCartasCompradas().observe(getActivity(), new Observer<List<YugiogUserEntity>>() {
            @Override
            public void onChanged(List<YugiogUserEntity> yugiogUserEntities) {
                adapter.setData(yugiogUserEntities);
            }
        });
    }

}
