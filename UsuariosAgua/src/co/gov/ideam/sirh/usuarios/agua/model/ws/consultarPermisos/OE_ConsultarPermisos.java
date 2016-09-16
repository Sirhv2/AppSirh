package co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos;


import java.io.Serializable;


public class OE_ConsultarPermisos implements Serializable  {
     
    private String codAutoridadAmbiental;
    private String filtroConsulta;
    private String fechaInicio;
    private String fechaFin;

    public OE_ConsultarPermisos() {
    }
    public void setCodAutoridadAmbiental(String codAutoridadAmbiental) {
        this.codAutoridadAmbiental = codAutoridadAmbiental;
    }

    public String getCodAutoridadAmbiental() {
        return codAutoridadAmbiental;
    }

    public void setFiltroConsulta(String filtroConsulta) {
        this.filtroConsulta = filtroConsulta;
    }

    public String getFiltroConsulta() {
        return filtroConsulta;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }
}
