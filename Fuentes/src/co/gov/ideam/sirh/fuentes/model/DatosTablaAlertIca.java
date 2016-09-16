package co.gov.ideam.sirh.fuentes.model;
// HUGO 20141204

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosTablaAlertIca   implements Serializable {

 
   
    @Id
    @Column(name="idMuestra") 
    private Long idMuestra;
    
    @Column(name="area")     
    private int area;
    
    @Column(name="zona")     
    private int zona;

    @Column(name="subzona")     
    private int subzona;
    
    @Column(name="fuente")     
    private Long fuente;

    @Column(name="tramo")     
    private Long tramo;

    @Column(name="punto")     
    private Long punto;

    @Column(name="nomFuente")     
    private String nomFuente;
    
    @Column(name="nomTramo") 
    private String nomTramo;
    
    @Column(name="nomPunto") 
    private String nomPunto;

    @Column(name="ica") 
    private double ica;

    @Column(name="fechaMuestra") 
    private String fechaMuestra;
    
    @Transient
    private String nivel;



    public void setArea(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getZona() {
        return zona;
    }

    public void setSubzona(int subzona) {
        this.subzona = subzona;
    }

    public int getSubzona() {
        return subzona;
    }

    public void setNomFuente(String nomFuente) {
        this.nomFuente = nomFuente;
    }

    public String getNomFuente() {
        return nomFuente;
    }

    public void setNomTramo(String nomTramo) {
        this.nomTramo = nomTramo;
    }

    public String getNomTramo() {
        return nomTramo;
    }

    public void setIdMuestra(Long idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Long getIdMuestra() {
        return idMuestra;
    }

    public void setFuente(Long fuente) {
        this.fuente = fuente;
    }

    public Long getFuente() {
        return fuente;
    }

    public void setTramo(Long tramo) {
        this.tramo = tramo;
    }

    public Long getTramo() {
        return tramo;
    }

    public void setPunto(Long punto) {
        this.punto = punto;
    }

    public Long getPunto() {
        return punto;
    }

    public void setNomPunto(String nomPunto) {
        this.nomPunto = nomPunto;
    }

    public String getNomPunto() {
        return nomPunto;
    }

    public void setIca(double ica) {
        this.ica = ica;
    }

    public double getIca() {
        return ica;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        
        
        String vlrNivel = "No registra";
        
        if ( getIca() >= 0 && getIca() <= 0.25 )
            vlrNivel = "Muy Malo";
        
        if ( getIca() > 0.25 && getIca() <= 0.50 )
            vlrNivel = "Malo";
        
        if ( getIca() > 0.50 && getIca() <= 0.70 )
            vlrNivel = "Regular";
        
        if ( getIca() > 0.70 && getIca() <= 0.90 )
            vlrNivel = "Aceptable";

        if ( getIca() > 0.90 )
            vlrNivel = "Bueno";

        return vlrNivel;
    }

    public void setFechaMuestra(String fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public String getFechaMuestra() {
        return fechaMuestra;
    }

}
