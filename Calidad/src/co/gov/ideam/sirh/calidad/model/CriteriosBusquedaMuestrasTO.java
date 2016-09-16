package co.gov.ideam.sirh.calidad.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.util.Calendar;

public class CriteriosBusquedaMuestrasTO implements Serializable{
    private Autoridades autoridad;
    private PartZonificListas area;
    private PartZonificListas zona;
    private PartZonificListas subZona;
    private Divipola departamento;
    private Divipola municipio;
    private FnttFuente fuente;
    private PuntoMonitoreo ptoMonitoreo;
    private Lista parametro;
    private Calendar fechaInicial;
    private Calendar fechaFinal;
    private Integer anio;
   
    
    public CriteriosBusquedaMuestrasTO() {
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
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

    public PuntoMonitoreo getPtoMonitoreo() {
        return ptoMonitoreo;
    }

    public void setPtoMonitoreo(PuntoMonitoreo ptoMonitoreo) {
        this.ptoMonitoreo = ptoMonitoreo;
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

    public Lista getParametro() {
        return parametro;
    }

    public void setParametro(Lista parametro) {
        this.parametro = parametro;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
