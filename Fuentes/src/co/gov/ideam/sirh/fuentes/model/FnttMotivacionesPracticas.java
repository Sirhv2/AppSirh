package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "FnttMotivacionesPracticas.findAll", query = "select o from FnttMotivacionesPracticas o")
})
@Table(name = "FNTT_MOTIVACIONES_PRACTICAS")
@IdClass(FnttMotivacionesPracticasPK.class)
public class FnttMotivacionesPracticas implements Serializable {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idbuenapraactica;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idmotivacion;
    @ManyToOne
    @JoinColumn(name = "IDBUENAPRAACTICA")
    private FnttBuenasPracticas fnttBuenasPracticas;
    @ManyToOne
    @JoinColumn(name = "IDMOTIVACION")
    private FnttMotivaciones fnttMotivaciones;

    public FnttMotivacionesPracticas() {
    }

    public FnttMotivacionesPracticas(FnttBuenasPracticas fnttBuenasPracticas,
                                     FnttMotivaciones fnttMotivaciones) {
        this.fnttBuenasPracticas = fnttBuenasPracticas;
        this.fnttMotivaciones = fnttMotivaciones;
    }

    public Long getIdbuenapraactica() {
        return idbuenapraactica;
    }

    public void setIdbuenapraactica(Long idbuenapraactica) {
        this.idbuenapraactica = idbuenapraactica;
    }

    public Long getIdmotivacion() {
        return idmotivacion;
    }

    public void setIdmotivacion(Long idmotivacion) {
        this.idmotivacion = idmotivacion;
    }

    public FnttBuenasPracticas getFnttBuenasPracticas() {
        return fnttBuenasPracticas;
    }

    public void setFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        this.fnttBuenasPracticas = fnttBuenasPracticas;
        if (fnttBuenasPracticas != null) {
            this.idbuenapraactica = fnttBuenasPracticas.getIdbuenapraactica();
        }
    }

    public FnttMotivaciones getFnttMotivaciones() {
        return fnttMotivaciones;
    }

    public void setFnttMotivaciones(FnttMotivaciones fnttMotivaciones) {
        this.fnttMotivaciones = fnttMotivaciones;
        if (fnttMotivaciones != null) {
            this.idmotivacion = fnttMotivaciones.getIdmotivacion();
        }
    }
}
