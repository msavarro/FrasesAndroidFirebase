package com.example.laboratorioars2019;

import java.io.Serializable;

public class Frase implements Serializable {
    private String frase;
    private String autor;
    private float cantVotos;
    private float sumaActual;
    private String id;

    public Frase(){
        super();
        cantVotos = 0;
        sumaActual = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getCantVotos() {
        return cantVotos;
    }

    public void setCantVotos(float cantVotos) {
        this.cantVotos = cantVotos;
    }

    public float getSumaActual() {
        return sumaActual;
    }

    public void setSumaActual(float sumaActual) {
        this.sumaActual = sumaActual;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "frase='" + frase + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
