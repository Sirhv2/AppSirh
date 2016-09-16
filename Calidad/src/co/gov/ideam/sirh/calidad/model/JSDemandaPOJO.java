package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


public class JSDemandaPOJO implements Serializable{
    @Column(name="captaciones") 
    private Double captaciones;
    @Column(name="acuifero") 
    private String acuifero;
    @Column(name="sigla")     
    private String sigla;
    @Column(name="valor")     
    private String valor;
  
    

    public JSDemandaPOJO() {
    }

  public void setCaptaciones(Double captaciones) {
    this.captaciones = captaciones;
  }

  public double getCaptaciones() {
    return captaciones;
  }

  public void setAcuifero(String acuifero) {
    this.acuifero = acuifero;
  }

  public String getAcuifero() {
    return acuifero;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public String getSigla() {
    return sigla;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }
}

