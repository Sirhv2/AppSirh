package co.gov.ideam.sirh.webservices.model.consultarDatosSirhAutoridad;


import co.gov.ideam.sirh.datosena.model.DatosSIRHAutoridad;

import java.util.List;

public class OS_ConsultarDatosSirhAutoridad{
    public OS_ConsultarDatosSirhAutoridad() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosSIRHAutoridad> datosSIRHAutoridad;


    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }


    public void setDatosSIRHAutoridad(List<DatosSIRHAutoridad> datosSIRHAutoridad) {
        this.datosSIRHAutoridad = datosSIRHAutoridad;
    }

    public List<DatosSIRHAutoridad> getDatosSIRHAutoridad() {
        return datosSIRHAutoridad;
    }
}
