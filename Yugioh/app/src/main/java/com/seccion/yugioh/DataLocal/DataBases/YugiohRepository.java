package com.seccion.yugioh.DataLocal.DataBases;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.seccion.yugioh.DataLocal.Dao.InterfaceYugiohFavoritos;
import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;

import org.w3c.dom.Text;

import java.util.List;

public class YugiohRepository {
    //Para esta clase debemos crear primero una variable que sea del mismo tipo que nuestra interfaz
    public InterfaceYugiohFavoritos interfaceYugiohFavoritos;
    //Despues debemos crear dos listas tipo LiveData con un List dentro para poder recuperar la informacion, la lista que tendran
    //sera del tipo de nuestra clase YugiohEntity
    LiveData<List<YugiohEntity>> allYugioh;
    LiveData<List<YugiohEntity>> allFavYugioh;

    //Estas variables se crean para consultar las cartas que el usuario a comprado
    LiveData<List<YugiogUserEntity>> allCartasUsuario;
    LiveData<List<YugiogUserEntity>> allCartasUsuarioFav;

    //Debemos de colocar el constructor de la clase y le pasaremos como parametro el tipo de Application
    public YugiohRepository(Application application) {
        //En el constructor creamos una nueva instancia de nuestra clase que extiende de RoomDatebase para poder recuperar la instancia
        //A esta instancia debemos pasarle como parametro nuestro context
        YugiohRoomDatabase db = YugiohRoomDatabase.getDatabase(application);
        //Ahora haciendo uso de la variable que instancia nuestra clase Database inicializamos nuestra variable de tipo Interface
        interfaceYugiohFavoritos = db.interfaceYugiohFavoritos();
        //Una vez que inicializamos la variable de la interfaz, hacemos lo mismo con las variables de tipo LiveData
        allYugioh = interfaceYugiohFavoritos.getAllYugioh();
        allFavYugioh = interfaceYugiohFavoritos.getAllYugiohFav();

        //Vamos a cargar la informacion que recuperamo desde la interfaz pero esta vez se consultaran las cartas compradas por el usuario
        allCartasUsuario = interfaceYugiohFavoritos.getAllCartasUsuario();
        allCartasUsuarioFav = interfaceYugiohFavoritos.getAllCartasUsuarioFav();
    }

    //Ahora que ya tenemos nuestras variablez inicializadas debemos crear dos metodos de tipo LiveData donde al final vamos a
    //retornar nuestras variables allYugioh y allFavYugioh
    public LiveData<List<YugiohEntity>> getAllYugioh(){return allYugioh;}
    public LiveData<List<YugiohEntity>> getAllFavYugioh(){return allFavYugioh;}

    //retornaremos nuestras variables para recuperar toda las cartas compradas por el usuario
    public LiveData<List<YugiogUserEntity>> getAllCartasUsuario(){return allCartasUsuario;}
    public LiveData<List<YugiogUserEntity>> getAllCartasUsuarioFav(){return allCartasUsuarioFav;}

    //Ahora crearemos los metodos para agregar un nuevo valor, para eliminar y actualizar, todos estos metodos ejecutan una clase
    //Asyncrona
    //********* Metodo para insertar informacion, a este metodo le pasamos por parametro nuestra clase Entity
    public void insertarYugioh(YugiohEntity yugiohEntity){
        //Aqui ejecutaremos la clase Asyncrona una vez creada
        new insertarYugiohAsyncTak(interfaceYugiohFavoritos).execute(yugiohEntity);
    }

    //La clase Asyncrona que creemos debe extener de AsyncTask y en su primer parametro debe recibir la clase YugiohEntity
    public class insertarYugiohAsyncTak extends AsyncTask<YugiohEntity, Void, Void>{
        //Dentro de esta clase debemos crear una nueva instancia a nuetra clase Interface
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsyncTask;
        //Crearemos el constructor que recibira como parametro nuestra interfaz
        public insertarYugiohAsyncTak(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            this.interfaceYugiohFavoritosAsyncTask = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(YugiohEntity... yugiohEntities) {
            //Dentro de este metodo debemos pasarle nuestro valor que teneimos en yugiohEntities a nuestra interfaz
            interfaceYugiohFavoritosAsyncTask.agregarYugioh(yugiohEntities[0]);
            return null;
        }
    }

    //El metodo para actualizar nuestra informacion debe ser creado de la misma manera que el metodo para insertar un nuevo valor
    public void actualizarYugioh(YugiohEntity yugiohEntity){
        //Dentro de esta clase tambien se ejecutara una clase de manera Asyncrona
        new actualizarYugiohAyncTak(interfaceYugiohFavoritos).execute(yugiohEntity);
    }

    //La clase Asyncrona que creemos aqui debe extender tambien de la clase AsyncTask
    public class actualizarYugiohAyncTak extends AsyncTask<YugiohEntity, Void, Void>{
        //Tambien debemos crear una nueva variable del tipo de nuestra interfaz
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsyncTask;
        //Crearemos el constructor de la clase, este constructor recibira como parametro nuestra clase Interfaz
        public actualizarYugiohAyncTak(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            this.interfaceYugiohFavoritosAsyncTask = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(YugiohEntity... yugiohEntities) {
            //Aqui pasamos nuestro valor que recibimos en yugiohEntities a nuestra variable de tipo Interfaz
            interfaceYugiohFavoritosAsyncTask.actualizarYugioh(yugiohEntities[0]);
            return null;
        }
    }

    //Ahora debemos crear el metodo para eliminar mediante un id, este metoo tambien ejecutara una clase Asyncrona
    public void eliminarYugioh(int idYugioh){
        //Este metodo tambien ejecuta una clase Asyncrona
        new eliminarYugiohAyncTask(interfaceYugiohFavoritos).execute(idYugioh);
    }

    public class eliminarYugiohAyncTask extends AsyncTask<Integer, Void, Void>{
        //Creamos una nueva variable del tipo de nuestra interfaz
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsyncTask;
        //Ahora creamos el constructor de nuestra clase, este constructr recibira como parametro nuestra Interfaz
        public eliminarYugiohAyncTask(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            this.interfaceYugiohFavoritosAsyncTask = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            //Recuperamos el integer selecionado por el usuario para eliminarlo
            interfaceYugiohFavoritosAsyncTask.eliminarById(integers[0]);
            return null;
        }
    }

    //Ahora crearemos el metodo para eliminar mediante el nombre de una carta
    public void eliminarCartaByNombre(String nombreCarta){
        //Este metodo ejecuta una clase Asyncrona
        new eliminarCartaByNombreAsyncTask(interfaceYugiohFavoritos).execute(nombreCarta);
    }

    public class eliminarCartaByNombreAsyncTask extends AsyncTask<String, Void, Void>{
        //Declaramos una nueva variable de nuestra interfaz
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsync;
        //Creamos el constructor de esta clase
        public eliminarCartaByNombreAsyncTask(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            interfaceYugiohFavoritosAsync = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(String... strings) {
            interfaceYugiohFavoritosAsync.eliminarByNombre(strings[0]);
            return null;
        }
    }

    /**********************************************************************************************/
    /*Este bloque decodigo esta dedicado a los metodo para consultar, eliminar, actualizar la informacion de las cartas compradas por el usuario*/
   public void insertarCartaComprada(YugiogUserEntity yugiogUserEntity){
       //Este metodo ejecuta una clase asyncrona para guardar una carta como comprada por el usuario
       new insertarCartaCompradaAsyncTask(interfaceYugiohFavoritos).execute(yugiogUserEntity);
   }

   public class insertarCartaCompradaAsyncTask extends AsyncTask<YugiogUserEntity, Void, Void>{
       //Creamos una nueva variable que conecte con nuestra interfaz
       private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsync;
       //Creamos el constructor de nuesta clase
       private insertarCartaCompradaAsyncTask(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
           this.interfaceYugiohFavoritosAsync = interfaceYugiohFavoritos;
       }
       @Override
       protected Void doInBackground(YugiogUserEntity... yugiogUserEntities) {
           interfaceYugiohFavoritosAsync.agregarCartaComprada(yugiogUserEntities[0]);
           return null;
       }
   }

   public void actualizarCartaComprada(YugiogUserEntity yugiogUserEntity){
       new actualizarCartaCompradaAsync(interfaceYugiohFavoritos).execute(yugiogUserEntity);
   }

   public class actualizarCartaCompradaAsync extends AsyncTask<YugiogUserEntity, Void,Void>{
       //Creamos una nueva variable para nuestra interfaz
       private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsync;
       //Creamos el constructor de la clase que recibe como parametro la interfaz
       private actualizarCartaCompradaAsync(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
           this.interfaceYugiohFavoritosAsync = interfaceYugiohFavoritos;
       }
       @Override
       protected Void doInBackground(YugiogUserEntity... yugiogUserEntities) {
           interfaceYugiohFavoritosAsync.actualizarCartaComprada(yugiogUserEntities[0]);
           return null;
       }
   }

   //Este metodo elimina las cartas compradas por el usuario mediante el id pero no se le dara la opcion al usuario
   public void eliminarCartaCompradaById(int idCartaComprada){
       new eliminarCartaCompradaByIdAsync(interfaceYugiohFavoritos).execute(idCartaComprada);
    }

    public class eliminarCartaCompradaByIdAsync extends AsyncTask<Integer, Void, Void>{
        //Creamos una nueva variable para la interfaz
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsync;
        //Creamos el constructor de la clase
        private eliminarCartaCompradaByIdAsync(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            this.interfaceYugiohFavoritosAsync = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            interfaceYugiohFavoritosAsync.eliminarCartasCompradasById(integers[0]);
            return null;
        }
    }

    //Este metodo elimina las cartas compradas por el usuario mediante el nombre de la carta
    public void eliminarCartaCompradaByNombre(String nombreCartaComprada){
       new eliminarCartaCompradaByNombreAsync(interfaceYugiohFavoritos).execute(nombreCartaComprada);
    }

    public class eliminarCartaCompradaByNombreAsync extends AsyncTask<String, Void, Void>{
        //Creamos una nueva variable de nuestra interfaz
        private InterfaceYugiohFavoritos interfaceYugiohFavoritosAsync;
        //Creamos el construtor
        private eliminarCartaCompradaByNombreAsync(InterfaceYugiohFavoritos interfaceYugiohFavoritos){
            this.interfaceYugiohFavoritosAsync = interfaceYugiohFavoritos;
        }
        @Override
        protected Void doInBackground(String... strings) {
            interfaceYugiohFavoritosAsync.eliminarCartasCompradasByNombre(strings[0]);
            return null;
        }
    }
}
