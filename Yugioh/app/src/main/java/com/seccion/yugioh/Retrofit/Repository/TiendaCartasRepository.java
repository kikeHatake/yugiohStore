package com.seccion.yugioh.Retrofit.Repository;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.seccion.yugioh.Common.MyApp;
import com.seccion.yugioh.Retrofit.Response.ResponseTiendaCartas;
import com.seccion.yugioh.Retrofit.YugiohClient;
import com.seccion.yugioh.Retrofit.YugiohServerApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaCartasRepository {
    //Creamos dos variables privadas, una es de tipo YugiohClient y la otra es del tipo YugiohServerApi
    private YugiohClient yugiohClient;
    private YugiohServerApi yugiohServerApi;
    //Debemos crear una variable de tipo MutableLiveData y dentro de ella una Lista
    private MutableLiveData<List<ResponseTiendaCartas>> allTiendaCartas;

    //Ahora toca crear el constructor de la clase donde inicizalizaremos nuestras variables, sera vacio
    public TiendaCartasRepository() {
        //Inicializamos nuestra variable del cliente para obtener la instancia
        yugiohClient = YugiohClient.getINSTANCE();
        //Inicializamos la variable del servidor
        yugiohServerApi = yugiohClient.getYugiohServerApi();
        //Por ultimo inicializamos la variable de tipo MutableLiveData, esta variable se igualara a un metodo que despues crearemos
        allTiendaCartas = getAllTiendaCartas();
    }

    //Ahora debemos crear un metodo que sea del tipo MutableLiveData
    public MutableLiveData<List<ResponseTiendaCartas>> getAllTiendaCartas(){
        //Tenemos que vaidar si nuestra variable de allTiendaCartas esta vacia, si esta vacia la igualamos a una nueva MutableLiveData
        if (allTiendaCartas == null){
            allTiendaCartas = new MutableLiveData<>();
        }
        //Debemos de crear una variable call de tipo Call, esta tendra dentro una lista del tipo ResponseTiendaCartas y
        //para inicializarla debemos igualarla al metodo que recupera la informacion que declaramos en la clase del server api
        Call<List<ResponseTiendaCartas>> call = yugiohServerApi.getCartasTienda();
        //Ejecutamos la peticion call
        call.enqueue(new Callback<List<ResponseTiendaCartas>>() {
            @Override
            public void onResponse(Call<List<ResponseTiendaCartas>> call, Response<List<ResponseTiendaCartas>> response) {
                if (response.isSuccessful()){
                    //Si la peticion sale bien, la enviamos a nuestra variable allTiendaCarta con el metodo setValue
                    allTiendaCartas.setValue(response.body());
                }else {
                    Toast.makeText(MyApp.getContext(), "Error al realizar la peticion al servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseTiendaCartas>> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Error al conectar al servidor", Toast.LENGTH_SHORT).show();
            }
        });

        //Retornamos nuestra variable que creamos
        return allTiendaCartas;
    }

}
