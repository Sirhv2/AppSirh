package co.gov.ideam.sirh.seguridad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcionario implements Serializable{
    @Column(name = "codigoTipoIdentificacion")
    private int codigoTipoIdentificacion;
    
    @Column(name = "identificacionPersona")
    private String identificacionPersona;
    
    @Id
    @Column(name = "aliasUsuario")
    private String aliasUsuario;
    
    @Column(name = "nombreFuncionario")
    private String nombreFuncionario;
    
    @Column(name = "direccionCorreoElectronico")
    private String direccionCorreoElectronico;


    public Funcionario() {
    }

    public void setCodigoTipoIdentificacion(int codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public int getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

   
   

    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    public void setDireccionCorreoElectronico(String direccionCorreoElectronico) {
        this.direccionCorreoElectronico = direccionCorreoElectronico;
    }

    public String getDireccionCorreoElectronico() {
        return direccionCorreoElectronico;
    }

    public void setIdentificacionPersona(String identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getIdentificacionPersona() {
        return identificacionPersona;
    }
}
