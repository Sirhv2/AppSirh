package co.gov.ideam.sirh.red.ideam.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.util.Calendar;


public class CriteriosBusquedaRedIdeamTO implements Serializable{
    
   
    private PartZonificListas area;
    private PartZonificListas zona;
    private PartZonificListas subZona;
    private Divipola departamento;
    private Divipola municipio;
    private FnttFuente fuente;
    private Calendar fechaInicial;
    private Calendar fechaFinal;
 
    
    public CriteriosBusquedaRedIdeamTO() {
    }
    
    public PartZonificListas getArea() {
        return area;
    }

    public void setArea(PartZonificListas area) {
        this.area = area;
    }

    public PartZonificListas getZona() {
        return zona;
    }

    public void setZona(PartZonificListas zona) {
        this.zona = zona;
    }

    public PartZonificListas getSubZona() {
        return subZona;
    }

    public void setSubZona(PartZonificListas subzona) {
        this.subZona = subzona;
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
    }

    public Divipola getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Divipola municipio) {
        this.municipio = municipio;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }
    
    public Calendar getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Calendar fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Calendar getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Calendar fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

}
