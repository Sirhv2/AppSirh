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
  @NamedQuery(name = "FnttLogros.findAll", query = "select o from FnttLogros o order by o.idlogro"),
  @NamedQuery(name = "FnttLogros.findAllByTipo", query = "select o from FnttLogros o where o.tipo = :tipo order by o.idlogro")
})
@Table(name = "FNTT_LOGROS")
public class FnttLogros implements Serializable {
    @Id
    @Column(nullable = false)
    private Long idlogro;
    @Column(nullable = false)
    private String logro;
    @Column(nullable = false)
    private String tipo;
    @OneToMany(mappedBy = "fnttLogros")
    private List<FnttLogrosConflicto> fnttLogrosConflictoList;
    @OneToMany(mappedBy = "fnttLogros")
    private List<FnttLogrosPracticas> fnttLogrosPracticasList;

    public FnttLogros() {
    }

    public FnttLogros(Long idlogro, String logro, String tipo) {
        this.idlogro = idlogro;
        this.logro = logro;
        this.tipo = tipo;
    }

    public Long getIdlogro() {
        return idlogro;
    }

    public void setIdlogro(Long idlogro) {
        this.idlogro = idlogro;
    }

    public String getLogro() {
        return logro;
    }

    public void setLogro(String logro) {
        this.logro = logro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<FnttLogrosConflicto> getFnttLogrosConflictoList() {
        return fnttLogrosConflictoList;
    }

    public void setFnttLogrosConflictoList(List<FnttLogrosConflicto> fnttLogrosConflictoList) {
        this.fnttLogrosConflictoList = fnttLogrosConflictoList;
    }

    public FnttLogrosConflicto addFnttLogrosConflicto(FnttLogrosConflicto fnttLogrosConflicto) {
        getFnttLogrosConflictoList().add(fnttLogrosConflicto);
        fnttLogrosConflicto.setFnttLogros(this);
        return fnttLogrosConflicto;
    }

    public FnttLogrosConflicto removeFnttLogrosConflicto(FnttLogrosConflicto fnttLogrosConflicto) {
        getFnttLogrosConflictoList().remove(fnttLogrosConflicto);
        fnttLogrosConflicto.setFnttLogros(null);
        return fnttLogrosConflicto;
    }

    public List<FnttLogrosPracticas> getFnttLogrosPracticasList() {
        return fnttLogrosPracticasList;
    }

    public void setFnttLogrosPracticasList(List<FnttLogrosPracticas> fnttLogrosPracticasList) {
        this.fnttLogrosPracticasList = fnttLogrosPracticasList;
    }

    public FnttLogrosPracticas addFnttLogrosPracticas(FnttLogrosPracticas fnttLogrosPracticas) {
        getFnttLogrosPracticasList().add(fnttLogrosPracticas);
        fnttLogrosPracticas.setFnttLogros(this);
        return fnttLogrosPracticas;
    }

    public FnttLogrosPracticas removeFnttLogrosPracticas(FnttLogrosPracticas fnttLogrosPracticas) {
        getFnttLogrosPracticasList().remove(fnttLogrosPracticas);
        fnttLogrosPracticas.setFnttLogros(null);
        return fnttLogrosPracticas;
    }
}
