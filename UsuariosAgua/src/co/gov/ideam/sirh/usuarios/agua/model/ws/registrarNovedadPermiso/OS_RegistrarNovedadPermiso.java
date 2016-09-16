package co.gov.ideam.sirh.usuarios.agua.model.ws.registrarNovedadPermiso;
import java.io.Serializable;


public class OS_RegistrarNovedadPermiso implements Serializable {
   
    private Integer codigoError;
    private String mensajeError;
    private Long numeroAutorizacion;
    
 
    public OS_RegistrarNovedadPermiso() {
        
      
        
        
    }
    
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }


    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setNumeroAutorizacion(Long numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Long getNumeroAutorizacion() {
        return numeroAutorizacion;
    }
}
