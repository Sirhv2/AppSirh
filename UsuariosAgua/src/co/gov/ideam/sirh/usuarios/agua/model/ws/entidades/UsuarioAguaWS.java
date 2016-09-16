package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;

import java.io.Serializable;


public class UsuarioAguaWS implements Serializable {
    public static final int CORPORACION = 1;
    public static final int MUNICIPIO = 2;
    public static final int JURIDICA_PRIVADA = 3;
    public static final int JURIDICA_PUBLICA = 4;
    public static final int NATURAL = 5;
    public static final int ACUEDUCTO = 136;
    public static final int MEGAPROYECTO = 956;
    
  
    private Integer codigoTipoIdentificacion;
    private String  identificacionPersona;
    private Integer tipoPersona;
    private Integer actividadCIIU;
    private String  direccionCorreoElectronico;
    private String  telefonoFijo;
    private String  celular;
    private String  primerNombre;
    private String  segundoNombre;
    private String  primerApellido;
    private String  segundoApellido;
    private String  razonSocial;
    private Integer codAutoridadAmbiental;
    private Integer idMunicipioCorrespondencia;
    private Integer idDeptoCorrespondencia;
    private String  direccionCorrespondencia;
    
 
    public void setCodigoTipoIdentificacion(Integer codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public Integer getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setIdentificacionPersona(String identificacionPersona) {
        this.identificacionPersona = identificacionPersona;
    }

    public String getIdentificacionPersona() {
        return identificacionPersona;
    }

    public void setTipoPersona(Integer tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getTipoPersona() {
        return tipoPersona;
    }

    public void setActividadCIIU(Integer actividadCIIU) {
        this.actividadCIIU = actividadCIIU;
    }

    public Integer getActividadCIIU() {
        return actividadCIIU;
    }

    public void setDireccionCorreoElectronico(String direccionCorreoElectronico) {
        this.direccionCorreoElectronico = direccionCorreoElectronico;
    }

    public String getDireccionCorreoElectronico() {
        return direccionCorreoElectronico;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelular() {
        return celular;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }


    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }



    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setCodAutoridadAmbiental(Integer codAutoridadAmbiental) {
        this.codAutoridadAmbiental = codAutoridadAmbiental;
    }

    public Integer getCodAutoridadAmbiental() {
        return codAutoridadAmbiental;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }



    public void setIdMunicipioCorrespondencia(Integer idMunicipioCorrespondencia) {
        this.idMunicipioCorrespondencia = idMunicipioCorrespondencia;
    }

    public Integer getIdMunicipioCorrespondencia() {
        return idMunicipioCorrespondencia;
    }

    public void setIdDeptoCorrespondencia(Integer idDeptoCorrespondencia) {
        this.idDeptoCorrespondencia = idDeptoCorrespondencia;
    }

    public Integer getIdDeptoCorrespondencia() {
        return idDeptoCorrespondencia;
    }

    public void setDireccionCorrespondencia(String direccionCorrespondencia) {
        this.direccionCorrespondencia = direccionCorrespondencia;
    }

    public String getDireccionCorrespondencia() {
        return direccionCorrespondencia;
    }
}
