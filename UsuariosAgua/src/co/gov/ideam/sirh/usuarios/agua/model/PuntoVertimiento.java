package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;

import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;


@Entity
@Table(name = "rurt_punto_vertimiento")
@NamedQueries({
    @NamedQuery(name = "PuntoVertimiento.findAll", query = "SELECT p FROM PuntoVertimiento p"),
    @NamedQuery(name = "PuntoVertimiento.findByTipoVertimiento", query = "SELECT p FROM PuntoVertimiento p WHERE p.tipoVertimiento = :tipoVertimiento"),
    @NamedQuery(name = "PuntoVertimiento.findByTipoFlujo", query = "SELECT p FROM PuntoVertimiento p WHERE p.tipoFlujo = :tipoFlujo"),
    @NamedQuery(name = "PuntoVertimiento.findByTiempoDescarga", query = "SELECT p FROM PuntoVertimiento p WHERE p.tiempoDescarga = :tiempoDescarga"),
    @NamedQuery(name = "PuntoVertimiento.findByFrecuencia", query = "SELECT p FROM PuntoVertimiento p WHERE p.frecuencia = :frecuencia"),
    @NamedQuery(name = "PuntoVertimiento.findByCaudalDisegno", query = "SELECT p FROM PuntoVertimiento p WHERE p.caudalDisegno = :caudalDisegno"),
    @NamedQuery(name = "PuntoVertimiento.findBySistemaReferencia", query = "SELECT p FROM PuntoVertimiento p WHERE p.sistemaReferencia = :sistemaReferencia"),
    @NamedQuery(name = "PuntoVertimiento.findByDescripcionAcceso", query = "SELECT p FROM PuntoVertimiento p WHERE p.descripcionAcceso = :descripcionAcceso"),
    @NamedQuery(name = "PuntoVertimiento.findById", query = "SELECT p FROM PuntoVertimiento p WHERE p.id = :id"),
    @NamedQuery(name = "PuntoVertimiento.findAllByPermiso", query = "SELECT p FROM PuntoVertimiento p WHERE p.idPermisoVertimiento.codigo = :idPermiso"),
    @NamedQuery(name = "PuntoVertimiento.findByFuente", query = "SELECT p FROM PuntoVertimiento p WHERE p.idFuente.id = :idFuente"),
    @NamedQuery(name = "PuntoVertimiento.findByTramo", query = "SELECT p FROM PuntoVertimiento p WHERE p.idTramo.id = :idTramo")
})
public class PuntoVertimiento implements Serializable {
    @GenericGenerator(name = "vertimientos_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_VERTIMIENTOS")})

    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "vertimientos_generator")
    private Long id;

    @Column(name = "departamento")
    private Integer idDepartamento;
    @Column(name = "municipio")
    private Integer idMunicipio;
    @Column(name = "tipo_centro_poblado")
    private Integer idTipoCentroPoblado;
    @Column(name = "nombre_centro_poblado")
    private String nombreCentroPoblado;
    @Column(name = "tipo_vertimiento", nullable = false)
    private Integer tipoVertimiento;
    @Column(name = "tipo_flujo")
    private Integer tipoFlujo;
    @Column(name = "tiempo_descarga", nullable = false)
    private Double tiempoDescarga;
    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;
    @Column(name = "caudal_disegno", nullable = false)
    private Double caudalDisegno;
    @Column(name = "sistema_referencia", nullable = false)
    private Integer sistemaReferencia;
    @Column(name = "grados_lat", nullable = false)
    private Integer gradosLat;
    @Column(name = "minutos_lat", nullable = false)
    private Integer minutosLat;
    @Column(name = "segundos_lat", nullable = false)
    private Double segundosLat;
    @Column(name = "grados_long", nullable = false)
    private Integer gradosLong;
    @Column(name = "minutos_long", nullable = false)
    private Integer minutosLong;
    @Column(name = "segundos_long", nullable = false)
    private Double segundosLong;
    @Column(name = "altitud", nullable = false)
    private Double altitud;
    @Column(name = "descripcion_acceso", nullable = false)
    private String descripcionAcceso;

    @JoinColumn(name = "id_permiso_vertimiento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PermisoVertimiento idPermisoVertimiento;
    @JoinColumn(name = "id_tramo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FnttTramo idTramo;
    @JoinColumn(name = "id_fuente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FnttFuente idFuente;

    @Transient
    private Long codigoAutoridad;
    @Transient
    private Divipola objDepartamento;
    @Transient
    private Divipola objMunicipio;
    @Transient
    private Lista objTipoCentroPoblado;
    @Transient
    private Lista objTipoVertimiento;
    @Transient
    private Lista objTipoFlujo;
    @Transient
    private Lista objSistemaReferencia;
    @Transient
    private PartZonificListas objArea;
    @Transient
    private PartZonificListas objZona;
    @Transient
    private PartZonificListas objSubzona;
    @Transient
    private List<PuntoVertimientoTratamiento> listaPuntosMonitoreoVert;
    @Transient
    private List<PuntoVertimientoTratamiento> listaPretratamiento;
    @Transient
    private List<PuntoVertimientoTratamiento> listaPrimario;
    @Transient
    private List<PuntoVertimientoTratamiento> listaSecundario;
    @Transient
    private List<PuntoVertimientoTratamiento> listaTerciario;
    @Transient
    private List<PuntoVertimientoTratamiento> listaOtro;
    @Transient
    private Autoridades autoridad;
    @Transient
    private List<PuntoVertimientoTratamiento> listTratamientos;
    @Transient
    private String nombreDpto;
    @Transient
    private String nombreMcpo;
    
    
    @Transient
    private String nombreSistRef;
    

    public PuntoVertimiento() {
    }

    public PuntoVertimiento(Long id) {
        this.id = id;
    }

    

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdTipoCentroPoblado(Integer idTipoCentroPoblado) {
        this.idTipoCentroPoblado = idTipoCentroPoblado;
    }

    public Integer getIdTipoCentroPoblado() {
        return idTipoCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public Integer getTipoVertimiento() {
        return tipoVertimiento;
    }

    public void setTipoVertimiento(Integer tipoVertimiento) {
        this.tipoVertimiento = tipoVertimiento;
    }

    public Integer getTipoFlujo() {
        return tipoFlujo;
    }

    public void setTipoFlujo(Integer tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public Double getTiempoDescarga() {
        return tiempoDescarga;
    }

    public void setTiempoDescarga(Double tiempoDescarga) {
        this.tiempoDescarga = tiempoDescarga;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Double getCaudalDisegno() {
        return caudalDisegno;
    }

    public void setCaudalDisegno(Double caudalDisegno) {
        this.caudalDisegno = caudalDisegno;
    }

    public Integer getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setSistemaReferencia(Integer sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    

    public String getDescripcionAcceso() {
        return descripcionAcceso;
    }

    public void setDescripcionAcceso(String descripcionAcceso) {
        this.descripcionAcceso = descripcionAcceso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermisoVertimiento getIdPermisoVertimiento() {
        return idPermisoVertimiento;
    }

    public void setIdPermisoVertimiento(PermisoVertimiento idPermisoVertimiento) {
        this.idPermisoVertimiento = idPermisoVertimiento;
    }

    public FnttTramo getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(FnttTramo idTramo) {
        this.idTramo = idTramo;
    }

    public FnttFuente getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(FnttFuente idFuente) {
        this.idFuente = idFuente;
    }


    //atributo transient
    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public void setObjDepartamento(Divipola objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

    public Divipola getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjMunicipio(Divipola objMunicipio) {
        this.objMunicipio = objMunicipio;
    }

    public Divipola getObjMunicipio() {
        return objMunicipio;
    }

    public void setObjTipoCentroPoblado(Lista objTipoCentroPoblado) {
        this.objTipoCentroPoblado = objTipoCentroPoblado;
    }

    public Lista getObjTipoCentroPoblado() {
        return objTipoCentroPoblado;
    }

    public void setObjTipoVertimiento(Lista objTipoVertimiento) {
        this.objTipoVertimiento = objTipoVertimiento;
    }

    public Lista getObjTipoVertimiento() {
        return objTipoVertimiento;
    }

    public void setObjTipoFlujo(Lista objTipoFlujo) {
        this.objTipoFlujo = objTipoFlujo;
    }

    public Lista getObjTipoFlujo() {
        return objTipoFlujo;
    }

    public void setObjSistemaReferencia(Lista objSistemaReferencia) {
        this.objSistemaReferencia = objSistemaReferencia;
    }

    public Lista getObjSistemaReferencia() {
        return objSistemaReferencia;
    }

    public void setObjArea(PartZonificListas objArea) {
        this.objArea = objArea;
    }

    public PartZonificListas getObjArea() {
        return objArea;
    }

    public void setObjZona(PartZonificListas objZona) {
        this.objZona = objZona;
    }

    public PartZonificListas getObjZona() {
        return objZona;
    }

    public void setObjSubzona(PartZonificListas objSubzona) {
        this.objSubzona = objSubzona;
    }

    public PartZonificListas getObjSubzona() {
        return objSubzona;
    }

    public void setListaPretratamiento(List<PuntoVertimientoTratamiento> listaPretratamiento) {
        this.listaPretratamiento = listaPretratamiento;
    }

    public List<PuntoVertimientoTratamiento> getListaPretratamiento() {
        return listaPretratamiento;
    }

    public void setListaPrimario(List<PuntoVertimientoTratamiento> listaPrimario) {
        this.listaPrimario = listaPrimario;
    }

    public List<PuntoVertimientoTratamiento> getListaPrimario() {
        return listaPrimario;
    }

    public void setListaSecundario(List<PuntoVertimientoTratamiento> listaSecundario) {
        this.listaSecundario = listaSecundario;
    }

    public List<PuntoVertimientoTratamiento> getListaSecundario() {
        return listaSecundario;
    }

    public void setListaTerciario(List<PuntoVertimientoTratamiento> listaTerciario) {
        this.listaTerciario = listaTerciario;
    }

    public List<PuntoVertimientoTratamiento> getListaTerciario() {
        return listaTerciario;
    }

    public void setListaOtro(List<PuntoVertimientoTratamiento> listaOtro) {
        this.listaOtro = listaOtro;
    }

    public List<PuntoVertimientoTratamiento> getListaOtro() {
        return listaOtro;
    }

  

    public void setSegundosLat(Double segundosLat) {
        this.segundosLat = segundosLat;
    }

    public Double getSegundosLat() {
        return segundosLat;
    }

  

    public void setSegundosLong(Double segundosLong) {
        this.segundosLong = segundosLong;
    }

    public Double getSegundosLong() {
        return segundosLong;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
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
        if (!(object instanceof PuntoVertimiento)) {
            return false;
        }
        PuntoVertimiento other = (PuntoVertimiento) object;
        if ((this.id == null && other.id != null)
            || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento[ id=" + id + " ]";
    }


    public void setListaPuntosMonitoreoVert(List listaPuntosMonitoreoVert) {
        this.listaPuntosMonitoreoVert = listaPuntosMonitoreoVert;
    }

    public List getListaPuntosMonitoreoVert() {
        return listaPuntosMonitoreoVert;
    }


    public void setGradosLat(Integer gradosLat) {
        this.gradosLat = gradosLat;
    }

    public Integer getGradosLat() {
        return gradosLat;
    }

    public void setMinutosLat(Integer minutosLat) {
        this.minutosLat = minutosLat;
    }

    public Integer getMinutosLat() {
        return minutosLat;
    }

    public void setGradosLong(Integer gradosLong) {
        this.gradosLong = gradosLong;
    }

    public Integer getGradosLong() {
        return gradosLong;
    }

    public void setMinutosLong(Integer minutosLong) {
        this.minutosLong = minutosLong;
    }

    public Integer getMinutosLong() {
        return minutosLong;
    }
    
    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }


    public void setListTratamientos(List listTratamientos) {
        this.listTratamientos = listTratamientos;
    }

    public List getListTratamientos() {
        return listTratamientos;
    }

    public void setNombreDpto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    public String getNombreDpto() {
        return nombreDpto;
    }

    public void setNombreMcpo(String nombreMcpo) {
        this.nombreMcpo = nombreMcpo;
    }

    public String getNombreMcpo() {
        return nombreMcpo;
    }

    public void setNombreSistRef(String nombreSistRef) {
        this.nombreSistRef = nombreSistRef;
    }

    public String getNombreSistRef() {
        return nombreSistRef;
    }
}
