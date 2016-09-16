package co.gov.ideam.sirh.webservices.model.consultarAutoridadesAmbientales;

import co.gov.ideam.sirh.seguridad.model.Funcionario;

import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.AutoridadesWS;

import java.util.List;

public class OS_ConsultarAutAmbientales {
    public OS_ConsultarAutAmbientales() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<AutoridadesWS> listaAutAmbientales;


   

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

  public void setListaAutAmbientales(List<AutoridadesWS> listaAutAmbientales) {
    this.listaAutAmbientales = listaAutAmbientales;
  }

  public List<AutoridadesWS> getListaAutAmbientales() {
    return listaAutAmbientales;
  }
}
