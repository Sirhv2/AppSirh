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
  @NamedQuery(name = "FnttLogrosPracticas.findAll", query = "select o from FnttLogrosPracticas o")
})
@Table(name = "FNTT_LOGROS_PRACTICAS")
@IdClass(FnttLogrosPracticasPK.class)
public class FnttLogrosPracticas implements Serializable {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idbuenapraactica;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idlogro;
    @ManyToOne
    @JoinColumn(name = "IDLOGRO")
    private FnttLogros fnttLogros;
    @ManyToOne
    @JoinColumn(name = "IDBUENAPRAACTICA")
    private FnttBuenasPracticas fnttBuenasPracticas;

    public FnttLogrosPracticas() {
    }

    public FnttLogrosPracticas(FnttBuenasPracticas fnttBuenasPracticas, FnttLogros fnttLogros) {
        this.fnttBuenasPracticas = fnttBuenasPracticas;
        this.fnttLogros = fnttLogros;
    }

    public Long getIdbuenapraactica() {
        return idbuenapraactica;
    }

    public void setIdbuenapraactica(Long idbuenapraactica) {
        this.idbuenapraactica = idbuenapraactica;
    }

    public Long getIdlogro() {
        return idlogro;
    }

    public void setIdlogro(Long idlogro) {
        this.idlogro = idlogro;
    }

    public FnttLogros getFnttLogros() {
        return fnttLogros;
    }

    public void setFnttLogros(FnttLogros fnttLogros) {
        this.fnttLogros = fnttLogros;
        if (fnttLogros != null) {
            this.idlogro = fnttLogros.getIdlogro();
        }
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
}
