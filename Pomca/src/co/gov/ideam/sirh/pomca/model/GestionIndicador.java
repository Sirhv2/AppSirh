package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "GestionIndicador.findAll", query = "select o from GestionIndicador o"),
  @NamedQuery(name = "GestionIndicador.findByIdGestion", query = "select o from GestionIndicador o where o.idGestion = :idGestion"),
  @NamedQuery(name = "GestionIndicador.findByIndicador", query = "select o from GestionIndicador o where o.idIndicador = :idIndicador")
  
})
@Table(name = "pomt_gestion_indicador")
public class GestionIndicador implements Serializable  {
    
    @GenericGenerator(name = "gestionind_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_gestionind")})
      
    @Id
    @Column(name = "id", nullable = false)
   // @GeneratedValue(generator = "gestionind_generator")        
    private Long id;

    @Transient
    private Long codigoAutoridad;
    
    @Column(name = "id_gestion", nullable = false)
    private Long idGestion;

    @Column(name = "id_indicador", nullable = false)
    private Long idIndicador;

    @Column(name = "valor_indicador", nullable = false)
    private Long valorIndicador;

    public GestionIndicador() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdIndicador(Long idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Long getIdIndicador() {
        return idIndicador;
    }

    public void setValorIndicador(Long valorIndicador) {
        this.valorIndicador = valorIndicador;
    }

    public Long getValorIndicador() {
        return valorIndicador;
    }
}
