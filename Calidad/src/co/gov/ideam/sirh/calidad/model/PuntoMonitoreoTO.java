package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "calt_puntosMonitoreo_v")
@NamedQueries( { @NamedQuery(name = "PuntoMonitoreoTO.findAllPuntos",
                             query = "SELECT s FROM PuntoMonitoreoTO s"),
                 @NamedQuery(name = "PuntoMonitoreoTO.findAutoridadAll",
                             query = "SELECT s FROM PuntoMonitoreoTO s where s.autoridad = :codigoAutoridad ")

   } )
public class PuntoMonitoreoTO implements Serializable {
        @Id
        @Column(name = "id", nullable = false)
        private Long id;
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "tipo_punto")
        private String tipo_punto;
        @Column(name = "ubicacion")
        private String ubicacion;
        @Column(name = "tipo_fuente")
        private String tipo_fuente;
        @Column(name = "fuente")
        private String fuente;
        @Column(name = "tramo")
        private String tramo;
        @Column(name = "municipio")
        private String municipio;
        @Column(name = "depto")
        private String depto;
        @Column(name = "area")
        private String area;
        @Column(name = "zona")
        private String zona;
        @Column(name = "subzona")
        private String subzona;
        @Column(name = "sist_ref")
        private String sist_ref;
        @Column(name = "latitud")
        private String latitud;
        @Column(name = "longitud")
        private String longitud;
        @Column(name = "altitud")
        private Double altitud;         
        @Column(name = "descripcion_acceso")
        private String descripcion_acceso;   
        @Column(name = "autoridad")
        private Long autoridad;    
        @Column(name = "vertimiento")
        private Long vertimiento;  
      
        
        
                       
    
    
    
    
    
    
    public PuntoMonitoreoTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipo_punto(String tipo_punto) {
        this.tipo_punto = tipo_punto;
    }

    public String getTipo_punto() {
        return tipo_punto;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getDepto() {
        return depto;
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

    public void setSist_ref(String sist_ref) {
        this.sist_ref = sist_ref;
    }

    public String getSist_ref() {
        return sist_ref;
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

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setDescripcion_acceso(String descripcion_acceso) {
        this.descripcion_acceso = descripcion_acceso;
    }

    public String getDescripcion_acceso() {
        return descripcion_acceso;
    }

    public void setAutoridad(Long autoridad) {
        this.autoridad = autoridad;
    }

    public Long getAutoridad() {
        return autoridad;
    }

    public void setVertimiento(Long vertimiento) {
        this.vertimiento = vertimiento;
    }

    public Long getVertimiento() {
        return vertimiento;
    }

    public void setTipo_fuente(String tipo_fuente) {
        this.tipo_fuente = tipo_fuente;
    }

    public String getTipo_fuente() {
        return tipo_fuente;
    }
}


