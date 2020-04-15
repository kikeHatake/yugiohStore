package com.seccion.yugioh.DataLocal.Dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Esta clase se va a definir como nuestra clase Entity, para hacer esto solamente debemos escribir @Entity y dentro de los
//parentecis debemos agregar el nombre de la tabla
@Entity(tableName = "yugiohFavorito")
public class YugiohEntity {

    //Tambien debemos definir una llame primaria, usaremos el metodo @PrimaryKey y dentro de los parentecis le pasamos el metodo
    //autoGenerate = true para que funcione como una primaryKey -> autogenerate
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int idLocal;
    private String nombre_carta;
    private String descripcion_carta;
    private String tipo_carta;
    private String ataque_carta;
    private String defensa_carta;
    private String estrellas_carta;
    private String imagen_carta;
    private String precio_carta;
    private String favorito_carta;

    //En el constructor pasaremos todas la variables menos el id por que se se genera de forma automatica
    public YugiohEntity(int idLocal, String nombre_carta, String descripcion_carta, String tipo_carta, String ataque_carta, String defensa_carta, String estrellas_carta, String imagen_carta, String precio_carta, String favorito_carta) {
        this.idLocal = idLocal;
        this.nombre_carta = nombre_carta;
        this.descripcion_carta = descripcion_carta;
        this.tipo_carta = tipo_carta;
        this.ataque_carta = ataque_carta;
        this.defensa_carta = defensa_carta;
        this.estrellas_carta = estrellas_carta;
        this.imagen_carta = imagen_carta;
        this.precio_carta = precio_carta;
        this.favorito_carta = favorito_carta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre_carta() {
        return nombre_carta;
    }

    public void setNombre_carta(String nombre_carta) {
        this.nombre_carta = nombre_carta;
    }

    public String getDescripcion_carta() {
        return descripcion_carta;
    }

    public void setDescripcion_carta(String descripcion_carta) {
        this.descripcion_carta = descripcion_carta;
    }

    public String getTipo_carta() {
        return tipo_carta;
    }

    public void setTipo_carta(String tipo_carta) {
        this.tipo_carta = tipo_carta;
    }

    public String getAtaque_carta() {
        return ataque_carta;
    }

    public void setAtaque_carta(String ataque_carta) {
        this.ataque_carta = ataque_carta;
    }

    public String getDefensa_carta() {
        return defensa_carta;
    }

    public void setDefensa_carta(String defensa_carta) {
        this.defensa_carta = defensa_carta;
    }

    public String getEstrellas_carta() {
        return estrellas_carta;
    }

    public void setEstrellas_carta(String estrellas_carta) {
        this.estrellas_carta = estrellas_carta;
    }

    public String getImagen_carta() {
        return imagen_carta;
    }

    public void setImagen_carta(String imagen_carta) {
        this.imagen_carta = imagen_carta;
    }

    public String getPrecio_carta() {
        return precio_carta;
    }

    public void setPrecio_carta(String precio_carta) {
        this.precio_carta = precio_carta;
    }

    public String getFavorito_carta() {
        return favorito_carta;
    }

    public void setFavorito_carta(String favorito_carta) {
        this.favorito_carta = favorito_carta;
    }
}
