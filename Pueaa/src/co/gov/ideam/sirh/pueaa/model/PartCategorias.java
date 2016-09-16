package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PartCategorias.findAll", query = "select o from PartCategorias o")
})
@Table(name = "PART_CATEGORIAS")
public class PartCategorias implements Serializable {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(length = 200)
    private String valor;

    public PartCategorias() {
    }

    public PartCategorias(Long id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
