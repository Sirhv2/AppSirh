package co.gov.ideam.sirh.calidad.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;


import java.io.Serializable;



import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.mapping.ManyToOne;
@Entity
@NamedQueries({
  @NamedQuery(name = "PuntoMonitoreo.findAllPuntos", query = "select o from PuntoMonitoreo o"),
  @NamedQuery(name = "PuntoMonitoreo.findAll", query = "select o from PuntoMonitoreo o where o.codigoAutoridad = :codigoAutoridad"),
  @NamedQuery(name = "PuntoMonitoreo.findById", query = "select o from PuntoMonitoreo  o where o.id = :id"),
  @NamedQuery(name = "PuntoMonitoreo.findByParam", query = "select o from PuntoMonitoreo  o where o.nombre = :nombre and o.idFuente.id  = :idFuente and o.idTramo.id = :idTramo "),
  @NamedQuery(name = "PuntoMonitoreo.findByTramo", query = "select o from PuntoMonitoreo  o where o.idTramo.id = :idTramo "),
  @NamedQuery(name = "PuntoMonitoreo.findByVert", query = "select o from PuntoMonitoreo  o where o.idVertimiento.id = :idVertimiento "),
  @NamedQuery(name = "PuntoMonitoreo.finByNombAut", query = "select o from PuntoMonitoreo o where o.codigoAutoridad = :codigoAutoridad and upper(o.nombre) = :nombre"),
  @NamedQuery(name = "PuntoMonitoreo.findByCaptacion", query = "select o from PuntoMonitoreo  o where o.idCaptacion.id = :idCaptacion ")
 
  
  
})
@Table(name = "calt_punto_monitoreo")
public class PuntoMonitoreo implements Serializable {
    
    
    @GenericGenerator(name = "puntosmonitoreo_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGeneratorCalidad",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_puntosmonitoreo")})
        
 
    
  @Id
  @Column(name = "id", nullable = false)
 // @GeneratedValue(generator = "puntosmonitoreo_generator")        
  private Long id;
    
  @Column(name="nombre")
  private String nombre;
  @Column(name="altitud")
  private Double altitud;
  @Column(name="descripcion_acceso")
  private String descripcion_acceso;
   @Column(name="latitud_grados")
  private Integer latitud_grados;
  @Column(name="latitud_minutos")
  private Integer latitud_minutos;
  @Column(name="latitud_segundos")
  private Double latitud_segundos;
  @Column(name="longitud_grados")
  private Integer longitud_grados;
  @Column(name="longitud_minutos")
  private Integer longitud_minutos;
  @Column(name="longitud_segundos")
  private Double longitud_segundos;
  @Column(name="id_autoridad")
  private Long codigoAutoridad;

  @JoinColumn(name = "id_captacion", referencedColumnName = "id")
  @javax.persistence.ManyToOne(optional = true)
  private Captacion idCaptacion;
    
  @JoinColumn(name = "id_fuente", referencedColumnName = "id")
  @javax.persistence.ManyToOne
  private FnttFuente idFuente;
  @JoinColumn(name = "id_tramo", referencedColumnName = "id")
  @javax.persistence.ManyToOne
  private FnttTramo idTramo;


  @JoinColumn(name = "id_vertimiento", referencedColumnName = "id")
  @javax.persistence.ManyToOne
  private PuntoVertimiento idVertimiento;
  
  
  @JoinColumn(name = "id_area", referencedColumnName = "id")
  @javax.persistence.ManyToOne(optional = false)
  private PartZonificListas idArea;
  @JoinColumn(name = "id_zona", referencedColumnName = "id")
  @javax.persistence.ManyToOne(optional = false)
  private PartZonificListas idZona;
  @JoinColumn(name = "id_subzona", referencedColumnName = "id")
  @javax.persistence.ManyToOne(optional = false)
  private PartZonificListas idSubzona;

  @Column(name="ubicacion")
  private Integer idubicacion;
  
  @Column(name="tipo_punto")
  private Integer idTipo_punto;
  

  
  @Column(name="sist_ref")
  private Integer idSist_ref;
  
  @Column(name="id_municipio")
  private Long  idmunicipio;

    
  @Transient
  private Divipola municipio;
  
  
  @Column(name="id_departamento")
  private Long  idDepartamento;

    
  @Transient
  private Divipola departamento;
  
  @Transient
  private Lista ubicacion;
   
  @Transient
  private Lista tipoPunto;
  
  @Transient
   private Lista sistemaRef;
   
  
   @Transient
   private List listaMuestras;

  
    @Transient
    private String nombreFuente;
    @Transient
    private String nombreTramo;
    @Transient
    private String tipofuente;
  
  
    public PuntoMonitoreo() {
        super();
    }

    public PuntoMonitoreo(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  public void setAltitud(Double altitud) {
    this.altitud = altitud;
  }

  public Double getAltitud() {
    return altitud;
  }

  public void setDescripcion_acceso(String descripcion_acceso) {
    this.descripcion_acceso = descripcion_acceso;
  }

  public String getDescripcion_acceso() {
    return descripcion_acceso;
  }

 


  public void setLatitud_segundos(Double latitud_segundos) {
    this.latitud_segundos = latitud_segundos;
  }

  public Double getLatitud_segundos() {
    return latitud_segundos;
  }

 

  public void setLongitud_segundos(Double longitud_segundos) {
    this.longitud_segundos = longitud_segundos;
  }

  public Double getLongitud_segundos() {
    return longitud_segundos;
  }



  public void setUbicacion(Lista ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Lista getUbicacion() {
    return ubicacion;
  }


  public void setTipoPunto(Lista tipoPunto) {
    this.tipoPunto = tipoPunto;
  }

  public Lista getTipoPunto() {
    return tipoPunto;
  }

  public void setIdubicacion(Integer idubicacion) {
    this.idubicacion = idubicacion;
  }

  public Integer getIdubicacion() {
    return idubicacion;
  }

  public void setIdTipo_punto(Integer idTipo_punto) {
    this.idTipo_punto = idTipo_punto;
  }

  public Integer getIdTipo_punto() {
    return idTipo_punto;
  }



  public void setIdFuente(FnttFuente idFuente) {
    this.idFuente = idFuente;
  }

  public FnttFuente getIdFuente() {
    return idFuente;
  }

  public void setIdSubzona(PartZonificListas idSubzona) {
    this.idSubzona = idSubzona;
  }

  public PartZonificListas getIdSubzona() {
    return idSubzona;
  }

  public void setIdTramo(FnttTramo idTramo) {
    this.idTramo = idTramo;
  }

  public FnttTramo getIdTramo() {
    return idTramo;
  }

  public void setIdmunicipio(Long idmunicipio) {
    this.idmunicipio = idmunicipio;
  }

  public Long getIdmunicipio() {
    return idmunicipio;
  }

  public void setMunicipio(Divipola municipio) {
    this.municipio = municipio;
  }

  public Divipola getMunicipio() {
    return municipio;
  }

  public void setIdSist_ref(Integer idSist_ref) {
    this.idSist_ref = idSist_ref;
  }

  public Integer getIdSist_ref() {
    return idSist_ref;
  }

  public void setSistemaRef(Lista sistemaRef) {
    this.sistemaRef = sistemaRef;
  }

  public Lista getSistemaRef() {
    return sistemaRef;
  }

  public void setCodigoAutoridad(Long codigoAutoridad) {
    this.codigoAutoridad = codigoAutoridad;
  }

  public Long getCodigoAutoridad() {
    return codigoAutoridad;
  }

  public void setIdZona(PartZonificListas idZona) {
    this.idZona = idZona;
  }

  public PartZonificListas getIdZona() {
    return idZona;
  }

  public void setIdArea(PartZonificListas idArea) {
    this.idArea = idArea;
  }

  public PartZonificListas getIdArea() {
    return idArea;
  }

  public void setIdDepartamento(Long idDepartamento) {
    this.idDepartamento = idDepartamento;
  }

  public Long getIdDepartamento() {
    return idDepartamento;
  }

  public void setDepartamento(Divipola departamento) {
    this.departamento = departamento;
  }

  public Divipola getDepartamento() {
    return departamento;
  }

    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }

    public void setIdVertimiento(PuntoVertimiento idVertimiento) {
        this.idVertimiento = idVertimiento;
    }

    public PuntoVertimiento getIdVertimiento() {
        return idVertimiento;
    }

    public void setLatitud_grados(Integer latitud_grados) {
        this.latitud_grados = latitud_grados;
    }

    public Integer getLatitud_grados() {
        return latitud_grados;
    }

    public void setLatitud_minutos(Integer latitud_minutos) {
        this.latitud_minutos = latitud_minutos;
    }

    public Integer getLatitud_minutos() {
        return latitud_minutos;
    }

    public void setLongitud_grados(Integer longitud_grados) {
        this.longitud_grados = longitud_grados;
    }

    public Integer getLongitud_grados() {
        return longitud_grados;
    }

    public void setLongitud_minutos(Integer longitud_minutos) {
        this.longitud_minutos = longitud_minutos;
    }

    public Integer getLongitud_minutos() {
        return longitud_minutos;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }



    public void setTipofuente(String tipofuente) {
        this.tipofuente = tipofuente;
    }

    public String getTipofuente() {
        return tipofuente;
    }

    public void setIdCaptacion(Captacion idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public Captacion getIdCaptacion() {
        return idCaptacion;
    }
}//Fin clase
