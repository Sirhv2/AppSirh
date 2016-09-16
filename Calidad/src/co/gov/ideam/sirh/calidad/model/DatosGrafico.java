package co.gov.ideam.sirh.calidad.model;


import java.io.Serializable;


public class DatosGrafico implements Serializable{
    
  
    private String descripcion;
    private Double cantidad;
    private String fecha;
 
    
    public DatosGrafico() {
        super();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCantidad() {
        return cantidad;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

   
}
