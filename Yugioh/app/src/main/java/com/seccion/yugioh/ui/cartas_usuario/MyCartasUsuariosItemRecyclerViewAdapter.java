package com.seccion.yugioh.ui.cartas_usuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.print.PrinterId;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.DataBases.YugiohViewModel;
import com.seccion.yugioh.R;

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
        holder.mIdView.setText(holder.mItem.getNombre_carta());
        holder.mContentView.setText(holder.mItem.getDescripcion_carta());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        public final TextView mIdView;
        public final TextView mContentView;
        public YugiogUserEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
