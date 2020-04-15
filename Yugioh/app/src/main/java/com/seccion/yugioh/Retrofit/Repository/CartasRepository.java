package com.seccion.yugioh.Retrofit.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.seccion.yugioh.Common.MyApp;
import com.seccion.yugioh.Retrofit.Response.ResponseCartas;
import com.seccion.yugioh.Retrofit.YugiohClient;
import com.seccion.yugioh.Retrofit.YugiohServerApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartasRepository {
    //Primero creamos dos variables una para nuestra clase cliente y otra para nuestro servicio api
    YugiohServerApi yugiohServerApi;
    YugiohClient yugiohClient;
    //Despues debemos crear una lista de tipo MutableData
    MutableLiveData<List<ResponseCartas>> allCartas;

    //Creamos el metdo constructor de nuestra clase, este constructor sera vacio
    public CartasRepository() {
        //Nuestra variable del cliente debemos inicializarla con la instancia que se creo en la clase
        yugiohClient = YugiohClient.getINSTANCE();
        //Nuestra variable del seriverApi debemos incicializarla con nuestra clase yuguiohClient
        yugiohServerApi = yugiohClient.getYugiohServerApi();
        //Nuestra variable allCartas debemos igualarla a un metodo que nos permita recuperar toda la lista de cartas
        //Este metodo sera de tipo MutableLiveData
        allCartas = getAllCartas();
    }

    public MutableLiveData<List<ResponseCartas>> getAllCartas(){
        //Primero validamos si nuestra variable de allCartas esta vacia
        if (allCartas == null) {
            //Si esta condicion se cumple, le asignamos a nuestra variable de allCartas una nueva inctancia a un
            //nuevo MutableLiveData
            allCartas = new MutableLiveData<>();
        }
        //Aqui ejecutamos la peticion con retrofit
        //Creamos una variable de tipo call que tendra una lista de nuestra clase ResponseCartas
        Call<List<ResponseCartas>> call = yugiohServerApi.getCartas();
        //Ahora ejecutamos la peticion a retrofit
        call.enqueue(new Callback<List<ResponseCartas>>() {
            @Override
            public void onResponse(Call<List<ResponseCartas>> call, Response<List<ResponseCartas>> response) {
                if (response.isSuccessful()){
                    //Si recibimos respuesta del servidor se la pasamos a nuestra variable allCartas
                    allCartas.setValue(response.body());
                }else {
                    Toast.makeText(MyApp.getContext(), "Error en la respuesta de servidor", Toast.LENGTH_SHORT).show();
                    Log.d("HIDEO","Error: "+response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseCartas>> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Algo salio mal al conectarse con el servidor", Toast.LENGTH_SHORT).show();
                Log.d("HIDEO","Error de conexion: "+t.getLocalizedMessage());
            }
        });

        return allCartas;
    }
}
