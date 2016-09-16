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
  @NamedQuery(name = "FnttEstrategiasComunicacion.findAll", query = "select o from FnttEstrategiasComunicacion o")
})
@Table(name = "FNTT_ESTRATEGIAS_COMUNICACION")
public class FnttEstrategiasComunicacion implements Serializable {
    @Column(nullable = false)
    private String estrategia;
    @Id
    @Column(nullable = false)
    private Long idestrategia;
    @OneToMany(mappedBy = "fnttEstrategiasComunicacion")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;

    public FnttEstrategiasComunicacion() {
    }

    public FnttEstrategiasComunicacion(String estrategia, Long idestrategia) {
        this.estrategia = estrategia;
        this.idestrategia = idestrategia;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    public Long getIdestrategia() {
        return idestrategia;
    }

    public void setIdestrategia(Long idestrategia) {
        this.idestrategia = idestrategia;
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttEstrategiasComunicacion(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttEstrategiasComunicacion(null);
        return fnttBuenasPracticas;
    }
}
