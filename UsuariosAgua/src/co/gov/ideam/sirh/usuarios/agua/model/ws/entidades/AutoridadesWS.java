package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "AutoridadesWS.findAll", query = "select o from AutoridadesWS o order by o.nombre ASC"),
  @NamedQuery(name = "AutoridadesWS.find", query = "select o from AutoridadesWS o where o.id = :codigo"),
   @NamedQuery(name = "AutoridadesWS.findSIGLA", query = "select o from AutoridadesWS o where upper(o.sigla) like upper(:sigla)")
  
})
@Table(name = "AUTORIDADES_AMBIENTALES_V")
public class AutoridadesWS implements Serializable {
    @Id
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="NOMBRE_AUTORIDAD")
    private String nombre;
    @Column(name="sigla")
    private String sigla;
   

    public AutoridadesWS() {
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
 
 
}
