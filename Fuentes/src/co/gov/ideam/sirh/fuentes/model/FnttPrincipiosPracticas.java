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
  @NamedQuery(name = "FnttPrincipiosPracticas.findAll", query = "select o from FnttPrincipiosPracticas o")
})
@Table(name = "FNTT_PRINCIPIOS_PRACTICAS")
@IdClass(FnttPrincipiosPracticasPK.class)
public class FnttPrincipiosPracticas implements Serializable {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idbuenapraactica;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idprincipio;
    @ManyToOne
    @JoinColumn(name = "IDBUENAPRAACTICA")
    private FnttBuenasPracticas fnttBuenasPracticas;
    @ManyToOne
    @JoinColumn(name = "IDPRINCIPIO")
    private FnttPrincipios fnttPrincipios;

    public FnttPrincipiosPracticas() {
    }

    public FnttPrincipiosPracticas(FnttBuenasPracticas fnttBuenasPracticas,
                                   FnttPrincipios fnttPrincipios) {
        this.fnttBuenasPracticas = fnttBuenasPracticas;
        this.fnttPrincipios = fnttPrincipios;
    }

    public Long getIdbuenapraactica() {
        return idbuenapraactica;
    }

    public void setIdbuenapraactica(Long idbuenapraactica) {
        this.idbuenapraactica = idbuenapraactica;
    }

    public Long getIdprincipio() {
        return idprincipio;
    }

    public void setIdprincipio(Long idprincipio) {
        this.idprincipio = idprincipio;
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

    public FnttPrincipios getFnttPrincipios() {
        return fnttPrincipios;
    }

    public void setFnttPrincipios(FnttPrincipios fnttPrincipios) {
        this.fnttPrincipios = fnttPrincipios;
        if (fnttPrincipios != null) {
            this.idprincipio = fnttPrincipios.getIdprincipio();
        }
    }
}
