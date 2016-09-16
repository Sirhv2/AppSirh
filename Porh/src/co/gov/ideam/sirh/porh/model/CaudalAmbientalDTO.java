package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

public class CaudalAmbientalDTO implements Serializable {

  private String nombre;
  private Double caudalAmbientalEnero;
  private Double caudalAmbientalFebrero;
  private Double caudalAmbientalMarzo;
  private Double caudalAmbientalAbril;
  private Double caudalAmbientalMayo;
  private Double caudalAmbientalJunio;
  private Double caudalAmbientalJulio;
  private Double caudalAmbientalAgosto;
  private Double caudalAmbientalSeptiembre;
  private Double caudalAmbientalOctubre;
  private Double caudalAmbientalNoviembre;
  private Double caudalAmbientalDiciembre;
  private Double promedio; 
  

  public CaudalAmbientalDTO() {
    super();
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setPromedio(Double promedio) {
    this.promedio = promedio;
  }

  public Double getPromedio() {
    Double suma = getValor (this.getCaudalAmbientalEnero()) +
        getValor( this.getCaudalAmbientalFebrero() ) + 
        getValor( this.getCaudalAmbientalMarzo() ) + 
        getValor( this.getCaudalAmbientalAbril() ) + 
        getValor( this.getCaudalAmbientalMayo() ) +  
        getValor( this.getCaudalAmbientalJunio() ) + 
        getValor( this.getCaudalAmbientalJulio() ) + 
        getValor( this.getCaudalAmbientalAgosto() ) + 
        getValor( this.getCaudalAmbientalSeptiembre() ) + 
        getValor( this.getCaudalAmbientalOctubre() ) + 
        getValor( this.getCaudalAmbientalNoviembre() ) + 
        getValor( this.getCaudalAmbientalDiciembre() );
    return suma / 12;
  }
  
  private Double getValor(Double mes){
      if (mes ==null){
          return 0D;
      }else{
          return mes;
      }            
  }

  public void setCaudalAmbientalEnero(Double caudalAmbientalEnero) {
    this.caudalAmbientalEnero = caudalAmbientalEnero;
  }

  public Double getCaudalAmbientalEnero() {
    return caudalAmbientalEnero;
  }

  public void setCaudalAmbientalFebrero(Double caudalAmbientalFebrero) {
    this.caudalAmbientalFebrero = caudalAmbientalFebrero;
  }

  public Double getCaudalAmbientalFebrero() {
    return caudalAmbientalFebrero;
  }

  public void setCaudalAmbientalMarzo(Double caudalAmbientalMarzo) {
    this.caudalAmbientalMarzo = caudalAmbientalMarzo;
  }

  public Double getCaudalAmbientalMarzo() {
    return caudalAmbientalMarzo;
  }

  public void setCaudalAmbientalAbril(Double caudalAmbientalAbril) {
    this.caudalAmbientalAbril = caudalAmbientalAbril;
  }

  public Double getCaudalAmbientalAbril() {
    return caudalAmbientalAbril;
  }

  public void setCaudalAmbientalMayo(Double caudalAmbientalMayo) {
    this.caudalAmbientalMayo = caudalAmbientalMayo;
  }

  public Double getCaudalAmbientalMayo() {
    return caudalAmbientalMayo;
  }

  public void setCaudalAmbientalJunio(Double caudalAmbientalJunio) {
    this.caudalAmbientalJunio = caudalAmbientalJunio;
  }

  public Double getCaudalAmbientalJunio() {
    return caudalAmbientalJunio;
  }

  public void setCaudalAmbientalJulio(Double caudalAmbientalJulio) {
    this.caudalAmbientalJulio = caudalAmbientalJulio;
  }

  public Double getCaudalAmbientalJulio() {
    return caudalAmbientalJulio;
  }

  public void setCaudalAmbientalAgosto(Double caudalAmbientalAgosto) {
    this.caudalAmbientalAgosto = caudalAmbientalAgosto;
  }

  public Double getCaudalAmbientalAgosto() {
    return caudalAmbientalAgosto;
  }

  public void setCaudalAmbientalSeptiembre(Double caudalAmbientalSeptiembre) {
    this.caudalAmbientalSeptiembre = caudalAmbientalSeptiembre;
  }

  public Double getCaudalAmbientalSeptiembre() {
    return caudalAmbientalSeptiembre;
  }

  public void setCaudalAmbientalOctubre(Double caudalAmbientalOctubre) {
    this.caudalAmbientalOctubre = caudalAmbientalOctubre;
  }

  public Double getCaudalAmbientalOctubre() {
    return caudalAmbientalOctubre;
  }

  public void setCaudalAmbientalNoviembre(Double caudalAmbientalNoviembre) {
    this.caudalAmbientalNoviembre = caudalAmbientalNoviembre;
  }

  public Double getCaudalAmbientalNoviembre() {
    return caudalAmbientalNoviembre;
  }

  public void setCaudalAmbientalDiciembre(Double caudalAmbientalDiciembre) {
    this.caudalAmbientalDiciembre = caudalAmbientalDiciembre;
  }

  public Double getCaudalAmbientalDiciembre() {
    return caudalAmbientalDiciembre;
  }

  public Double getPromedio1() {
    return promedio;
  }
}
