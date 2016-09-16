package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
    @NamedQuery(name = "RurtCaptacionComponentes.findAll", query = "SELECT r FROM RurtCaptacionComponentes r"),
    @NamedQuery(name = "RurtCaptacionComponentes.findById", query = "SELECT r FROM RurtCaptacionComponentes r WHERE r.id = :id"),
    @NamedQuery(name = "RurtCaptacionComponentes.findByCaptacion", query = "SELECT r FROM RurtCaptacionComponentes r WHERE r.idCaptacion = :id"),
    @NamedQuery(name = "RurtCaptacionComponentes.findByComponenteCaptacion", query = "SELECT r FROM RurtCaptacionComponentes r WHERE r.idComponente = :idComponente and r.idCaptacion = :id")
})
@Table(name = "rurt_captacion_componentes")
public class RurtCaptacionComponentes implements Serializable {
    
    @GenericGenerator(name = "componentes_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_COMPONENTES_CAPTACION")})
    
    @Id
    @Column(name = "id", nullable = false)
   // @GeneratedValue(generator = "componentes_generator")
    private Long id;
    @Column(name = "id_captacion")
    private Long idCaptacion;
    @Column(name = "id_componente")
    private Integer idComponente;
    
    @Transient
    private Long codigoAutoridad;

    public RurtCaptacionComponentes() {
    }

    public RurtCaptacionComponentes(Long id) {
        this.id = id;
    }
    
    //atributo transient
    public Long getCodigoAutoridad() {

        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCaptacion() {
        return idCaptacion;
    }

    public void setIdCaptacion(Long idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RurtCaptacionComponentes)) {
            return false;
        }
        RurtCaptacionComponentes other = (RurtCaptacionComponentes)object;
        if ((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.RurtCaptacionComponentes[ id=" +
            id + " ]";
    }

}
