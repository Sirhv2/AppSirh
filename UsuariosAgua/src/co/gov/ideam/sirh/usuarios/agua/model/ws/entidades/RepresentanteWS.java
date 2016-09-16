package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;
import java.io.Serializable;



public class RepresentanteWS implements Serializable {

    private String codigoTipoIdentificacionRepLegal;
    private String identificacionRepLegal;
    private String primerNombreRepLegal;
    private String SegundoNombreRepLegal;
    private String primerApellidoRepLegal;
    private String SegundoApellidoRepLegal;
    private Integer idMunicipioCorrespondencia;
    private Integer idDeptoCorrespondencia;
    private String direccionCorrespondencia;


    public void setCodigoTipoIdentificacionRepLegal(String codigoTipoIdentificacionRepLegal) {
        this.codigoTipoIdentificacionRepLegal = codigoTipoIdentificacionRepLegal;
    }

    public String getCodigoTipoIdentificacionRepLegal() {
        return codigoTipoIdentificacionRepLegal;
    }

    public void setIdentificacionRepLegal(String identificacionRepLegal) {
        this.identificacionRepLegal = identificacionRepLegal;
    }

    public String getIdentificacionRepLegal() {
        return identificacionRepLegal;
    }

    public void setPrimerNombreRepLegal(String primerNombreRepLegal) {
        this.primerNombreRepLegal = primerNombreRepLegal;
    }

    public String getPrimerNombreRepLegal() {
        return primerNombreRepLegal;
    }

    public void setSegundoNombreRepLegal(String SegundoNombreRepLegal) {
        this.SegundoNombreRepLegal = SegundoNombreRepLegal;
    }

    public String getSegundoNombreRepLegal() {
        return SegundoNombreRepLegal;
    }

    public void setPrimerApellidoRepLegal(String primerApellidoRepLegal) {
        this.primerApellidoRepLegal = primerApellidoRepLegal;
    }

    public String getPrimerApellidoRepLegal() {
        return primerApellidoRepLegal;
    }

    public void setSegundoApellidoRepLegal(String SegundoApellidoRepLegal) {
        this.SegundoApellidoRepLegal = SegundoApellidoRepLegal;
    }

    public String getSegundoApellidoRepLegal() {
        return SegundoApellidoRepLegal;
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
