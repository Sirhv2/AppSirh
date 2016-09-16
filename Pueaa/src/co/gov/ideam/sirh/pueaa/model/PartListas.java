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
  @NamedQuery(name = "PartListas.findAll", query = "select o from PartListas o")
})
@Table(name = "PART_LISTAS")
public class PartListas implements Serializable {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(name="ID_CATEGORIA")
    private Long idCategoria;
    @Column(length = 200)
    private String valor;

    public PartListas() {
    }

    public PartListas(Long id, Long idCategoria, String valor) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
