
package co.gov.ideam.sirh.red.ideam.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "fq_muestras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FqMuestras.findAll", query = "SELECT f FROM FqMuestras f"),
    @NamedQuery(name = "FqMuestras.findByCodigoMuestra", query = "SELECT f FROM FqMuestras f WHERE f.codigoMuestra = :codigoMuestra"),
    @NamedQuery(name = "FqMuestras.findByProemProyIdProyecto", query = "SELECT f FROM FqMuestras f WHERE f.proemProyIdProyecto = :proemProyIdProyecto"),
    @NamedQuery(name = "FqMuestras.findByProemEmprNumeroIdenti", query = "SELECT f FROM FqMuestras f WHERE f.proemEmprNumeroIdenti = :proemEmprNumeroIdenti"),
    @NamedQuery(name = "FqMuestras.findByProemEmprConsecEmpresa", query = "SELECT f FROM FqMuestras f WHERE f.proemEmprConsecEmpresa = :proemEmprConsecEmpresa"),
    @NamedQuery(name = "FqMuestras.findBySucaCateCodigoCategoria", query = "SELECT f FROM FqMuestras f WHERE f.sucaCateCodigoCategoria = :sucaCateCodigoCategoria"),
    @NamedQuery(name = "FqMuestras.findBySucaCodigoSubcategoria", query = "SELECT f FROM FqMuestras f WHERE f.sucaCodigoSubcategoria = :sucaCodigoSubcategoria"),
    @NamedQuery(name = "FqMuestras.findByDeteConsecutivoTematica", query = "SELECT f FROM FqMuestras f WHERE f.deteConsecutivoTematica = :deteConsecutivoTematica"),
    @NamedQuery(name = "FqMuestras.findByDeteCodigoDetalleTematica", query = "SELECT f FROM FqMuestras f WHERE f.deteCodigoDetalleTematica = :deteCodigoDetalleTematica"),
    @NamedQuery(name = "FqMuestras.findByLaboPuntoMonitoreoId", query = "SELECT f FROM FqMuestras f WHERE f.laboPuntoMonitoreoId = :laboPuntoMonitoreoId"),
    @NamedQuery(name = "FqMuestras.findByLaboConsecutivoLaboratrio", query = "SELECT f FROM FqMuestras f WHERE f.laboConsecutivoLaboratrio = :laboConsecutivoLaboratrio"),
    @NamedQuery(name = "FqMuestras.findByLaboIdLaboratorio", query = "SELECT f FROM FqMuestras f WHERE f.laboIdLaboratorio = :laboIdLaboratorio"),
    @NamedQuery(name = "FqMuestras.findByNumAlicuotas", query = "SELECT f FROM FqMuestras f WHERE f.numAlicuotas = :numAlicuotas"),
    @NamedQuery(name = "FqMuestras.findByFechaMuestreo", query = "SELECT f FROM FqMuestras f WHERE f.fechaMuestreo = :fechaMuestreo"),
    @NamedQuery(name = "FqMuestras.findByObservaciones", query = "SELECT f FROM FqMuestras f WHERE f.observaciones = :observaciones"),
    @NamedQuery(name = "FqMuestras.findByFechaEnvio", query = "SELECT f FROM FqMuestras f WHERE f.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "FqMuestras.findByFechaRecibo", query = "SELECT f FROM FqMuestras f WHERE f.fechaRecibo = :fechaRecibo"),
    @NamedQuery(name = "FqMuestras.findByPuntoMonitoreoId", query = "SELECT f FROM FqMuestras f WHERE f.puntoMonitoreoId = :puntoMonitoreoId"),
    @NamedQuery(name = "FqMuestras.findByEstacionId", query = "SELECT f FROM FqMuestras f WHERE f.estacionId = :estacionId Order By f.fechaMuestreo"),
    @NamedQuery(name = "FqMuestras.findByFechaCreacion", query = "SELECT f FROM FqMuestras f WHERE f.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "FqMuestras.findByFechaModificacion", query = "SELECT f FROM FqMuestras f WHERE f.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "FqMuestras.findByCreadoPor", query = "SELECT f FROM FqMuestras f WHERE f.creadoPor = :creadoPor"),
    @NamedQuery(name = "FqMuestras.findByModificadoPor", query = "SELECT f FROM FqMuestras f WHERE f.modificadoPor = :modificadoPor")
    })
public class FqMuestras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo__muestra", nullable = false)
    private Long codigoMuestra;
    @Column(name = "proem_proy_id_proyecto", nullable = false)
    private String proemProyIdProyecto;
    @Column(name = "proem_empr_numero_identi", nullable = false)
    private String proemEmprNumeroIdenti;
    @Column(name = "proem_empr_consec_empresa", nullable = false)
    private Integer proemEmprConsecEmpresa;
    @Column(name = "suca_cate_codigo_categoria", nullable = false)
    private Integer sucaCateCodigoCategoria;
    @Column(name = "suca_codigo_subcategoria", nullable = false)
    private Integer sucaCodigoSubcategoria;
    @Basic(optional = false)
    @Column(name = "dete_consecutivo_tematica", nullable = false)
    private String deteConsecutivoTematica;
    @Column(name = "dete_codigo_detalle_tematica", nullable = false)
    private String deteCodigoDetalleTematica;
    @Column(name = "labo_punto_monitoreo_id", nullable = false)
    private String laboPuntoMonitoreoId;
    @Column(name = "labo_consecutivo_laboratrio", nullable = false)
    private Integer laboConsecutivoLaboratrio;
    @Column(name = "labo_id_laboratorio", nullable = false)
    private String laboIdLaboratorio;
    @Column(name = "num_alicuotas", nullable = false)
    private Integer numAlicuotas;
    @Column(name = "fecha_muestreo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaMuestreo;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha_envio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "fecha_recibo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRecibo;
    @Column(name = "punto_monitoreo_id", nullable = false)
    private Long puntoMonitoreoId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "estacion_id")
    private Long estacionId;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "creado_por")
    private String creadoPor;
    @Column(name = "modificado_por")
    private String modificadoPor;

    public FqMuestras() {
    }

    public FqMuestras(Long codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public FqMuestras(Long codigoMuestra, String proemProyIdProyecto, String proemEmprNumeroIdenti, Integer proemEmprConsecEmpresa, Integer sucaCateCodigoCategoria, Integer sucaCodigoSubcategoria, String deteConsecutivoTematica, String deteCodigoDetalleTematica, String laboPuntoMonitoreoId, Integer laboConsecutivoLaboratrio, String laboIdLaboratorio, Integer numAlicuotas, Date fechaMuestreo, Date fechaEnvio, Date fechaRecibo, Long puntoMonitoreoId) {
        this.codigoMuestra = codigoMuestra;
        this.proemProyIdProyecto = proemProyIdProyecto;
        this.proemEmprNumeroIdenti = proemEmprNumeroIdenti;
        this.proemEmprConsecEmpresa = proemEmprConsecEmpresa;
        this.sucaCateCodigoCategoria = sucaCateCodigoCategoria;
        this.sucaCodigoSubcategoria = sucaCodigoSubcategoria;
        this.deteConsecutivoTematica = deteConsecutivoTematica;
        this.deteCodigoDetalleTematica = deteCodigoDetalleTematica;
        this.laboPuntoMonitoreoId = laboPuntoMonitoreoId;
        this.laboConsecutivoLaboratrio = laboConsecutivoLaboratrio;
        this.laboIdLaboratorio = laboIdLaboratorio;
        this.numAlicuotas = numAlicuotas;
        this.fechaMuestreo = fechaMuestreo;
        this.fechaEnvio = fechaEnvio;
        this.fechaRecibo = fechaRecibo;
        this.puntoMonitoreoId = puntoMonitoreoId;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMuestra != null ? codigoMuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FqMuestras)) {
            return false;
        }
        FqMuestras other = (FqMuestras) object;
        if ((this.codigoMuestra == null && other.codigoMuestra != null) || (this.codigoMuestra != null && !this.codigoMuestra.equals(other.codigoMuestra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.red.ideam.model.FqMuestras[ codigoMuestra=" + codigoMuestra + " ]";
    }

    public void setCodigoMuestra(Long codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public Long getCodigoMuestra() {
        return codigoMuestra;
    }

    public void setProemProyIdProyecto(String proemProyIdProyecto) {
        this.proemProyIdProyecto = proemProyIdProyecto;
    }

    public String getProemProyIdProyecto() {
        return proemProyIdProyecto;
    }

    public void setProemEmprNumeroIdenti(String proemEmprNumeroIdenti) {
        this.proemEmprNumeroIdenti = proemEmprNumeroIdenti;
    }

    public String getProemEmprNumeroIdenti() {
        return proemEmprNumeroIdenti;
    }

    public void setProemEmprConsecEmpresa(Integer proemEmprConsecEmpresa) {
        this.proemEmprConsecEmpresa = proemEmprConsecEmpresa;
    }

    public Integer getProemEmprConsecEmpresa() {
        return proemEmprConsecEmpresa;
    }

    public void setSucaCateCodigoCategoria(Integer sucaCateCodigoCategoria) {
        this.sucaCateCodigoCategoria = sucaCateCodigoCategoria;
    }

    public Integer getSucaCateCodigoCategoria() {
        return sucaCateCodigoCategoria;
    }

    public void setSucaCodigoSubcategoria(Integer sucaCodigoSubcategoria) {
        this.sucaCodigoSubcategoria = sucaCodigoSubcategoria;
    }

    public Integer getSucaCodigoSubcategoria() {
        return sucaCodigoSubcategoria;
    }

    public void setDeteConsecutivoTematica(String deteConsecutivoTematica) {
        this.deteConsecutivoTematica = deteConsecutivoTematica;
    }

    public String getDeteConsecutivoTematica() {
        return deteConsecutivoTematica;
    }

    public void setDeteCodigoDetalleTematica(String deteCodigoDetalleTematica) {
        this.deteCodigoDetalleTematica = deteCodigoDetalleTematica;
    }

    public String getDeteCodigoDetalleTematica() {
        return deteCodigoDetalleTematica;
    }

    public void setLaboPuntoMonitoreoId(String laboPuntoMonitoreoId) {
        this.laboPuntoMonitoreoId = laboPuntoMonitoreoId;
    }

    public String getLaboPuntoMonitoreoId() {
        return laboPuntoMonitoreoId;
    }

    public void setLaboConsecutivoLaboratrio(Integer laboConsecutivoLaboratrio) {
        this.laboConsecutivoLaboratrio = laboConsecutivoLaboratrio;
    }

    public Integer getLaboConsecutivoLaboratrio() {
        return laboConsecutivoLaboratrio;
    }

    public void setLaboIdLaboratorio(String laboIdLaboratorio) {
        this.laboIdLaboratorio = laboIdLaboratorio;
    }

    public String getLaboIdLaboratorio() {
        return laboIdLaboratorio;
    }

    public void setNumAlicuotas(Integer numAlicuotas) {
        this.numAlicuotas = numAlicuotas;
    }

    public Integer getNumAlicuotas() {
        return numAlicuotas;
    }

    public void setFechaMuestreo(Date fechaMuestreo) {
        this.fechaMuestreo = fechaMuestreo;
    }

    public Date getFechaMuestreo() {
        return fechaMuestreo;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public Date getFechaRecibo() {
        return fechaRecibo;
    }

    public void setPuntoMonitoreoId(Long puntoMonitoreoId) {
        this.puntoMonitoreoId = puntoMonitoreoId;
    }

    public Long getPuntoMonitoreoId() {
        return puntoMonitoreoId;
    }

    public void setEstacionId(Long estacionId) {
        this.estacionId = estacionId;
    }

    public Long getEstacionId() {
        return estacionId;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }
}
