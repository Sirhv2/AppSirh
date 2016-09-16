package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Proyecto.findAll", query = "select o from Proyecto o"),
  @NamedQuery(name = "Proyecto.findByPrograma", query = "select o from Proyecto o where o.idPrograma = :idPrograma"),
  @NamedQuery(name = "Proyecto.findById", query = "select o from Proyecto o where o.id = :idProyecto")

})
@Table(name = "pomt_proyecto")

public class Proyecto implements Serializable {
   
// Table's Colummns
    
    @GenericGenerator(name = "proyecto_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_proyecto")})
      
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "proyecto_generator")        
    private Long id;

    @Transient
    private Long codigoAutoridad;

    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name="nombre")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="fecha_inicio", nullable = false)
    private Timestamp fechaInicio;

    @Column(name="fecha_vigencia", nullable = false)
    private Timestamp fechaVigencia;

    @Column(name="presupuesto_asignado")
    private BigDecimal presupuestoAsignado;

    @Column(name="porc_cumplimiento")
    private Double porcCumplimiento;
    
    @Column(name="porc_ejecucion")
    private Double porcEjecucion;

    @Column(name="presupuesto_ejecutado")
    private BigDecimal presupuestoEjecutado;

// Constructor
    public Proyecto() {
    }


// Getters y Setters

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

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
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

    public void setPresupuestoAsignado(BigDecimal presupuestoAsignado) {
        this.presupuestoAsignado = presupuestoAsignado;
    }

    public BigDecimal getPresupuestoAsignado() {
        return presupuestoAsignado;
    }

    public void setPorcCumplimiento(Double porcCumplimiento) {
        this.porcCumplimiento = porcCumplimiento;
    }

    public Double getPorcCumplimiento() {
        return porcCumplimiento;
    }

    public void setPorcEjecucion(Double porcEjecucion) {
        this.porcEjecucion = porcEjecucion;
    }

    public Double getPorcEjecucion() {
        return porcEjecucion;
    }

    public void setPresupuestoEjecutado(BigDecimal presupuestoEjecutado) {
        this.presupuestoEjecutado = presupuestoEjecutado;
    }

    public BigDecimal getPresupuestoEjecutado() {
        return presupuestoEjecutado;
    }
}


