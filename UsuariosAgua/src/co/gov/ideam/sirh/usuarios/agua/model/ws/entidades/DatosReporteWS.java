package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;
import java.io.Serializable;

public class DatosReporteWS implements Serializable{
 
    private String descripcion;
    private Long cantidad;


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCantidad() {
        return cantidad;
    }
}
