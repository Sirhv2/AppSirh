package co.gov.ideam.sirh.webservices.model.consultarFuentesExternas;


import co.gov.ideam.sirh.usuarios.agua.model.ConsultasExtFuentesTO;

import java.util.List;

public class OS_ConsultarFuentesExt {
    public OS_ConsultarFuentesExt() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<ConsultasExtFuentesTO> fuentesExternas;


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


    public void setFuentesExternas(List<ConsultasExtFuentesTO> fuentesExternas) {
        this.fuentesExternas = fuentesExternas;
    }

    public List<ConsultasExtFuentesTO> getFuentesExternas() {
        return fuentesExternas;
    }
}
