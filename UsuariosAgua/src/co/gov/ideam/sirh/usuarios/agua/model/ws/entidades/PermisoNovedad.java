package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;

import java.io.Serializable;

public class PermisoNovedad implements Serializable {
  
    private Integer codigoTipoIdentificacion;
    private String  identificacionPersona;
    private String  cedulaCatastral;
    private String  tipoPermiso;
    private String  numExpedienteAct;
    private String  numResolucionAct;
    private String  numResolucionNuevo;
    private String  fechaResolucionNuevo;
    private String  fechaExpedicionNuevo;
    private String  fechaVencimientoNuevo;
    private Double  cantidadRecursoOtorgado;
    private String  observacionesNovedad;
    private String  tipoNovedad;


    public static final String MODIFICACION = "MOD";
    public static final String RENOVACION = "REN";
    public static final String REVOCACION = "REV";


    public void setCodigoTipoIdentificacion(Integer codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public Integer getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setIdentificacionPersona(String identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getIdentificacionPersona() {
        return identificacionPersona;
    }

    public void setCedulaCatastral(String cedulaCatastral) {
        this.cedulaCatastral = cedulaCatastral;
    }

    public String getCedulaCatastral() {
        return cedulaCatastral;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setNumExpedienteAct(String numExpedienteAct) {
        this.numExpedienteAct = numExpedienteAct;
    }

    public String getNumExpedienteAct() {
        return numExpedienteAct;
    }

    public void setNumResolucionAct(String numResolucionAct) {
        this.numResolucionAct = numResolucionAct;
    }

    public String getNumResolucionAct() {
        return numResolucionAct;
    }

    public void setNumResolucionNuevo(String numResolucionNuevo) {
        this.numResolucionNuevo = numResolucionNuevo;
    }

    public String getNumResolucionNuevo() {
        return numResolucionNuevo;
    }

    public void setFechaResolucionNuevo(String fechaResolucionNuevo) {
        this.fechaResolucionNuevo = fechaResolucionNuevo;
    }

    public String getFechaResolucionNuevo() {
        return fechaResolucionNuevo;
    }

    public void setFechaExpedicionNuevo(String fechaExpedicionNuevo) {
        this.fechaExpedicionNuevo = fechaExpedicionNuevo;
    }

    public String getFechaExpedicionNuevo() {
        return fechaExpedicionNuevo;
    }

    public void setFechaVencimientoNuevo(String fechaVencimientoNuevo) {
        this.fechaVencimientoNuevo = fechaVencimientoNuevo;
    }

    public String getFechaVencimientoNuevo() {
        return fechaVencimientoNuevo;
    }

    public void setCantidadRecursoOtorgado(Double cantidadRecursoOtorgado) {
        this.cantidadRecursoOtorgado = cantidadRecursoOtorgado;
    }

    public Double getCantidadRecursoOtorgado() {
        return cantidadRecursoOtorgado;
    }

    public void setObservacionesNovedad(String observacionesNovedad) {
        this.observacionesNovedad = observacionesNovedad;
    }

    public String getObservacionesNovedad() {
        return observacionesNovedad;
    }

    public void setTipoNovedad(String tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public String getTipoNovedad() {
        return tipoNovedad;
    }
}
