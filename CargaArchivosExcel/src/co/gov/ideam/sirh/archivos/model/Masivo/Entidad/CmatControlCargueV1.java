/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;

import java.io.Serializable;
import java.math.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
    import co.gov.ideam.sirh.parametros.model.Lista;
    import co.gov.ideam.sirh.parametros.model.Autoridades;

/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_CONTROL_CARGUE_V1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatControlCargueV1.findAllV1", query = "SELECT c FROM CmatControlCargueV1 c"),
    @NamedQuery(name = "CmatControlCargueV1.findByIdV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.id = :id"),
    @NamedQuery(name = "CmatControlCargueV1.findByFechaCargueV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.fechaCargue = :fechaCargue"),
    @NamedQuery(name = "CmatControlCargueV1.findByOrigenCargueV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.origenCargue = :origenCargue"),
    @NamedQuery(name = "CmatControlCargueV1.findByIdUsuarioCargueV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.idUsuarioCargue = :idUsuarioCargue"),
    @NamedQuery(name = "CmatControlCargueV1.findByEstadoCargueV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.estadoCargue = :estadoCargue"),
    @NamedQuery(name = "CmatControlCargueV1.findByFechaEstadoV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "CmatControlCargueV1.findByIdUsuarioEstadoV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.idUsuarioEstado = :idUsuarioEstado"),
    @NamedQuery(name = "CmatControlCargueV1.findByCodigoRechazoV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatControlCargueV1.findByCantidadRegistrosV1", query = "SELECT c FROM CmatControlCargueV1 c WHERE c.cantidadRegistros = :cantidadRegistros")})
public class CmatControlCargueV1 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Double id;
    @Column(name = "FECHA_CARGUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCargue;
    @Column(name = "ORIGEN_CARGUE")
    private Character origenCargue;
    @Column(name = "ID_USUARIO_CARGUE")
    private BigInteger idUsuarioCargue;
    @Column(name = "ESTADO_CARGUE")
    private Character estadoCargue;
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Column(name = "ID_USUARIO_ESTADO")
    private BigInteger idUsuarioEstado;
    @Column(name = "CODIGO_RECHAZO")
    private BigInteger codigoRechazo;
    @Column(name = "CANTIDAD_REGISTROS")
    private BigInteger cantidadRegistros;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaNaturalPvV1> cmatPersonaNaturalPvV1Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaJuridicaCaV1> cmatPersonaJuridicaCaV1Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaNaturalCaV1> cmatPersonaNaturalCaV1Collection;
    @JoinColumn(name = "ID_AUTORIDAD", referencedColumnName = "ID")
    @ManyToOne
    private Autoridades idAutoridad;
    @JoinColumn(name = "ID_TIPO_PLANTILLA", referencedColumnName = "ID")
    @ManyToOne
    private Lista idTipoPlantilla;

    public CmatControlCargueV1() {
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
        if (!(object instanceof CmatControlCargueV1)) {
            return false;
        }
        CmatControlCargueV1 other = (CmatControlCargueV1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CmatControlCargueV1[ id=" + id + " ]";
    }

    public void setId(double id) {
        this.id = id;
    }

    public Double getId() {
        return id;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public Date getFechaCargue() {
        return fechaCargue;
    }

    public void setOrigenCargue(Character origenCargue) {
        this.origenCargue = origenCargue;
    }

    public Character getOrigenCargue() {
        return origenCargue;
    }

    public void setIdUsuarioCargue(BigInteger idUsuarioCargue) {
        this.idUsuarioCargue = idUsuarioCargue;
    }

    public BigInteger getIdUsuarioCargue() {
        return idUsuarioCargue;
    }

    public void setEstadoCargue(Character estadoCargue) {
        this.estadoCargue = estadoCargue;
    }

    public Character getEstadoCargue() {
        return estadoCargue;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setIdUsuarioEstado(BigInteger idUsuarioEstado) {
        this.idUsuarioEstado = idUsuarioEstado;
    }

    public BigInteger getIdUsuarioEstado() {
        return idUsuarioEstado;
    }

    public void setCodigoRechazo(BigInteger codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public BigInteger getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCantidadRegistros(BigInteger cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public BigInteger getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCmatPersonaNaturalPvV1Collection(Collection<CmatPersonaNaturalPvV1> cmatPersonaNaturalPvV1Collection) {
        this.cmatPersonaNaturalPvV1Collection = cmatPersonaNaturalPvV1Collection;
    }

    public Collection<CmatPersonaNaturalPvV1> getCmatPersonaNaturalPvV1Collection() {
        return cmatPersonaNaturalPvV1Collection;
    }

    public void setCmatPersonaJuridicaCaV1Collection(Collection<CmatPersonaJuridicaCaV1> cmatPersonaJuridicaCaV1Collection) {
        this.cmatPersonaJuridicaCaV1Collection = cmatPersonaJuridicaCaV1Collection;
    }

    public Collection<CmatPersonaJuridicaCaV1> getCmatPersonaJuridicaCaV1Collection() {
        return cmatPersonaJuridicaCaV1Collection;
    }

    public void setCmatPersonaNaturalCaV1Collection(Collection<CmatPersonaNaturalCaV1> cmatPersonaNaturalCaV1Collection) {
        this.cmatPersonaNaturalCaV1Collection = cmatPersonaNaturalCaV1Collection;
    }

    public Collection<CmatPersonaNaturalCaV1> getCmatPersonaNaturalCaV1Collection() {
        return cmatPersonaNaturalCaV1Collection;
    }

    public void setIdAutoridad(Autoridades idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Autoridades getIdAutoridad() {
        return idAutoridad;
    }

    public void setIdTipoPlantilla(Lista idTipoPlantilla) {
        this.idTipoPlantilla = idTipoPlantilla;
    }

    public Lista getIdTipoPlantilla() {
        return idTipoPlantilla;
    }
}
