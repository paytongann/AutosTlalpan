package com.example.autostlalpan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarsPojo {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("titulo")
    @Expose
    public String titulo;
    @SerializedName("marca")
    @Expose
    public String marca;
    @SerializedName("tipo")
    @Expose
    public String tipo;
    @SerializedName("modelo")
    @Expose
    public Integer modelo;
    @SerializedName("precio")
    @Expose
    public Integer precio;
    @SerializedName("colorExterior")
    @Expose
    public String colorExterior;
    @SerializedName("colorInterior")
    @Expose
    public String colorInterior;
    @SerializedName("kilomeraje")
    @Expose
    public Integer kilomeraje;
    @SerializedName("motor")
    @Expose
    public MotorPojo motor;
    @SerializedName("equipoInterior")
    @Expose
    public String equipoInterior;
    @SerializedName("equipoExterior")
    @Expose
    public String equipoExterior;
    @SerializedName("imagenes")
    @Expose
    public List<String> imagenes = null;
}
