package co.gov.ideam.sirh.calidad.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatosGraficoCalidad  implements Serializable{
 
   
    @Id
    @Column(name="num") 
    private Long num;
    @Column(name="fecha")     
    private String fecha;
    @Column(name="cantidad")     
    private Double cantidad;
    @Column(name="unidad") 
    private String unidad;
    @Column(name="valor") 
    private String valor;
    @Column(name="descripcion") 
    private String descripcion;
    
    
   
  public DatosGraficoCalidad() {
    }


    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

  


    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
