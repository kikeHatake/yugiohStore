package com.seccion.yugioh.Common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    //Creamos una variable de tipo private y static, esta se declarara como final y de tipo String, esta contendra el nombre de
    //nuestro archivo donde se guardaran las configuraciones
    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";
    //Creamos el constructor de la clase este constructor sera privado, sera un constructor vacio
    private SharedPreferencesManager(){}
    //Ahora debemos crear un metodo SharedPreference donde configuraremos nuestro archivo de configuracion
    //Este metodo debe de ser de tipo private y static, tambien sera del tipo SharedPreferences, lo llamaramos getSharedPreferences
    private static SharedPreferences getSharedPreferences(){
        //Este metodo nos va a retornar nuestra configuracion del archivo, devolvera el nombre y el contexto del archivo
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }
    //Ahora debemos crear los metodos para enviar parametros para guardar y para recuperar estos mismos
    //Este metodo recibie dos parametro de tipo String, uno de ellos es el que funcionara como nuestra llave para identificar el valor
    //Y el otro parametro tambien sera de tipo String y contendra nuestro vaor a guardar
    public static void setSomeStringValue(String dataLabel, String dataValue){
        //Llamamos a nuestro editor para poder guardar archivos en el fichero que se creo
        //Una vez que creamos nuestra instancia esta debe apuntar a nuestro metodo dentro de la misma clase pero que es de tipo
        //SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        //Al editor que cabamos de crear debemos pasarle los dos datos que recibe este metodo de tipo String
        editor.putString(dataLabel, dataValue);
        //Al final ejecutamos el metodo commit para poder guardar los cambios
        editor.commit();
    }

    //Ahora creamos otro metodo pero este recibira un parametro boleano, la estructura es similar al metodo de arriba
    public static void setSomeBooleanValue(String dataLabel, boolean dataValue){
        //Tambien creamos una instancia al Editor que nos permitira guardar los valores en el archivo de configuraciones
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        //Cuando le pasemos los datos en su metodo putboolean debemos pasar el valor String que recibimos al llamar el metodo
        //Este String no servira para poder usarlo como llame y el valor en este caso sera un booleando
        editor.putBoolean(dataLabel, dataValue);
        //Tambien para terminar con este metodo debemos ejecutar el metodo commit
        editor.commit();
    }

    //Ahora crearemos los metodo para recuperar la informacion guarda dentro de nuestro archivo, el primer metodo se llamara
    //getSomeStringValue, este metodo sera de tipo String y recibira un String como parametro, el String sera usado para poder
    //Consultar la informacion dentro del fichero, este metodo debe retornar un String
    public static String getSomeStringValue(String dataLabel){
        //El return de este metodo debe invocar al metodo getSharedPreferences seguido de .getString y como primer parametro
        //le pasaremos el dataLabel y en el segundo parametro que socita sera un valor null
        return getSharedPreferences().getString(dataLabel, null);
    }

    //Para crear el metodo que nos permita recuperar valores booleanos sera la misma estructura que el metodo de arriba,
    //Este metodo devolvera un booleando
    public static boolean getSomeBooleanValue(String dataLabel){
        //El retur de este metodo debe ser similar al de que nos devuelve un String, la unica diferencia es que este metodo
        //en su segundo parametro del getBoolean en vez de pasarle null, le pasamos un false
        return getSharedPreferences().getBoolean(dataLabel, false);
    }

}
