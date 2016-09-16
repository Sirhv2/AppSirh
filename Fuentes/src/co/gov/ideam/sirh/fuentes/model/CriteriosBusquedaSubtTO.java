package co.gov.ideam.sirh.fuentes.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

public class CriteriosBusquedaSubtTO implements Serializable {
  
  private Lista tipoFuente;
  private Lista provinciahidro;
  private String unidadhidro;
  private Divipola departamento;
  private Divipola municipio;
  private FnttFuente fuente;
  private Autoridades autoridad;
  private String nombreFuente;

  public CriteriosBusquedaSubtTO() {
    super();
  }

  public void setDepartamento(Divipola departamento) {
    this.departamento = departamento;
  }

  public Divipola getDepartamento() {
    return departamento;
  }

  public void setMunicipio(Divipola municipio) {
    this.municipio = municipio;
  }

  public Divipola getMunicipio() {
    return municipio;
  }

  public void setFuente(FnttFuente fuente) {
    this.fuente = fuente;
  }

  public FnttFuente getFuente() {
    return fuente;
  }

  public void setAutoridad(Autoridades autoridad) {
    this.autoridad = autoridad;
  }

  public Autoridades getAutoridad() {
    return autoridad;
  }

  public void setNombreFuente(String nombreFuente) {
    this.nombreFuente = nombreFuente;
  }

  public String getNombreFuente() {
    return nombreFuente;
  }

  public void setTipoFuente(Lista tipoFuente) {
    this.tipoFuente = tipoFuente;
  }

  public Lista getTipoFuente() {
    return tipoFuente;
  }

  public void setProvinciahidro(Lista provinciahidro) {
    this.provinciahidro = provinciahidro;
  }

  public Lista getProvinciahidro() {
    return provinciahidro;
  }

  public void setUnidadhidro(String unidadhidro) {
    this.unidadhidro = unidadhidro;
  }

  public String getUnidadhidro() {
    return unidadhidro;
  }
}
