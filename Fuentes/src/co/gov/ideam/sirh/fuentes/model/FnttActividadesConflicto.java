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
  @NamedQuery(name = "FnttActividadesConflicto.findAll", query = "select o from FnttActividadesConflicto o")
})
@Table(name = "FNTT_ACTIVIDADES_CONFLICTO")
@IdClass(FnttActividadesConflictoPK.class)
public class FnttActividadesConflicto implements Serializable {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idactividad;
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private Long idconflicto;
    @ManyToOne
    @JoinColumn(name = "IDACTIVIDAD")
    private FnttActividadesEconomicas fnttActividadesEconomicas;
    @ManyToOne
    @JoinColumn(name = "IDCONFLICTO")
    private FnttConflicto fnttConflicto;

    public FnttActividadesConflicto() {
    }

    public FnttActividadesConflicto(FnttActividadesEconomicas fnttActividadesEconomicas,
                                    FnttConflicto fnttConflicto) {
        this.fnttActividadesEconomicas = fnttActividadesEconomicas;
        this.fnttConflicto = fnttConflicto;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public Long getIdconflicto() {
        return idconflicto;
    }

    public void setIdconflicto(Long idconflicto) {
        this.idconflicto = idconflicto;
    }

    public FnttActividadesEconomicas getFnttActividadesEconomicas() {
        return fnttActividadesEconomicas;
    }

    public void setFnttActividadesEconomicas(FnttActividadesEconomicas fnttActividadesEconomicas) {
        this.fnttActividadesEconomicas = fnttActividadesEconomicas;
        if (fnttActividadesEconomicas != null) {
            this.idactividad = fnttActividadesEconomicas.getIdactividad();
        }
    }

    public FnttConflicto getFnttConflicto() {
        return fnttConflicto;
    }

    public void setFnttConflicto(FnttConflicto fnttConflicto) {
        this.fnttConflicto = fnttConflicto;
        if (fnttConflicto != null) {
            this.idconflicto = fnttConflicto.getId();
        }
    }
}
