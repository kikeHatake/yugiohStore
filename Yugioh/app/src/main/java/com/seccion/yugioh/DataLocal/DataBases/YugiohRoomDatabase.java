package com.seccion.yugioh.DataLocal.DataBases;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.seccion.yugioh.DataLocal.Dao.InterfaceYugiohFavoritos;
import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;

@Database(entities = {YugiohEntity.class, YugiogUserEntity.class}, version = 1)
//Esta clase debe ser abstracta y extender de RoomDatebase
public abstract class YugiohRoomDatabase extends RoomDatabase {
    //Primero creamos una variable que sea del tipo de nuestra interfaz, la variable debe ser tipo publica y abstracta
    public abstract InterfaceYugiohFavoritos interfaceYugiohFavoritos();
    //Ahora creamos una variable que funcione como nuestra instancia y sera privada, statica y volatil, debe ser del mismo tipo
    //que nuestra clase
    public static volatile YugiohRoomDatabase INSTANCE;

    //Ahora crearemos el metodo donde vamos a crear nuestra instancia con el metodo singleton, este metodo recibe como parametro
    //el contexto de tipo final
    public static YugiohRoomDatabase getDatabase(final Context context){
        //Primero validamos si nuestra instancia es igual a null
        if (INSTANCE == null){
            //Si esta condicion se cumple ejecutamos el metodo synchronized
            synchronized (YugiohRoomDatabase.class){
                //Dentro de este metodo tambien debemos volver a validar si la instancia es igual a null
                if (INSTANCE == null){
                    //Si se cumple de nuevo nuestra condicion, iniciamos una nueva instancia
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    YugiohRoomDatabase.class,
                                             "yugiohDatabase").build();
                }
            }
        }
        //Retornamos nuestra instancia
        return INSTANCE;
    }
}
