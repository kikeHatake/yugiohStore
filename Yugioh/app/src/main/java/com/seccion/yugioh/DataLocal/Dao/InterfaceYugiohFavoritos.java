package com.seccion.yugioh.DataLocal.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//Esta clase va a iniciar con la clase Dao y  debera ser una Interface
@Dao
public interface InterfaceYugiohFavoritos {

    /*Estos metodos son para la clase donde se enlistan las cartas que se encuentrar en la tienda*/

    //El metodo insert no recibe nada, solamente en el metodo debemos pasarle un objeto de la clase Entity
    @Insert
    void agregarYugioh(YugiohEntity yugiohEntity);
    //El metodo update funciona igual, solamente recibe en el metodo un objeto de la clase Entity
    @Update
    void actualizarYugioh(YugiohEntity yugiohEntity);
    //El metodo query si recibe una cunsulta, esta consulta es DELETE FROM tablename -> esta consulta es igual a la de MySql
    @Query("DELETE FROM yugiohFavorito")
    void eliminarTodos();
    //El metodo Query para eliminar mediante un id tiene la misma estructura solo agregamos el metodo WHERE y eliminaremos por
    //id, este metodo debe recibir en sus parametros un id de tipo Int
    @Query("DELETE FROM yugiohFavorito WHERE idLocal = :idYugioh")
    void eliminarById(int idYugioh);
    //Crearemos un metodo para eliminar pero mediante el nombre
    @Query("DELETE FROM yugiohFavorito WHERE nombre_carta = :nombreCarta")
    void eliminarByNombre(String nombreCarta);
    //Para el metodo Select se ejecuta de la misma forma que en MySql, en su metodo no recibe ningun parametro
    @Query("SELECT * FROM yugiohFavorito")
    LiveData<List<YugiohEntity>> getAllYugioh();
    //Para el metodo Select se ejecuta de la misma forma que en MySql, en su metodo no recibe ningun parametro
    @Query("SELECT * FROM yugiohFavorito WHERE favorito_carta LIKE 'si'")
    LiveData<List<YugiohEntity>> getAllYugiohFav();

    /*Estos metodos son para las cartas que el usuario ya compro, no le daremos acceso para eliminar cartas pero se creara el metodo*/
    //Este metodo sera el encargado de enlistar todas las cartas que el usuario compre
    @Insert
    void agregarCartaComprada(YugiogUserEntity yugiogUserEntity);
    //El metodo update tampoco se le dara el acceso al usuario mas que para cambiar el estatus de favorito
    @Update
    void actualizarCartaComprada(YugiogUserEntity yugiogUserEntity);
    //Este metodo se ejecutara para eliminar una carta, no se le dara acceso a esta funcion al usuario
    @Query("DELETE FROM cartasUsuario")
    void eliminarCartasCompradas();
    //En base en casos pasados vamos a implementar otros dos metodos para eliminar, uno sera mediante el id y el otro mediante el nombre de la arta
    @Query("DELETE FROM cartasUsuario WHERE id = :idCartaUsuario")
    void eliminarCartasCompradasById(int idCartaUsuario);
    //Este otro metodo servira para eliminar una carta mediante el nombre de la misma
    @Query("DELETE FROM cartasUsuario WHERE nombre_carta = :nombreCartaUsuario")
    void eliminarCartasCompradasByNombre(String nombreCartaUsuario);
    //Crearemos los metodo necesarios para consultar la informacion y enlistar todas las cartas
    @Query("SELECT * FROM cartasUsuario")
    LiveData<List<YugiogUserEntity>> getAllCartasUsuario();
    //Creamos el metodo para consultar cartas del usuario pero filtradas por favoritas
    @Query("SELECT * FROM cartasUsuario WHERE favorito_carta = 'si'")
    LiveData<List<YugiogUserEntity>> getAllCartasUsuarioFav();
}
