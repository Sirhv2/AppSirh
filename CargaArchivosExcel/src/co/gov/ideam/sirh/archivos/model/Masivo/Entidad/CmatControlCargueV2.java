/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;
import java.io.Serializable;
import java.math.*;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Autoridades;
/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_CONTROL_CARGUE_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatControlCargueV2.findAllV2", query = "SELECT c FROM CmatControlCargueV2 c"),
    @NamedQuery(name = "CmatControlCargueV2.findByIdV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.id = :id"),
    @NamedQuery(name = "CmatControlCargueV2.findByFechaCargueV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.fechaCargue = :fechaCargue"),
    @NamedQuery(name = "CmatControlCargueV2.findByOrigenCargueV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.origenCargue = :origenCargue"),
    @NamedQuery(name = "CmatControlCargueV2.findByIdUsuarioCargueV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.idUsuarioCargue = :idUsuarioCargue"),
    @NamedQuery(name = "CmatControlCargueV2.findByEstadoCargueV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.estadoCargue = :estadoCargue"),
    @NamedQuery(name = "CmatControlCargueV2.findByFechaEstadoV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.fechaEstado = :fechaEstado"),
    @NamedQuery(name = "CmatControlCargueV2.findByIdUsuarioEstadoV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.idUsuarioEstado = :idUsuarioEstado"),
    @NamedQuery(name = "CmatControlCargueV2.findByCodigoRechazoV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatControlCargueV2.findByIdAutoridadV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.idAutoridad = :idAutoridad"),
    @NamedQuery(name = "CmatControlCargueV2.findByCantidadRegistrosV2", query = "SELECT c FROM CmatControlCargueV2 c WHERE c.cantidadRegistros = :cantidadRegistros")})
public class CmatControlCargueV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private double id;
    @Column(name = "FECHA_CARGUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCargue;
    @Column(name = "ORIGEN_CARGUE")
    private Character origenCargue;
    @Column(name = "ID_USUARIO_CARGUE")
    private Long idUsuarioCargue;
    @Column(name = "ESTADO_CARGUE")
    private Character estadoCargue;
    @Column(name = "FECHA_ESTADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstado;
    @Column(name = "ID_USUARIO_ESTADO")
    private Long idUsuarioEstado;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    @Column(name = "CANTIDAD_REGISTROS")
    private Long cantidadRegistros;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPuntosMonitoreoV2> cmatPuntosMonitoreoV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatSubteMedicionCalV2> cmatSubteMedicionCalV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasManantialV2> cmatFuniasManantialV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatMedicionesV2> cmatMedicionesV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaJuridicaCaV2> cmatPersonaJuridicaCaV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatSubteRegCuerpoAguaV2> cmatSubteRegCuerpoAguaV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaNaturalPvV2> cmatPersonaNaturalPvV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasPersNatCaV2> cmatFuniasPersNatCaV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasMedicionNvlsV2> cmatFuniasMedicionNvlsV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaNaturalCaV2> cmatPersonaNaturalCaV2Collection;
    @JoinColumn(name = "ID_AUTORIDAD", referencedColumnName = "ID")
    @ManyToOne
    private Autoridades idAutoridad;
    @JoinColumn(name = "ID_TIPO_PLANTILLA", referencedColumnName = "ID")
    @ManyToOne
    private Lista idTipoPlantilla;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasDiagSanitarioV2> cmatFuniasDiagSanitarioV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasTopografiaGeoV2> cmatFuniasTopografiaGeoV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasConstruccionV2> cmatFuniasConstruccionV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatPersonaJuridicaPvV2> cmatPersonaJuridicaPvV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatRegistroFuentesV2> cmatRegistroFuentesV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasGeofisicaV2> cmatFuniasGeofisicaV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasPersJurCaV2> cmatFuniasPersJurCaV2Collection;
    @OneToMany(mappedBy = "idControlCargue")
    private Collection<CmatFuniasExplotacionV2> cmatFuniasExplotacionV2Collection;

    public CmatControlCargueV2() {
    }

    public CmatControlCargueV2(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Date getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public Character getOrigenCargue() {
        return origenCargue;
    }

    public void setOrigenCargue(Character origenCargue) {
        this.origenCargue = origenCargue;
    }

    public Long getIdUsuarioCargue() {
        return idUsuarioCargue;
    }

    public void setIdUsuarioCargue(Long idUsuarioCargue) {
        this.idUsuarioCargue = idUsuarioCargue;
    }

    public Character getEstadoCargue() {
        return estadoCargue;
    }

    public void setEstadoCargue(Character estadoCargue) {
        this.estadoCargue = estadoCargue;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Long getIdUsuarioEstado() {
        return idUsuarioEstado;
    }

    public void setIdUsuarioEstado(Long idUsuarioEstado) {
        this.idUsuarioEstado = idUsuarioEstado;
    }

    public Long getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(Long codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public Long getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Long cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    @XmlTransient
    public Collection<CmatPuntosMonitoreoV2> getCmatPuntosMonitoreoV2Collection() {
        return cmatPuntosMonitoreoV2Collection;
    }

    public void setCmatPuntosMonitoreoV2Collection(Collection<CmatPuntosMonitoreoV2> cmatPuntosMonitoreoV2Collection) {
        this.cmatPuntosMonitoreoV2Collection = cmatPuntosMonitoreoV2Collection;
    }

    @XmlTransient
    public Collection<CmatSubteMedicionCalV2> getCmatSubteMedicionCalV2Collection() {
        return cmatSubteMedicionCalV2Collection;
    }

    public void setCmatSubteMedicionCalV2Collection(Collection<CmatSubteMedicionCalV2> cmatSubteMedicionCalV2Collection) {
        this.cmatSubteMedicionCalV2Collection = cmatSubteMedicionCalV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasManantialV2> getCmatFuniasManantialV2Collection() {
        return cmatFuniasManantialV2Collection;
    }

    public void setCmatFuniasManantialV2Collection(Collection<CmatFuniasManantialV2> cmatFuniasManantialV2Collection) {
        this.cmatFuniasManantialV2Collection = cmatFuniasManantialV2Collection;
    }

    @XmlTransient
    public Collection<CmatMedicionesV2> getCmatMedicionesV2Collection() {
        return cmatMedicionesV2Collection;
    }

    public void setCmatMedicionesV2Collection(Collection<CmatMedicionesV2> cmatMedicionesV2Collection) {
        this.cmatMedicionesV2Collection = cmatMedicionesV2Collection;
    }

    @XmlTransient
    public Collection<CmatPersonaJuridicaCaV2> getCmatPersonaJuridicaCaV2Collection() {
        return cmatPersonaJuridicaCaV2Collection;
    }

    public void setCmatPersonaJuridicaCaV2Collection(Collection<CmatPersonaJuridicaCaV2> cmatPersonaJuridicaCaV2Collection) {
        this.cmatPersonaJuridicaCaV2Collection = cmatPersonaJuridicaCaV2Collection;
    }

    @XmlTransient
    public Collection<CmatSubteRegCuerpoAguaV2> getCmatSubteRegCuerpoAguaV2Collection() {
        return cmatSubteRegCuerpoAguaV2Collection;
    }

    public void setCmatSubteRegCuerpoAguaV2Collection(Collection<CmatSubteRegCuerpoAguaV2> cmatSubteRegCuerpoAguaV2Collection) {
        this.cmatSubteRegCuerpoAguaV2Collection = cmatSubteRegCuerpoAguaV2Collection;
    }

    @XmlTransient
    public Collection<CmatPersonaNaturalPvV2> getCmatPersonaNaturalPvV2Collection() {
        return cmatPersonaNaturalPvV2Collection;
    }

    public void setCmatPersonaNaturalPvV2Collection(Collection<CmatPersonaNaturalPvV2> cmatPersonaNaturalPvV2Collection) {
        this.cmatPersonaNaturalPvV2Collection = cmatPersonaNaturalPvV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasPersNatCaV2> getCmatFuniasPersNatCaV2Collection() {
        return cmatFuniasPersNatCaV2Collection;
    }

    public void setCmatFuniasPersNatCaV2Collection(Collection<CmatFuniasPersNatCaV2> cmatFuniasPersNatCaV2Collection) {
        this.cmatFuniasPersNatCaV2Collection = cmatFuniasPersNatCaV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasMedicionNvlsV2> getCmatFuniasMedicionNvlsV2Collection() {
        return cmatFuniasMedicionNvlsV2Collection;
    }

    public void setCmatFuniasMedicionNvlsV2Collection(Collection<CmatFuniasMedicionNvlsV2> cmatFuniasMedicionNvlsV2Collection) {
        this.cmatFuniasMedicionNvlsV2Collection = cmatFuniasMedicionNvlsV2Collection;
    }

    @XmlTransient
    public Collection<CmatPersonaNaturalCaV2> getCmatPersonaNaturalCaV2Collection() {
        return cmatPersonaNaturalCaV2Collection;
    }

    public void setCmatPersonaNaturalCaV2Collection(Collection<CmatPersonaNaturalCaV2> cmatPersonaNaturalCaV2Collection) {
        this.cmatPersonaNaturalCaV2Collection = cmatPersonaNaturalCaV2Collection;
    }

   

    public Lista getIdTipoPlantilla() {
        return idTipoPlantilla;
    }

    public void setIdTipoPlantilla(Lista idTipoPlantilla) {
        this.idTipoPlantilla = idTipoPlantilla;
    }

    @XmlTransient
    public Collection<CmatFuniasDiagSanitarioV2> getCmatFuniasDiagSanitarioV2Collection() {
        return cmatFuniasDiagSanitarioV2Collection;
    }

    public void setCmatFuniasDiagSanitarioV2Collection(Collection<CmatFuniasDiagSanitarioV2> cmatFuniasDiagSanitarioV2Collection) {
        this.cmatFuniasDiagSanitarioV2Collection = cmatFuniasDiagSanitarioV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasTopografiaGeoV2> getCmatFuniasTopografiaGeoV2Collection() {
        return cmatFuniasTopografiaGeoV2Collection;
    }

    public void setCmatFuniasTopografiaGeoV2Collection(Collection<CmatFuniasTopografiaGeoV2> cmatFuniasTopografiaGeoV2Collection) {
        this.cmatFuniasTopografiaGeoV2Collection = cmatFuniasTopografiaGeoV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasConstruccionV2> getCmatFuniasConstruccionV2Collection() {
        return cmatFuniasConstruccionV2Collection;
    }

    public void setCmatFuniasConstruccionV2Collection(Collection<CmatFuniasConstruccionV2> cmatFuniasConstruccionV2Collection) {
        this.cmatFuniasConstruccionV2Collection = cmatFuniasConstruccionV2Collection;
    }

    @XmlTransient
    public Collection<CmatPersonaJuridicaPvV2> getCmatPersonaJuridicaPvV2Collection() {
        return cmatPersonaJuridicaPvV2Collection;
    }

    public void setCmatPersonaJuridicaPvV2Collection(Collection<CmatPersonaJuridicaPvV2> cmatPersonaJuridicaPvV2Collection) {
        this.cmatPersonaJuridicaPvV2Collection = cmatPersonaJuridicaPvV2Collection;
    }

    @XmlTransient
    public Collection<CmatRegistroFuentesV2> getCmatRegistroFuentesV2Collection() {
        return cmatRegistroFuentesV2Collection;
    }

    public void setCmatRegistroFuentesV2Collection(Collection<CmatRegistroFuentesV2> cmatRegistroFuentesV2Collection) {
        this.cmatRegistroFuentesV2Collection = cmatRegistroFuentesV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasGeofisicaV2> getCmatFuniasGeofisicaV2Collection() {
        return cmatFuniasGeofisicaV2Collection;
    }

    public void setCmatFuniasGeofisicaV2Collection(Collection<CmatFuniasGeofisicaV2> cmatFuniasGeofisicaV2Collection) {
        this.cmatFuniasGeofisicaV2Collection = cmatFuniasGeofisicaV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasPersJurCaV2> getCmatFuniasPersJurCaV2Collection() {
        return cmatFuniasPersJurCaV2Collection;
    }

    public void setCmatFuniasPersJurCaV2Collection(Collection<CmatFuniasPersJurCaV2> cmatFuniasPersJurCaV2Collection) {
        this.cmatFuniasPersJurCaV2Collection = cmatFuniasPersJurCaV2Collection;
    }

    @XmlTransient
    public Collection<CmatFuniasExplotacionV2> getCmatFuniasExplotacionV2Collection() {
        return cmatFuniasExplotacionV2Collection;
    }

    public void setCmatFuniasExplotacionV2Collection(Collection<CmatFuniasExplotacionV2> cmatFuniasExplotacionV2Collection) {
        this.cmatFuniasExplotacionV2Collection = cmatFuniasExplotacionV2Collection;
    }



    @Override
    public String toString() {
        return "Entidades.CmatControlCargueV2[ id=" + id + " ]";
    }

    public void setIdAutoridad(Autoridades idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Autoridades getIdAutoridad() {
        return idAutoridad;
    }
}
