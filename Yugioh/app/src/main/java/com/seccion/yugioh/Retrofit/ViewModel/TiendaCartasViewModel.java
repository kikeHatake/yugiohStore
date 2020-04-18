package com.seccion.yugioh.Retrofit.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.seccion.yugioh.Retrofit.Repository.TiendaCartasRepository;
import com.seccion.yugioh.Retrofit.Response.ResponseTiendaCartas;

import java.util.List;

import okhttp3.Response;

public class TiendaCartasViewModel extends AndroidViewModel {
    //Primer debemo de declarar dos variables, una variable sera del tipo de nuestra clase Repository y la otra sera una LiveData
    private TiendaCartasRepository tiendaCartasRepository;
    //Variable de tipo LiveData con una Lista dentro, la lista sera de nuestra clase ResponseTiendaCartas
    private LiveData<List<ResponseTiendaCartas>> listaTienda;

    //Debemos crear un construtor donde le pasemos como parametro Application y dentro del constructor un super con application
    public TiendaCartasViewModel(Application application) {
        super(application);
        //Inicialozamos la variable de tiendaCartasRepository con su clase
        tiendaCartasRepository = new TiendaCartasRepository();
        //La variable de tipo LiveData debemos inicializarla con el metodo que se encuentra en nuestra clase TiendaCartasRepository
        listaTienda = tiendaCartasRepository.getAllTiendaCartas();
    }

    //Creamos un metodo para recuperar la informacion, este metodo debe retornar nuestra variable llamada listaTienda
    public LiveData<List<ResponseTiendaCartas>> getListaTienda(){return listaTienda;}
    //Creamos el metodo para actualizar haciendo un pull to refresh
    public LiveData<List<ResponseTiendaCartas>> getListaNuevaTienda(){
        listaTienda = tiendaCartasRepository.getAllTiendaCartas();
        return listaTienda;
    }
}
