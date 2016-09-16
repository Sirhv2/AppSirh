/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "sirhv_caudal_tipo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findAll", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findById", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.id = :id"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findByFechaExpCaudal", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.fechaExpCaudal = :fechaExpCaudal"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findByCaudalConcesionado", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.caudalConcesionado = :caudalConcesionado"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findByTipoPersona", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.tipoPersona = :tipoPersona"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findByAutoridad", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.autoridad = :autoridad"),
    @NamedQuery(name = "SirhvCaudalTipoUsuario.findByFechas", 
                query = "SELECT s FROM SirhvCaudalTipoUsuario s WHERE s.fechaExpCaudal Between  :fechaInicio AND :fechaFin ")
    })
public class SirhvCaudalTipoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "fechaExpCaudal")
    @Temporal(TemporalType.DATE)
    private Date fechaExpCaudal;
    @Column(name = "caudalConcesionado")
    private Double caudalConcesionado;
    @Column(name = "tipoPersona")
    private String tipoPersona;
    @Column(name = "autoridad")
    private String autoridad;
    @Transient
    private Double caudalTotal;

    public SirhvCaudalTipoUsuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaExpCaudal() {
        return fechaExpCaudal;
    }

    public void setFechaExpCaudal(Date fechaExpCaudal) {
        this.fechaExpCaudal = fechaExpCaudal;
    }

    public Double getCaudalConcesionado() {
        return caudalConcesionado;
    }

    public void setCaudalConcesionado(Double caudalConcesionado) {
        this.caudalConcesionado = caudalConcesionado;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public void setCaudalTotal(Double caudalTotal) {
        this.caudalTotal = caudalTotal;
    }

    public Double getCaudalTotal() {
        return caudalTotal;
    }
}
