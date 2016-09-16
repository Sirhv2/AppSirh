package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosReporteCalidadPOJO implements Serializable{
    @Id
    @Column(name = "id")
    private Long id;
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
    @Column(name = "fechaMuestreo")
    private String fechaMuestreo;
    @Transient
    private Integer num;
    
    public DatosReporteCalidadPOJO() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFechaMuestreo() {
        return fechaMuestreo;
    }

    public void setFechaMuestreo(String fechaMuestreo) {
        this.fechaMuestreo = fechaMuestreo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
