package co.gov.ideam.sirh.fuentes.model;


import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "FNTT_TRM_AFORO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FnttTrmAforo.findAll", query = "SELECT f FROM FnttTrmAforo f"),
    @NamedQuery(name = "FnttTrmAforo.findById", query = "SELECT f FROM FnttTrmAforo f WHERE f.id = :id"),
    @NamedQuery(name = "FnttTrmAforo.findByCaudal", query = "SELECT f FROM FnttTrmAforo f WHERE f.caudal = :caudal"),
    @NamedQuery(name = "FnttTrmAforo.findByGradosLat", query = "SELECT f FROM FnttTrmAforo f WHERE f.gradosLat = :gradosLat"),
    @NamedQuery(name = "FnttTrmAforo.findByMinutosLat", query = "SELECT f FROM FnttTrmAforo f WHERE f.minutosLat = :minutosLat"),
    @NamedQuery(name = "FnttTrmAforo.findBySegundosLat", query = "SELECT f FROM FnttTrmAforo f WHERE f.segundosLat = :segundosLat"),
    @NamedQuery(name = "FnttTrmAforo.findByGradosLong", query = "SELECT f FROM FnttTrmAforo f WHERE f.gradosLong = :gradosLong"),
    @NamedQuery(name = "FnttTrmAforo.findByMinutosLong", query = "SELECT f FROM FnttTrmAforo f WHERE f.minutosLong = :minutosLong"),
    @NamedQuery(name = "FnttTrmAforo.findBySegundosLong", query = "SELECT f FROM FnttTrmAforo f WHERE f.segundosLong = :segundosLong"),
    @NamedQuery(name = "FnttTrmAforo.findByFecha", query = "SELECT f FROM FnttTrmAforo f WHERE f.fecha = :fecha")})
public class FnttTrmAforo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "CAUDAL")
    private Double caudal;
    @Column(name = "GRADOS_LAT")
    private int gradosLat;
    @Column(name = "MINUTOS_LAT")
    private int minutosLat;
    @Column(name = "SEGUNDOS_LAT")
    private BigDecimal segundosLat;
    @Column(name = "GRADOS_LONG")
    private int gradosLong;
    @Column(name = "MINUTOS_LONG")
    private int minutosLong;
    @Column(name = "SEGUNDOS_LONG")
    private BigDecimal segundosLong;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "TRAMO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FnttTramo tramoId;

    public FnttTrmAforo() {
    }

  

   

    public Double getCaudal() {
        return caudal;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public int getGradosLat() {
        return gradosLat;
    }

    public void setGradosLat(int gradosLat) {
        this.gradosLat = gradosLat;
    }

    public int getMinutosLat() {
        return minutosLat;
    }

    public void setMinutosLat(int minutosLat) {
        this.minutosLat = minutosLat;
    }

    public BigDecimal getSegundosLat() {
        return segundosLat;
    }

    public void setSegundosLat(BigDecimal segundosLat) {
        this.segundosLat = segundosLat;
    }

    public int getGradosLong() {
        return gradosLong;
    }

    public void setGradosLong(int gradosLong) {
        this.gradosLong = gradosLong;
    }

    public int getMinutosLong() {
        return minutosLong;
    }

    public void setMinutosLong(int minutosLong) {
        this.minutosLong = minutosLong;
    }

    public BigDecimal getSegundosLong() {
        return segundosLong;
    }

    public void setSegundosLong(BigDecimal segundosLong) {
        this.segundosLong = segundosLong;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public FnttTramo getTramoId() {
        return tramoId;
    }

    public void setTramoId(FnttTramo tramoId) {
        this.tramoId = tramoId;
    }


  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
