/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.oferta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cesar
 */
@Entity
@Table(name = "PART_DATOS_IDF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartDatosIdf.findAll", query = "SELECT p FROM PartDatosIdf p"),
    @NamedQuery(name = "PartDatosIdf.findById", query = "SELECT p FROM PartDatosIdf p WHERE p.id = :id"),
    @NamedQuery(name = "PartDatosIdf.findByNombreEs", query = "SELECT p FROM PartDatosIdf p WHERE p.nombreEs = :nombreEs"),
    @NamedQuery(name = "PartDatosIdf.findByLongitud", query = "SELECT p FROM PartDatosIdf p WHERE p.longitud = :longitud"),
    @NamedQuery(name = "PartDatosIdf.findByLatitud", query = "SELECT p FROM PartDatosIdf p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "PartDatosIdf.findByAnios", query = "SELECT p FROM PartDatosIdf p WHERE p.anios = :anios"),
    @NamedQuery(name = "PartDatosIdf.findByTr", query = "SELECT p FROM PartDatosIdf p WHERE p.tr = :tr"),
    @NamedQuery(name = "PartDatosIdf.findByC1", query = "SELECT p FROM PartDatosIdf p WHERE p.c1 = :c1"),
    @NamedQuery(name = "PartDatosIdf.findByX0", query = "SELECT p FROM PartDatosIdf p WHERE p.x0 = :x0"),
    @NamedQuery(name = "PartDatosIdf.findByC2", query = "SELECT p FROM PartDatosIdf p WHERE p.c2 = :c2"),
    @NamedQuery(name = "PartDatosIdf.findByMetodo", query = "SELECT p FROM PartDatosIdf p WHERE p.metodo = :metodo"),
    @NamedQuery(name = "PartDatosIdf.findByDitribucion", query = "SELECT p FROM PartDatosIdf p WHERE p.ditribucion = :ditribucion"),
    @NamedQuery(name = "PartDatosIdf.findByEstacionId", query = "SELECT p FROM PartDatosIdf p WHERE p.estacionId = :estacionId")})
public class PartDatosIdf implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ES")
    private String nombreEs;
    @Basic(optional = false)
    @Column(name = "LONGITUD")
    private String longitud;
    @Basic(optional = false)
    @Column(name = "LATITUD")
    private String latitud;
    @Basic(optional = false)
    @Column(name = "ANIOS")
    private BigInteger anios;
    @Basic(optional = false)
    @Column(name = "TR")
    private BigInteger tr;
    @Basic(optional = false)
    @Column(name = "C1")
    private BigDecimal c1;
    @Basic(optional = false)
    @Column(name = "X0")
    private BigDecimal x0;
    @Basic(optional = false)
    @Column(name = "C2")
    private BigDecimal c2;
    @Basic(optional = false)
    @Column(name = "METODO")
    private String metodo;
    @Basic(optional = false)
    @Column(name = "DITRIBUCION")
    private String ditribucion;
    @Basic(optional = false)
    @Column(name = "ESTACION_ID")
    private BigInteger estacionId;

    public PartDatosIdf() {
    }

    public PartDatosIdf(Long id) {
        this.id = id;
    }

    public PartDatosIdf(Long id, String nombreEs, String longitud, String latitud, BigInteger anios, BigInteger tr, BigDecimal c1, BigDecimal x0, BigDecimal c2, String metodo, String ditribucion, BigInteger estacionId) {
        this.id = id;
        this.nombreEs = nombreEs;
        this.longitud = longitud;
        this.latitud = latitud;
        this.anios = anios;
        this.tr = tr;
        this.c1 = c1;
        this.x0 = x0;
        this.c2 = c2;
        this.metodo = metodo;
        this.ditribucion = ditribucion;
        this.estacionId = estacionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public BigInteger getAnios() {
        return anios;
    }

    public void setAnios(BigInteger anios) {
        this.anios = anios;
    }

    public BigInteger getTr() {
        return tr;
    }

    public void setTr(BigInteger tr) {
        this.tr = tr;
    }

    public BigDecimal getC1() {
        return c1;
    }

    public void setC1(BigDecimal c1) {
        this.c1 = c1;
    }

    public BigDecimal getX0() {
        return x0;
    }

    public void setX0(BigDecimal x0) {
        this.x0 = x0;
    }

    public BigDecimal getC2() {
        return c2;
    }

    public void setC2(BigDecimal c2) {
        this.c2 = c2;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getDitribucion() {
        return ditribucion;
    }

    public void setDitribucion(String ditribucion) {
        this.ditribucion = ditribucion;
    }

    public BigInteger getEstacionId() {
        return estacionId;
    }

    public void setEstacionId(BigInteger estacionId) {
        this.estacionId = estacionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartDatosIdf)) {
            return false;
        }
        PartDatosIdf other = (PartDatosIdf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.oferta.model.PartDatosIdf[ id=" + id + " ]";
    }
    
}
