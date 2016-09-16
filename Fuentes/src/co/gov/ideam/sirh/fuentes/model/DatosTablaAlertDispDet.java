package co.gov.ideam.sirh.fuentes.model;

// HUGO 20141030

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatosTablaAlertDispDet implements Serializable {


    @Id
    @Column(name = "idCaptacion")
    private Long idCaptacion;

    @Column(name = "idTramo")
    private Long idTramo;

    @Column(name = "idFuente")
    private int idFuente;

    @Column(name = "nomFuente")
    private String nomFuente;

    @Column(name = "nomTramo")
    private String nomTramo;

    @Column(name = "nomCaptacion")
    private String nomCaptacion;

    @Column(name = "oferta")
    private double oferta;

    @Column(name = "demanda")
    private double demanda;

    @Column(name = "alerta")
    private String alerta;

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdTramo() {
        return idTramo;
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

    public void setNomCaptacion(String nomCaptacion) {
        this.nomCaptacion = nomCaptacion;
    }

    public String getNomCaptacion() {
        return nomCaptacion;
    }

    public void setDemanda(double demanda) {
        this.demanda = demanda;
    }

    public double getDemanda() {
        return demanda;
    }

    public void setOferta(double oferta) {
        this.oferta = oferta;
    }

    public double getOferta() {
        return oferta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getAlerta() {
        return alerta;
    }
}
