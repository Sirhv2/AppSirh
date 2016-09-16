package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sirh_captaciones_v")
@NamedQueries( { @NamedQuery(name = "CaptacionTO.findAll",query = "SELECT s FROM CaptacionTO s")
        ,
        @NamedQuery(name = "CaptacionTO.findById", query = "SELECT s FROM CaptacionTO s WHERE s.id = :id")
        ,
        @NamedQuery(name = "CaptacionTO.findByNumExpediente", query = "SELECT s FROM CaptacionTO s WHERE s.numExpediente = :numExpediente")
        ,
        @NamedQuery(name = "CaptacionTO.findByNumResCaudal", query = "SELECT s FROM CaptacionTO s WHERE s.numResCaudal = :numResCaudal")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaNotCaudal", query = "SELECT s FROM CaptacionTO s WHERE s.fechaNotCaudal = :fechaNotCaudal")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaExpCaudal", query = "SELECT s FROM CaptacionTO s WHERE s.fechaExpCaudal = :fechaExpCaudal")
        ,
        @NamedQuery(name = "CaptacionTO.findByNumResPlanos", query = "SELECT s FROM CaptacionTO s WHERE s.numResPlanos = :numResPlanos")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaNotPlanos", query = "SELECT s FROM CaptacionTO s WHERE s.fechaNotPlanos = :fechaNotPlanos")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaExpPlanos", query = "SELECT s FROM CaptacionTO s WHERE s.fechaExpPlanos = :fechaExpPlanos")
        ,
        @NamedQuery(name = "CaptacionTO.findByNumResObras", query = "SELECT s FROM CaptacionTO s WHERE s.numResObras = :numResObras")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaNotObras", query = "SELECT s FROM CaptacionTO s WHERE s.fechaNotObras = :fechaNotObras")
        ,
        @NamedQuery(name = "CaptacionTO.findByFechaExpObras", query = "SELECT s FROM CaptacionTO s WHERE s.fechaExpObras = :fechaExpObras")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdPredio", query = "SELECT s FROM CaptacionTO s WHERE s.idPredio = :idPredio")
        ,
        @NamedQuery(name = "CaptacionTO.findByPredio", query = "SELECT s FROM CaptacionTO s WHERE s.predio = :predio")
        ,
        @NamedQuery(name = "CaptacionTO.findByUsuario", query = "SELECT s FROM CaptacionTO s WHERE s.usuario = :usuario")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdUsuario", query = "SELECT s FROM CaptacionTO s WHERE s.idUsuario = :idUsuario")
        ,
        @NamedQuery(name = "CaptacionTO.findByAutoridad", query = "SELECT s FROM CaptacionTO s WHERE s.autoridad = :autoridad")
        ,
        @NamedQuery(name = "CaptacionTO.findByDeptoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.deptoCapt = :deptoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByMunCapt", query = "SELECT s FROM CaptacionTO s WHERE s.munCapt = :munCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByTipoCentroPobladoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.tipoCentroPobladoCapt = :tipoCentroPobladoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByNombreCentroPobladoCapt", query =
                    "SELECT s FROM CaptacionTO s WHERE s.nombreCentroPobladoCapt = :nombreCentroPobladoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByOfertaHidricaTotalCapt", query =
                    "SELECT s FROM CaptacionTO s WHERE s.ofertaHidricaTotalCapt = :ofertaHidricaTotalCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByOfertaDisponibleCapt", query = "SELECT s FROM CaptacionTO s WHERE s.ofertaDisponibleCapt = :ofertaDisponibleCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByTieneMacroMedicionCapt", query =
                    "SELECT s FROM CaptacionTO s WHERE s.tieneMacroMedicionCapt = :tieneMacroMedicionCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByEstadoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.estadoCapt = :estadoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByCaudalDisegnoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.caudalDisegnoCapt = :caudalDisegnoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByTieneServidumbreCapt", query = "SELECT s FROM CaptacionTO s WHERE s.tieneServidumbreCapt = :tieneServidumbreCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findBySistemaReferenciaCapt", query = "SELECT s FROM CaptacionTO s WHERE s.sistemaReferenciaCapt = :sistemaReferenciaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByDescripcionAccesoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.descripcionAccesoCapt = :descripcionAccesoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdFuenteCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idFuenteCapt = :idFuenteCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdTramoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idTramoCapt = :idTramoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdConcesionCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idConcesionCapt = :idConcesionCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdConcesion", query = "SELECT s FROM CaptacionTO s WHERE s.idConcesion = :idConcesion")
        ,
        @NamedQuery(name = "CaptacionTO.findByTipoFuenteCaptacion", query = "SELECT s FROM CaptacionTO s WHERE s.tipoFuenteCaptacion = :tipoFuenteCaptacion")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdSubzonaCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idSubzonaCapt = :idSubzonaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdZonaCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idZonaCapt = :idZonaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByIdAreaCapt", query = "SELECT s FROM CaptacionTO s WHERE s.idAreaCapt = :idAreaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByProvinciaHidrogeologicaCapt", query =
                    "SELECT s FROM CaptacionTO s WHERE s.provinciaHidrogeologicaCapt = :provinciaHidrogeologicaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByUnidadHidrogeologicaCapt", query =
                    "SELECT s FROM CaptacionTO s WHERE s.unidadHidrogeologicaCapt = :unidadHidrogeologicaCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByTipoPuntoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.tipoPuntoCapt = :tipoPuntoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByMetodoExtraccionCapt", query = "SELECT s FROM CaptacionTO s WHERE s.metodoExtraccionCapt = :metodoExtraccionCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByTipoCaptacion", query = "SELECT s FROM CaptacionTO s WHERE s.tipoCaptacion = :tipoCaptacion")
        ,
        @NamedQuery(name = "CaptacionTO.findByAreaCaptacion", query = "SELECT s FROM CaptacionTO s WHERE s.areaCaptacion = :areaCaptacion")
        ,
        @NamedQuery(name = "CaptacionTO.findByCaudalVertimientoCapt", query = "SELECT s FROM CaptacionTO s WHERE s.caudalVertimientoCapt = :caudalVertimientoCapt")
        ,
        @NamedQuery(name = "CaptacionTO.findByContinuidadCapt", query = "SELECT s FROM CaptacionTO s WHERE s.continuidadCapt = :continuidadCapt"),
        @NamedQuery(name = "CaptacionTO.findByGeoreferencia", 
                    query = "SELECT s FROM CaptacionTO s WHERE s.gradosLatCapt = :gradosLat and s.minutosLatCapt = :minutosLat and s.segundosLatCapt = :segundosLat " +
                                                            "and s.gradosLongCapt = :gradosLong and s.minutosLongCapt = :minutosLong and s.segundosLongCapt = :segundosLong "+
                                                            "and s.idConcesion = :idConcesion and s.autoridad = :codigoAutoridad"),
        @NamedQuery(name = "CaptacionTO.findByIdPunto", 
                    query = "SELECT r FROM CaptacionTO r WHERE r.identificadorPunto = :idPunto and r.autoridad = :codigoAutoridad ")
        } )

public class CaptacionTO implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "num_expediente")
    private String numExpediente;
    @Column(name = "num_res_caudal")
    private String numResCaudal;
    @Column(name = "fecha_not_caudal")
    private Date fechaNotCaudal;
    @Column(name = "fecha_exp_caudal")
    private Date fechaExpCaudal;
    @Column(name = "num_res_planos")
    private String numResPlanos;
    @Column(name = "cedula_catastral")
    private String cedula_catastral;
    @Column(name = "fecha_not_planos")
    private Date fechaNotPlanos;
    @Column(name = "fecha_exp_planos")
    private Date fechaExpPlanos;
    @Column(name = "num_res_obras")
    private String numResObras;
    @Column(name = "fecha_not_obras")
    private Date fechaNotObras;
    @Column(name = "fecha_exp_obras")
    private Date fechaExpObras;
    @Column(name = "id_predio")
    private Long idPredio;
    @Column(name = "predio")
    private String predio;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "autoridad")
    private Integer autoridad;
    @Column(name = "depto_capt")
    private Integer deptoCapt;
    @Column(name = "mun_capt")
    private Integer munCapt;
    @Column(name = "tipo_centro_poblado_capt")
    private Integer tipoCentroPobladoCapt;
    @Column(name = "nombre_centro_poblado_capt")
    private String nombreCentroPobladoCapt;
    @Column(name = "oferta_hidrica_total_capt")
    private BigDecimal ofertaHidricaTotalCapt;
    @Column(name = "oferta_disponible_capt")
    private BigDecimal ofertaDisponibleCapt;
    @Column(name = "tiene_macro_medicion_capt")
    private Integer tieneMacroMedicionCapt;
    @Column(name = "estado_capt")
    private String estadoCapt;
    @Column(name = "caudal_disegno_capt")
    private BigDecimal caudalDisegnoCapt;
    @Column(name = "tiene_servidumbre_capt")
    private Integer tieneServidumbreCapt;
    @Column(name = "sistema_referencia_capt")
    private Integer sistemaReferenciaCapt;
    @Column(name = "grados_lat_capt")
    private Integer gradosLatCapt;
    @Column(name = "minutos_lat_capt")
    private Integer minutosLatCapt;
    @Column(name = "segundos_lat_capt")
    private Double segundosLatCapt;
    @Column(name = "grados_long_capt")
    private Integer gradosLongCapt;
    @Column(name = "minutos_long_capt")
    private Integer minutosLongCapt;
    @Column(name = "segundos_long_capt")
    private Double segundosLongCapt;
    @Column(name = "altitud_capt")
    private Double altitudCapt;
    @Column(name = "descripcion_acceso_capt")
    private String descripcionAccesoCapt;
    @Column(name = "id_fuente_capt")
    private Integer idFuenteCapt;
    @Column(name = "id_tramo_capt")
    private Integer idTramoCapt;
  
    @Column(name = "tramo_capt")
    private String tramo;
   
    @Column(name = "fuente_capt")
    private String fuente;
    @Column(name = "tipo_fuente")
    private String tipoFuente;
    @Column(name = "id_concesion_capt")
    private Integer idConcesionCapt;
    @Column(name = "id_concesion")
    private Long idConcesion;
    @Column(name = "tipo_fuente_captacion")
    private Integer tipoFuenteCaptacion;
    @Column(name = "id_subzona_capt")
    private Integer idSubzonaCapt;
    @Column(name = "id_zona_capt")
    private Integer idZonaCapt;
    @Column(name = "id_area_capt")
    private Integer idAreaCapt;
    @Column(name = "provincia_hidrogeologica_capt")
    private Integer provinciaHidrogeologicaCapt;
    @Column(name = "unidad_hidrogeologica_capt")
    private String unidadHidrogeologicaCapt;
    @Column(name = "tipo_punto_capt")
    private Integer tipoPuntoCapt;
    @Column(name = "metodo_extraccion_capt")
    private Integer metodoExtraccionCapt;
    @Column(name = "tipo_captacion")
    private Integer tipoCaptacion;
    @Column(name = "area_captacion")
    private BigDecimal areaCaptacion;
    @Column(name = "caudal_vertimiento_capt")
    private BigDecimal caudalVertimientoCapt;
    @Column(name = "continuidad_capt")
    private String continuidadCapt;
    @Column(name = "nombre_sr")
    private String nombreSistemaReferencia;
    @Column(name = "nombre_municipio")
    private String nombreMunicipio;
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @Column(name = "nombre_tipo_fuente")
    private String nombreTipoFuenteCaptacion;
    @Column(name = "identificador_punto")
    private String identificadorPunto;

    @Transient
    String grados;
    @Transient
    String minutos;
    
    @Column(name = "no_valida")
    private Integer no_valida;
    
    //Pilar
    @Column(name = "red_monitoreo")
    private Integer red_monitoreo;
    
    //Cabril 04/03/2015
    @Transient
    private Integer num;





    public CaptacionTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdConcesion() {
        return idConcesion;
    }

    public void setIdConcesion(Long idConcesion) {
        this.idConcesion = idConcesion;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNumResCaudal() {
        return numResCaudal;
    }

    public void setNumResCaudal(String numResCaudal) {
        this.numResCaudal = numResCaudal;
    }

    public Date getFechaNotCaudal() {
        return fechaNotCaudal;
    }

    public void setFechaNotCaudal(Date fechaNotCaudal) {
        this.fechaNotCaudal = fechaNotCaudal;
    }

    public Date getFechaExpCaudal() {
        return fechaExpCaudal;
    }

    public void setFechaExpCaudal(Date fechaExpCaudal) {
        this.fechaExpCaudal = fechaExpCaudal;
    }

    public String getNumResPlanos() {
        return numResPlanos;
    }

    public void setNumResPlanos(String numResPlanos) {
        this.numResPlanos = numResPlanos;
    }

    public Date getFechaNotPlanos() {
        return fechaNotPlanos;
    }

    public void setFechaNotPlanos(Date fechaNotPlanos) {
        this.fechaNotPlanos = fechaNotPlanos;
    }

    public Date getFechaExpPlanos() {
        return fechaExpPlanos;
    }

    public void setFechaExpPlanos(Date fechaExpPlanos) {
        this.fechaExpPlanos = fechaExpPlanos;
    }

    public String getNumResObras() {
        return numResObras;
    }

    public void setNumResObras(String numResObras) {
        this.numResObras = numResObras;
    }

    public Date getFechaNotObras() {
        return fechaNotObras;
    }

    public void setFechaNotObras(Date fechaNotObras) {
        this.fechaNotObras = fechaNotObras;
    }

    public Date getFechaExpObras() {
        return fechaExpObras;
    }

    public void setFechaExpObras(Date fechaExpObras) {
        this.fechaExpObras = fechaExpObras;
    }

    public Long getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Integer autoridad) {
        this.autoridad = autoridad;
    }

    public Integer getDeptoCapt() {
        return deptoCapt;
    }

    public void setDeptoCapt(Integer deptoCapt) {
        this.deptoCapt = deptoCapt;
    }

    public Integer getMunCapt() {
        return munCapt;
    }

    public void setMunCapt(Integer munCapt) {
        this.munCapt = munCapt;
    }

    public Integer getTipoCentroPobladoCapt() {
        return tipoCentroPobladoCapt;
    }

    public void setTipoCentroPobladoCapt(Integer tipoCentroPobladoCapt) {
        this.tipoCentroPobladoCapt = tipoCentroPobladoCapt;
    }

    public String getNombreCentroPobladoCapt() {
        return nombreCentroPobladoCapt;
    }

    public void setNombreCentroPobladoCapt(String nombreCentroPobladoCapt) {
        this.nombreCentroPobladoCapt = nombreCentroPobladoCapt;
    }

    public BigDecimal getOfertaHidricaTotalCapt() {
        return ofertaHidricaTotalCapt;
    }

    public void setOfertaHidricaTotalCapt(BigDecimal ofertaHidricaTotalCapt) {
        this.ofertaHidricaTotalCapt = ofertaHidricaTotalCapt;
    }

    public BigDecimal getOfertaDisponibleCapt() {
        return ofertaDisponibleCapt;
    }

    public void setOfertaDisponibleCapt(BigDecimal ofertaDisponibleCapt) {
        this.ofertaDisponibleCapt = ofertaDisponibleCapt;
    }

    public Integer getTieneMacroMedicionCapt() {
        return tieneMacroMedicionCapt;
    }

    public void setTieneMacroMedicionCapt(Integer tieneMacroMedicionCapt) {
        this.tieneMacroMedicionCapt = tieneMacroMedicionCapt;
    }

    public String getEstadoCapt() {
        return estadoCapt;
    }

    public void setEstadoCapt(String estadoCapt) {
        this.estadoCapt = estadoCapt;
    }

    public BigDecimal getCaudalDisegnoCapt() {
        return caudalDisegnoCapt;
    }

    public void setCaudalDisegnoCapt(BigDecimal caudalDisegnoCapt) {
        this.caudalDisegnoCapt = caudalDisegnoCapt;
    }

    public Integer getTieneServidumbreCapt() {
        return tieneServidumbreCapt;
    }

    public void setTieneServidumbreCapt(Integer tieneServidumbreCapt) {
        this.tieneServidumbreCapt = tieneServidumbreCapt;
    }

    public Integer getSistemaReferenciaCapt() {
        return sistemaReferenciaCapt;
    }

    public void setSistemaReferenciaCapt(Integer sistemaReferenciaCapt) {
        this.sistemaReferenciaCapt = sistemaReferenciaCapt;
    }

   

    public String getDescripcionAccesoCapt() {
        return descripcionAccesoCapt;
    }

    public void setDescripcionAccesoCapt(String descripcionAccesoCapt) {
        this.descripcionAccesoCapt = descripcionAccesoCapt;
    }

    public Integer getIdFuenteCapt() {
        return idFuenteCapt;
    }

    public void setIdFuenteCapt(Integer idFuenteCapt) {
        this.idFuenteCapt = idFuenteCapt;
    }

    public Integer getIdTramoCapt() {
        return idTramoCapt;
    }

    public void setIdTramoCapt(Integer idTramoCapt) {
        this.idTramoCapt = idTramoCapt;
    }

    public Integer getIdConcesionCapt() {
        return idConcesionCapt;
    }

    public void setIdConcesionCapt(Integer idConcesionCapt) {
        this.idConcesionCapt = idConcesionCapt;
    }

    public Integer getTipoFuenteCaptacion() {
        return tipoFuenteCaptacion;
    }

    public void setTipoFuenteCaptacion(Integer tipoFuenteCaptacion) {
        this.tipoFuenteCaptacion = tipoFuenteCaptacion;
    }

    public Integer getIdSubzonaCapt() {
        return idSubzonaCapt;
    }

    public void setIdSubzonaCapt(Integer idSubzonaCapt) {
        this.idSubzonaCapt = idSubzonaCapt;
    }

    public Integer getIdZonaCapt() {
        return idZonaCapt;
    }

    public void setIdZonaCapt(Integer idZonaCapt) {
        this.idZonaCapt = idZonaCapt;
    }

    public Integer getIdAreaCapt() {
        return idAreaCapt;
    }

    public void setIdAreaCapt(Integer idAreaCapt) {
        this.idAreaCapt = idAreaCapt;
    }

    public Integer getProvinciaHidrogeologicaCapt() {
        return provinciaHidrogeologicaCapt;
    }

    public void setProvinciaHidrogeologicaCapt(Integer provinciaHidrogeologicaCapt) {
        this.provinciaHidrogeologicaCapt = provinciaHidrogeologicaCapt;
    }

    public String getUnidadHidrogeologicaCapt() {
        return unidadHidrogeologicaCapt;
    }

    public void setUnidadHidrogeologicaCapt(String unidadHidrogeologicaCapt) {
        this.unidadHidrogeologicaCapt = unidadHidrogeologicaCapt;
    }

    public Integer getTipoPuntoCapt() {
        return tipoPuntoCapt;
    }

    public void setTipoPuntoCapt(Integer tipoPuntoCapt) {
        this.tipoPuntoCapt = tipoPuntoCapt;
    }

    public Integer getMetodoExtraccionCapt() {
        return metodoExtraccionCapt;
    }

    public void setMetodoExtraccionCapt(Integer metodoExtraccionCapt) {
        this.metodoExtraccionCapt = metodoExtraccionCapt;
    }

    public Integer getTipoCaptacion() {
        return tipoCaptacion;
    }

    public void setTipoCaptacion(Integer tipoCaptacion) {
        this.tipoCaptacion = tipoCaptacion;
    }

    public BigDecimal getAreaCaptacion() {
        return areaCaptacion;
    }

    public void setAreaCaptacion(BigDecimal areaCaptacion) {
        this.areaCaptacion = areaCaptacion;
    }

    public BigDecimal getCaudalVertimientoCapt() {
        return caudalVertimientoCapt;
    }

    public void setCaudalVertimientoCapt(BigDecimal caudalVertimientoCapt) {
        this.caudalVertimientoCapt = caudalVertimientoCapt;
    }

    public String getContinuidadCapt() {
        return continuidadCapt;
    }

    public void setContinuidadCapt(String continuidadCapt) {
        this.continuidadCapt = continuidadCapt;
    }
    
    public void setGradosLatCapt(Integer gradosLatCapt) {
        this.gradosLatCapt = gradosLatCapt;
    }

    public Integer getGradosLatCapt() {
        return gradosLatCapt;
    }

    public void setMinutosLatCapt(Integer minutosLatCapt) {
        this.minutosLatCapt = minutosLatCapt;
    }

    public Integer getMinutosLatCapt() {
        return minutosLatCapt;
    }

    public void setSegundosLatCapt(Double segundosLatCapt) {
        this.segundosLatCapt = segundosLatCapt;
    }

    public Double getSegundosLatCapt() {
        return segundosLatCapt;
    }

    public void setGradosLongCapt(Integer gradosLongCapt) {
        this.gradosLongCapt = gradosLongCapt;
    }

    public Integer getGradosLongCapt() {
        return gradosLongCapt;
    }

    public void setMinutosLongCapt(Integer minutosLongCapt) {
        this.minutosLongCapt = minutosLongCapt;
    }

    public Integer getMinutosLongCapt() {
        return minutosLongCapt;
    }

    public void setSegundosLongCapt(Double segundosLongCapt) {
        this.segundosLongCapt = segundosLongCapt;
    }

    public Double getSegundosLongCapt() {
        return segundosLongCapt;
    }

    public void setAltitudCapt(Double altitudCapt) {
        this.altitudCapt = altitudCapt;
    }

    public Double getAltitudCapt() {
        return altitudCapt;
    }


    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMinutos() {
        minutos = getMinutosLatCapt().toString();
        minutos = minutos.substring(0, minutos.indexOf("."));
        return minutos;
    }

    public void setGrados(String grados) {
        this.grados = grados;
    }

    public String getGrados() {
        grados = getGradosLatCapt().toString();
        grados = grados.substring(0, grados.indexOf("."));
        return grados;
    }

    public void setNombreSistemaReferencia(String nombreSistemaReferencia) {
        this.nombreSistemaReferencia = nombreSistemaReferencia;
    }

    public String getNombreSistemaReferencia() {
        return nombreSistemaReferencia;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreTipoFuenteCaptacion(String nombreTipoFuenteCaptacion) {
        this.nombreTipoFuenteCaptacion = nombreTipoFuenteCaptacion;
    }

    public String getNombreTipoFuenteCaptacion() {
        return nombreTipoFuenteCaptacion;
    }

    public void setCedula_catastral(String cedula_catastral) {
        this.cedula_catastral = cedula_catastral;
    }

    public String getCedula_catastral() {
        return cedula_catastral;
    }

    public void setNo_valida(Integer no_valida) {
        this.no_valida = no_valida;
    }

    public Integer getNo_valida() {
        return no_valida;
    }

    public void setRed_monitoreo(Integer red_monitoreo) {
        this.red_monitoreo = red_monitoreo;
    }

    public Integer getRed_monitoreo() {
        return red_monitoreo;
    }


    public void setIdentificadorPunto(String identificadorPunto) {
        this.identificadorPunto = identificadorPunto;
    }

    public String getIdentificadorPunto() {
        return identificadorPunto;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }


    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTipoFuente(String tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    public String getTipoFuente() {
        return tipoFuente;
    }
  
}
