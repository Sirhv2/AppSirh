package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;


import java.io.Serializable;



public class PermisoWS implements Serializable {

   
    private String numExpediente;
    private String numResolucion;
    private String fechaExpedicion;
    private String fechaVencimiento;
    private Double cantidadRecursoOtorgado;
    private String tipoPermiso;

    public PermisoWS() {
                 
        }
 
   

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumResolucion(String numResolucion) {
        this.numResolucion = numResolucion;
    }

    public String getNumResolucion() {
        return numResolucion;
    }



    public void setCantidadRecursoOtorgado(Double cantidadRecursoOtorgado) {
        this.cantidadRecursoOtorgado = cantidadRecursoOtorgado;
    }

    public Double getCantidadRecursoOtorgado() {
        return cantidadRecursoOtorgado;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
}
