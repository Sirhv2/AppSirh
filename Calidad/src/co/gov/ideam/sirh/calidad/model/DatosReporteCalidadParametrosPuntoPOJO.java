package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosReporteCalidadParametrosPuntoPOJO implements Serializable{
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name="valor") 
    private String valor;
    @Column(name="fecha")     
    private String fecha;
    @Column(name="parametro") 
    private String parametro;
    @Column(name="LD")     
    private String LD;
    @Column(name="cantidad")     
    private Double cantidad;
    @Column(name="unidad") 
    private String unidad;
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "puntoMonitoreo")
    private String puntoMonitoreo;
    @Column(name = "area")
    private String area;
    @Column(name = "zona")
    private String zona;
    @Column(name = "subzona")
    private String subzona;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "latitud")
    private String latitud;
    @Column(name = "longitud")
    private String longitud;
    @Column(name = "altitud")
    private String altitud;
    @Column(name = "nroMuestra")
    private String nroMuestra;
    @Transient
    private Integer num;

    public DatosReporteCalidadParametrosPuntoPOJO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLD() {
        return LD;
    }

    public void setLD(String LD) {
        this.LD = LD;
    }
 
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getPuntoMonitoreo() {
        return puntoMonitoreo;
    }

    public void setPuntoMonitoreo(String puntoMonitoreo) {
        this.puntoMonitoreo = puntoMonitoreo;
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getNroMuestra() {
        return nroMuestra;
    }

    public void setNroMuestra(String nroMuestra) {
        this.nroMuestra = nroMuestra;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

