package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;

import java.io.Serializable;

public class PermisoTraspaso implements Serializable {
  
    private Integer codigoTipoIdentificacion;
    private String  identificacionPersona;
    private String  cedulaCatastral;
    private String  tipoPermiso;
    private String  numExpedienteAct;
    private String  numResolucionAct;
    private String  observacionesNovedad;
   

    private UsuarioAguaWS  UsuarioTraspaso;
    private RepresentanteWS  RepresentanteTraspaso;
    private PredioWS  PredioTraspaso;
    private PermisoWS  PermisoTraspasoNuevo;
    
    public static final String TRASPASO = "TRP";


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

    public void setUsuarioTraspaso(UsuarioAguaWS UsuarioTraspaso) {
        this.UsuarioTraspaso = UsuarioTraspaso;
    }

    public UsuarioAguaWS getUsuarioTraspaso() {
        return UsuarioTraspaso;
    }

    public void setRepresentanteTraspaso(RepresentanteWS RepresentanteTraspaso) {
        this.RepresentanteTraspaso = RepresentanteTraspaso;
    }

    public RepresentanteWS getRepresentanteTraspaso() {
        return RepresentanteTraspaso;
    }

    public void setPredioTraspaso(PredioWS PredioTraspaso) {
        this.PredioTraspaso = PredioTraspaso;
    }

    public PredioWS getPredioTraspaso() {
        return PredioTraspaso;
    }


    public void setPermisoTraspasoNuevo(PermisoWS PermisoTraspasoNuevo) {
        this.PermisoTraspasoNuevo = PermisoTraspasoNuevo;
    }

    public PermisoWS getPermisoTraspasoNuevo() {
        return PermisoTraspasoNuevo;
    }

    public void setObservacionesNovedad(String observacionesNovedad) {
        this.observacionesNovedad = observacionesNovedad;
    }

    public String getObservacionesNovedad() {
        return observacionesNovedad;
    }
}
