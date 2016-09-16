package co.gov.ideam.sirh.fuentes.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Calendar;

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
@Table(name = "FNTT_CONFLICTO_GESTION")
@NamedQueries({
    @NamedQuery(name = "GestionConflicto.findAll", query = "SELECT t FROM GestionConflicto t"),
    @NamedQuery(name = "GestionConflicto.findById", query = "SELECT t FROM GestionConflicto t WHERE t.id = :id"),
    @NamedQuery(name = "GestionConflicto.findByIdConflicto", query = "SELECT t FROM GestionConflicto t WHERE t.idConflicto = :idConflicto")
})

public class GestionConflicto implements Serializable {
        
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ID_CONFLICTO")
    private Long idConflicto;
    
    @Column(name = "FECHA_GESTION")
    private Timestamp fechaGestion;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @JoinColumn(name = "ID_TIPO_GESTION", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lista tipoGestion;
   
    @JoinColumn(name = "ID_SUBTIPO_GESTION", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lista subTipoGestion;

    @Column(name = "NOMBRE_GESTIONADOR")
    private String nombreGestionador;

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipoGestion(Lista tipoGestion) {
        this.tipoGestion = tipoGestion;
    }

    public Lista getTipoGestion() {
        return tipoGestion;
    }

    public void setSubTipoGestion(Lista subTipoGestion) {
        this.subTipoGestion = subTipoGestion;
    }

    public Lista getSubTipoGestion() {
        return subTipoGestion;
    }

    public void setNombreGestionador(String nombreGestionador) {
        this.nombreGestionador = nombreGestionador;
    }

    public String getNombreGestionador() {
        return nombreGestionador;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setFechaGestion(Timestamp fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public Timestamp getFechaGestion() {
        return fechaGestion;
    }
}
