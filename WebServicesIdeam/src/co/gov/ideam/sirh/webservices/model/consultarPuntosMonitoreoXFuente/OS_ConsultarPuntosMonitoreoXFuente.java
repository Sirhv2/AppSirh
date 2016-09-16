package co.gov.ideam.sirh.webservices.model.consultarPuntosMonitoreoXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;

import java.util.List;

public class OS_ConsultarPuntosMonitoreoXFuente {
    public OS_ConsultarPuntosMonitoreoXFuente() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<DatosReporteWS> datosPuntosMonitoreoFuente;
    
    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }


    public void setDatosPuntosMonitoreoFuente(List<DatosReporteWS> datosPuntosMonitoreoFuente) {
        this.datosPuntosMonitoreoFuente = datosPuntosMonitoreoFuente;
    }

    public List<DatosReporteWS> getDatosPuntosMonitoreoFuente() {
        return datosPuntosMonitoreoFuente;
    }
}
