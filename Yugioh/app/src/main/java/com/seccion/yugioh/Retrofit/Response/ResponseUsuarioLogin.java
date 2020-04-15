
package com.seccion.yugioh.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUsuarioLogin {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("nombre_usuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("nombre_completo")
    @Expose
    private String nombreCompleto;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("correo_electronico")
    @Expose
    private String correoElectronico;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("edad_usuario")
    @Expose
    private String edadUsuario;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseUsuarioLogin() {
    }

    /**
     * 
     * @param _0
     * @param _1
     * @param password
     * @param _2
     * @param _3
     * @param _4
     * @param _5
     * @param edadUsuario
     * @param id
     * @param nombreUsuario
     * @param nombreCompleto
     * @param correoElectronico
     */
    public ResponseUsuarioLogin(String _0, String id, String _1, String nombreUsuario, String _2, String nombreCompleto, String _3, String correoElectronico, String _4, String password, String _5, String edadUsuario) {
        super();
        this._0 = _0;
        this.id = id;
        this._1 = _1;
        this.nombreUsuario = nombreUsuario;
        this._2 = _2;
        this.nombreCompleto = nombreCompleto;
        this._3 = _3;
        this.correoElectronico = correoElectronico;
        this._4 = _4;
        this.password = password;
        this._5 = _5;
        this.edadUsuario = edadUsuario;
    }

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String get3() {
        return _3;
    }

    public void set3(String _3) {
        this._3 = _3;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String get4() {
        return _4;
    }

    public void set4(String _4) {
        this._4 = _4;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get5() {
        return _5;
    }

    public void set5(String _5) {
        this._5 = _5;
    }

    public String getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(String edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

}
