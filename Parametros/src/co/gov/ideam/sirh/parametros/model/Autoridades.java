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
  @NamedQuery(name = "Autoridades.findAll", query = "select o from Autoridades o order by o.nombre ASC"),
  @NamedQuery(name = "Autoridades.find", query = "select o from Autoridades o where o.id = :codigo"),
   @NamedQuery(name = "Autoridades.findSIGLA", query = "select o from Autoridades o where upper(o.sigla) like upper(:sigla)"),
  @NamedQuery(name = "AutoridadesNodos.find", query = "select o from Autoridades o where o.endpointnovedades is not null")
})
@Table(name = "autoridades")
public class Autoridades implements Serializable {
    @Id
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="sigla")
    private String sigla;
    @Column(name="endpointnovedades")
    private String endpointnovedades;
    

    public Autoridades() {
    }

    public Autoridades(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
    
    public boolean equals(Object obj){
        return (obj!=null &&
           obj instanceof Autoridades &&
           ((Autoridades)obj).getId()!=null &&
           this.getId() != null &&
           this.getId().intValue() == ((Autoridades)obj).getId().intValue() );
    }

    public void setEndpointnovedades(String endpointnovedades) {
        this.endpointnovedades = endpointnovedades;
    }

    public String getEndpointnovedades() {
        return endpointnovedades;
    }
}
