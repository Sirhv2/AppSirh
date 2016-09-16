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
 * Utilizado para cargar datos desde la vista y mostrar una lista de todas las
 * concesiones registradas en el sistema
 */
 @Entity
 @NamedQueries({
   @NamedQuery(name = "ConcesionTO.findAll", query = "select o from ConcesionTO o"),
   @NamedQuery(name = "ConcesionTO.findByExpediente", query = "select o from ConcesionTO o where upper(o.numeroExpediente) like :numeroExpediente"),
   @NamedQuery(name = "ConcesionTO.findByExpedienteAutoridad", query = "select o from ConcesionTO o where upper(o.numeroExpediente) like :numeroExpediente and o.codigoAutoridad =:codigoAutoridad"),
   @NamedQuery(name = "ConcesionTO.findByAutoridad", query = "select o from ConcesionTO o where o.codigoAutoridad =:codigoAutoridad"),
   @NamedQuery(name = "ConcesionTO.findByExpedienteResolucionPredioAutoridad", query = "select o from ConcesionTO o where upper(o.numeroExpediente) like :numeroExpediente and upper(o.resolucionCaudal) = :resolucionCaudal and o.codigoPredio = :codigoPredio and o.codigoAutoridad =:codigoAutoridad")
 })
 @Table(name = "rurv_concesiones")
public class ConcesionTO implements Serializable{
    @Id    
    @Column(name = "id", nullable = false)
    private Long codigo;
    @Column(name = "num_expediente", nullable = false)
    private String numeroExpediente;
    @Column(name = "num_res_caudal", nullable = false)
    private String resolucionCaudal;
    @Column(name = "caudal_concesionado", nullable = false)
    private Double caudal_concesionado;
    @Column(name = "fecha_inicio", nullable = false)
    private Calendar fecha_inicio;
    @Column(name = "fecha_fin", nullable = false)
    private Calendar fecha_fin;
    @Column(name = "fecha_not_caudal", nullable = false)
    private Calendar fechaNotificacionCaudal;
    @Column(name = "fecha_exp_caudal", nullable = true)
    private Calendar fechaExpedicionCaudal;
    @Column(name = "num_res_planos", nullable = true)
    private String resolucionPlanos;
    @Column(name = "fecha_not_planos", nullable = true)
    private Calendar fechaNotificacionPlanos;
    @Column(name = "fecha_exp_planos", nullable = true)
    private Calendar fechaExpedicionPlanos;
    @Column(name = "num_res_obras", nullable = true)
    private String resolucionObras;
    @Column(name = "fecha_not_obras", nullable = true)
    private Calendar fechaNotificacionObras;
    @Column(name = "fecha_exp_obras", nullable = true)
    private Calendar fechaExpedicionObras;
    @Column(name = "id_predio", nullable = true)
    private Long codigoPredio;
    @Column(name = "predio", nullable = true)
    private String nombrePredio;
    
  @Column(name = "Centro_Poblado", nullable = true)
  private String centroPoblado;
  @Column(name = "Municipio", nullable = true)
  private String municipio;
  @Column(name = "Departamento", nullable = true)
  private String departamento;
  
  @Column(name = "cod_sistema_referencia")
  private Integer sistemaReferencia;
  @Column(name = "sistema_referencia")
  private String nombreSistemaReferencia;
  @Column(name = "grados_lat_concesion")
  private Integer gradosLat;
  @Column(name = "minutos_lat_concesion")
  private Integer minutosLat;
  @Column(name = "segundos_lat_concesion")
  private Double segundosLat;
  @Column(name = "grados_long_concesion")
  private Integer gradosLong;
  @Column(name = "minutos_long_concesion")
  private Integer minutosLong;
  @Column(name = "segundos_long_concesion")
  private Double segundosLong;
  @Column(name = "altitud_concesion")
  private Double altitud;
  @Transient
  String grados;
  @Transient
  String minutos;
    
    
    @Column(name = "usuario", nullable = true)
    private String nombreUsuario;
    @Column(name = "id_usuario", nullable = true)
    private Long codigoUsuario;
    @Column(name = "autoridad", nullable = true)
    private Long codigoAutoridad;
    
    // Informacion de la novedad
    @Column(name = "TIPO_NOVEDAD", nullable = true)
    private String tipoNovedad;
    @Column(name = "NUM_EXPEDIENTE_NOVEDAD", nullable = true)
    private String numeroExpedienteNovedad;
    @Column(name = "FECHA_EXP_NOVEDAD", nullable = true)
    private Calendar fechaExpedicionNovedad;
    @Column(name = "OBSERVACIONES_NOVEDAD", nullable = true)
    private String observacionesNovedad;
    @Column(name="total_captaciones", nullable = false)    
    private Long totalCaptaciones;
    
    @Transient
    private String erroresValidacion;    
    @Transient
    private Boolean validado = null; 
    //Pilar
    @Column(name = "no_valida")
    private Integer no_valida;
    
    //Cabril 04/03/2015
    @Transient
    private Integer num;
    
    public ConcesionTO() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getResolucionCaudal() {
        return resolucionCaudal;
    }

    public void setResolucionCaudal(String resolucionCaudal) {
        this.resolucionCaudal = resolucionCaudal;
    }

    public Calendar getFechaNotificacionCaudal() {
        return fechaNotificacionCaudal;
    }

    public void setFechaNotificacionCaudal(Calendar fechaNotificacionCaudal) {
        this.fechaNotificacionCaudal = fechaNotificacionCaudal;
    }

    public Calendar getFechaExpedicionCaudal() {
        return fechaExpedicionCaudal;
    }

    public void setFechaExpedicionCaudal(Calendar fechaExpedicionCaudal) {
        this.fechaExpedicionCaudal = fechaExpedicionCaudal;
    }

    public String getResolucionPlanos() {
        return resolucionPlanos;
    }

    public void setResolucionPlanos(String resolucionPlanos) {
        this.resolucionPlanos = resolucionPlanos;
    }

    public Calendar getFechaNotificacionPlanos() {
        return fechaNotificacionPlanos;
    }

    public void setFechaNotificacionPlanos(Calendar fechaNotificacionPlanos) {
        this.fechaNotificacionPlanos = fechaNotificacionPlanos;
    }

    public Calendar getFechaExpedicionPlanos() {
        return fechaExpedicionPlanos;
    }

    public void setFechaExpedicionPlanos(Calendar fechaExpedicionPlanos) {
        this.fechaExpedicionPlanos = fechaExpedicionPlanos;
    }

    public String getResolucionObras() {
        return resolucionObras;
    }

    public void setResolucionObras(String resolucionObras) {
        this.resolucionObras = resolucionObras;
    }

    public Calendar getFechaNotificacionObras() {
        return fechaNotificacionObras;
    }

    public void setFechaNotificacionObras(Calendar fechaNotificacionObras) {
        this.fechaNotificacionObras = fechaNotificacionObras;
    }

    public Calendar getFechaExpedicionObras() {
        return fechaExpedicionObras;
    }

    public void setFechaExpedicionObras(Calendar fechaExpedicionObras) {
        this.fechaExpedicionObras = fechaExpedicionObras;
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
    
    //dl
    public String getCentroPoblado() {
        return centroPoblado;
    }

    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }
    public String getMunicipio() {
        return municipio;
    }
  
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public String getDepartamento() {
        return departamento;
    }
  
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
  }
    
  public Integer getSistemaReferencia() {
      return sistemaReferencia;
  }

  public void setSistemaReferencia(Integer sistemaReferencia) {
      this.sistemaReferencia = sistemaReferencia;
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

  public void setAltitud(Double altitud) {
      this.altitud = altitud;
  }

  public Double getAltitud() {
      return altitud;
  }


  public void setMinutos(String minutos) {
      this.minutos = minutos;
  }

  public String getMinutos() {
      minutos = getMinutosLat().toString();
      minutos = minutos.substring(0, minutos.indexOf("."));
      return minutos;
  }

  public void setGrados(String grados) {
      this.grados = grados;
  }

  public String getGrados() {
      grados = getGradosLat().toString();
      grados = grados.substring(0, grados.indexOf("."));
      return grados;
  }

  public void setNombreSistemaReferencia(String nombreSistemaReferencia) {
      this.nombreSistemaReferencia = nombreSistemaReferencia;
  }

  public String getNombreSistemaReferencia() {
      return nombreSistemaReferencia;
  }
  
    //dl


    
    
    
    
    
    
    

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

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public void setCaudal_concesionado(Double caudal_concesionado) {
        this.caudal_concesionado = caudal_concesionado;
    }

    public Double getCaudal_concesionado() {
        return caudal_concesionado;
    }

    public void setFecha_inicio(Calendar fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Calendar getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_fin(Calendar fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Calendar getFecha_fin() {
        return fecha_fin;
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
    
    public String getDetalleNovedad(){
        if (this.getTipoNovedad()!=null){
            if(this.tipoNovedad.equalsIgnoreCase(TipoModificacionTO.MODIFICACION)){
                return "Modificada por " + this.numeroExpedienteNovedad;
            }
            if(this.tipoNovedad.equalsIgnoreCase(TipoModificacionTO.RENOVACION)){
                return "Renovada por " + this.numeroExpedienteNovedad;
            }            
            if(this.tipoNovedad.equalsIgnoreCase(TipoModificacionTO.REVOCACION)){
                return "Revocada por " + this.numeroExpedienteNovedad;
            }
            if(this.tipoNovedad.equalsIgnoreCase(TipoModificacionTO.TRASPASO)){
                return "Traspasada por " + this.numeroExpedienteNovedad;
            }            
        }
        return "";
    }    
    
    public boolean isValid(){
        if(validado==null){
            boolean valido = true;        
            if(this.resolucionPlanos==null || this.resolucionPlanos.length()==0){
                this.setErroresValidacion("La Concesión no tiene datos de resolución de aprobación de planos");            
                valido = false;
            }
            if(this.resolucionObras==null || this.resolucionObras.length()==0){
                this.setErroresValidacion("La Concesión no tiene datos de resolución de aprobación de obras");            
                valido = false;
            }
            if(this.totalCaptaciones.longValue()==0){
                this.setErroresValidacion("La Concesión no tiene información de captaciones");            
                valido = false;
            }
            validado = Boolean.valueOf(valido);
        }
        return validado.booleanValue();        
    }

    public String getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(String erroresValidacion) {
        this.erroresValidacion = erroresValidacion;
    }

    public Long getTotalCaptaciones() {
        return totalCaptaciones;
    }

    public void setTotalCaptaciones(Long totalCaptaciones) {
        this.totalCaptaciones = totalCaptaciones;
    }

    public void setNo_valida(Integer no_valida) {
        this.no_valida = no_valida;
    }

    public Integer getNo_valida() {
        return no_valida;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }
}
