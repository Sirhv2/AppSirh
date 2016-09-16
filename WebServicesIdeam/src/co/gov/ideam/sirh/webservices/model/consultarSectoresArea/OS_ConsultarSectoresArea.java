package co.gov.ideam.sirh.webservices.model.consultarSectoresArea;


import co.gov.ideam.sirh.datosena.model.DatosSectoresArea;

import java.util.List;

public class OS_ConsultarSectoresArea {
    public OS_ConsultarSectoresArea() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosSectoresArea> sectoresArea;


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


    public void setSectoresArea(List<DatosSectoresArea> sectoresArea) {
        this.sectoresArea = sectoresArea;
    }

    public List<DatosSectoresArea> getSectoresArea() {
        return sectoresArea;
    }
}
