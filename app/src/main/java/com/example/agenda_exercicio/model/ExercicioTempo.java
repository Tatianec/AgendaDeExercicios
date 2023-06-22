package com.example.agenda_exercicio.model;

import com.google.firebase.database.PropertyName;

public class ExercicioTempo {

    public Double distancia;

    public Long tempo;

    public String data;

    public ExercicioTempo() {
    }

    public ExercicioTempo(Double distancia, Long tempo, String data) {
        this.distancia = distancia;
        this.tempo = tempo;
        this.data = data;
    }

    public ExercicioTempo(Double distancia, Long tempo) {
        this.distancia = distancia;
        this.tempo = tempo;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
