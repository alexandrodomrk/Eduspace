package br.com.eduspaceandroid.cursoandroid.eduspace.model;

/**
 * Created by Alex on 04/08/2017.
 */
public class Curso {
    private String id;
    private String nome;

    public Curso() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}