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
  @NamedQuery(name = "FnttCategorias.findAll", query = "select o from FnttCategorias o order by o.idcategoria")
})
@Table(name = "FNTT_CATEGORIAS")
public class FnttCategorias implements Serializable {
    @Column(nullable = false)
    private String categoria;
    @Id
    @Column(nullable = false)
    private Long idcategoria;
    @OneToMany(mappedBy = "fnttCategorias")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;

    public FnttCategorias() {
    }

    public FnttCategorias(String categoria, Long idcategoria) {
        this.categoria = categoria;
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttCategorias(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttCategorias(null);
        return fnttBuenasPracticas;
    }
}
