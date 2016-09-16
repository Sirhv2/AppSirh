package co.gov.ideam.sirh.usuarios.agua.model;

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
@Table(name = "rurt_punto_vert_tratamiento")
@NamedQueries({
    @NamedQuery(name = "PuntoVertimientoTratamiento.findAll", query = "SELECT r FROM PuntoVertimientoTratamiento r"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findById", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.id = :id"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findByIdTratamiento", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.idTratamiento = :id"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findByIdCategoria", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.idCategoria = :id"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findByIdPuntoVertimiento", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.idPuntoVertimiento = :id"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findByIdPuntoVertimientoCategoria", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.idPuntoVertimiento = :idPunto AND r.idCategoria = :idCategoria"),
    @NamedQuery(name = "PuntoVertimientoTratamiento.findByIdPuntoCategoriaTratamiento", query = "SELECT r FROM PuntoVertimientoTratamiento r WHERE r.idPuntoVertimiento = :idPunto AND r.idCategoria = :idCategoria AND r.idTratamiento = :idTratamiento")
})

public class PuntoVertimientoTratamiento implements Serializable {
    
    @GenericGenerator(name = "tratamiento_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_TRATAMIENTO_VERTIMIENTO")})
        
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "tratamiento_generator")
    private Long id;
    @Column(name = "id_punto_vertimiento")
    private Long idPuntoVertimiento;
    @Column(name = "id_tratamiento")
    private Integer idTratamiento;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    
    @Transient
    private Long codigoAutoridad;

    public PuntoVertimientoTratamiento() {
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPuntoVertimiento() {
        return idPuntoVertimiento;
    }

    public void setIdPuntoVertimiento(Long idPuntoVertimiento) {
        this.idPuntoVertimiento = idPuntoVertimiento;
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
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
        if (!(object instanceof PuntoVertimientoTratamiento)) {
            return false;
        }
        PuntoVertimientoTratamiento other = (PuntoVertimientoTratamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento[ id=" + id + " ]";
    }

}
