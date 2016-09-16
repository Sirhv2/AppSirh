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

/**
 *
 * @author MI EQUIPO
 */
@Entity
@Table(name = "sirh_vertimientos_v")
@NamedQueries({
    @NamedQuery(name = "PuntoVertimientoTO.findAll", query = "SELECT s FROM PuntoVertimientoTO s"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdPermiso", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idPermiso = :idPermiso"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResInicioTramite", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResInicioTramite = :numResInicioTramite"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpInicioTramite", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpInicioTramite = :fechaExpInicioTramite"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumExpediente", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numExpediente = :numExpediente"),
    @NamedQuery(name = "PuntoVertimientoTO.findByCaudal", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.caudal = :caudal"),
    @NamedQuery(name = "PuntoVertimientoTO.findByEvaluacionAmbiental", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.evaluacionAmbiental = :evaluacionAmbiental"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResSolicPlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResSolicPlanCumpl = :numResSolicPlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpPlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpPlanCumpl = :fechaExpPlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResAprobPlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResAprobPlanCumpl = :numResAprobPlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpAprobPlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpAprobPlanCumpl = :fechaExpAprobPlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByVigenciaDesdePlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.vigenciaDesdePlanCumpl = :vigenciaDesdePlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByVigenciaHastaPlanCumpl", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.vigenciaHastaPlanCumpl = :vigenciaHastaPlanCumpl"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResPermisoVert", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResPermisoVert = :numResPermisoVert"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpPermisoVert", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpPermisoVert = :fechaExpPermisoVert"),
    @NamedQuery(name = "PuntoVertimientoTO.findByVigenciasDesde", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.vigenciasDesde = :vigenciasDesde"),
    @NamedQuery(name = "PuntoVertimientoTO.findByVigenciaHasta", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.vigenciaHasta = :vigenciaHasta"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResAprobPlanos", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResAprobPlanos = :numResAprobPlanos"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpAprobPlanos", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpAprobPlanos = :fechaExpAprobPlanos"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNumResAprobObras", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.numResAprobObras = :numResAprobObras"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFechaExpAprbObras", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.fechaExpAprbObras = :fechaExpAprbObras"),
    @NamedQuery(name = "PuntoVertimientoTO.findByModificadaPor", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.modificadaPor = :modificadaPor"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdPredio", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idPredio = :idPredio"),
    @NamedQuery(name = "PuntoVertimientoTO.findByPredio", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.predio = :predio"),
    @NamedQuery(name = "PuntoVertimientoTO.findByUsuario", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdUsuario", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "PuntoVertimientoTO.findByAutoridad", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.autoridad = :autoridad"),
    @NamedQuery(name = "PuntoVertimientoTO.findByTipoVertimiento", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.tipoVertimiento = :tipoVertimiento"),
    @NamedQuery(name = "PuntoVertimientoTO.findByTipoFlujo", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.tipoFlujo = :tipoFlujo"),
    @NamedQuery(name = "PuntoVertimientoTO.findByTiempoDescarga", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.tiempoDescarga = :tiempoDescarga"),
    @NamedQuery(name = "PuntoVertimientoTO.findByFrecuencia", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.frecuencia = :frecuencia"),
    @NamedQuery(name = "PuntoVertimientoTO.findByCaudalDisegno", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.caudalDisegno = :caudalDisegno"),
    @NamedQuery(name = "PuntoVertimientoTO.findBySistemaReferencia", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.sistemaReferencia = :sistemaReferencia"),
    @NamedQuery(name = "PuntoVertimientoTO.findByDescripcionAcceso", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.descripcionAcceso = :descripcionAcceso"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdFuente", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idFuente = :idFuente"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdTramo", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idTramo = :idTramo"),
    @NamedQuery(name = "PuntoVertimientoTO.findByIdPermisoVertimiento", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.idPermisoVertimiento = :idPermisoVertimiento"),
    @NamedQuery(name = "PuntoVertimientoTO.findByDepartamento", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.departamento = :departamento"),
    @NamedQuery(name = "PuntoVertimientoTO.findByMunicipio", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.municipio = :municipio"),
    @NamedQuery(name = "PuntoVertimientoTO.findByTipoCentroPoblado", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.tipoCentroPoblado = :tipoCentroPoblado"),
    @NamedQuery(name = "PuntoVertimientoTO.findByNombreCentroPoblado", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.nombreCentroPoblado = :nombreCentroPoblado"),
    @NamedQuery(name = "PuntoVertimientoTO.findById", query = "SELECT s FROM PuntoVertimientoTO s WHERE s.id = :id"),
    @NamedQuery(name = "PuntoVertimientoTO.findByGeoreferencia", 
                                    query = "SELECT s FROM PuntoVertimientoTO s WHERE s.gradosLat = :gradosLat and s.minutosLat = :minutosLat and s.segundosLat = :segundosLat " +
                                                            "and s.gradosLong = :gradosLong and s.minutosLong = :minutosLong and s.segundosLong = :segundosLong "+
                                                            "and s.idPermiso = :idPermiso and s.autoridad = :codigoAutoridad")
    
})
public class PuntoVertimientoTO implements Serializable {
    @Column(name = "id_permiso")
    private Long idPermiso;
    @Column(name = "num_res_inicio_tramite")
    private String numResInicioTramite;
    @Column(name = "fecha_exp_inicio_tramite")
    private Date fechaExpInicioTramite;
    @Column(name = "num_expediente")
    private String numExpediente;
    @Column(name = "caudal")
    private Double caudal;
    @Column(name = "evaluacion_ambiental")
    private String evaluacionAmbiental;
    @Column(name = "num_res_solic_plan_cumpl")
    private String numResSolicPlanCumpl;
    @Column(name = "fecha_exp_plan_cumpl")
    private Date fechaExpPlanCumpl;
    @Column(name = "num_res_aprob_plan_cumpl")
    private String numResAprobPlanCumpl;
    @Column(name = "fecha_exp_aprob_plan_cumpl")
    private Date fechaExpAprobPlanCumpl;
    @Column(name = "vigencia_desde_plan_cumpl")
    private Date vigenciaDesdePlanCumpl;
    @Column(name = "vigencia_hasta_plan_cumpl")
    private Date vigenciaHastaPlanCumpl;
    @Column(name = "num_res_permiso_vert")
    private String numResPermisoVert;
    @Column(name = "fecha_exp_permiso_vert")
    private Date fechaExpPermisoVert;
    @Column(name = "vigencias_desde")
    private Date vigenciasDesde;
    @Column(name = "vigencia_hasta")
    private Date vigenciaHasta;
    @Column(name = "num_res_aprob_planos")
    private String numResAprobPlanos;
    @Column(name = "fecha_exp_aprob_planos")
    private Date fechaExpAprobPlanos;
    @Column(name = "num_res_aprob_obras")
    private String numResAprobObras;
    @Column(name = "fecha_exp_aprb_obras")
    private Date fechaExpAprbObras;
    @Column(name = "modificada_por")
    private Integer modificadaPor;
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
    @Column(name = "tipo_vertimiento")
    private Integer tipoVertimiento;
    @Column(name = "tipo_flujo")
    private Integer tipoFlujo;
    @Column(name = "tiempo_descarga")
    private BigDecimal tiempoDescarga;
    @Column(name = "frecuencia")
    private BigDecimal frecuencia;
    @Column(name = "caudal_disegno")
    private BigDecimal caudalDisegno;
    @Column(name = "sistema_referencia")
    private Integer sistemaReferencia;
    
    @Column(name = "grados_lat")
    private Integer gradosLat;
    @Column(name = "minutos_lat")
    private Integer minutosLat;
    @Column(name = "segundos_lat")
    private Double segundosLat;
    @Column(name = "grados_long")
    private Integer gradosLong;
    @Column(name = "minutos_long")
    private Integer minutosLong;
    @Column(name = "segundos_long")
    private Double segundosLong;
    @Column(name = "altitud")
    private Double altitud;
    @Column(name = "descripcion_acceso")
    private String descripcionAcceso;
    @Column(name = "id_fuente")
    private Integer idFuente;
    @Column(name = "id_tramo")
    private Integer idTramo;
    @Column(name = "id_permiso_vertimiento")
    private Integer idPermisoVertimiento;
    @Column(name = "departamento")
    private Integer departamento;
    @Column(name = "municipio")
    private Integer municipio;
    @Column(name = "tipo_centro_poblado")
    private Integer tipoCentroPoblado;
    @Column(name = "nombre_centro_poblado")
    private String nombreCentroPoblado;
    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre_sr")
    private String nombreSistemaReferencia;
    @Column(name = "nombre_municipio")
    private String nombreMunicipio;
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @Column(name = "nombre_tipo_vert")
    private String nombreTipoVertimiento;
    
    
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "tipo_fuente")
    private String tipo_fuente;
    @Column(name = "tramo")
    private String tramo;
    
    
    
    
    
    
    //Cabril 04/03/2015
    @Transient
    private Integer num;

    public PuntoVertimientoTO() {
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNumResInicioTramite() {
        return numResInicioTramite;
    }

    public void setNumResInicioTramite(String numResInicioTramite) {
        this.numResInicioTramite = numResInicioTramite;
    }

    public Date getFechaExpInicioTramite() {
        return fechaExpInicioTramite;
    }

    public void setFechaExpInicioTramite(Date fechaExpInicioTramite) {
        this.fechaExpInicioTramite = fechaExpInicioTramite;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public String getEvaluacionAmbiental() {
        return evaluacionAmbiental;
    }

    public void setEvaluacionAmbiental(String evaluacionAmbiental) {
        this.evaluacionAmbiental = evaluacionAmbiental;
    }

    public String getNumResSolicPlanCumpl() {
        return numResSolicPlanCumpl;
    }

    public void setNumResSolicPlanCumpl(String numResSolicPlanCumpl) {
        this.numResSolicPlanCumpl = numResSolicPlanCumpl;
    }

    public Date getFechaExpPlanCumpl() {
        return fechaExpPlanCumpl;
    }

    public void setFechaExpPlanCumpl(Date fechaExpPlanCumpl) {
        this.fechaExpPlanCumpl = fechaExpPlanCumpl;
    }

    public String getNumResAprobPlanCumpl() {
        return numResAprobPlanCumpl;
    }

    public void setNumResAprobPlanCumpl(String numResAprobPlanCumpl) {
        this.numResAprobPlanCumpl = numResAprobPlanCumpl;
    }

    public Date getFechaExpAprobPlanCumpl() {
        return fechaExpAprobPlanCumpl;
    }

    public void setFechaExpAprobPlanCumpl(Date fechaExpAprobPlanCumpl) {
        this.fechaExpAprobPlanCumpl = fechaExpAprobPlanCumpl;
    }

    public Date getVigenciaDesdePlanCumpl() {
        return vigenciaDesdePlanCumpl;
    }

    public void setVigenciaDesdePlanCumpl(Date vigenciaDesdePlanCumpl) {
        this.vigenciaDesdePlanCumpl = vigenciaDesdePlanCumpl;
    }

    public Date getVigenciaHastaPlanCumpl() {
        return vigenciaHastaPlanCumpl;
    }

    public void setVigenciaHastaPlanCumpl(Date vigenciaHastaPlanCumpl) {
        this.vigenciaHastaPlanCumpl = vigenciaHastaPlanCumpl;
    }

    public String getNumResPermisoVert() {
        return numResPermisoVert;
    }

    public void setNumResPermisoVert(String numResPermisoVert) {
        this.numResPermisoVert = numResPermisoVert;
    }

    public Date getFechaExpPermisoVert() {
        return fechaExpPermisoVert;
    }

    public void setFechaExpPermisoVert(Date fechaExpPermisoVert) {
        this.fechaExpPermisoVert = fechaExpPermisoVert;
    }

    public Date getVigenciasDesde() {
        return vigenciasDesde;
    }

    public void setVigenciasDesde(Date vigenciasDesde) {
        this.vigenciasDesde = vigenciasDesde;
    }

    public Date getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(Date vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public String getNumResAprobPlanos() {
        return numResAprobPlanos;
    }

    public void setNumResAprobPlanos(String numResAprobPlanos) {
        this.numResAprobPlanos = numResAprobPlanos;
    }

    public Date getFechaExpAprobPlanos() {
        return fechaExpAprobPlanos;
    }

    public void setFechaExpAprobPlanos(Date fechaExpAprobPlanos) {
        this.fechaExpAprobPlanos = fechaExpAprobPlanos;
    }

    public String getNumResAprobObras() {
        return numResAprobObras;
    }

    public void setNumResAprobObras(String numResAprobObras) {
        this.numResAprobObras = numResAprobObras;
    }

    public Date getFechaExpAprbObras() {
        return fechaExpAprbObras;
    }

    public void setFechaExpAprbObras(Date fechaExpAprbObras) {
        this.fechaExpAprbObras = fechaExpAprbObras;
    }

    public Integer getModificadaPor() {
        return modificadaPor;
    }

    public void setModificadaPor(Integer modificadaPor) {
        this.modificadaPor = modificadaPor;
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

    public BigDecimal getTiempoDescarga() {
        return tiempoDescarga;
    }

    public void setTiempoDescarga(BigDecimal tiempoDescarga) {
        this.tiempoDescarga = tiempoDescarga;
    }

    public BigDecimal getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(BigDecimal frecuencia) {
        this.frecuencia = frecuencia;
    }

    public BigDecimal getCaudalDisegno() {
        return caudalDisegno;
    }

    public void setCaudalDisegno(BigDecimal caudalDisegno) {
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

    public Integer getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Integer idFuente) {
        this.idFuente = idFuente;
    }

    public Integer getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Integer idTramo) {
        this.idTramo = idTramo;
    }

    public Integer getIdPermisoVertimiento() {
        return idPermisoVertimiento;
    }

    public void setIdPermisoVertimiento(Integer idPermisoVertimiento) {
        this.idPermisoVertimiento = idPermisoVertimiento;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getTipoCentroPoblado() {
        return tipoCentroPoblado;
    }

    public void setTipoCentroPoblado(Integer tipoCentroPoblado) {
        this.tipoCentroPoblado = tipoCentroPoblado;
    }

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


   
    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
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

    public void setSegundosLat(Double segundosLat) {
        this.segundosLat = segundosLat;
    }

    public Double getSegundosLat() {
        return segundosLat;
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

    public void setSegundosLong(Double segundosLong) {
        this.segundosLong = segundosLong;
    }

    public Double getSegundosLong() {
        return segundosLong;
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

    public void setNombreTipoVertimiento(String nombreTipoVertimiento) {
        this.nombreTipoVertimiento = nombreTipoVertimiento;
    }

    public String getNombreTipoVertimiento() {
        return nombreTipoVertimiento;
    }


    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTipo_fuente(String tipo_fuente) {
        this.tipo_fuente = tipo_fuente;
    }

    public String getTipo_fuente() {
        return tipo_fuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }
}
