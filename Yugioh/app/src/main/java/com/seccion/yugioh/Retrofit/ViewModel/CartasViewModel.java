package com.seccion.yugioh.Retrofit.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.seccion.yugioh.Retrofit.Repository.CartasRepository;
import com.seccion.yugioh.Retrofit.Response.ResponseCartas;

import java.util.List;

//Importante: esta clase debe extender de la clase AndroidViewModel
public class CartasViewModel extends AndroidViewModel {
    //Debemos crear una variable de tipo CartasRepository
    private CartasRepository cartasRepository;
    //Debemos crear una variable que sea de tipo LiveData y le pasaremos una Lista de nuestra clase ResponseCartad
    private LiveData<List<ResponseCartas>> listaCartas;

    //Debemos crear el constructor de la clase y pasarle como parametro el contexto, el parametro sera de tipo Appication
    public CartasViewModel(Application application){
        super(application);
        //En el constructor inicializamos las variables que creamos
        cartasRepository = new CartasRepository();
        listaCartas = cartasRepository.getAllCartas();
    }

    //Debemos crear un metodo de tipo LiveData para recuperar y listar la lista de cartas
    public LiveData<List<ResponseCartas>> getListaCartas(){return listaCartas;}

}
