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
@Table(name = "CMAT_PUNTOS_MONITOREO_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatPuntosMonitoreoV2.findAllPtosMonV2", query = "SELECT c FROM CmatPuntosMonitoreoV2 c"),
    @NamedQuery(name = "CmatPuntosMonitoreoV2.findByCodigoRechazoPtosMonV2", query = "SELECT c FROM CmatPuntosMonitoreoV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatPuntosMonitoreoV2.findByIdRegistroPtosMonV2", query = "SELECT c FROM CmatPuntosMonitoreoV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatPuntosMonitoreoV2.findByIdFuentePtosMonV2", query = "SELECT c FROM CmatPuntosMonitoreoV2 c WHERE c.idFuente = :idFuente"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCarguePtosMonV2", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatPuntosMonitoreoV2 implements Serializable {

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
    @Column(name = "NOMBRE_PUNTO")
    private String nombrePunto;
    @Column(name = "TIPO_PUNTO")
    private String tipoPunto;
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "AREA")
    private String area;
    @Column(name = "ZONA")
    private String zona;
    @Column(name = "SUBZONA")
    private String subzona;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FUENTE")
    private String fuente;
    @Column(name = "TRAMO")
    private String tramo;
    @Column(name = "SISTEMA_REFERENCIA")
    private String sistemaReferencia;
    @Column(name = "GRADOS_LATITUD")
    private String gradosLatitud;
    @Column(name = "MINUTOS_LATITUD")
    private String minutosLatitud;
    @Column(name = "SEGUNDOS_LATITUD")
    private String segundosLatitud;
    @Column(name = "GRADOS_LONGITUD")
    private String gradosLongitud;
    @Column(name = "MINUTOS_LONGITUD")
    private String minutosLongitud;
    @Column(name = "SEGUNDOS_LONGITUD")
    private String segundosLongitud;
    @Column(name = "ALTITUD")
    private String altitud;
    @Column(name = "DESCRIPCION_ACCESO")
    private String descripcionAcceso;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatPuntosMonitoreoV2() {
    }

    public CmatPuntosMonitoreoV2(double idRegistro) {
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

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getTipoPunto() {
        return tipoPunto;
    }

    public void setTipoPunto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(String sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public String getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(String gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public String getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(String minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public String getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(String segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public String getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(String gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public String getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(String minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public String getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(String segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getDescripcionAcceso() {
        return descripcionAcceso;
    }

    public void setDescripcionAcceso(String descripcionAcceso) {
        this.descripcionAcceso = descripcionAcceso;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

  

    @Override
    public String toString() {
        return "Entidades.CmatPuntosMonitoreoV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
