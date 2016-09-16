package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "ActividadCIIU.findByCode", query = "select o from ActividadCIIU o where o.codigo = :codigo order by o.descripcion ASC"),
  @NamedQuery(name = "ActividadCIIU.findByName", query = "select o from ActividadCIIU o where upper(o.descripcion) like :descripcion order by o.descripcion ASC"),
  @NamedQuery(name = "ActividadCIIU.find", query = "select o from ActividadCIIU o where o.id = :codigo")
})

@Table(name = "part_ciiu")
public class ActividadCIIU implements Serializable{
    
    @Id
    @Column(name="ciu_id", nullable = false)
    private Long id;
    @Column(name="cui_revision", nullable = false)
    private Integer revision;
    @Column(name="ciu_codigo", nullable = false)
    private String codigo;
    @Column(name="ciu_descripcion", nullable = false)
    private String descripcion;
                  
    public ActividadCIIU() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
