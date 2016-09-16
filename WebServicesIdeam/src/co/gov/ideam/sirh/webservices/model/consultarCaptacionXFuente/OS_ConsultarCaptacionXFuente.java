package co.gov.ideam.sirh.webservices.model.consultarCaptacionXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;

import java.util.List;

public class OS_ConsultarCaptacionXFuente {
    public OS_ConsultarCaptacionXFuente() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<DatosReporteWS> datosCaptacionFuente;
    
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


    public void setDatosCaptacionFuente(List<DatosReporteWS> datosCaptacionFuente) {
        this.datosCaptacionFuente = datosCaptacionFuente;
    }    

    public List<DatosReporteWS> getDatosCaptacionFuente() {
        return datosCaptacionFuente;
    }
}
