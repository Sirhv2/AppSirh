package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "FnttCostos.findAll", query = "select o from FnttCostos o order by o.idcosto")
})
@Table(name = "FNTT_COSTOS")
public class FnttCostos implements Serializable {
    @Column(nullable = false)
    private String costo;
    @Id
    @Column(nullable = false)
    private Long idcosto;
    @OneToMany(mappedBy = "fnttCostos")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;

    public FnttCostos() {
    }

    public FnttCostos(String costo, Long idcosto) {
        this.costo = costo;
        this.idcosto = idcosto;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public Long getIdcosto() {
        return idcosto;
    }

    public void setIdcosto(Long idcosto) {
        this.idcosto = idcosto;
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttCostos(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttCostos(null);
        return fnttBuenasPracticas;
    }
}
