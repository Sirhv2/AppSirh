package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorhIndicesBasicos.findAll", query = "select o from PorhIndicesBasicos o order by o.id")
})
@Table(name = "PORH_INDICES_BASICOS")
public class PorhIndicesBasicos implements Serializable {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nombre;

    public PorhIndicesBasicos() {
    }

    public PorhIndicesBasicos(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
