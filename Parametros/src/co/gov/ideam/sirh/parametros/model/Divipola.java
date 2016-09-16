package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@NamedQueries({
  @NamedQuery(name = "Divipola.findAll", query = "select o from Divipola o"),
  @NamedQuery(name = "Divipola.find", query = "select o from Divipola o where o.id = :codigo"),
  @NamedQuery(name = "Divipola.findAllParents", query = "select o from Divipola o where o.padreId is null order by o.nombre ASC"),
  @NamedQuery(name = "Divipola.findChild", query = "select o from Divipola o where o.padreId = :padre order by o.nombre ASC"),
  @NamedQuery(name = "Divipola.findByName", query = "select o from Divipola o where upper(o.nombre) = :nombre and upper(o.clase) = :clase "),
  @NamedQuery(name = "Divipola.findChildByName", query = "select o from Divipola o where upper(o.nombre) = :nombre and  o.padreId = :padreId")
})
@Table(name = "part_divipola")
public class Divipola implements Serializable {
    
    public static final String CLASE_DEPTO = "DEP";
    public static final String CLASE_MUNICIPIO = "CM";
    
    @Id
    @Column(name="divipola_id", nullable = false)    
    private Long id;
    @Column(name="codigo", nullable = false)    
    private String codigo;
    @Column(name="clase", nullable = false)    
    private String clase;
    @Column(name="nombre", nullable = false)    
    private String nombre;
    @Column(name="divipola_padre_id", nullable = false)    
    private Long  padreId;
    @Transient
    private TipoDivipola tipo;
    
    public Divipola() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPadreId() {
        return padreId;
    }

    public void setPadreId(Long padreId) {
        this.padreId = padreId;
    }

    public TipoDivipola getTipo() {
        return tipo;
    }

    public void setTipo(TipoDivipola tipo) {
        this.tipo = tipo;
    }
    
    public boolean equals(Object obj){
        return obj!=null &&
                obj instanceof Divipola &&
                ((Divipola)obj).getId() != null &&
                this.getId() != null &&
                ((Divipola)obj).getId().longValue() == this.getId().longValue();
    }
}
