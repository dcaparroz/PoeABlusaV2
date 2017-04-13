package com.davidcs.poeablusa.model;

/**
 * Created by logonrm on 09/03/2017.
 */

public class Usuario {

    private int id;

    private String nome;


    private String frio;
    private String calor;
    private String chuva;
   // private String periodo;

    public String getFrio() {
        return frio;
    }

    public void setFrio(String frio) {
        this.frio = frio;
    }

    public String getCalor() {
        return calor;
    }

    public void setCalor(String calor) {
        this.calor = calor;
    }

    public String getChuva() {
        return chuva;
    }

    public void setChuva(String chuva) {
        this.chuva = chuva;
    }

//    public String getPeriodo() {
//        return periodo;
//    }

//    public void setPeriodo(String periodo) {
//        this.periodo = periodo;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /* public Temperatura temperatura;

    public Periodo periodo;

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }*/
}


