package co.gov.ideam.sirh.webservices.model.consultarOferta;

import co.gov.ideam.sirh.datosena.model.DatosOfertaAreaTO;

import java.util.List;

public class OS_ConsultarOferta {
    public OS_ConsultarOferta() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosOfertaAreaTO> ofertaArea;


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


    public void setOfertaArea(List<DatosOfertaAreaTO> ofertaArea) {
        this.ofertaArea = ofertaArea;
    }

    public List<DatosOfertaAreaTO> getOfertaArea() {
        return ofertaArea;
    }
}
