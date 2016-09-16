package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "sirh_consultaExtFuentes_v")
@NamedQueries( { @NamedQuery(name = "ConsultasExtFuentesTO.findAll", query = "SELECT s FROM ConsultasExtFuentesTO s WHERE s.tipo_fuente NOT LIKE 'Aguas subterraneas'"),
                 @NamedQuery(name = "ConsultasExtFuentesTO.findAll2", query = "SELECT s FROM ConsultasExtFuentesTO s WHERE s.tipo_fuente LIKE 'Aguas subterraneas'")
                
   } )
public class ConsultasExtFuentesTO implements Serializable {
    
   @Id
  @Column(name = "num", nullable = false)
    private Long num;    
   
    @Column(name = "idfuente", nullable = false)
    private Long id;
    @Column(name = "fuente")
    private String fuente;
    
    @Column(name = "id_subzona")
    private Long id_subzona;
    @Column(name = "subzona")
     private String subzona;
    @Column(name = "id_zona")
    private Long id_zona;
    @Column(name = "id_area")
    private Long id_area;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "zona")
    private String zona;
    
    
    @Column(name = "tramos_id")
    private Long tramo_id;
    @Column(name = "tramo")
    private String tramo;
    @Column(name = "tipo_tramite")
    private String tipo_tramite;
    @Column(name = "numero_tramites")
    private Integer numero_tramites;
    @Column(name = "caudal")
    private Double caudal;
    @Column(name = "id_autoridad")
    private Long id_autoridad;
    @Column(name = "autoridad")
    private String autoridad;  
      
    @Column(name = "tipo_fuente")
    private String tipo_fuente;  
      
        public ConsultasExtFuentesTO() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

   

    
    public void setId_autoridad(Long id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public Long getId_autoridad() {
        return id_autoridad;
    }

   

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }


    public void setTramo_id(Long tramo_id) {
        this.tramo_id = tramo_id;
    }

    public Long getTramo_id() {
        return tramo_id;
    }

    public void setId_subzona(Long id_subzona) {
        this.id_subzona = id_subzona;
    }

    public Long getId_subzona() {
        return id_subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setId_zona(Long id_zona) {
        this.id_zona = id_zona;
    }

    public Long getId_zona() {
        return id_zona;
    }

    public void setId_area(Long id_area) {
        this.id_area = id_area;
    }

    public Long getId_area() {
        return id_area;
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

    public void setTipo_fuente(String tipo_fuente) {
        this.tipo_fuente = tipo_fuente;
    }

    public String getTipo_fuente() {
        return tipo_fuente;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public void setNumero_tramites(Integer numero_tramites) {
        this.numero_tramites = numero_tramites;
    }

    public Integer getNumero_tramites() {
        return numero_tramites;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }
}
