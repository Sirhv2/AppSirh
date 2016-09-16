package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;

public class PomtJurisdiccionesTO implements Serializable {
    private PomtJurisdiccion jurisdiccion;
    private String departamento;
    private String municipio;


    public PomtJurisdiccionesTO() {
    }

    public PomtJurisdiccion getJurisdiccion() {
        return jurisdiccion;
    }

    public void setJurisdiccion(PomtJurisdiccion jurisdiccion) {
        this.jurisdiccion = jurisdiccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public boolean equals(Object obj) {
        return obj != null && obj instanceof PomtJurisdiccionesTO &&
            this.getJurisdiccion() != null &&
            ((PomtJurisdiccionesTO)obj).getJurisdiccion().equals(this.jurisdiccion);
    }
}

