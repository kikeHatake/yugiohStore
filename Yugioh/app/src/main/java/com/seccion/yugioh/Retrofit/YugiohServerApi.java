package com.seccion.yugioh.Retrofit;

import com.seccion.yugioh.Retrofit.Response.ResponseCartas;
import com.seccion.yugioh.Retrofit.Response.ResponseTiendaCartas;
import com.seccion.yugioh.Retrofit.Response.ResponseUpdateUser;
import com.seccion.yugioh.Retrofit.Response.ResponseUsuario;
import com.seccion.yugioh.Retrofit.Response.ResponseUsuarioLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

//Esta interfaz sera la encarga de generar las peticiones get, post, put y delete
public interface YugiohServerApi {
    //Para poder pasarle parametros tenemos que hacer uso de el metodo Query, este metodo nos permite pasar la informacion por meidio
    //de la url
    @GET("yugioh-webservice/registroUsuario.php")
    Call<ResponseUsuario> doRegister(@Query("nombre_usuario")String nombre_usuario,
                                     @Query("nombre_completo")String nombre_completo,
                                     @Query("correo_electronico")String correo_electronico,
                                     @Query("password")String password,
                                     @Query("edad_usuario")String edad_usuario);

    @GET("yugioh-webservice/consultaUsuario.php")
    Call<ResponseUsuarioLogin> doLogin(@Query("nombre_usuario")String nombre_usuario,
                                       @Query("password")String password);

    @GET("yugioh-webservice/consultaCartas.php")
    Call<List<ResponseCartas>> getCartas();

    @GET("yugioh-webservice/consultaTienda.php")
    Call<List<ResponseTiendaCartas>> getCartasTienda();

    @PUT("yugioh-webservice/actualizarUserName.php")
    Call<ResponseUpdateUser> updateUserName(@Query("idUsuario")String idUsuario,
                                            @Query("nombre_usuario")String nombre_usuario);

}
