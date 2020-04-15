
package com.seccion.yugioh.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTiendaCartas {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("nombre_carta")
    @Expose
    private String nombreCarta;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("descripcion_carta")
    @Expose
    private String descripcionCarta;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("tipo_carta")
    @Expose
    private String tipoCarta;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("ataque_carta")
    @Expose
    private String ataqueCarta;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("defensa_carta")
    @Expose
    private String defensaCarta;
    @SerializedName("6")
    @Expose
    private String _6;
    @SerializedName("estrellas_carta")
    @Expose
    private String estrellasCarta;
    @SerializedName("7")
    @Expose
    private String _7;
    @SerializedName("imagen_carta")
    @Expose
    private String imagenCarta;
    @SerializedName("8")
    @Expose
    private String _8;
    @SerializedName("precio_carta")
    @Expose
    private String precioCarta;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseTiendaCartas() {
    }

    /**
     * 
     * @param descripcionCarta
     * @param nombreCarta
     * @param imagenCarta
     * @param _0
     * @param _1
     * @param tipoCarta
     * @param _2
     * @param _3
     * @param estrellasCarta
     * @param _4
     * @param _5
     * @param defensaCarta
     * @param _6
     * @param precioCarta
     * @param _7
     * @param _8
     * @param id
     * @param ataqueCarta
     */
    public ResponseTiendaCartas(String _0, String id, String _1, String nombreCarta, String _2, String descripcionCarta, String _3, String tipoCarta, String _4, String ataqueCarta, String _5, String defensaCarta, String _6, String estrellasCarta, String _7, String imagenCarta, String _8, String precioCarta) {
        super();
        this._0 = _0;
        this.id = id;
        this._1 = _1;
        this.nombreCarta = nombreCarta;
        this._2 = _2;
        this.descripcionCarta = descripcionCarta;
        this._3 = _3;
        this.tipoCarta = tipoCarta;
        this._4 = _4;
        this.ataqueCarta = ataqueCarta;
        this._5 = _5;
        this.defensaCarta = defensaCarta;
        this._6 = _6;
        this.estrellasCarta = estrellasCarta;
        this._7 = _7;
        this.imagenCarta = imagenCarta;
        this._8 = _8;
        this.precioCarta = precioCarta;
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

    public String getNombreCarta() {
        return nombreCarta;
    }

    public void setNombreCarta(String nombreCarta) {
        this.nombreCarta = nombreCarta;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String getDescripcionCarta() {
        return descripcionCarta;
    }

    public void setDescripcionCarta(String descripcionCarta) {
        this.descripcionCarta = descripcionCarta;
    }

    public String get3() {
        return _3;
    }

    public void set3(String _3) {
        this._3 = _3;
    }

    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public String get4() {
        return _4;
    }

    public void set4(String _4) {
        this._4 = _4;
    }

    public String getAtaqueCarta() {
        return ataqueCarta;
    }

    public void setAtaqueCarta(String ataqueCarta) {
        this.ataqueCarta = ataqueCarta;
    }

    public String get5() {
        return _5;
    }

    public void set5(String _5) {
        this._5 = _5;
    }

    public String getDefensaCarta() {
        return defensaCarta;
    }

    public void setDefensaCarta(String defensaCarta) {
        this.defensaCarta = defensaCarta;
    }

    public String get6() {
        return _6;
    }

    public void set6(String _6) {
        this._6 = _6;
    }

    public String getEstrellasCarta() {
        return estrellasCarta;
    }

    public void setEstrellasCarta(String estrellasCarta) {
        this.estrellasCarta = estrellasCarta;
    }

    public String get7() {
        return _7;
    }

    public void set7(String _7) {
        this._7 = _7;
    }

    public String getImagenCarta() {
        return imagenCarta;
    }

    public void setImagenCarta(String imagenCarta) {
        this.imagenCarta = imagenCarta;
    }

    public String get8() {
        return _8;
    }

    public void set8(String _8) {
        this._8 = _8;
    }

    public String getPrecioCarta() {
        return precioCarta;
    }

    public void setPrecioCarta(String precioCarta) {
        this.precioCarta = precioCarta;
    }

}
