/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.red.ideam.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "SIRHV_PUNTO_MONITOREO_FQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findAll", query = "SELECT s FROM SirhvPuntoMonitoreoFq s ORDER BY s.punto asc"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findById", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.id = :id"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByPunto", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE upper(s.punto) LIKE :punto"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByFuente", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.fuente = :fuente"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByTipofuente", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.tipofuente = :tipofuente"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByTramo", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.tramo = :tramo"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByTipopunto", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.tipopunto = :tipopunto"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByUbicacion", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.ubicacion = :ubicacion"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByMunicipio", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.municipio = :municipio"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByDepartamento", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.departamento = :departamento"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByArea", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.area = :area"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByZona", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.zona = :zona"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findBySubzona", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.subzona = :subzona"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findBySistemaReferencia", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.sistemaReferencia = :sistemaReferencia"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByGradoslat", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.gradoslat = :gradoslat"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByMinutoslat", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.minutoslat = :minutoslat"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findBySegundolat", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.segundolat = :segundolat"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByGradoslong", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.gradoslong = :gradoslong"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByMinutoslong", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.minutoslong = :minutoslong"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findBySegundolong", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.segundolong = :segundolong"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByAltitud", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.altitud = :altitud"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByLatitud", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.latitud = :latitud"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByLongitud", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.longitud = :longitud"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByDescripcionacceso", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.descripcionacceso = :descripcionacceso"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByAutoridad", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.autoridad = :autoridad"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByVertimiento", query = "SELECT s FROM SirhvPuntoMonitoreoFq s WHERE s.vertimiento = :vertimiento"),
    @NamedQuery(name = "SirhvPuntoMonitoreoFq.findByCodCatalogoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codCatalogoEs = :codCatalogoEs")
    })

public class SirhvPuntoMonitoreoFq implements Serializable {
    
    public static final String listaPuntosMonitoreos = "Select id, punto, fuente, area, zona,subzona, departamento, municipio, latitud, " + 
                                                        "       direccion_latitud, longitud, direccion_longitud, altitud, tipofuente, " + 
                                                        "       autoridad, gradoslat, minutoslat, segundolat, segundoslat, gradoslong, " + 
                                                        "        minutoslong, segundolong, codcatalogoes, tramo, tipopunto, " + 
                                                        "       sistema_referencia, descripcionacceso, ubicacion, vertimiento " + 
                                                        "From(select pm.id, pm.autoridad, pm.punto, pm.id_fuente, pm.fuente, pm.id_area, pm.area, pm.id_zona, pm.zona, pm.id_subzona, " + 
                                                        "       pm.subzona, pm.id_departamento, pm.departamento, pm.id_municipio, pm.municipio, pm.latitud, " + 
                                                        "       pm.direccion_latitud, pm.longitud, pm.direccion_longitud, pm.tipofuente,pm.altitud,  pmm.fecha_muestreo as fecha_ini, " + 
                                                        "       pmm.fecha_muestreo as fecha_fin, pm.gradoslat,  pm.minutoslat, pm.segundolat, pm.segundoslat, " + 
                                                        "       pm.gradoslong, pm.minutoslong, pm.segundolong, pm.codcatalogoes,pm.tramo, pm.tipopunto, " + 
                                                        "       pm.sistema_referencia, pm.descripcionacceso, pm.ubicacion, pm.vertimiento " + 
                                                        "From  sirhv_punto_monitoreo_fq pm " + 
                                                        "     left join sirhv_part_puntos_muestras_fq  pmm on pmm.id_punto = pm.id ) ";
    
    public static final String altitudPuntoMonitoreoIdeam =  "Select distinct pm.id, pm.autoridad, pm.punto, pm.id_fuente, pm.fuente, pm.id_area, pm.area, pm.id_zona, pm.zona, pm.id_subzona," + 
                                                            "    pm.subzona, pm.id_departamento, pm.departamento, pm.id_municipio, pm.municipio, pm.latitud," + 
                                                            "    pm.direccion_latitud, pm.longitud, pm.direccion_longitud, pm.tipofuente,pm.altitud," + 
                                                            "    pm.gradoslat,  pm.minutoslat, pm.segundolat, pm.segundoslat," + 
                                                            "    pm.gradoslong, pm.minutoslong, pm.segundolong, pm.codcatalogoes,pm.tramo, pm.tipopunto," + 
                                                            "    pm.sistema_referencia, pm.descripcionacceso, pm.ubicacion, pm.vertimiento  " + 
                                                             " from sirhv_punto_monitoreo_fq pm, sirhv_muestras_fq mm " + 
                                                             " where pm.id = mm.estacionid" + 
                                                             " and codigomuestra= ?1";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "PUNTO", nullable = false)
    private String punto;
    @Column(name = "FUENTE")
    private String fuente;
    @Column(name = "TIPOFUENTE")
    private String tipofuente;
    @Column(name = "TRAMO")
    private String tramo;
    @Column(name = "TIPOPUNTO")
    private String tipopunto;
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "AREA")
    private String area;
    @Column(name = "ZONA")
    private String zona;
    @Column(name = "SUBZONA")
    private String subzona;
    @Column(name = "SISTEMA_REFERENCIA")
    private String sistemaReferencia;
    @Column(name = "GRADOSLAT")
    private Integer gradoslat;
    @Column(name = "MINUTOSLAT")
    private Integer minutoslat;
    @Column(name = "SEGUNDOLAT")
    private Double segundolat;
    @Column(name = "GRADOSLONG")
    private Integer gradoslong;
    @Column(name = "MINUTOSLONG")
    private Integer minutoslong;
    @Column(name = "SEGUNDOLONG")
    private Double segundolong;
    @Column(name = "ALTITUD")
    private Double altitud;
    @Column(name = "LATITUD")
    private String latitud;
    @Column(name = "LONGITUD")
    private String longitud;
    @Column(name = "DIRECCION_LATITUD")
    private String direccionLatitud;
    @Column(name = "DIRECCION_LONGITUD")
    private String direccionLongitud;
    @Column(name = "DESCRIPCIONACCESO")
    private String descripcionacceso;
    @Column(name = "AUTORIDAD")
    private Long autoridad;
    @Column(name = "VERTIMIENTO")
    private String vertimiento;
    @Column(name = "codCatalogoEs")
    private String codCatalogoEs;
    
    @Transient
    private Integer num;

    public SirhvPuntoMonitoreoFq() {
        super();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public String getPunto() {
        return punto;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTipofuente(String tipofuente) {
        this.tipofuente = tipofuente;
    }

    public String getTipofuente() {
        return tipofuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTipopunto(String tipopunto) {
        this.tipopunto = tipopunto;
    }

    public String getTipopunto() {
        return tipopunto;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setSistemaReferencia(String sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public String getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setGradoslat(Integer gradoslat) {
        this.gradoslat = gradoslat;
    }

    public Integer getGradoslat() {
        return gradoslat;
    }

    public void setMinutoslat(Integer minutoslat) {
        this.minutoslat = minutoslat;
    }

    public Integer getMinutoslat() {
        return minutoslat;
    }

    public void setSegundolat(Double segundolat) {
        this.segundolat = segundolat;
    }

    public Double getSegundolat() {
        return segundolat;
    }

    public void setGradoslong(Integer gradoslong) {
        this.gradoslong = gradoslong;
    }

    public Integer getGradoslong() {
        return gradoslong;
    }

    public void setMinutoslong(Integer minutoslong) {
        this.minutoslong = minutoslong;
    }

    public Integer getMinutoslong() {
        return minutoslong;
    }

    public void setSegundolong(Double segundolong) {
        this.segundolong = segundolong;
    }

    public Double getSegundolong() {
        return segundolong;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setDescripcionacceso(String descripcionacceso) {
        this.descripcionacceso = descripcionacceso;
    }

    public String getDescripcionacceso() {
        return descripcionacceso;
    }

    public void setAutoridad(Long autoridad) {
        this.autoridad = autoridad;
    }

    public Long getAutoridad() {
        return autoridad;
    }

    public void setVertimiento(String vertimiento) {
        this.vertimiento = vertimiento;
    }

    public String getVertimiento() {
        return vertimiento;
    }

    public void setCodCatalogoEs(String codCatalogoEs) {
        this.codCatalogoEs = codCatalogoEs;
    }

    public String getCodCatalogoEs() {
        return codCatalogoEs;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDireccionLatitud() {
        return direccionLatitud;
    }

    public void setDireccionLatitud(String direccionLatitud) {
        this.direccionLatitud = direccionLatitud;
    }

    public String getDireccionLongitud() {
        return direccionLongitud;
    }

    public void setDireccionLongitud(String direccionLongitud) {
        this.direccionLongitud = direccionLongitud;
    }
}
