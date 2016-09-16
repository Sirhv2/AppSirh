package co.gov.ideam.sirh.datossinc.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "PermisoVertimientoSinc.findAll", query = "select o from PermisoVertimientoSinc o where o.codigoPredio = :codigoPredio"),
  @NamedQuery(name = "PermisoVertimientoSinc.find", query = "select o from PermisoVertimientoSinc o where o.codigo = :codigo"),
  @NamedQuery(name = "PermisoVertimientoSinc.findByExpediente", query = "select o from PermisoVertimientoSinc o where upper(o.numeroExpediente) = :numeroExpediente")
})
@Table(name = "RURT_PERMISOS_VERT_SINC")
public class PermisoVertimientoSinc implements Serializable{
  
    @Id
    @Column(name="id", nullable = false)    
    private Long codigo;
    @Column(name="num_res_inicio_tramite", nullable = true)    
    private String resolucionInicioTramite;
    @Column(name="fecha_exp_inicio_tramite", nullable = true)    
    private Calendar fechaExpedicionInicioTramite;
    @Column(name="num_expediente", nullable = true)    
    private String numeroExpediente;
    @Column(name="caudal", nullable = true)    
    private Double caudal;
    @Column(name="evaluacion_ambiental", nullable = true)    
    private String evaluacionAmbiental;
    @Column(name="num_res_solic_plan_cumpl", nullable = true)    
    private String resolucionSolicitudPlanCumplimiento;
    @Column(name="fecha_exp_plan_cumpl", nullable = true)    
    private Calendar fechaExpedicionPlan;
    @Column(name="num_res_aprob_plan_cumpl", nullable = true)    
    private String resolucionAprobacionPlan;
    @Column(name="fecha_exp_aprob_plan_cumpl", nullable = true)    
    private Calendar fechaExpedicionAprobacionPlan;
    @Column(name="vigencia_desde_plan_cumpl", nullable = true)    
    private Calendar fechaInicoPlan;
    @Column(name="vigencia_hasta_plan_cumpl", nullable = true)    
    private Calendar fechaFinPlan;
    @Column(name="num_res_permiso_vert", nullable = true)    
    private String resolucionPermisoVertimiento;
    @Column(name="fecha_exp_permiso_vert", nullable = true)    
    private Calendar fechaExpedicionPermiso;
    @Column(name="vigencias_desde", nullable = true)    
    private Calendar fechaInicio;
    @Column(name="vigencia_hasta", nullable = true)    
    private Calendar fechaFin;
    @Column(name="num_res_aprob_planos", nullable = true)    
    private String resolucionAprobacionPlanos;
    @Column(name="fecha_exp_aprob_planos", nullable = true)    
    private Calendar fechaExpedicionPlanos;
    @Column(name="num_res_aprob_obras", nullable = true)    
    private String resolucionAprobacionObras;
    @Column(name="fecha_exp_aprb_obras", nullable = true)    
    private Calendar fechaExpedicionObras;
    @Column(name="modificada_por", nullable = true)    
    private Long idUsuarioModifica;
    @Column(name="id_predio", nullable = true)    
    private Long codigoPredio;
    
    @Column(name="archivo", nullable = true)
    private byte[] archivo;
    @Column(name="tipo_archivo", nullable = true, length = 10)
    private String tipoArchivo;    
    @Transient
    private Long codigoAutoridadAmbiental;

    // Campos correspondientes al PSMV
    @Column(name="resolucion_psmv", nullable = true)    
    private String resolucionPsmv;
    @Column(name="fecha_resolucion_psmv", nullable = true)    
    private Calendar fechaResolucionPsmv;
    @Column(name="fecha_inicial_psmv", nullable = true)    
    private Calendar fechaInicioPsmv;
    @Column(name="fecha_final_psmv", nullable = true)    
    private Calendar fechaFinPsmv;
    @Column(name="resolucion_aprob_planos_psmv", nullable = true)    
    private String resolucionPlanosPsmv;
    @Column(name="fecha_res_aprob_planos_psmv", nullable = true)    
    private Calendar fechaResolucionPlanosPsmv;
    @Column(name="resolucion_aprob_obras_psmv", nullable = true)    
    private String resolucionObrasPsmv;
    @Column(name="fecha_res_aprob_obras_psmv", nullable = true)    
    private Calendar fechaResolucionObrasPsmv;
    @Column(name="fecha_notificacion_psmv", nullable = true)    
    private Calendar fechaNotificacionObrasPsmv;
    @Column(name="onservaciones_psmv", nullable = true)    
    private String obervacionesPsmv;
    
    // Informacion de la novedad
    @Column(name = "TIPO_NOVEDAD", nullable = true)
    private String tipoNovedad;
    @Column(name = "NUM_EXPEDIENTE_NOVEDAD", nullable = true)
    private String numeroExpedienteNovedad;
    @Column(name = "FECHA_EXP_NOVEDAD", nullable = true)
    private Calendar fechaExpedicionNovedad;
    @Column(name = "OBSERVACIONES_NOVEDAD", nullable = true)
    private String observacionesNovedad;
    
    
    //** Lisbeth Integracion datos 
    @Column(name="origen")
    private String origen;    
    @Column(name="id_origen")
    private String id_origen;
    @Column(name="trasmitido")
    private Integer trasmitido;
    
    
    public PermisoVertimientoSinc() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getResolucionInicioTramite() {
        return resolucionInicioTramite;
    }

    public void setResolucionInicioTramite(String resolucionInicioTramite) {
        this.resolucionInicioTramite = resolucionInicioTramite;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
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

    public String getResolucionSolicitudPlanCumplimiento() {
        return resolucionSolicitudPlanCumplimiento;
    }

    public void setResolucionSolicitudPlanCumplimiento(String resolucionSolicitudPlanCumplimiento) {
        this.resolucionSolicitudPlanCumplimiento =
                resolucionSolicitudPlanCumplimiento;
    }

    public String getResolucionAprobacionPlan() {
        return resolucionAprobacionPlan;
    }

    public void setResolucionAprobacionPlan(String resolucionAprobacionPlan) {
        this.resolucionAprobacionPlan = resolucionAprobacionPlan;
    }

    public Calendar getFechaInicoPlan() {
        return fechaInicoPlan;
    }

    public void setFechaInicoPlan(Calendar fechaInicoPlan) {
        this.fechaInicoPlan = fechaInicoPlan;
    }

    public Calendar getFechaFinPlan() {
        return fechaFinPlan;
    }

    public void setFechaFinPlan(Calendar fechaFinPlan) {
        this.fechaFinPlan = fechaFinPlan;
    }

    public String getResolucionPermisoVertimiento() {
        return resolucionPermisoVertimiento;
    }

    public void setResolucionPermisoVertimiento(String resolucionPermisoVertimiento) {
        this.resolucionPermisoVertimiento = resolucionPermisoVertimiento;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getResolucionAprobacionPlanos() {
        return resolucionAprobacionPlanos;
    }

    public void setResolucionAprobacionPlanos(String resolucionAprobacionPlanos) {
        this.resolucionAprobacionPlanos = resolucionAprobacionPlanos;
    }
    
    public String getResolucionAprobacionObras() {
        return resolucionAprobacionObras;
    }

    public void setResolucionAprobacionObras(String resolucionAprobacionObras) {
        this.resolucionAprobacionObras = resolucionAprobacionObras;
    }

    public Long getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(Long idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    public Long getCodigoPredio() {
        return codigoPredio;
    }

    public void setCodigoPredio(Long codigoPredio) {
        this.codigoPredio = codigoPredio;
    }

    public Long getCodigoAutoridadAmbiental() {
        return codigoAutoridadAmbiental;
    }

    public void setCodigoAutoridadAmbiental(Long codigoAutoridadAmbiental) {
        this.codigoAutoridadAmbiental = codigoAutoridadAmbiental;
    }

    public Calendar getFechaExpedicionInicioTramite() {
        return fechaExpedicionInicioTramite;
    }

    public void setFechaExpedicionInicioTramite(Calendar fechaExpedicionInicioTramite) {
        this.fechaExpedicionInicioTramite = fechaExpedicionInicioTramite;
    }

    public Calendar getFechaExpedicionPlan() {
        return fechaExpedicionPlan;
    }

    public void setFechaExpedicionPlan(Calendar fechaExpedicionPlan) {
        this.fechaExpedicionPlan = fechaExpedicionPlan;
    }

    public Calendar getFechaExpedicionAprobacionPlan() {
        return fechaExpedicionAprobacionPlan;
    }

    public void setFechaExpedicionAprobacionPlan(Calendar fechaExpedicionAprobacionPlan) {
        this.fechaExpedicionAprobacionPlan = fechaExpedicionAprobacionPlan;
    }

    public Calendar getFechaExpedicionPermiso() {
        return fechaExpedicionPermiso;
    }

    public void setFechaExpedicionPermiso(Calendar fechaExpedicionPermiso) {
        this.fechaExpedicionPermiso = fechaExpedicionPermiso;
    }

    public Calendar getFechaExpedicionPlanos() {
        return fechaExpedicionPlanos;
    }

    public void setFechaExpedicionPlanos(Calendar fechaExpedicionPlanos) {
        this.fechaExpedicionPlanos = fechaExpedicionPlanos;
    }

    public Calendar getFechaExpedicionObras() {
        return fechaExpedicionObras;
    }

    public void setFechaExpedicionObras(Calendar fechaExpedicionObras) {
        this.fechaExpedicionObras = fechaExpedicionObras;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getResolucionPsmv() {
        return resolucionPsmv;
    }

    public void setResolucionPsmv(String resolucionPsmv) {
        this.resolucionPsmv = resolucionPsmv;
    }

    public Calendar getFechaResolucionPsmv() {
        return fechaResolucionPsmv;
    }

    public void setFechaResolucionPsmv(Calendar fechaResolucionPsmv) {
        this.fechaResolucionPsmv = fechaResolucionPsmv;
    }

    public Calendar getFechaInicioPsmv() {
        return fechaInicioPsmv;
    }

    public void setFechaInicioPsmv(Calendar fechaInicioPsmv) {
        this.fechaInicioPsmv = fechaInicioPsmv;
    }

    public Calendar getFechaFinPsmv() {
        return fechaFinPsmv;
    }

    public void setFechaFinPsmv(Calendar fechaFinPsmv) {
        this.fechaFinPsmv = fechaFinPsmv;
    }

    public String getResolucionPlanosPsmv() {
        return resolucionPlanosPsmv;
    }

    public void setResolucionPlanosPsmv(String resolucionPlanosPsmv) {
        this.resolucionPlanosPsmv = resolucionPlanosPsmv;
    }

    public Calendar getFechaResolucionPlanosPsmv() {
        return fechaResolucionPlanosPsmv;
    }

    public void setFechaResolucionPlanosPsmv(Calendar fechaResolucionPlanosPsmv) {
        this.fechaResolucionPlanosPsmv = fechaResolucionPlanosPsmv;
    }

    public String getResolucionObrasPsmv() {
        return resolucionObrasPsmv;
    }

    public void setResolucionObrasPsmv(String resolucionObrasPsmv) {
        this.resolucionObrasPsmv = resolucionObrasPsmv;
    }

    public Calendar getFechaResolucionObrasPsmv() {
        return fechaResolucionObrasPsmv;
    }

    public void setFechaResolucionObrasPsmv(Calendar fechaResolucionObrasPsmv) {
        this.fechaResolucionObrasPsmv = fechaResolucionObrasPsmv;
    }

    public Calendar getFechaNotificacionObrasPsmv() {
        return fechaNotificacionObrasPsmv;
    }

    public void setFechaNotificacionObrasPsmv(Calendar fechaNotificacionObrasPsmv) {
        this.fechaNotificacionObrasPsmv = fechaNotificacionObrasPsmv;
    }

    public String getObervacionesPsmv() {
        return obervacionesPsmv;
    }

    public void setObervacionesPsmv(String obervacionesPsmv) {
        this.obervacionesPsmv = obervacionesPsmv;
    }

    public String getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(String tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public String getNumeroExpedienteNovedad() {
        return numeroExpedienteNovedad;
    }

    public void setNumeroExpedienteNovedad(String numeroExpedienteNovedad) {
        this.numeroExpedienteNovedad = numeroExpedienteNovedad;
    }

    public Calendar getFechaExpedicionNovedad() {
        return fechaExpedicionNovedad;
    }

    public void setFechaExpedicionNovedad(Calendar fechaExpedicionNovedad) {
        this.fechaExpedicionNovedad = fechaExpedicionNovedad;
    }

    public String getObservacionesNovedad() {
        return observacionesNovedad;
    }

    public void setObservacionesNovedad(String observacionesNovedad) {
        this.observacionesNovedad = observacionesNovedad;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setId_origen(String id_origen) {
        this.id_origen = id_origen;
    }

    public String getId_origen() {
        return id_origen;
    }

    public void setTrasmitido(Integer trasmitido) {
        this.trasmitido = trasmitido;
    }

    public Integer getTrasmitido() {
        return trasmitido;
    }
}
