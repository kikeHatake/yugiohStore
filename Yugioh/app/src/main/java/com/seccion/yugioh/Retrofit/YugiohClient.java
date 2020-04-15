package com.seccion.yugioh.Retrofit;

import com.seccion.yugioh.Common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YugiohClient {
    //Creamos una variable privata y static que sera del mismo tipo que nuestra clase, esta variable nos servira como instancia
    private static  YugiohClient INSTANCE = null;
    //Ahora creamos una variable de tipo privada y static de nuestra interfaz YugiohServerApi
    private static YugiohServerApi yugiohServerApi;
    //Ahora crearemos una variable de tipo Retrofit y tambien sera privada
    private Retrofit retrofit;

    //Crearemosel constructor de nuestra clase, este constructor estara vacion
    public YugiohClient() {
        //Aqui lo que haremos sera inicializar nuestro servicio de retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_YUGIOH_URL) //-> Al base url debemos pasarle la url con la que se conectara con nuestro servidor
                .addConverterFactory(GsonConverterFactory.create())//-> Debemos pasare un GsonConverFactory.create() para poder convertor los json recuperados de nuestra url
                .build();
        //Creamos nuestro servicio retrofit, recordar que la case YugioherverApi es la clase que contendra nuestras peticiones al servidor
        yugiohServerApi = retrofit.create(YugiohServerApi.class);
    }

    //Creamos un metodo publico y statico para iniciar o crear nuestra instancia en caso de que sea null
    public static YugiohClient getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new YugiohClient();
        }
        return INSTANCE;
    }
    //Creamos un metodo public del tipo de la interface que nos retornara nuestra variable yugiohServerApi
    public YugiohServerApi getYugiohServerApi(){return yugiohServerApi;}
}
