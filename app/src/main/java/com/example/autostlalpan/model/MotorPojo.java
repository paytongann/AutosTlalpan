package com.example.autostlalpan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotorPojo {

    @SerializedName("transmision")
    @Expose
    public String transmision;
    @SerializedName("drivetrain")
    @Expose
    public String drivetrain;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("hp")
    @Expose
    public String hp;
    @SerializedName("cilindros")
    @Expose
    public Integer cilindros;
    @SerializedName("inyeccion")
    @Expose
    public String inyeccion;

}
