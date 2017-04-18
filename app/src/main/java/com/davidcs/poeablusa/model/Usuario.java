package com.davidcs.poeablusa.model;

/**
 * Created by logonrm on 09/03/2017.
 */

public class Usuario {

    private Integer id;
    private String nome;
    private String frio;
    private String calor;
    private String chuva;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}


