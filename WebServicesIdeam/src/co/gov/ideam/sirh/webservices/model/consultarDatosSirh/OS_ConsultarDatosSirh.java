package co.gov.ideam.sirh.webservices.model.consultarDatosSirh;


import co.gov.ideam.sirh.datosena.model.DatosSIRH;

import java.util.List;

public class OS_ConsultarDatosSirh{
    public OS_ConsultarDatosSirh() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosSIRH> datosSIRH;


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


    public void setDatosSIRH(List<DatosSIRH> datosSIRH) {
        this.datosSIRH = datosSIRH;
    }

    public List<DatosSIRH> getDatosSIRH() {
        return datosSIRH;
    }
}
