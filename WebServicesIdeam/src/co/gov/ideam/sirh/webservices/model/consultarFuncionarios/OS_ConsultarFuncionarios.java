package co.gov.ideam.sirh.webservices.model.consultarFuncionarios;

import co.gov.ideam.sirh.seguridad.model.Funcionario;

import java.util.List;

public class OS_ConsultarFuncionarios {
    public OS_ConsultarFuncionarios() {
    }
    
    private Integer codigoError;
    private String mensajeError;
    private List<Funcionario> funcionarios;


   

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }


  public void setCodigoError(Integer codigoError) {
    this.codigoError = codigoError;
  }

  public Integer getCodigoError() {
    return codigoError;
  }
}
