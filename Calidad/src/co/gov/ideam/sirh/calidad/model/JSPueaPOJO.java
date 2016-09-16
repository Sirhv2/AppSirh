package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

public class JSPueaPOJO implements Serializable {
  @Column(name = "descripcion")
  private String descripcion;
  @Column(name = "Cantidad")
  private Double cantidad;

  @Transient
  private Integer num;


  public JSPueaPOJO() {
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Double getCantidad() {
    return cantidad;
  }

  public void setCantidad(Double cantidad) {
    this.cantidad = cantidad;
  }
}
