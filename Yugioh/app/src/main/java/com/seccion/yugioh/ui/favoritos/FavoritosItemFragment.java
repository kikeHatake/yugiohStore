package com.seccion.yugioh.ui.favoritos;

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

import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritosItemFragment extends Fragment {

    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private MyFavoritosItemRecyclerViewAdapter adapter;
    private List<YugiohEntity> yugiohEntityList;
    private YugiohViewModel mViewMoel;

    public FavoritosItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            yugiohEntityList = new ArrayList<>();
            adapter = new MyFavoritosItemRecyclerViewAdapter(yugiohEntityList, getActivity());
            recyclerView.setAdapter(adapter);
            loadDatos();
        }
        return view;
    }

    private void loadDatos() {
        mViewMoel = ViewModelProviders.of(getActivity()).get(YugiohViewModel.class);
        mViewMoel.getAllFavYugioh().observe(getActivity(), new Observer<List<YugiohEntity>>() {
            @Override
            public void onChanged(List<YugiohEntity> yugiohEntities) {
                adapter.setNuevaCarta(yugiohEntities);
            }
        });
    }

}
