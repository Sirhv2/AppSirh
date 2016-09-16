package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

public class JSMonitoreoPiezometricoPOJO implements Serializable {
  @Column(name = "tipoCaptacion")
  private String tipoCaptacion;
  @Column(name = "tipoPunto")
  private String tipoPunto;
  @Column(name = "tipoFuente")
  private String tipoFuente;
  @Column(name = "departamento")
  private String departamento;
  @Column(name = "identificadorPunto")
  private String identificadorPunto;
  @Column(name = "acuifero")
  private String acuifero;
  @Column(name = "nivelPiezometrico")
  private Double nivelPiezometrico;
  @Column(name = "nivel")
  private String nivel;
  @Column(name = "fecha")
  private Date fecha;

  public JSMonitoreoPiezometricoPOJO() {
  }


  public void setTipoCaptacion(String tipoCaptacion) {
    this.tipoCaptacion = tipoCaptacion;
  }

  public String getTipoCaptacion() {
    return tipoCaptacion;
  }

  public void setTipoPunto(String tipoPunto) {
    this.tipoPunto = tipoPunto;
  }

  public String getTipoPunto() {
    return tipoPunto;
  }

  public void setTipoFuente(String tipoFuente) {
    this.tipoFuente = tipoFuente;
  }

  public String getTipoFuente() {
    return tipoFuente;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setIdentificadorPunto(String identificadorPunto) {
    this.identificadorPunto = identificadorPunto;
  }

  public String getIdentificadorPunto() {
    return identificadorPunto;
  }

  public void setAcuifero(String acuifero) {
    this.acuifero = acuifero;
  }

  public String getAcuifero() {
    return acuifero;
  }

  

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public String getNivel() {
    return nivel;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setNivelPiezometrico(Double nivelPiezometrico) {
    this.nivelPiezometrico = nivelPiezometrico;
  }

  public Double getNivelPiezometrico() {
    return nivelPiezometrico;
  }
}
