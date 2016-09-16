package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


public class JSCalidadParametrosPOJO implements Serializable{
   
  @Column(name="IDCAPTACION") 
  private int idCaptacion;
  @Column(name="TIPOCAPTACION")
  private String tipoCaptacion;
  @Column(name="DEPARTAMENTO")
  private String departamento;
  @Column(name="MUNICIPIO")
  private String municipio;
  @Column(name="IDENTIFICADOR_PUNTO")
  private String idPunto;
  @Column(name="TIPOPUNTO")
  private String tipoPunto;
  @Column(name="TIPOFUENTE")
  private String tipoFuente;
  @Column(name="ACUIFERO")
  private String acuifero;
  @Column(name="PUNTOMONITOREO")
  private String puntoMonitoreo;
  @Column(name="ID_MUESTRA")
  private int idMuestra;
  @Column(name="TIPOMUESTRA")
  private String tipoMuestra;
  @Column(name="MEDICION")
  private int medicion;
  @Column(name="PARAMETRO_CALIDAD")
  private String parametroCalidad;
  @Column(name="VALOR")
  private Double valor;
  @Column(name="Prov_Hidro")
  private String provHidro;
  @Column(name="red")
  private String red;
  @Column(name="red2")
  private String red2;

    public JSCalidadParametrosPOJO() {
    }


  public void setIdCaptacion(int idCaptacion) {
    this.idCaptacion = idCaptacion;
  }

  public int getIdCaptacion() {
    return idCaptacion;
  }

  public void setTipoCaptacion(String tipoCaptacion) {
    this.tipoCaptacion = tipoCaptacion;
  }

  public String getTipoCaptacion() {
    return tipoCaptacion;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setIdPunto(String idPunto) {
    this.idPunto = idPunto;
  }

  public String getIdPunto() {
    return idPunto;
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

  public void setAcuifero(String acuifero) {
    this.acuifero = acuifero;
  }

  public String getAcuifero() {
    return acuifero;
  }

  public void setPuntoMonitoreo(String puntoMonitoreo) {
    this.puntoMonitoreo = puntoMonitoreo;
  }

  public String getPuntoMonitoreo() {
    return puntoMonitoreo;
  }

  public void setIdMuestra(int idMuestra) {
    this.idMuestra = idMuestra;
  }

  public int getIdMuestra() {
    return idMuestra;
  }

  public void setTipoMuestra(String tipoMuestra) {
    this.tipoMuestra = tipoMuestra;
  }

  public String getTipoMuestra() {
    return tipoMuestra;
  }

  public void setMedicion(int medicion) {
    this.medicion = medicion;
  }

  public int getMedicion() {
    return medicion;
  }

  public void setParametroCalidad(String parametroCalidad) {
    this.parametroCalidad = parametroCalidad;
  }

  public String getParametroCalidad() {
    return parametroCalidad;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Double getValor() {
    return valor;
  }


  public void setProvHidro(String provHidro) {
    this.provHidro = provHidro;
  }

  public String getProvHidro() {
    return provHidro;
  }


  public void setRed(String red) {
    this.red = red;
  }

  public String getRed() {
    return red;
  }


  public void setRed2(String red2) {
    this.red2 = red2;
  }

  public String getRed2() {
    return red2;
  }
}

