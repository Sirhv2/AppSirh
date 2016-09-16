package co.gov.ideam.sirh.fuentes.model;

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
@Table(name = "FNTT_CONFLICTO_POBLACION")
@NamedQueries({
    @NamedQuery(name = "PoblacionConflicto.findAll", query = "SELECT t FROM PoblacionConflicto t"),
    @NamedQuery(name = "PoblacionConflicto.findById", query = "SELECT t FROM PoblacionConflicto t WHERE t.id = :id"),
    @NamedQuery(name = "PoblacionConflicto.findByIdConflicto", query = "SELECT t FROM PoblacionConflicto t WHERE t.idConflicto = :idConflicto")
})

public class PoblacionConflicto implements Serializable {
        
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ID_CONFLICTO")
    private Long idConflicto;
    
    @Column(name = "ID_POBLACION_AFECTADA")
    private Integer idPoblacionConflcito;
    
    @Transient
    private Long codigoAutoridad;

    public PoblacionConflicto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdConflicto(Long idConflicto) {
        this.idConflicto = idConflicto;
    }

    public Long getIdConflicto() {
        return idConflicto;
    }

    public void setIdPoblacionConflcito(Integer idPoblacionConflcito) {
        this.idPoblacionConflcito = idPoblacionConflcito;
    }

    public Integer getIdPoblacionConflcito() {
        return idPoblacionConflcito;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}
