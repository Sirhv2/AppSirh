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
  @NamedQuery(name = "FnttExpresionesCulturales.findAll", query = "select o from FnttExpresionesCulturales o")
})
@Table(name = "FNTT_EXPRESIONES_CULTURALES")
public class FnttExpresionesCulturales implements Serializable {
    @Column(nullable = false)
    private String expresion;
    @Id
    @Column(nullable = false)
    private Long idexpresion;
    @OneToMany(mappedBy = "fnttExpresionesCulturales")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;

    public FnttExpresionesCulturales() {
    }

    public FnttExpresionesCulturales(String expresion, Long idexpresion) {
        this.expresion = expresion;
        this.idexpresion = idexpresion;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public Long getIdexpresion() {
        return idexpresion;
    }

    public void setIdexpresion(Long idexpresion) {
        this.idexpresion = idexpresion;
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttExpresionesCulturales(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttExpresionesCulturales(null);
        return fnttBuenasPracticas;
    }
}
