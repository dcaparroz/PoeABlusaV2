package com.davidcs.poeablusa.model;

/**
 * Created by logonrm on 07/03/2017.
 */

public class Temperatura {

    private int id;

    private int frio;

    private int calor;

    private int chuva;

    private Periodo periodo;


    public Temperatura(int id, int frio, int calor, int chuva) {
        this.id = id;
        this.frio = frio;
        this.calor = calor;
        this.chuva = chuva;

    }

    public Temperatura() {
    }

    public int getChuva() {
        return chuva;
    }

    public void setChuva(int chuva) {
        this.chuva = chuva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrio() {
        return frio;
    }

    public void setFrio(int frio) {
        this.frio = frio;
    }

    public int getCalor() {
        return calor;
    }

    public void setCalor(int calor) {
        this.calor = calor;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
