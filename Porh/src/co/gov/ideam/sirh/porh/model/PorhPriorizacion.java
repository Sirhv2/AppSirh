package co.gov.ideam.sirh.porh.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
  @NamedQuery(name = "PorhPriorizacion.findByPlan", query = "select o from PorhPriorizacion o where o.codigoPlan = :codigoPlan"),
  @NamedQuery(name = "PorhPriorizacion.deleteByPlan", query = "delete from PorhPriorizacion o where o.codigoPlan = :codigoPlan")
})
@Table(name = "porh_priorizacion")
public class PorhPriorizacion implements Serializable {
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutgoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})    
    @Id
   // @GeneratedValue(generator = "porh_generator")        
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="id_lista", nullable = false)
    private Integer id_lista;
    @Column(name="id_plan", nullable = false)
    private Long codigoPlan;    
    @Transient        
    private Lista prioridad;
    @Transient
    private Long codigoAutgoridad;

    public PorhPriorizacion() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_lista() {
        return id_lista;
    }

    public void setId_lista(Integer id_lista) {
        this.id_lista = id_lista;
    }

    public Lista getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Lista prioridad) {
        this.prioridad = prioridad;
    }

    public Long getCodigoAutgoridad() {
        return codigoAutgoridad;
    }

    public void setCodigoAutgoridad(Long codigoAutgoridad) {
        this.codigoAutgoridad = codigoAutgoridad;
    }

    public Long getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(Long codigoPlan) {
        this.codigoPlan = codigoPlan;
    }
}
