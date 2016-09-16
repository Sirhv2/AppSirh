package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosTablaAlertIcaDet implements Serializable{

    @Id
    @Column(name="idMuestra") 
    private Long idMuestra;

    @Column(name="ica") 
    private double ica;

    @Column(name="fechaMuestra") 
    private String fechaMuestra;
    
    @Transient
    private String nivel;


    public void setIdMuestra(Long idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Long getIdMuestra() {
        return idMuestra;
    }

    public void setIca(double ica) {
        this.ica = ica;
    }

    public double getIca() {
        return ica;
    }

    public void setFechaMuestra(String fechaMuestra) {
        this.fechaMuestra = fechaMuestra;
    }

    public String getFechaMuestra() {
        return fechaMuestra;
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
}
