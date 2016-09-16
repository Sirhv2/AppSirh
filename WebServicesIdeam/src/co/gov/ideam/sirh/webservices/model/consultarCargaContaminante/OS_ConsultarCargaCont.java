package co.gov.ideam.sirh.webservices.model.consultarCargaContaminante;

import co.gov.ideam.sirh.datosena.model.DatosCargaContaminante;
import java.util.List;

public class OS_ConsultarCargaCont {
    public OS_ConsultarCargaCont() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosCargaContaminante> datosCarga;


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


    public void setDatosCarga(List<DatosCargaContaminante> datosCarga) {
        this.datosCarga = datosCarga;
    }

    public List<DatosCargaContaminante> getDatosCarga() {
        return datosCarga;
    }
}
