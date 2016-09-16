package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries({
  @NamedQuery(name = "Lista.find", query = "select o from Lista o where o.codigo = :codigo order by valor ASC"),
  @NamedQuery(name = "Lista.findAll", query = "select o from Lista o Order by valor ASC"),
  @NamedQuery(name = "Lista.findByCategoria", query = "select o from Lista o where o.categoria.id = :categoria order by valor ASC"),
  @NamedQuery(name = "Lista.findByValor", query = "select o from Lista o where o.categoria.id = :categoria and upper(o.valor) = :valor ")
})
@Table(name = "part_listas")
public class Lista  implements Serializable{
    @Id
    @Column(name = "id", nullable = false)
    private Integer codigo;
    @Column(name = "valor", nullable = false)
    private String valor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
    
    public Lista() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            obj instanceof Lista &&
            ((Lista)obj).getCodigo()!=null &&
            this.getCodigo() != null &&
            ((Lista)obj).getCodigo().intValue() == this.getCodigo().intValue();
            
    }
}
