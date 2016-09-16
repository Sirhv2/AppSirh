package co.gov.ideam.sirh.calidad.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity

@Table(name = "calt_punto_monitoreo")

public class PuntoMonitoreoPOJO implements Serializable {
    
    
    @GenericGenerator(name = "puntosmonitoreo_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGeneratorCalidad",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_puntosmonitoreo")})
        
    
    
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "puntosmonitoreo_generator")
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
   
    @Column(name = "id_tramo", nullable = false)
    private Long id_tramo;
    @Column(name = "id_fuente", nullable = false)
    private Long id_fuente;
    @Column(name = "id_area", nullable = false)
    private Integer id_area;
    @Column(name = "id_zona", nullable = false)
    private Integer id_zona;
    @Column(name = "id_subzona", nullable = false)
    private Integer id_subzona;

    @Column(name="ubicacion")
    private Integer idubicacion;
    
    @Column(name="tipo_punto")
    private Integer idTipo_punto;
   
    @Column(name="sist_ref")
    private Integer idSist_ref;
    
    @Column(name="id_municipio")
    private Long  idmunicipio;

    @Column(name="id_departamento")
    private Long  idDepartamento;
  
    @Column(name="id_vertimiento")
    private Long  idVertimiento;
      
    @Column(name="id_captacion")
    private Long idCaptacion;
    
    public PuntoMonitoreoPOJO() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

    public void setLatitud_segundos(Double latitud_segundos) {
        this.latitud_segundos = latitud_segundos;
    }

    public Double getLatitud_segundos() {
        return latitud_segundos;
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

    public void setLongitud_segundos(Double longitud_segundos) {
        this.longitud_segundos = longitud_segundos;
    }

    public Double getLongitud_segundos() {
        return longitud_segundos;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setId_tramo(Long id_tramo) {
        this.id_tramo = id_tramo;
    }

    public Long getId_tramo() {
        return id_tramo;
    }

    public void setId_fuente(Long id_fuente) {
        this.id_fuente = id_fuente;
    }

    public Long getId_fuente() {
        return id_fuente;
    }

    public void setId_area(Integer id_area) {
        this.id_area = id_area;
    }

    public Integer getId_area() {
        return id_area;
    }

    public void setId_zona(Integer id_zona) {
        this.id_zona = id_zona;
    }

    public Integer getId_zona() {
        return id_zona;
    }

    public void setId_subzona(Integer id_subzona) {
        this.id_subzona = id_subzona;
    }

    public Integer getId_subzona() {
        return id_subzona;
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

    public void setIdSist_ref(Integer idSist_ref) {
        this.idSist_ref = idSist_ref;
    }

    public Integer getIdSist_ref() {
        return idSist_ref;
    }

    public void setIdmunicipio(Long idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Long getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

  
    public void setIdCaptacion(Long idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public Long getIdCaptacion() {
        return idCaptacion;
    }

    public void setIdVertimiento(Long idVertimiento) {
        this.idVertimiento = idVertimiento;
    }

    public Long getIdVertimiento() {
        return idVertimiento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }
}
