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
  @NamedQuery(name = "FnttProyectosEducativos.findAll", query = "select o from FnttProyectosEducativos o order by o.idproyecto")
})
@Table(name = "FNTT_PROYECTOS_EDUCATIVOS")
public class FnttProyectosEducativos implements Serializable {
    @Column(nullable = false)
    private String descripcion;
    @Id
    @Column(nullable = false)
    private Long idproyecto;
    @OneToMany(mappedBy = "fnttProyectosEducativos")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;

    public FnttProyectosEducativos() {
    }

    public FnttProyectosEducativos(String descripcion, Long idproyecto) {
        this.descripcion = descripcion;
        this.idproyecto = idproyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttProyectosEducativos(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttProyectosEducativos(null);
        return fnttBuenasPracticas;
    }
}
