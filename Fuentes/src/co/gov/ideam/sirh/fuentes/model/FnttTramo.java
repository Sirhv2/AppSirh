package co.gov.ideam.sirh.fuentes.model;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@Table(name = "fntt_tramo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FnttTramo.findAll", query = "SELECT f FROM FnttTramo f"),
    @NamedQuery(name = "FnttTramo.findByNombre", query = "SELECT f FROM FnttTramo f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FnttTramo.findByDescripcion", query = "SELECT f FROM FnttTramo f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "FnttTramo.findByLongitud", query = "SELECT f FROM FnttTramo f WHERE f.longitud = :longitud"),
    @NamedQuery(name = "FnttTramo.findByTipoFlujo", query = "SELECT f FROM FnttTramo f WHERE f.idTipoFlujo = :idTipoFlujo"),
    @NamedQuery(name = "FnttTramo.findByOfertaHidricaTotal", query = "SELECT f FROM FnttTramo f WHERE f.ofertaHidricaTotal = :ofertaHidricaTotal"),
    @NamedQuery(name = "FnttTramo.findById", query = "SELECT f FROM FnttTramo f WHERE f.id = :id"),
    @NamedQuery(name = "FnttTramo.findByIdFuente", query = "SELECT f FROM FnttTramo f WHERE f.idFuente.id = :id"),
    @NamedQuery(name = "FnttTramo.findByIdWithDivipola", query = "SELECT f FROM FnttTramo f join fetch f.fnttFuenteTramoMunicipioList WHERE f.id = :id"),
    @NamedQuery(name = "FnttTramo.findByNombreAndFuenteAndAutoridad", 
                query = "SELECT f FROM FnttTramo f WHERE upper(f.nombre) = :nombre AND f.idFuente.id = :idFuente AND f.idFuente.codAutoridad.id = :idAutoridad"),
    @NamedQuery(name = "FnttTramo.findByNombreAndFuente", 
                query = "SELECT f FROM FnttTramo f WHERE upper(f.nombre) = :nombre AND f.idFuente.id = :idFuente ")
    }
)
public class FnttTramo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Basic(optional = false)
    @Column(name = "oferta_hidrica_total")
    private BigDecimal ofertaHidricaTotal;
    @Basic(optional = false)
    @Column(name = "grados_pi")
    private int gradosPi;
    @Basic(optional = false)
    @Column(name = "minutos_pi")
    private int minutosPi;
    @Basic(optional = false)
    @Column(name = "segundos_pi")
    private BigDecimal segundosPi;
    @Basic(optional = false)
    @Column(name = "altitud_pi")
    private BigDecimal altitudPi;
    @Basic(optional = false)
    @Column(name = "grados_pf")
    private int gradosPf;
    @Basic(optional = false)
    @Column(name = "minutos_pf")
    private int minutosPf;
    @Basic(optional = false)
    @Column(name = "segundos_pf")
    private BigDecimal segundosPf;
    @Basic(optional = false)
    @Column(name = "altitud_pf")
    private BigDecimal altitudPf;
    
    @Column(name = "grados_long_pi")
    private int gradosLongPi;
    @Column(name = "minutos_long_pi")
    private int minutosLongPi;    
    @Column(name = "segundos_long_pi")
    private BigDecimal segundosLongPi;
    
    @Column(name = "grados_long_pf")
    private int gradosLongPf;
    @Column(name = "minutos_long_pf")
    private int minutosLongPf;    
    @Column(name = "segundos_long_pf")
    private BigDecimal segundosLongPf;
    
    @Column(name = "observacion_tramo")
    private String observacion;    
    
    @Column(name = "ACTO_ADM")
    private String actoAdm;    

    @Column(name = "FECHA_EXP")
    private Date fechaExp;
    
    @Column(name = "VIGENCIA_INI")
        
    private Date vigenciaIni;

    @Column(name = "VIGENCIA_FIN")
    private Date vigenciaFin;

    @Column(name = "ACTO_ADM_U")
    private String actoAdmU;    

    @Column(name = "FECHA_EXP_U")
    private Date fechaExpU;
    
    @Column(name = "VIGENCIA_INI_U")
        
    private Date vigenciaIniU;

    @Column(name = "VIGENCIA_FIN_U")
    private Date vigenciaFinU;

    @Transient
    private List listaPuntosMonitoreo;

    
    
    @GenericGenerator(name = "tramos_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_TRAMOS")
                                    }
                      )
    @Id
    @Column(name = "id", nullable = false)
   // @GeneratedValue(generator = "tramos_generator") 
    private Long id;

    @Transient
    private Lista tipoFlujo;
    
    @Column(name = "tipo_flujo", nullable = false)
    private Integer idTipoFlujo;
    
    
    
    @JoinColumn(name = "sistema_referencia", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Lista sistemaReferencia;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, 
               mappedBy = "fnttTramo")
    private List<FnttFuenteTramoMunicipio> fnttFuenteTramoMunicipioList;
    
    @JoinColumn(name = "id_fuente", referencedColumnName = "id")
    @ManyToOne
    private FnttFuente idFuente;
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private PartZonificListas idArea;
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private PartZonificListas idZona;
    @JoinColumn(name = "id_subzona", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private PartZonificListas idSubzona;
    /*
    @OneToMany(mappedBy = "idTramo")
    private List<RurtCaptacion> rurtCaptacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTramo")
    private List<RurtPuntoVertimiento> rurtPuntoVertimientoList;
    */
    
    @Transient
    private Long codigoAutoridad;

    
    public FnttTramo() {
    }

    public FnttTramo(Long id) {
        this.id = id;
    }

    public FnttTramo(Long id, String nombre, BigDecimal ofertaHidricaTotal, 
                     int gradosPi, int minutosPi, BigDecimal segundosPi, 
                     BigDecimal altitudPi, int gradosPf, int minutosPf, 
                     BigDecimal segundosPf, BigDecimal altitudPf) {
        this.id = id;
        this.nombre = nombre;
        this.ofertaHidricaTotal = ofertaHidricaTotal;
        this.gradosPi = gradosPi;
        this.minutosPi = minutosPi;
        this.segundosPi = segundosPi;
        this.altitudPi = altitudPi;
        this.gradosPf = gradosPf;
        this.minutosPf = minutosPf;
        this.segundosPf = segundosPf;
        this.altitudPf = altitudPf;
    }
    
    //atributo transient
    public Long getCodigoAutoridad() {
        if(this.idFuente!=null){
            codigoAutoridad = this.idFuente.getCodAutoridad().getId().longValue();
        }
        
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    //

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Lista getTipoFlujo() {
        return tipoFlujo;
    }

    public void setTipoFlujo(Lista tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public BigDecimal getOfertaHidricaTotal() {
        return ofertaHidricaTotal;
    }

    public void setOfertaHidricaTotal(BigDecimal ofertaHidricaTotal) {
        this.ofertaHidricaTotal = ofertaHidricaTotal;
    }

    public int getGradosPi() {
        return gradosPi;
    }

    public void setGradosPi(int gradosPi) {
        this.gradosPi = gradosPi;
    }

    public int getMinutosPi() {
        return minutosPi;
    }

    public void setMinutosPi(int minutosPi) {
        this.minutosPi = minutosPi;
    }

    public BigDecimal getSegundosPi() {
        return segundosPi;
    }

    public void setSegundosPi(BigDecimal segundosPi) {
        this.segundosPi = segundosPi;
    }

    public BigDecimal getAltitudPi() {
        return altitudPi;
    }

    public void setAltitudPi(BigDecimal altitudPi) {
        this.altitudPi= altitudPi;
    }

    public int getGradosPf() {
        return gradosPf;
    }

    public void setGradosPf(int gradosPf) {
        this.gradosPf = gradosPf;
    }

    public int getMinutosPf() {
        return minutosPf;
    }

    public void setMinutosPf(int minutosPf) {
        this.minutosPf = minutosPf;
    }

    public BigDecimal getSegundosPf() {
        return segundosPf;
    }

    public void setSegundosPf(BigDecimal segundosPf) {
        this.segundosPf = segundosPf;
    }

    public BigDecimal getAltitudPf() {
        return altitudPf;
    }

    public void setAltitudPf(BigDecimal altitudPf) {
        this.altitudPf = altitudPf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lista getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(Lista sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    @XmlTransient
    public List<FnttFuenteTramoMunicipio> getFnttFuenteTramoMunicipioList() {
        return fnttFuenteTramoMunicipioList;
    }

    public void setFnttFuenteTramoMunicipioList(List<FnttFuenteTramoMunicipio> fnttFuenteTramoMunicipioList) {
        this.fnttFuenteTramoMunicipioList = fnttFuenteTramoMunicipioList;
    }

    public FnttFuente getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(FnttFuente idFuente) {
        this.idFuente = idFuente;
    }
    
    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.FnttTramo[ id=" + id + " ]";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setIdArea(PartZonificListas idArea) {
        this.idArea = idArea;
    }

    public PartZonificListas getIdArea() {
        return idArea;
    }

    public void setIdZona(PartZonificListas idZona) {
        this.idZona = idZona;
    }

    public PartZonificListas getIdZona() {
        return idZona;
    }

    public void setIdSubzona(PartZonificListas idSubzona) {
        this.idSubzona = idSubzona;
    }

    public PartZonificListas getIdSubzona() {
        return idSubzona;
    }
    
    /*
    @XmlTransient
    public List<RurtCaptacion> getRurtCaptacionList() {
        return rurtCaptacionList;
    }

    public void setRurtCaptacionList(List<RurtCaptacion> rurtCaptacionList) {
        this.rurtCaptacionList = rurtCaptacionList;
    }
    
    @XmlTransient
    public List<RurtPuntoVertimiento> getRurtPuntoVertimientoList() {
        return rurtPuntoVertimientoList;
    }

    public void setRurtPuntoVertimientoList(List<RurtPuntoVertimiento> rurtPuntoVertimientoList) {
        this.rurtPuntoVertimientoList = rurtPuntoVertimientoList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FnttTramo)) {
            return false;
        }
        FnttTramo other = (FnttTramo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
        this.listaPuntosMonitoreo = listaPuntosMonitoreo;
    }

    public List getListaPuntosMonitoreo() {
        return listaPuntosMonitoreo;
    }

    public void setGradosLongPi(int gradosLongPi) {
        this.gradosLongPi = gradosLongPi;
    }

    public int getGradosLongPi() {
        return gradosLongPi;
    }

    public void setMinutosLongPi(int minutosLongPi) {
        this.minutosLongPi = minutosLongPi;
    }

    public int getMinutosLongPi() {
        return minutosLongPi;
    }

    public void setSegundosLongPi(BigDecimal segundosLongPi) {
        this.segundosLongPi = segundosLongPi;
    }

    public BigDecimal getSegundosLongPi() {
        return segundosLongPi;
    }

    public void setGradosLongPf(int gradosLongPf) {
        this.gradosLongPf = gradosLongPf;
    }

    public int getGradosLongPf() {
        return gradosLongPf;
    }

    public void setMinutosLongPf(int minutosLongPf) {
        this.minutosLongPf = minutosLongPf;
    }

    public int getMinutosLongPf() {
        return minutosLongPf;
    }

    public void setSegundosLongPf(BigDecimal segundosLongPf) {
        this.segundosLongPf = segundosLongPf;
    }

    public BigDecimal getSegundosLongPf() {
        return segundosLongPf;
    }

    public void setIdTipoFlujo(Integer idTipoFlujo) {
        this.idTipoFlujo = idTipoFlujo;
    }

    public Integer getIdTipoFlujo() {
        return idTipoFlujo;
    }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

    public void setActoAdm(String actoAdm) {
        this.actoAdm = actoAdm;
    }

    public String getActoAdm() {
        return actoAdm;
    }

    public void setFechaExp(Date fechaExp) {
        this.fechaExp = fechaExp;
    }

    public Date getFechaExp() {
        return fechaExp;
    }

    public void setVigenciaIni(Date vigenciaIni) {
        this.vigenciaIni = vigenciaIni;
    }

    public Date getVigenciaIni() {
        return vigenciaIni;
    }

    public void setVigenciaFin(Date vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public Date getVigenciaFin() {
        return vigenciaFin;
    }

    public void setActoAdmU(String actoAdmU) {
        this.actoAdmU = actoAdmU;
    }

    public String getActoAdmU() {
        return actoAdmU;
    }

    public void setFechaExpU(Date fechaExpU) {
        this.fechaExpU = fechaExpU;
    }

    public Date getFechaExpU() {
        return fechaExpU;
    }

    public void setVigenciaIniU(Date vigenciaIniU) {
        this.vigenciaIniU = vigenciaIniU;
    }

    public Date getVigenciaIniU() {
        return vigenciaIniU;
    }

    public void setVigenciaFinU(Date vigenciaFinU) {
        this.vigenciaFinU = vigenciaFinU;
    }

    public Date getVigenciaFinU() {
        return vigenciaFinU;
    }
}
