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
  @NamedQuery(name = "Indicador.findAll", query = "select o from Indicador o"),
  @NamedQuery(name = "Indicador.findByActividad", query = "select o from Indicador o where o.idActividad = :idActividad"),
  @NamedQuery(name = "Indicador.findById", query = "select o from Indicador o where o.id = :idIndicador")
})
@Table(name = "pomt_indicador")

public class Indicador  implements Serializable {
    // Table's Colummns
        
        @GenericGenerator(name = "indicador_generator", 
                          strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                          parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                        @Parameter(name = "Codigo", value = "id"),
                                        @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_indicador")})
          
        @Id
        @Column(name = "id", nullable = false)
        //@GeneratedValue(generator = "indicador_generator")        
        private Long id;

    @Transient
    private Long codigoAutoridad;

    @Column(name = "id_actividad", nullable = false)
    private Long idActividad;

    @Column(name="nombre")
    private String nombre;

    @Column(name="meta")
    private Integer meta;

    @Column(name="porc_cumplimiento")
    private Double porcCumplimiento;
    
    // Constructor

    public Indicador() {
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

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setPorcCumplimiento(Double porcCumplimiento) {
        this.porcCumplimiento = porcCumplimiento;
    }

    public Double getPorcCumplimiento() {
        return porcCumplimiento;
    }
}
