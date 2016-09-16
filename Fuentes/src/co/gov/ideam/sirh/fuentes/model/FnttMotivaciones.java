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
  @NamedQuery(name = "FnttMotivaciones.findAll", query = "select o from FnttMotivaciones o")
})
@Table(name = "FNTT_MOTIVACIONES")
public class FnttMotivaciones implements Serializable {
    @Id
    @Column(nullable = false)
    private Long idmotivacion;
    @Column(nullable = false)
    private String motivacion;
    @OneToMany(mappedBy = "fnttMotivaciones")
    private List<FnttMotivacionesPracticas> fnttMotivacionesPracticasList;

    public FnttMotivaciones() {
    }

    public FnttMotivaciones(Long idmotivacion, String motivacion) {
        this.idmotivacion = idmotivacion;
        this.motivacion = motivacion;
    }

    public Long getIdmotivacion() {
        return idmotivacion;
    }

    public void setIdmotivacion(Long idmotivacion) {
        this.idmotivacion = idmotivacion;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public List<FnttMotivacionesPracticas> getFnttMotivacionesPracticasList() {
        return fnttMotivacionesPracticasList;
    }

    public void setFnttMotivacionesPracticasList(List<FnttMotivacionesPracticas> fnttMotivacionesPracticasList) {
        this.fnttMotivacionesPracticasList = fnttMotivacionesPracticasList;
    }

    public FnttMotivacionesPracticas addFnttMotivacionesPracticas(FnttMotivacionesPracticas fnttMotivacionesPracticas) {
        getFnttMotivacionesPracticasList().add(fnttMotivacionesPracticas);
        fnttMotivacionesPracticas.setFnttMotivaciones(this);
        return fnttMotivacionesPracticas;
    }

    public FnttMotivacionesPracticas removeFnttMotivacionesPracticas(FnttMotivacionesPracticas fnttMotivacionesPracticas) {
        getFnttMotivacionesPracticasList().remove(fnttMotivacionesPracticas);
        fnttMotivacionesPracticas.setFnttMotivaciones(null);
        return fnttMotivacionesPracticas;
    }
}
