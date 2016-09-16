package co.gov.ideam.sirh.webservices.model.consultarDatosSirhDep;


import co.gov.ideam.sirh.datosena.model.DatosSIRHDep;

import java.util.List;

public class OS_ConsultarDatosSirhDep{
    public OS_ConsultarDatosSirhDep() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosSIRHDep> datosSIRHDep;


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


    public void setDatosSIRHDep(List<DatosSIRHDep> datosSIRHDep) {
        this.datosSIRHDep = datosSIRHDep;
    }

    public List<DatosSIRHDep> getDatosSIRHDep() {
        return datosSIRHDep;
    }
}
