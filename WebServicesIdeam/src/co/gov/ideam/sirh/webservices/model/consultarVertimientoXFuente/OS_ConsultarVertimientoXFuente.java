package co.gov.ideam.sirh.webservices.model.consultarVertimientoXFuente;


import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import java.util.List;

public class OS_ConsultarVertimientoXFuente {
    public OS_ConsultarVertimientoXFuente() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<DatosReporteWS> datosVertimientosFuente;
    
    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }


    public void setDatosVertimientosFuente(List<DatosReporteWS> datosVertimientosFuente) {
        this.datosVertimientosFuente = datosVertimientosFuente;
    }

    public List<DatosReporteWS> getDatosVertimientosFuente() {
        return datosVertimientosFuente;
    }
}
