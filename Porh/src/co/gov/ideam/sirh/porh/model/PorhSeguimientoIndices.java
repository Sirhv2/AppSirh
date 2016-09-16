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
  @NamedQuery(name = "PorhSeguimientoIndices.findAllByIndicador", query = "select o from PorhSeguimientoIndices o where o.idIndice = :codigoIndicador order by o.fecha ASC"),
  @NamedQuery(name = "PorhSeguimientoIndices.deleteByPlan", query = "delete from PorhSeguimientoIndices o where o.idIndice in (select i.id from PorhIndices i where i.idPlan = :codigoPlan)"),
  @NamedQuery(name = "PorhSeguimientoIndices.deleteByIndicador", query = "delete from PorhSeguimientoIndices o where o.idIndice  = :codigoIndice)")
})
@Table(name = "PORH_SEGUIMIENTO_INDICES")
public class PorhSeguimientoIndices implements Serializable {
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})
    @Id
    //@GeneratedValue(generator = "porh_generator")            
    @Column(nullable = false)
    private Long id;    
    @Column(name="FECHA")
    private Date fecha;
    @Column(name="ID_INDICE", nullable = false)
    private Long idIndice;
    @Column(name="VALOR")
    private Double valor;

    @Transient
    private Long codigoAutoridad;

    public PorhSeguimientoIndices() {
    }

    public PorhSeguimientoIndices(Date fecha, 
                                  Long id, Long idIndice,
                                  Double valor) {
        this.setFecha(fecha);
        this.id = id;
        this.idIndice = idIndice;
        this.setValor(valor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdIndice() {
        return idIndice;
    }

    public void setIdIndice(Long idIndice) {
        this.idIndice = idIndice;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
