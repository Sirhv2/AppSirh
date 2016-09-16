/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cesar
 */
@Entity
@Table(name = "RURT_CAP_AFORO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RurtCapAforo.findAll", query = "SELECT r FROM RurtCapAforo r"),
    @NamedQuery(name = "RurtCapAforo.findById", query = "SELECT r FROM RurtCapAforo r WHERE r.id = :id"),
    @NamedQuery(name = "RurtCapAforo.findByCaudal", query = "SELECT r FROM RurtCapAforo r WHERE r.caudal = :caudal"),
    @NamedQuery(name = "RurtCapAforo.findByFecha", query = "SELECT r FROM RurtCapAforo r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RurtCapAforo.findByCapt", query = "SELECT r FROM RurtCapAforo r WHERE r.captacionId = :captacionId"),
    @NamedQuery(name = "RurtCapAforo.findByObservacion", query = "SELECT r FROM RurtCapAforo r WHERE r.observacion = :observacion")})
public class RurtCapAforo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CAUDAL")
    private Double caudal;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "CAPTACION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Captacion captacionId;

    public RurtCapAforo() {
    }

    public RurtCapAforo(Long id) {
        this.id = id;
    }

    public RurtCapAforo(Long id, Double caudal) {
        this.id = id;
        this.caudal = caudal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Captacion getCaptacionId() {
        return captacionId;
    }

    public void setCaptacionId(Captacion captacionId) {
        this.captacionId = captacionId;
    }
    
}
