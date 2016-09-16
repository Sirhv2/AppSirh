package co.gov.ideam.sirh.parametros.model;

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
  @NamedQuery(name = "PartZonificCategorias.findAll", query = "select o from PartZonificCategorias o")
})
@Table(name = "part_zonific_categorias")
public class PartZonificCategorias implements Serializable {
    /**
     * Constante para identificar Areas
     */
    public static final int AREA = 1;
    /**
     * Constante para identificar Zonas
     */
    public static final int ZONA = 2;
    /**
     * Constante para identificar subzonas
     */
    public static final int SUBZONA = 3;
    
    @Id
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="valor")
    private String valor;
    @OneToMany(mappedBy = "partZonificCategorias")
    private List<PartZonificListas> partZonificListasList;

    public PartZonificCategorias() {
    }

    public PartZonificCategorias(Integer id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<PartZonificListas> getPartZonificListasList() {
        return partZonificListasList;
    }

    public void setPartZonificListasList(List<PartZonificListas> partZonificListasList) {
        this.partZonificListasList = partZonificListasList;
    }

    public PartZonificListas addPartZonificListas(PartZonificListas partZonificListas) {
        getPartZonificListasList().add(partZonificListas);
        partZonificListas.setPartZonificCategorias(this);
        return partZonificListas;
    }

    public PartZonificListas removePartZonificListas(PartZonificListas partZonificListas) {
        getPartZonificListasList().remove(partZonificListas);
        partZonificListas.setPartZonificCategorias(null);
        return partZonificListas;
    }
}
