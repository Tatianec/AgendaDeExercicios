package com.example.agenda_exercicio.model;

public class ExercicioRepeticao {
    private String nome;
    private int repeticoes;
    private double peso;
    private int descanso;
    private String dia;

    public ExercicioRepeticao() {
    }

    public ExercicioRepeticao(String nome, int repeticoes, double peso, int descanso, String dia) {
        this.nome = nome;
        this.repeticoes = repeticoes;
        this.peso = peso;
        this.descanso = descanso;
        this.dia = dia;
    }

    public String getNome() {
        return nome;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public double getPeso() {
        return peso;
    }

    public int getDescanso() {
        return descanso;
    }

    public String getDia() {
        return dia;
    }

}

