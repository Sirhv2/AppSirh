package co.gov.ideam.sirh.fuentes.model;

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
@Table(name = "FNTT_CONFLICTO_ACTOR")
@NamedQueries({
    @NamedQuery(name = "ActorConflicto.findAll", query = "SELECT t FROM ActorConflicto t"),
    @NamedQuery(name = "ActorConflicto.findById", query = "SELECT t FROM ActorConflicto t WHERE t.id = :id"),
    @NamedQuery(name = "ActorConflicto.findByIdConflicto", query = "SELECT t FROM ActorConflicto t WHERE t.idConflicto = :idConflicto")
})

public class ActorConflicto implements Serializable {
        
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ID_CONFLICTO")
    private Long idConflicto;
    
    @JoinColumn(name = "ACTOR_CONFLICTO", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lista actorConflicto;
   
    @Column(name = "NOMBRE")
    private String nombre;

    @Transient
    private Long codigoAutoridad;

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

    public void setActorConflicto(Lista actorConflicto) {
        this.actorConflicto = actorConflicto;
    }

    public Lista getActorConflicto() {
        return actorConflicto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}
