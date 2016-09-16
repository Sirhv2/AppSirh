package co.gov.ideam.sirh.porh.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries( { @NamedQuery(name =
                             "PorhTramosProhibiciones.findAllByPorhTramo",
                             query =
                             "select o from PorhTramosProhibiciones o where o.tramo_id = :codigoTramo")
    ,
    @NamedQuery(name = "PorhTramosProhibiciones.deleteByPlan", query = "delete from PorhTramosProhibiciones o where o.porhId = :codigoPlan")
    ,
    @NamedQuery(name = "PorhTramosProhibiciones.find", query = "select o from PorhTramosProhibiciones o where o.id = :codigo")
    } )
@Table(name = "porh_tramos_prohibiciones")
public class PorhTramosProhibiciones implements Serializable {
  @Column(name = "fecha_final")
  private Date fecha_final;
  @Column(name = "fecha_inicial", nullable = false)
  private Date fecha_inicial;

  @GenericGenerator(name = "porh_generator",
                    strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                    parameters =
                    { @Parameter(name = "AutoridadAmbientalId", value =
                                 "codigoAutoridad")
        , @Parameter(name = "Codigo", value = "id")
        , @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_porh")
        } )
  @Id
  // @GeneratedValue(generator = "porh_generator")
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "justificacion", nullable = false)
  private String justificacion;
  @Column(name = "sector", nullable = false)
  private String sector;
  @Column(name = "tramo_id", nullable = false)
  private Long tramo_id;
  @Column(name = "plan_id", nullable = false)
  private Long porhId;
  @Transient
  private Long codigoAutoridad;

  @Column(name = "sistemaref_inicio")
  private Integer sistemaRef_inicio;
  @Column(name = "latitud_grados_inicio")
  private Integer latitud_grados_inicio;
  @Column(name = "latitud_minutos_inicio")
  private Integer latitud_minutos_inicio;
  @Column(name = "latitud_segundos_inicio")
  private Double latitud_segundos_inicio;
  @Column(name = "longitud_grados_inicio")
  private Integer longitud_grados_inicio;
  @Column(name = "longitud_minutos_inicio")
  private Integer longitud_minutos_inicio;
  @Column(name = "longitud_segundos_inicio")
  private Double longitud_segundos_inicio;
  @Column(name = "altitud_inicio")
  private Double altitud_inicio;
  @Column(name = "descripcion_acceso_inicio")
  private String descripcion_acceso_inicio;


  @Column(name = "sistemaref_fin")
  private Integer sistemaRef_fin;
  @Column(name = "latitud_grados_fin")
  private Integer latitud_grados_fin;
  @Column(name = "latitud_minutos_fin")
  private Integer latitud_minutos_fin;
  @Column(name = "latitud_segundos_fin")
  private Double latitud_segundos_fin;
  @Column(name = "longitud_grados_fin")
  private Integer longitud_grados_fin;
  @Column(name = "longitud_minutos_fin")
  private Integer longitud_minutos_fin;
  @Column(name = "longitud_segundos_fin")
  private Double longitud_segundos_fin;
  @Column(name = "altitud_fin")
  private Double altitud_fin;
  @Column(name = "descripcion_acceso_fin")
  private String descripcion_acceso_fin;

  @Column(name = "prohibicion")
  private Integer prohibicion;
  @Column(name = "prohibicion_tipo")
  private Integer prohibicion_tipo;
  @Column(name = "longitud_prohibicion")
  private Double longitud_prohibicion;

  @Transient
  private Lista listaSistemaRef_inicio;

  @Transient
  private Lista listaSistemaRef_fin;


  public PorhTramosProhibiciones() {
  }

  public PorhTramosProhibiciones(Date fecha_final, Date fecha_inicial, Long id,
                                 String justificacion, String sector,
                                 Long tramo_id) {
    this.fecha_final = fecha_final;
    this.fecha_inicial = fecha_inicial;
    this.id = id;
    this.justificacion = justificacion;
    this.sector = sector;
    this.setTramo_id(tramo_id);
  }

  public Date getFecha_final() {
    return fecha_final;
  }

  public void setFecha_final(Date fecha_final) {
    this.fecha_final = fecha_final;
  }

  public Date getFecha_inicial() {
    return fecha_inicial;
  }

  public void setFecha_inicial(Date fecha_inicial) {
    this.fecha_inicial = fecha_inicial;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJustificacion() {
    return justificacion;
  }

  public void setJustificacion(String justificacion) {
    this.justificacion = justificacion;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }


  public Long getCodigoAutoridad() {
    return codigoAutoridad;
  }

  public void setCodigoAutoridad(Long codigoAutoridad) {
    this.codigoAutoridad = codigoAutoridad;
  }

  public Long getPorhId() {
    return porhId;
  }

  public void setPorhId(Long porhId) {
    this.porhId = porhId;
  }

  public void setTramo_id(Long tramo_id) {
    this.tramo_id = tramo_id;
  }

  public Long getTramo_id() {
    return tramo_id;
  }

  public void setLatitud_grados_inicio(Integer latitud_grados_inicio) {
    this.latitud_grados_inicio = latitud_grados_inicio;
  }

  public Integer getLatitud_grados_inicio() {
    return latitud_grados_inicio;
  }

  public void setLatitud_minutos_inicio(Integer latitud_minutos_inicio) {
    this.latitud_minutos_inicio = latitud_minutos_inicio;
  }

  public Integer getLatitud_minutos_inicio() {
    return latitud_minutos_inicio;
  }

  public void setLatitud_segundos_inicio(Double latitud_segundos_inicio) {
    this.latitud_segundos_inicio = latitud_segundos_inicio;
  }

  public Double getLatitud_segundos_inicio() {
    return latitud_segundos_inicio;
  }

  public void setLongitud_grados_inicio(Integer longitud_grados_inicio) {
    this.longitud_grados_inicio = longitud_grados_inicio;
  }

  public Integer getLongitud_grados_inicio() {
    return longitud_grados_inicio;
  }

  public void setLongitud_minutos_inicio(Integer longitud_minutos_inicio) {
    this.longitud_minutos_inicio = longitud_minutos_inicio;
  }

  public Integer getLongitud_minutos_inicio() {
    return longitud_minutos_inicio;
  }

  public void setLongitud_segundos_inicio(Double longitud_segundos_inicio) {
    this.longitud_segundos_inicio = longitud_segundos_inicio;
  }

  public Double getLongitud_segundos_inicio() {
    return longitud_segundos_inicio;
  }

  public void setAltitud_inicio(Double altitud_inicio) {
    this.altitud_inicio = altitud_inicio;
  }

  public Double getAltitud_inicio() {
    return altitud_inicio;
  }

  public void setDescripcion_acceso_inicio(String descripcion_acceso_inicio) {
    this.descripcion_acceso_inicio = descripcion_acceso_inicio;
  }

  public String getDescripcion_acceso_inicio() {
    return descripcion_acceso_inicio;
  }

  public void setLatitud_grados_fin(Integer latitud_grados_fin) {
    this.latitud_grados_fin = latitud_grados_fin;
  }

  public Integer getLatitud_grados_fin() {
    return latitud_grados_fin;
  }

  public void setLatitud_minutos_fin(Integer latitud_minutos_fin) {
    this.latitud_minutos_fin = latitud_minutos_fin;
  }

  public Integer getLatitud_minutos_fin() {
    return latitud_minutos_fin;
  }

  public void setLatitud_segundos_fin(Double latitud_segundos_fin) {
    this.latitud_segundos_fin = latitud_segundos_fin;
  }

  public Double getLatitud_segundos_fin() {
    return latitud_segundos_fin;
  }

  public void setLongitud_grados_fin(Integer longitud_grados_fin) {
    this.longitud_grados_fin = longitud_grados_fin;
  }

  public Integer getLongitud_grados_fin() {
    return longitud_grados_fin;
  }

  public void setLongitud_minutos_fin(Integer longitud_minutos_fin) {
    this.longitud_minutos_fin = longitud_minutos_fin;
  }

  public Integer getLongitud_minutos_fin() {
    return longitud_minutos_fin;
  }

  public void setLongitud_segundos_fin(Double longitud_segundos_fin) {
    this.longitud_segundos_fin = longitud_segundos_fin;
  }

  public Double getLongitud_segundos_fin() {
    return longitud_segundos_fin;
  }

  public void setAltitud_fin(Double altitud_fin) {
    this.altitud_fin = altitud_fin;
  }

  public Double getAltitud_fin() {
    return altitud_fin;
  }

  public void setDescripcion_acceso_fin(String descripcion_acceso_fin) {
    this.descripcion_acceso_fin = descripcion_acceso_fin;
  }

  public String getDescripcion_acceso_fin() {
    return descripcion_acceso_fin;
  }

  public void setSistemaRef_inicio(Integer sistemaRef_inicio) {
    this.sistemaRef_inicio = sistemaRef_inicio;
  }

  public Integer getSistemaRef_inicio() {
    return sistemaRef_inicio;
  }

  public void setSistemaRef_fin(Integer sistemaRef_fin) {
    this.sistemaRef_fin = sistemaRef_fin;
  }

  public Integer getSistemaRef_fin() {
    return sistemaRef_fin;
  }

  public void setListaSistemaRef_inicio(Lista listaSistemaRef_inicio) {
    this.listaSistemaRef_inicio = listaSistemaRef_inicio;
  }

  public Lista getListaSistemaRef_inicio() {
    return listaSistemaRef_inicio;
  }

  public void setListaSistemaRef_fin(Lista listaSistemaRef_fin) {
    this.listaSistemaRef_fin = listaSistemaRef_fin;
  }

  public Lista getListaSistemaRef_fin() {
    return listaSistemaRef_fin;
  }

  public void setProhibicion(Integer prohibicion) {
    this.prohibicion = prohibicion;
  }

  public Integer getProhibicion() {
    return prohibicion;
  }

  public void setProhibicion_tipo(Integer prohibicion_tipo) {
    this.prohibicion_tipo = prohibicion_tipo;
  }

  public Integer getProhibicion_tipo() {
    return prohibicion_tipo;
  }

  public void setLongitud_prohibicion(Double longitud_prohibicion) {
    this.longitud_prohibicion = longitud_prohibicion;
  }

  public Double getLongitud_prohibicion() {
    return longitud_prohibicion;
  }
}
