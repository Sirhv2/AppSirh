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
  @NamedQuery(name = "FnttPrincipios.findAll", query = "select o from FnttPrincipios o order by o.idprincipio")
})
@Table(name = "FNTT_PRINCIPIOS")
public class FnttPrincipios implements Serializable {
    @Id
    @Column(nullable = false)
    private Long idprincipio;
    @Column(nullable = false)
    private String principio;
    @OneToMany(mappedBy = "fnttPrincipios")
    private List<FnttPrincipiosPracticas> fnttPrincipiosPracticasList;

    public FnttPrincipios() {
    }

    public FnttPrincipios(Long idprincipio, String principio) {
        this.idprincipio = idprincipio;
        this.principio = principio;
    }

    public Long getIdprincipio() {
        return idprincipio;
    }

    public void setIdprincipio(Long idprincipio) {
        this.idprincipio = idprincipio;
    }

    public String getPrincipio() {
        return principio;
    }

    public void setPrincipio(String principio) {
        this.principio = principio;
    }

    public List<FnttPrincipiosPracticas> getFnttPrincipiosPracticasList() {
        return fnttPrincipiosPracticasList;
    }

    public void setFnttPrincipiosPracticasList(List<FnttPrincipiosPracticas> fnttPrincipiosPracticasList) {
        this.fnttPrincipiosPracticasList = fnttPrincipiosPracticasList;
    }

    public FnttPrincipiosPracticas addFnttPrincipiosPracticas(FnttPrincipiosPracticas fnttPrincipiosPracticas) {
        getFnttPrincipiosPracticasList().add(fnttPrincipiosPracticas);
        fnttPrincipiosPracticas.setFnttPrincipios(this);
        return fnttPrincipiosPracticas;
    }

    public FnttPrincipiosPracticas removeFnttPrincipiosPracticas(FnttPrincipiosPracticas fnttPrincipiosPracticas) {
        getFnttPrincipiosPracticasList().remove(fnttPrincipiosPracticas);
        fnttPrincipiosPracticas.setFnttPrincipios(null);
        return fnttPrincipiosPracticas;
    }
}
