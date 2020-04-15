
package com.seccion.yugioh.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUsuario {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("estatus")
    @Expose
    private String estatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseUsuario() {
    }

    /**
     * 
     * @param estatus
     * @param success
     */
    public ResponseUsuario(Integer success, String estatus) {
        super();
        this.success = success;
        this.estatus = estatus;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
