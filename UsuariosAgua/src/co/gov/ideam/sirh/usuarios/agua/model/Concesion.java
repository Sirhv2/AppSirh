package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import java.util.Calendar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries( { 
        @NamedQuery(name = "Concesion.findAll",query = "select o from Concesion o where o.codigoPredio = :codigoPredio and o.no_valida is null order by o.numeroExpediente"),//Pilar
        @NamedQuery(name = "Concesion.find", query = "select o from Concesion o where o.codigo = :codigo"),
        @NamedQuery(name = "Concesion.findByExpediente", query = "select o from Concesion o where upper(o.numeroExpediente) = :numeroExpediente"),
        @NamedQuery(name = "Concesion.findByResolucion", query = "select o from Concesion o where upper(o.resolucionCaudal) = :resolucionCaudal"),
        @NamedQuery(name = "Concesion.findByIdAndAA", query = "select o from Concesion o where upper(o.numeroExpediente) = :numeroExpediente and o.codigoPredio in (" +
                                                              " select p.codigo from Predio p where codigoUsuario in (select u.codigo from UsuarioAgua u where codigoAutoridad =:idAutoridad))"),//Pilar
        @NamedQuery(name = "Concesion.findAllVigentes", query = "select o from Concesion o where fechaFin >= sysdate and  o.no_valida is null"),//Pilar
        @NamedQuery(name = "Concesion.findAllVencidas", query = "select o from Concesion o where fechaFin < sysdate and o.no_valida is null"),//Pilar
        @NamedQuery(name = "Concesion.findAllSinEstado", query = "select o from Concesion o where fechaFin is null and o.no_valida is null"),//Pilar
        @NamedQuery(name = "Concesion.findByPredioResolucion", query = "select o from Concesion o where o.codigoPredio = :codigoPredio AND upper(o.resolucionCaudal) = :resolucionCaudal"),
        @NamedQuery(name = "Concesion.findByPredioSinConc", query = "select o from Concesion o where o.codigoPredio = :codigoPredio and no_valida is not null")//Pilar
        } )
@Table(name = "rurt_concesiones")
public class Concesion implements Serializable {
    @GenericGenerator(name = "concesiones_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value =
                                   "codigoAutoridadAmbiental")
                , @Parameter(name = "Codigo", value = "codigo")
                ,
                @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_usuario_agua")
                } )

    @Id
    @Column(name = "id", nullable = false)
    private Long codigo;
    @Column(name = "num_expediente", nullable = false)
    private String numeroExpediente;
    @Column(name = "num_res_caudal", nullable = false)
    private String resolucionCaudal;
    @Column(name = "fecha_not_caudal", nullable = true)
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
    @Transient
    private Long codigoAutoridadAmbiental;
    @Column(name="archivo", nullable = true)
    private byte[] archivo;
    @Column(name="tipo_archivo", nullable = true, length = 10)
    private String tipoArchivo;    
    @Column(name = "fecha_inicio", nullable = true)
    private Calendar fechaInicio;
    @Column(name = "fecha_fin", nullable = true)
    private Calendar fechaFin;
    @Column(name = "caudal_concesionado", nullable = true)
    private Double caudalConcesionado;
    @Column(name = "caudal_anio_seco", nullable = true)
    private Double caudalAnioSeco;
    @Column(name = "caudal_anio_medio", nullable = true)
    private Double caudalAnioMedio;
    @Column(name = "caudal_anio_humedo", nullable = true)
    private Double caudalAnioHumedo;
    
    @Transient
    private List listaCaptaciones;
    @Transient
    private List fechaVigencia;
        
    // Informacion de la novedad
    @Column(name = "TIPO_NOVEDAD", nullable = true)
    private String tipoNovedad;
    @Column(name = "NUM_EXPEDIENTE_NOVEDAD", nullable = true)
    private String numeroExpedienteNovedad;
    @Column(name = "FECHA_EXP_NOVEDAD", nullable = true)
    private Calendar fechaExpedicionNovedad;
    @Column(name = "OBSERVACIONES_NOVEDAD", nullable = true)
    private String observacionesNovedad;
    //Pilar
    @Column(name = "no_valida")
    private Integer no_valida;

    
    //** Lisbeth Integracion datos 
    @Column(name="origen")
    private String origen;    
    @Column(name="id_origen")
    private String id_origen;
    
    
    
    public Concesion() {
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

    public Calendar getFechaExpedicionCaudal() {
        return fechaExpedicionCaudal;
    }

    public void setFechaExpedicionCaudal(Calendar fechaExpedicionCaudal) {
        this.fechaExpedicionCaudal = fechaExpedicionCaudal;
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

    public void setListaCaptaciones(List listaCaptaciones) {
        this.listaCaptaciones = listaCaptaciones;
    }

    public List getListaCaptaciones() {
        return listaCaptaciones;
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

    public Double getCaudalConcesionado() {
        return caudalConcesionado;
    }

    public void setCaudalConcesionado(Double caudalConcesionado) {
        this.caudalConcesionado = caudalConcesionado;
    }

    public Double getCaudalAnioSeco() {
        return caudalAnioSeco;
    }

    public void setCaudalAnioSeco(Double caudalAnioSeco) {
        this.caudalAnioSeco = caudalAnioSeco;
    }

    public Double getCaudalAnioMedio() {
        return caudalAnioMedio;
    }

    public void setCaudalAnioMedio(Double caudalAnioMedio) {
        this.caudalAnioMedio = caudalAnioMedio;
    }

    public Double getCaudalAnioHumedo() {
        return caudalAnioHumedo;
    }

    public void setCaudalAnioHumedo(Double caudalAnioHumedo) {
        this.caudalAnioHumedo = caudalAnioHumedo;
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

    public void setNo_valida(Integer no_valida) {
        this.no_valida = no_valida;
    }

    public Integer getNo_valida() {
        return no_valida;
    }


    public void setFechaVigencia(List fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public List getFechaVigencia() {
        return fechaVigencia;
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
}
