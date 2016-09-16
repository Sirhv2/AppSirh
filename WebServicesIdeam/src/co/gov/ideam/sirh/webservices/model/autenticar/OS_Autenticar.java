package co.gov.ideam.sirh.webservices.model.autenticar;


public class OS_Autenticar{
    public OS_Autenticar() {
    }
    
    private Integer codigoError;
    private String mensajeError;
   

 

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
}
