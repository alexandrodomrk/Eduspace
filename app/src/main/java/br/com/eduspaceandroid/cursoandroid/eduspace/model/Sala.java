package br.com.eduspaceandroid.cursoandroid.eduspace.model;

import com.google.firebase.database.DatabaseReference;

import br.com.eduspaceandroid.cursoandroid.eduspace.config.ConfiguracaoFirebase;

/**
 * Created by Alex on 29/07/2017.
 */
     public class Sala {
    private String id;
    private int numero;
    private int capacidade;

    public Sala() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Sala"+numero;

    }
}

