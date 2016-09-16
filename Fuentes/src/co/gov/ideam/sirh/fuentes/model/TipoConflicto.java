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
@Table(name = "FNTT_CONFLICTO_TIPO")
@NamedQueries({
    @NamedQuery(name = "TipoConflicto.findAll", query = "SELECT t FROM TipoConflicto t"),
    @NamedQuery(name = "TipoConflicto.findById", query = "SELECT t FROM TipoConflicto t WHERE t.id = :id"),
    @NamedQuery(name = "TipoConflicto.findByIdConflicto", query = "SELECT t FROM TipoConflicto t WHERE t.idConflicto = :idConflicto")
})

public class TipoConflicto implements Serializable {
        
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ID_CONFLICTO")
    private Long idConflicto;
    
    @Column(name = "ID_TIPO_CONFLICTO")
    private Integer idTipoConflcito;
    
    @Transient
    private Long codigoAutoridad;

    public TipoConflicto() {
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

    public void setIdTipoConflcito(Integer idTipoConflcito) {
        this.idTipoConflcito = idTipoConflcito;
    }

    public Integer getIdTipoConflcito() {
        return idTipoConflcito;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}

