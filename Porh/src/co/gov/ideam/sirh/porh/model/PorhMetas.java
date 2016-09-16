package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.util.Date;

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
  @NamedQuery(name = "PorhMetas.findAllByTramo", query = "select o from PorhMetas o where o.idTramo = :codigoTramo"),
  @NamedQuery(name = "PorhMetas.find", query = "select o from PorhMetas o where o.id = :codigo"),
  @NamedQuery(name = "PorhMetas.deleteByPlan", query = "delete from PorhMetas o where o.idPlan = :codigoPlan")  
})
@Table(name = "PORH_METAS")
public class PorhMetas implements Serializable {
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})
    @Id
    //@GeneratedValue(generator = "porh_generator")            
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private Date fecha;    
    @Column(name="ID_PARAMETRO", nullable = false)
    private Long idParametro;
    @Column(name="ID_PLAN", nullable = false)
    private Long idPlan;
    @Column(name="ID_TRAMO", nullable = false)
    private Long idTramo;
    @Column(name="ID_UNIDAD", nullable = false)
    private Long idUnidad;
    @Column(name="VALOR_ACTUAL", nullable = false)
    private Double valorActual;
    @Column(name="VALOR_META", nullable = false)
    private Double valorMeta;
    @Transient
    private Long codigoAutoridad;
    public PorhMetas() {
    }

    public PorhMetas(Date fecha, Long id, Long idParametro, Long idPlan,
                     Long idTramo, Long idUnidad, Double valorActual,
                     Double valorMeta) {
        this.fecha = fecha;
        this.id = id;
        this.idParametro = idParametro;
        this.idPlan = idPlan;
        this.idTramo = idTramo;
        this.idUnidad = idUnidad;
        this.valorActual = valorActual;
        this.valorMeta = valorMeta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Long idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
