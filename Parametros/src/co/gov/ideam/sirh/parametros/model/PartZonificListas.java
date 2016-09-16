package co.gov.ideam.sirh.parametros.model;

import co.gov.ideam.sirh.parametros.model.PartZonificCategorias;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PartZonificListas.findAll", query = "select o from PartZonificListas o"),
  @NamedQuery(name = "PartZonificListas.findAllChild", query = "select o from PartZonificListas o where o.id_padre = :padre ORDER by o.valor ASC"),
  @NamedQuery(name = "PartZonificListas.findAllParents", query = "select o from PartZonificListas o where o.id_padre is null ORDER by o.valor ASC"),
  @NamedQuery(name = "PartZonificListas.findAllByCategoria", query = "select o from PartZonificListas o where o.partZonificCategorias.id = :categoria "),
  @NamedQuery(name = "PartZonificListas.findById", query = "select o from PartZonificListas o where o.id = :id "),
  @NamedQuery(name = "PartZonificListas.findByNameParent", query = "select o from PartZonificListas o where upper(o.valor) = :valor and o.id_padre = :id_padre"),
  @NamedQuery(name = "PartZonificListas.findByName", query = "select o from PartZonificListas o where upper(o.valor) = :valor")
})
@Table(name = "part_zonific_listas")
public class PartZonificListas implements Serializable {
    @Id
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="id_padre")
    private Integer id_padre;
    @Column(name="valor")
    private String valor;
    @ManyToOne
    @JoinColumn(name = "id_zonific_categoria")
    private PartZonificCategorias partZonificCategorias;

    public PartZonificListas() {
    }

    public PartZonificListas(Integer id, Integer id_padre,
                             PartZonificCategorias partZonificCategorias, String valor) {
        this.id = id;
        this.id_padre = id_padre;
        this.partZonificCategorias = partZonificCategorias;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_padre() {
        return id_padre;
    }

    public void setId_padre(Integer id_padre) {
        this.id_padre = id_padre;
    }


    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public PartZonificCategorias getPartZonificCategorias() {
        return partZonificCategorias;
    }

    public void setPartZonificCategorias(PartZonificCategorias partZonificCategorias) {
        this.partZonificCategorias = partZonificCategorias;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            obj instanceof PartZonificListas &&
            ((PartZonificListas)obj).getId()!=null &&
            this.getId() != null &&
            ((PartZonificListas)obj).getId().intValue() == this.getId().intValue();
            
    }
}
