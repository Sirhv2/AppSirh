package co.gov.ideam.sirh.webservices.model.consultarAreaPomcas;


import co.gov.ideam.sirh.datosena.model.DatosAreaPomcas;

import java.util.List;

public class OS_ConsultarAreaPomcas{
    public OS_ConsultarAreaPomcas() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosAreaPomcas> datosAreaPomcas;


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


    public void setDatosAreaPomcas(List<DatosAreaPomcas> datosAreaPomcas) {
        this.datosAreaPomcas = datosAreaPomcas;
    }

    public List<DatosAreaPomcas> getDatosAreaPomcas() {
        return datosAreaPomcas;
    }
}
