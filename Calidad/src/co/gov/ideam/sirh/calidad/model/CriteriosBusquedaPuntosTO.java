package co.gov.ideam.sirh.calidad.model;

  
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

public class CriteriosBusquedaPuntosTO implements Serializable {
  
  private PartZonificListas area;
  private PartZonificListas zona;
  private PartZonificListas subzona;
  private FnttFuente listaFuente;
  
  
    public CriteriosBusquedaPuntosTO() {
        super();
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

    public PartZonificListas getSubzona() {
        return subzona;
    }

    public void setSubzona(PartZonificListas subzona) {
        this.subzona = subzona;
    }

    public FnttFuente getListaFuente() {
        return listaFuente;
    }

    public void setListaFuente(FnttFuente listaFuente) {
        this.listaFuente = listaFuente;
    }
}
