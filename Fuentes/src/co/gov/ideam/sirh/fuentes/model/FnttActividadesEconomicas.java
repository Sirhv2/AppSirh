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
  @NamedQuery(name = "FnttActividadesEconomicas.findAll", query = "select o from FnttActividadesEconomicas o")
})
@Table(name = "FNTT_ACTIVIDADES_ECONOMICAS")
public class FnttActividadesEconomicas implements Serializable {
    @Column(nullable = false)
    private String actividad;
    @Id
    @Column(nullable = false)
    private Long idactividad;
    @OneToMany(mappedBy = "fnttActividadesEconomicas")
    private List<FnttActividadesConflicto> fnttActividadesConflictoList;

    public FnttActividadesEconomicas() {
    }

    public FnttActividadesEconomicas(String actividad, Long idactividad) {
        this.actividad = actividad;
        this.idactividad = idactividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public List<FnttActividadesConflicto> getFnttActividadesConflictoList() {
        return fnttActividadesConflictoList;
    }

    public void setFnttActividadesConflictoList(List<FnttActividadesConflicto> fnttActividadesConflictoList) {
        this.fnttActividadesConflictoList = fnttActividadesConflictoList;
    }

    public FnttActividadesConflicto addFnttActividadesConflicto(FnttActividadesConflicto fnttActividadesConflicto) {
        getFnttActividadesConflictoList().add(fnttActividadesConflicto);
        fnttActividadesConflicto.setFnttActividadesEconomicas(this);
        return fnttActividadesConflicto;
    }

    public FnttActividadesConflicto removeFnttActividadesConflicto(FnttActividadesConflicto fnttActividadesConflicto) {
        getFnttActividadesConflictoList().remove(fnttActividadesConflicto);
        fnttActividadesConflicto.setFnttActividadesEconomicas(null);
        return fnttActividadesConflicto;
    }
}
