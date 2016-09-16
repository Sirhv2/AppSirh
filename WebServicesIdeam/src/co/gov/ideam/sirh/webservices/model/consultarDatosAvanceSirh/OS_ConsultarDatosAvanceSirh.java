package co.gov.ideam.sirh.webservices.model.consultarDatosAvanceSirh;


import co.gov.ideam.sirh.datosena.model.DatosAvanceSIRH;
import java.util.List;

public class OS_ConsultarDatosAvanceSirh{
    public OS_ConsultarDatosAvanceSirh() {
    }
    
    private String codigoError;
    private String mensajeError;
    private List<DatosAvanceSIRH> DatosAvanceSirh;


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


    public void setDatosAvanceSirh(List<DatosAvanceSIRH> DatosAvanceSirh) {
        this.DatosAvanceSirh = DatosAvanceSirh;
    }

    public List<DatosAvanceSIRH> getDatosAvanceSirh() {
        return DatosAvanceSirh;
    }
}
