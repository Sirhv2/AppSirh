package co.gov.ideam.sirh.calidad.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.util.Calendar;


public class CriteriosBusquedaPuntos implements Serializable{
  
  private Lista listatipoPunto;
  private String nombre;
  private PartZonificListas area;
  private PartZonificListas zona;
  private PartZonificListas subzona;
  private Lista tipoMuestra;
  private String fechaMuestra;
  private PuntoMonitoreo listapuntos;
  private String nroMuestra;
  private FnttFuente listaFuente;
  
  
  public CriteriosBusquedaPuntos() {
    super();
  }



  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }



  public void setListatipoPunto(Lista listatipoPunto) {
    this.listatipoPunto = listatipoPunto;
  }

  public Lista getListatipoPunto() {
    return listatipoPunto;
  }


  public void setSubzona(PartZonificListas subzona) {
    this.subzona = subzona;
  }

  public PartZonificListas getSubzona() {
    return subzona;
  }





    public void setListapuntos(PuntoMonitoreo listapuntos) {
        this.listapuntos = listapuntos;
    }

    public PuntoMonitoreo getListapuntos() {
        return listapuntos;
    }

    public void setTipoMuestra(Lista tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public Lista getTipoMuestra() {
        return tipoMuestra;
    }

    public void setNroMuestra(String nroMuestra) {
        this.nroMuestra = nroMuestra;
    }

    public String getNroMuestra() {
        return nroMuestra;
    }

    public void setArea(PartZonificListas area) {
        this.area = area;
    }

    public PartZonificListas getArea() {
        return area;
    }

    public void setZona(PartZonificListas zona) {
        this.zona = zona;
    }

    public PartZonificListas getZona() {
        return zona;
    }


    public void setListaFuente(FnttFuente listaFuente) {
        this.listaFuente = listaFuente;
    }

    public FnttFuente getListaFuente() {
        return listaFuente;
    }


    public void setFechaMuestra(String fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public String getFechaMuestra() {
        return fechaMuestra;
    }
}
