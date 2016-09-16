package co.gov.ideam.sirh.calidad.model;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;


import javax.ejb.EJB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import co.gov.ideam.sirh.util.IdeamException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
   @NamedQuery(name = "NormaUsos.findAll", query = "select o from NormaUsos o"), 
   @NamedQuery(name = "NormaUsos.findId", query = "select o from NormaUsos  o where o.id = :id "),
    @NamedQuery(name = "NormaUsos.findXAutoridad", query = "select o from NormaUsos o where o.codigoAutoridad = :codigoAutoridad ")
 
 
})
@Table(name = "part_norma")
public class NormaUsos implements Serializable {
    
    
    @GenericGenerator(name = "norma_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_norma_usos")})
        
 
    
  @Id
  @Column(name = "id", nullable = false)
  //@GeneratedValue(generator = "norma_generator")        
  private Long id;
  @Column(name="nombre")
  private String nombreNorma;
  @Column(name="id_uso")
  private Integer uso;
  @Column(name="id_autoridad")
  private Long codigoAutoridad;
  @Column(name="id_riesgo")
  private Integer riesgo;
  @Transient
  private Lista listaUsos;
  @Transient
  private Lista listaRiesgo;

    public NormaUsos() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombreNorma(String nombreNorma) {
        this.nombreNorma = nombreNorma;
    }

    public String getNombreNorma() {
        return nombreNorma;
    }

    public void setUso(Integer uso) {
        this.uso = uso;
    }

    public Integer getUso() {
        return uso;
    }

    
    

    public Lista getListaUsos() {
        return listaUsos;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }


    public void setListaUsos(Lista listaUsos) {
        this.listaUsos = listaUsos;
    }

    public void setRiesgo(Integer riesgo) {
        this.riesgo = riesgo;
    }

    public Integer getRiesgo() {
        return riesgo;
    }

    public void setListaRiesgo(Lista listaRiesgo) {
        this.listaRiesgo = listaRiesgo;
    }

    public Lista getListaRiesgo() {
        return listaRiesgo;
    }
}
