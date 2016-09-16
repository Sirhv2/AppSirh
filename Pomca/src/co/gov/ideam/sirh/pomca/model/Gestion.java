package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
@NamedQueries({
  @NamedQuery(name = "Gestion.findAll", query = "select o from Gestion o"),
  @NamedQuery(name = "Gestion.findByActividad", query = "select o from Gestion o where o.idActividad = :idActividad")
})
@Table(name = "pomt_gestion")

public class Gestion implements Serializable  {

    @GenericGenerator(name = "gestion_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_gestion")})
      
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "gestion_generator")        
    private Long id;

    @Transient
    private Long codigoAutoridad;
    
    @Column(name = "id_cuenca", nullable = false)
    private Long idCuenca;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "id_proyecto", nullable = false)
    private Long idProyecto;

    @Column(name = "id_actividad", nullable = false)
    private Long idActividad;

    @Column(name="fecha_reporte", nullable = false)
    private Timestamp fechaReporte;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name="fecha_inicio", nullable = false)
    private Timestamp fechaInicio;

    @Column(name="fecha_vigencia", nullable = false)
    private Timestamp fechaVigencia;

    @Column(name="presupuesto_ejecutado")
    private BigDecimal presupuestoEjecutado;

    @Column(name="observaciones")
    private String observaciones;


    public Gestion() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setIdCuenca(Long idCuenca) {
        this.idCuenca = idCuenca;
    }

    public Long getIdCuenca() {
        return idCuenca;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setFechaReporte(Timestamp fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Timestamp getFechaReporte() {
        return fechaReporte;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaVigencia(Timestamp fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Timestamp getFechaVigencia() {
        return fechaVigencia;
    }

    public void setPresupuestoEjecutado(BigDecimal presupuestoEjecutado) {
        this.presupuestoEjecutado = presupuestoEjecutado;
    }

    public BigDecimal getPresupuestoEjecutado() {
        return presupuestoEjecutado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
