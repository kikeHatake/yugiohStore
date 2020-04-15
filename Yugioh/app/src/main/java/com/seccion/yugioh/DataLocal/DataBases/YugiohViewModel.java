package com.seccion.yugioh.DataLocal.DataBases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.seccion.yugioh.DataLocal.Dao.YugiogUserEntity;
import com.seccion.yugioh.DataLocal.Dao.YugiohEntity;

import java.util.List;

//Esta clase debe extender de AndroidViewModel, si marca error es por que debemos crear un constructor que reciba como parametro
//Appication y debemos pasar este parametro al super del constructor
public class YugiohViewModel extends AndroidViewModel {
    //Esta clase se conectara con nuestro repositorio por lo cual debemos crear una variable del tipo Repository
    private YugiohRepository yugiohRepository;
    //Ahora crearemos dos variables de tipo LiveData, estas variables tendra una lista del tipo de nuestra clase Entity
    private LiveData<List<YugiohEntity>> allYugioh;
    private LiveData<List<YugiohEntity>> allFavYugioh;

    //Ahora crearemos dos variables de tipo LiveData, estas variables tendran una lista del topo de la entidad de cartas compradas
    private LiveData<List<YugiogUserEntity>> allCartasCompradas;
    private LiveData<List<YugiogUserEntity>> allCartasCompradasFav;

    public YugiohViewModel(Application application) {
        super(application);
        //Incializamos como new a nuestra variable yugiohRepository, esta instancia debe recibir como parametro el contexto Application
        yugiohRepository = new YugiohRepository(application);
        //Ahora inicializaremos nuestras variables de tipo LiveData
        allYugioh = yugiohRepository.getAllYugioh();
        allFavYugioh = yugiohRepository.getAllFavYugioh();
        //Inicializaremos nuestras variables para la lista de las cartas compradas
        allCartasCompradas = yugiohRepository.getAllCartasUsuario();
        allCartasCompradasFav = yugiohRepository.getAllCartasUsuarioFav();
    }

    //Creamos dos metodos de tipo LiveData con un List entro y retornaremos nuestras variables allYugioh y allFavYugioh
    public LiveData<List<YugiohEntity>> getAllYugioh(){return allYugioh;}
    public LiveData<List<YugiohEntity>> getAllFavYugioh(){return allFavYugioh;}

    //Ahora debemos crear los tres metodos necesarios para eliminar, actualizar y insertar un nuevo elemento
    public void insertarYugioh(YugiohEntity yugiohEntity){yugiohRepository.insertarYugioh(yugiohEntity);}
    public void actualizarYugioh(YugiohEntity yugiohEntity){yugiohRepository.actualizarYugioh(yugiohEntity);}
    public void eliminarYugioh(int idYugioh){yugiohRepository.eliminarYugioh(idYugioh);}
    public void eliminarCartaByNombre(String nombreCarta){yugiohRepository.eliminarCartaByNombre(nombreCarta);}

    /***************************************************************************************************/
    //Este bloque de codigo es para la lista de cartas compradas por el usuario
    public LiveData<List<YugiogUserEntity>> getAllCartasCompradas(){return allCartasCompradas;}
    public LiveData<List<YugiogUserEntity>> getAllCartasCompradasFav(){return allCartasCompradasFav;}

    //Aqui se enlistan todos los metodos para eliminar, actualizar, insertas en la lista de cartas compradas
    public void insertarCartaComprada(YugiogUserEntity yugiogUserEntity){yugiohRepository.insertarCartaComprada(yugiogUserEntity);}
    public void actualizarCartaComprada(YugiogUserEntity yugiogUserEntity){yugiohRepository.actualizarCartaComprada(yugiogUserEntity);}
    public void eliminarCartaComprada(int idCartaComprada){yugiohRepository.eliminarCartaCompradaById(idCartaComprada);}
    public void eliminarCartaCompradaByName(String nombreCarta){yugiohRepository.eliminarCartaCompradaByNombre(nombreCarta);}
}
