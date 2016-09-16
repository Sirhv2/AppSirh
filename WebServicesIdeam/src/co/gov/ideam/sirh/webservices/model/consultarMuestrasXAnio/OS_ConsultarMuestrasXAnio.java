package co.gov.ideam.sirh.webservices.model.consultarMuestrasXAnio;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;

import java.util.List;

public class OS_ConsultarMuestrasXAnio {
    public OS_ConsultarMuestrasXAnio() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<DatosReporteWS> datosMuestrasAnio;
    
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

    public void setDatosMuestrasAnio(List<DatosReporteWS> datosMuestrasAnio) {
        this.datosMuestrasAnio = datosMuestrasAnio;
    }

    public List<DatosReporteWS> getDatosMuestrasAnio() {
        return datosMuestrasAnio;
    }
}
