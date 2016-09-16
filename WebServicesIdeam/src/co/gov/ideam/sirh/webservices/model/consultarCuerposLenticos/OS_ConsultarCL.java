package co.gov.ideam.sirh.webservices.model.consultarCuerposLenticos;

import co.gov.ideam.sirh.datosena.model.DatosCuerposLenticosTO;
import java.util.List;

public class OS_ConsultarCL {
    public OS_ConsultarCL() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosCuerposLenticosTO> listaCuerposLenticos;


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


    public void setListaCuerposLenticos(List<DatosCuerposLenticosTO> listaCuerposLenticos) {
        this.listaCuerposLenticos = listaCuerposLenticos;
    }

    public List<DatosCuerposLenticosTO> getListaCuerposLenticos() {
        return listaCuerposLenticos;
    }
}
