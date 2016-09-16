
package co.gov.ideam.sirh.datosena.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Lis
 */
@Entity
@Table(name = "DATOS_AVANCESIRH_V")

@NamedQueries({
    @NamedQuery(name = "DatosAvanceSIRH.findAll", query = "SELECT e FROM DatosAvanceSIRH e")})
public class DatosAvanceSIRH implements Serializable {
   
    @Id
    @Column(name = "num")
    private Long id;
    
    @Column(name = "usuarios")
    private Integer usuarios;
    @Column(name = "fuentes")
    private Integer fuentes;
   
    @Column(name = "annio")
    private String annio;

    public DatosAvanceSIRH() {
    }


    public void setUsuarios(Integer usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getUsuarios() {
        return usuarios;
    }

    public void setFuentes(Integer fuentes) {
        this.fuentes = fuentes;
    }

    public Integer getFuentes() {
        return fuentes;
    }

       public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public String getAnnio() {
        return annio;
    }
}
