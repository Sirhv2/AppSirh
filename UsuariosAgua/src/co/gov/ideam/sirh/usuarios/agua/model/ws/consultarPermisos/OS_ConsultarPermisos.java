package co.gov.ideam.sirh.usuarios.agua.model.ws.consultarPermisos;
import co.gov.ideam.sirh.usuarios.agua.model.ConsultarPermisosV;
import java.io.Serializable;
import java.util.List;


public class OS_ConsultarPermisos implements Serializable {
   
    private Integer codigoError;
    private String mensajeError;
    private List<ConsultarPermisosV> listaPermisos;
 
    public OS_ConsultarPermisos() {
    
        
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


    public void setListaPermisos(List<ConsultarPermisosV> listaPermisos) {
        this.listaPermisos = listaPermisos;
    }

    public List<ConsultarPermisosV> getListaPermisos() {
        return listaPermisos;
    }
}
