package co.gov.ideam.sirh.fuentes.model;

// HUGO 20141030

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosTablaAlertDisp  implements Serializable{
 
   
    @Id
    @Column(name="idTramo") 
    private Long idTramo;
    
    @Column(name="area")     
    private int area;
    
    @Column(name="zona")     
    private int zona;

    @Column(name="subzona")     
    private int subzona;
    
    @Column(name="idFuente")     
    private int idFuente;

    @Column(name="nomFuente")     
    private String nomFuente;
    
    @Column(name="nomTramo") 
    private String nomTramo;
    
    @Column(name="oferta") 
    private double oferta;
    
    @Column(name="demanda") 
    private double demanda;
    
    @Transient
    private String demandaStr;

    @Column(name="alerta") 
    private String alerta;

    @Transient
    private Boolean tieneSobreOferta;

    @Transient
    private Boolean tieneAdvertencia;

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdTramo() {
        return idTramo;
    }

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

    public void setIdFuente(int idFuente) {
        this.idFuente = idFuente;
    }

    public int getIdFuente() {
        return idFuente;
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

    public void setOferta(double oferta) {
        this.oferta = oferta;
    }

    public double getOferta() {
        return oferta;
    }

    public void setDemanda(double demanda) {
        this.demanda = demanda;
    }

    public double getDemanda() {
        return demanda;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setTieneSobreOferta(Boolean tieneSobreOferta) {
        this.tieneSobreOferta = tieneSobreOferta;
    }

    public Boolean getTieneSobreOferta() {
        
        if ( getOferta() > 0 && getDemanda() > getOferta() )
            return true;
        
        return false;
        
    }

    public Boolean getTieneAdvertencia() {
        
        if ( getOferta() > 0 && getDemanda() > ( getOferta() - getOferta()* 0.25) )
            return true;
        
        return false;

    }

    public void setDemandaStr(String demandaStr) {
        this.demandaStr = demandaStr;
    }

    public String getDemandaStr() {
        this.demandaStr = String.format("%.12f", demanda);
        return demandaStr;
    }
}
