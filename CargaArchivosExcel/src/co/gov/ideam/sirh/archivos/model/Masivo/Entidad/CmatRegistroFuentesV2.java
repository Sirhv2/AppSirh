/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;

import java.io.Serializable;
import java.math.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_REGISTRO_FUENTES_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatRegistroFuentesV2.findAllRegFuenV2", query = "SELECT c FROM CmatRegistroFuentesV2 c"),
    @NamedQuery(name = "CmatRegistroFuentesV2.findByCodigoRechazoRegFuenV2", query = "SELECT c FROM CmatRegistroFuentesV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatRegistroFuentesV2.findByIdRegistroRegFuenV2", query = "SELECT c FROM CmatRegistroFuentesV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueRegFuenV2", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatRegistroFuentesV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_FUENTE")
    private String idFuente;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "NOMBRE_FUENTE")
    private String nombreFuente;
    @Column(name = "FUENTE_COMPARTIDA")
    private String fuenteCompartida;
    @Column(name = "CODIGO_CUENCA")
    private String codigoCuenca;
    @Column(name = "DESCRIPCION_FUENTE")
    private String descripcionFuente;
    @Column(name = "NOMBRE_TRAMO")
    private String nombreTramo;
    @Column(name = "DESCRIPCION_TRAMO")
    private String descripcionTramo;
    @Column(name = "LONGITUD_TRAMO")
    private String longitudTramo;
    @Column(name = "TIPO_FLUJO")
    private String tipoFlujo;
    @Column(name = "AREA")
    private String area;
    @Column(name = "ZONA")
    private String zona;
    @Column(name = "SUBZONA")
    private String subzona;
    @Column(name = "OFERTA_HIDRICA_A\u00d1O_MEDIO")
    private String ofertaHidricaAnioMedio;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "SISTEMA_REF_PI")
    private String sistemaRefPi;
    @Column(name = "GRAD_LAT_PI")
    private String gradLatPi;
    @Column(name = "MIN_LAT_PI")
    private String minLatPi;
    @Column(name = "SEG_LAT_PI")
    private String segLatPi;
    @Column(name = "GRAD_LOG_PI")
    private String gradLogPi;
    @Column(name = "MIN_LOG_PI")
    private String minLogPi;
    @Column(name = "SEG_LOG_PI")
    private String segLogPi;
    @Column(name = "ALTITUD_PI")
    private String altitudPi;
    @Column(name = "SISTEMA_REF_PF")
    private String sistemaRefPf;
    @Column(name = "GRAD_LAT_PF")
    private String gradLatPf;
    @Column(name = "MIN_LAT_PF")
    private String minLatPf;
    @Column(name = "SEG_LAT_PF")
    private String segLatPf;
    @Column(name = "GRAD_LOG_PF")
    private String gradLogPf;
    @Column(name = "MIN_LOG_PF")
    private String minLogPf;
    @Column(name = "SEG_LOG_PF")
    private String segLogPf;
    @Column(name = "ALTITUD_PF")
    private String altitudPf;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatRegistroFuentesV2() {
    }

    public CmatRegistroFuentesV2(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Long getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(Long codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public double getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(String idFuente) {
        this.idFuente = idFuente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getFuenteCompartida() {
        return fuenteCompartida;
    }

    public void setFuenteCompartida(String fuenteCompartida) {
        this.fuenteCompartida = fuenteCompartida;
    }

    public String getCodigoCuenca() {
        return codigoCuenca;
    }

    public void setCodigoCuenca(String codigoCuenca) {
        this.codigoCuenca = codigoCuenca;
    }

    public String getDescripcionFuente() {
        return descripcionFuente;
    }

    public void setDescripcionFuente(String descripcionFuente) {
        this.descripcionFuente = descripcionFuente;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public String getDescripcionTramo() {
        return descripcionTramo;
    }

    public void setDescripcionTramo(String descripcionTramo) {
        this.descripcionTramo = descripcionTramo;
    }

    public String getLongitudTramo() {
        return longitudTramo;
    }

    public void setLongitudTramo(String longitudTramo) {
        this.longitudTramo = longitudTramo;
    }

    public String getTipoFlujo() {
        return tipoFlujo;
    }

    public void setTipoFlujo(String tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getOfertaHidricaAnioMedio() {
        return ofertaHidricaAnioMedio;
    }

    public void setOfertaHidricaAnioMedio(String ofertaHidricaAnioMedio) {
        this.ofertaHidricaAnioMedio = ofertaHidricaAnioMedio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSistemaRefPi() {
        return sistemaRefPi;
    }

    public void setSistemaRefPi(String sistemaRefPi) {
        this.sistemaRefPi = sistemaRefPi;
    }

    public String getGradLatPi() {
        return gradLatPi;
    }

    public void setGradLatPi(String gradLatPi) {
        this.gradLatPi = gradLatPi;
    }

    public String getMinLatPi() {
        return minLatPi;
    }

    public void setMinLatPi(String minLatPi) {
        this.minLatPi = minLatPi;
    }

    public String getSegLatPi() {
        return segLatPi;
    }

    public void setSegLatPi(String segLatPi) {
        this.segLatPi = segLatPi;
    }

    public String getGradLogPi() {
        return gradLogPi;
    }

    public void setGradLogPi(String gradLogPi) {
        this.gradLogPi = gradLogPi;
    }

    public String getMinLogPi() {
        return minLogPi;
    }

    public void setMinLogPi(String minLogPi) {
        this.minLogPi = minLogPi;
    }

    public String getSegLogPi() {
        return segLogPi;
    }

    public void setSegLogPi(String segLogPi) {
        this.segLogPi = segLogPi;
    }

    public String getAltitudPi() {
        return altitudPi;
    }

    public void setAltitudPi(String altitudPi) {
        this.altitudPi = altitudPi;
    }

    public String getSistemaRefPf() {
        return sistemaRefPf;
    }

    public void setSistemaRefPf(String sistemaRefPf) {
        this.sistemaRefPf = sistemaRefPf;
    }

    public String getGradLatPf() {
        return gradLatPf;
    }

    public void setGradLatPf(String gradLatPf) {
        this.gradLatPf = gradLatPf;
    }

    public String getMinLatPf() {
        return minLatPf;
    }

    public void setMinLatPf(String minLatPf) {
        this.minLatPf = minLatPf;
    }

    public String getSegLatPf() {
        return segLatPf;
    }

    public void setSegLatPf(String segLatPf) {
        this.segLatPf = segLatPf;
    }

    public String getGradLogPf() {
        return gradLogPf;
    }

    public void setGradLogPf(String gradLogPf) {
        this.gradLogPf = gradLogPf;
    }

    public String getMinLogPf() {
        return minLogPf;
    }

    public void setMinLogPf(String minLogPf) {
        this.minLogPf = minLogPf;
    }

    public String getSegLogPf() {
        return segLogPf;
    }

    public void setSegLogPf(String segLogPf) {
        this.segLogPf = segLogPf;
    }

    public String getAltitudPf() {
        return altitudPf;
    }

    public void setAltitudPf(String altitudPf) {
        this.altitudPf = altitudPf;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

 

    @Override
    public String toString() {
        return "Entidades.CmatRegistroFuentesV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
