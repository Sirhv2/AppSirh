package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Utilizado para cargar datos desde la vista y mostrar una lista de todos los
 * permisos de vertimiento registrados en el sistema
 */

@Entity
@NamedQueries({
   @NamedQuery(name = "PermisoVertimientoDetalle.findAll", query = "select o from PermisoVertimientoDetalle o"),
   @NamedQuery(name = "PermisoVertimientoDetalle.findByExpediente", query = "select o from PermisoVertimientoDetalle o where upper(o.numeroExpediente) like :numeroExpediente"),
   @NamedQuery(name = "PermisoVertimientoDetalle.findByExpedienteAutoridad", query = "select o from PermisoVertimientoDetalle o where upper(o.numeroExpediente) like :numeroExpediente and o.codigoAutoridad =:codigoAutoridad"),
   @NamedQuery(name = "PermisoVertimientoDetalle.findByOneExpedienteAutoridad", query = "select o from PermisoVertimientoDetalle o where upper(o.numeroExpediente) = :numeroExpediente and o.codigoAutoridad =:codigoAutoridad"),
   @NamedQuery(name = "PermisoVertimientoDetalle.findByAutoridad", query = "select o from PermisoVertimientoDetalle o where o.codigoAutoridad =:codigoAutoridad"),
   @NamedQuery(name = "PermisoVertimientoDetalle.findByExpedienteResolucionPredioAutoridad", query = "select o from PermisoVertimientoDetalle o where upper(o.numeroExpediente) like :numeroExpediente and upper(o.resolucionPermisoVertimiento) = :resolucionPermisoVertimiento and o.codigoPredio = :codigoPredio and o.codigoAutoridad =:codigoAutoridad")
 })
@Table(name = "rurv_permisos_detalle")
public class PermisoVertimientoDetalle implements Serializable{
    
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
    
    @Column(name = "predio", nullable = true)
    private String nombrePredio;
    @Column(name = "usuario", nullable = true)
    private String nombreUsuario;
    @Column(name = "id_usuario", nullable = true)
    private Long codigoUsuario;
    @Column(name = "autoridad", nullable = true)
    private Integer codigoAutoridad;    
    
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
    
    @Column(name="total_vertimientos", nullable = false)    
    private Long totalVertimientos;
    
    
    //Cabril 04/03/2015
       @Transient
       private Integer num;
       
    @Transient
    private String erroresValidacion;    
    @Transient
    private Boolean validado = null;      
    
    
    @Column(name = "Centro_Poblado", nullable = true)
    private String centroPoblado;
    @Column(name = "municipio", nullable = true)
    private String Municipio;
    @Column(name = "departamento", nullable = true)
    private String Departamento;
    @Column(name = "nombre_vertimiento", nullable = true)
    private String nombreVertimiento;
    @Column(name = "nombre_fuente", nullable = true)
    private String nombreFuente;
    @Column(name = "tipo_fuente", nullable = true)
    private String tipoFuente;
    @Column(name = "nombre_sr", nullable = true)
    private String sistemaReferencia;
    @Column(name = "grados_lat_vert")
    private Integer gradosLatVert;
    @Column(name = "minutos_lat_vert")
    private Integer minutosLatVert;
    @Column(name = "segundos_lat_vert")
    private Double segundosLatVert;
    @Column(name = "grados_long_vert")
    private Integer gradosLongVert;
    @Column(name = "minutos_long_vert")
    private Integer minutosLongVert;
    @Column(name = "segundos_long_vert")
    private Double segundosLongVert;
    @Column(name = "altitud")
    private Double altitudVert;
  
  @Transient
  String grados;
  @Transient
  String minutos;
    
    public PermisoVertimientoDetalle() {
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

    public Calendar getFechaExpedicionInicioTramite() {
        return fechaExpedicionInicioTramite;
    }

    public void setFechaExpedicionInicioTramite(Calendar fechaExpedicionInicioTramite) {
        this.fechaExpedicionInicioTramite = fechaExpedicionInicioTramite;
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

    public Calendar getFechaExpedicionPlan() {
        return fechaExpedicionPlan;
    }

    public void setFechaExpedicionPlan(Calendar fechaExpedicionPlan) {
        this.fechaExpedicionPlan = fechaExpedicionPlan;
    }

    public String getResolucionAprobacionPlan() {
        return resolucionAprobacionPlan;
    }

    public void setResolucionAprobacionPlan(String resolucionAprobacionPlan) {
        this.resolucionAprobacionPlan = resolucionAprobacionPlan;
    }

    public Calendar getFechaExpedicionAprobacionPlan() {
        return fechaExpedicionAprobacionPlan;
    }

    public void setFechaExpedicionAprobacionPlan(Calendar fechaExpedicionAprobacionPlan) {
        this.fechaExpedicionAprobacionPlan = fechaExpedicionAprobacionPlan;
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

    public Calendar getFechaExpedicionPermiso() {
        return fechaExpedicionPermiso;
    }

    public void setFechaExpedicionPermiso(Calendar fechaExpedicionPermiso) {
        this.fechaExpedicionPermiso = fechaExpedicionPermiso;
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

    public Calendar getFechaExpedicionPlanos() {
        return fechaExpedicionPlanos;
    }

    public void setFechaExpedicionPlanos(Calendar fechaExpedicionPlanos) {
        this.fechaExpedicionPlanos = fechaExpedicionPlanos;
    }

    public String getResolucionAprobacionObras() {
        return resolucionAprobacionObras;
    }

    public void setResolucionAprobacionObras(String resolucionAprobacionObras) {
        this.resolucionAprobacionObras = resolucionAprobacionObras;
    }

    public Calendar getFechaExpedicionObras() {
        return fechaExpedicionObras;
    }

    public void setFechaExpedicionObras(Calendar fechaExpedicionObras) {
        this.fechaExpedicionObras = fechaExpedicionObras;
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

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Integer codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    public String getDetalleNovedad(){
        if (this.getTipoNovedad()!=null){
            if(this.getTipoNovedad().equalsIgnoreCase(TipoModificacionTO.MODIFICACION)){
                return "Modificada por " + this.getNumeroExpedienteNovedad();
            }
            if(this.getTipoNovedad().equalsIgnoreCase(TipoModificacionTO.RENOVACION)){
                return "Renovada por " + this.getNumeroExpedienteNovedad();
            }            
            if(this.getTipoNovedad().equalsIgnoreCase(TipoModificacionTO.REVOCACION)){
                return "Revocada por " + this.getNumeroExpedienteNovedad();
            }
            if(this.getTipoNovedad().equalsIgnoreCase(TipoModificacionTO.TRASPASO)){
                return "Traspasada por " + this.getNumeroExpedienteNovedad();
            }            
        }
        return "";
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

    public Long getTotalVertimientos() {
        return totalVertimientos;
    }

    public void setTotalVertimientos(Long totalVertimientos) {
        this.totalVertimientos = totalVertimientos;
    }

    public String getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(String erroresValidacion) {
        this.erroresValidacion = erroresValidacion;
    }
    
    public boolean isValid(){
        if(validado==null){
            boolean valido = true;        
            if(this.resolucionInicioTramite==null || this.resolucionInicioTramite.length()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos sobre Resolución Inicio Trámite");            
                valido = false;
            }
            if(this.resolucionSolicitudPlanCumplimiento==null || this.resolucionSolicitudPlanCumplimiento.length()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos de Plan de Cumplimiento");            
                valido = false;
            }            
            if(this.resolucionPsmv==null || this.resolucionPsmv.length()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos de PSMV");            
                valido = false;
            }             
            if(this.resolucionAprobacionPlanos==null || this.resolucionAprobacionPlanos.length()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos de resolución de aprobación de planos");            
                valido = false;
            }             
            if(this.resolucionAprobacionObras==null || this.resolucionAprobacionObras.length()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos de resolución de aprobación de obras");            
                valido = false;
            }       
            if(this.totalVertimientos.longValue()==0){
                this.setErroresValidacion("El permiso de vertimiento no tiene datos de vertimientos");            
                valido = false;
            }
            validado = Boolean.valueOf(valido);
            
        }
        return validado.booleanValue();        
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

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public String getCentroPoblado() {
        return centroPoblado;
    }
  
    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }
    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }
  
    public String getMunicipio() {
        return Municipio;
    }
  
    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }
  
    public String getDepartamento() {
        return Departamento;
    }
    public void setNombreVertimiento(String nombreVertimiento) {
        this.nombreVertimiento = nombreVertimiento;
    }
    public String getNombreVertimiento() {
        return nombreVertimiento;
    }
    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }
  
    public String getNombreFuente() {
        return nombreFuente;
    }
    public void setTipoFuente(String tipoFuente) {
        this.tipoFuente = tipoFuente;
    }
  
    public String getTipoFuente() {
        return tipoFuente;
    }
    public void setSistemaReferencia(String sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }
  
    public String getSistemaReferencia() {
        return sistemaReferencia;
    }
  public void setGradosLatVert(Integer gradosLatVert) {
      this.gradosLatVert = gradosLatVert;
  }

  public Integer getGradosLatVert() {
      return gradosLatVert;
  }

  public void setMinutosLatVert(Integer minutosLatVert) {
      this.minutosLatVert = minutosLatVert;
  }

  public Integer getMinutosLatVert() {
      return minutosLatVert;
  }

  public void setSegundosLatVert(Double segundosLatVert) {
      this.segundosLatVert = segundosLatVert;
  }

  public Double getSegundosLatVert() {
      return segundosLatVert;
  }

  public void setGradosLongVert(Integer gradosLongVert) {
      this.gradosLongVert = gradosLongVert;
  }

  public Integer getGradosLongVert() {
      return gradosLongVert;
  }

  public void setMinutosLongVert(Integer minutosLongVert) {
      this.minutosLongVert = minutosLongVert;
  }

  public Integer getMinutosLongVert() {
      return minutosLongVert;
  }

  public void setSegundosLongVert(Double segundosLongVert) {
      this.segundosLongVert = segundosLongVert;
  }

  public Double getSegundosLongVert() {
      return segundosLongVert;
  }

  public void setAltitudVert(Double altitudVert) {
      this.altitudVert = altitudVert;
  }

  public Double getAltitudVert() {
      return altitudVert;
  }


  public void setMinutos(String minutos) {
      this.minutos = minutos;
  }

  public String getMinutos() {
      minutos = getMinutosLatVert().toString();
      minutos = minutos.substring(0, minutos.indexOf("."));
      return minutos;
  }

  public void setGrados(String grados) {
      this.grados = grados;
  }

  public String getGrados() {
      grados = getGradosLatVert().toString();
      grados = grados.substring(0, grados.indexOf("."));
      return grados;
  }
 
}
