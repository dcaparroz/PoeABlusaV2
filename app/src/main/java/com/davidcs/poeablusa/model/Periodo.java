package com.davidcs.poeablusa.model;



/**
 * Created by logonrm on 09/03/2017.
 */

public class Periodo {

    private int id;

    private String periodo;

    public Periodo(String periodo, int id) {
        this.periodo = periodo;
        this.id = id;
    }

    @Override
    public String toString() {return periodo;}

    public Periodo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
