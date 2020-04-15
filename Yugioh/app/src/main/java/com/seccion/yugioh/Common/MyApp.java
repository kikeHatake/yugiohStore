package com.seccion.yugioh.Common;

import android.app.Application;
import android.content.Context;

//TODO: Importante recordar que debemos extender la clase de Application para poder obtener el contxto y el metodo onCreate
//Esta clae debe extender de Application para poder recuperar el context en otras clases
public class MyApp extends Application {
    //TODO: Para poder implementar esta clase debemos declarar en el AndroidManifest justo debajo de donde se abre la etiqueta
    //TODO: <application, debemos declarar este atributo "android:name=".Common.MyApp""
    //Creamos una variable privada y static de nuestra clase para poder usarla como instancia
    private static MyApp instance;
    //Creamos un metodo publico y static del mismo tipo que nuestra clase donde retorne nuestra instancia, este metodo se llama
    //getInstance()
    public static MyApp getInstance(){
        return instance;
    }
    //Creamos un metodo publico y static de tipo Context y tambien retornara nuestra instancia, se llamara getContext
    public static Context getContext(){
        return instance;
    }
    //Creamos e metodo onCreate donde igualaremo nuestra variable instancia a this, antes del super del metodo
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

}
