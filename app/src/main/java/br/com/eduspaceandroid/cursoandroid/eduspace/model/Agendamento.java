package br.com.eduspaceandroid.cursoandroid.eduspace.model;

import java.util.Date;
import java.util.Timer;

/**
 * Created by Alex on 11/08/2017.
 */
public class Agendamento {
    private String id;
    private Sala sala;
    private Curso curso;
    private Date dataInical;
    private Date dataFinal;
    private Timer horaInicial;
    private Timer horaFinal;
    private String turno;


    public Agendamento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDataInical() {
        return dataInical;
    }

    public void setDataInical(Date dataInical) {
        this.dataInical = dataInical;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Timer getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Timer horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Timer getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Timer horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "sala=" + sala +
                '}';
    }
}
