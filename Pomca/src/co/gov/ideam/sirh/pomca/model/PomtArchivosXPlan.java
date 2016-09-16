package co.gov.ideam.sirh.pomca.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "PomtArchivosXPlan.findAll", query = "select o from PomtArchivosXPlan o")
               , @NamedQuery(name = "PomtArchivosXPlan.findByPomtPlanes", query = "SELECT o FROM PomtArchivosXPlan o WHERE o.pomtPlanes = :pomtPlanes")
               , @NamedQuery(name = "PomtArchivosXPlan.findById", query = "SELECT o FROM PomtArchivosXPlan o WHERE o.id = :id")
               , @NamedQuery(name = "PomtArchivosXPlan.findByarchiPlan", query = "SELECT o FROM PomtArchivosXPlan o WHERE o.id = :id and o.pomtPlanes = :pomtPlanes ")
             } )
@Table(name = "pomt_archivos_planes")
public class PomtArchivosXPlan implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id_archivo", nullable = false)
    private Long id;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id",  nullable = false)
    private PomtPlanes pomtPlanes;
    public PomtArchivosXPlan() {
    }
    public PomtPlanes getPomtPlanes() {
        return pomtPlanes;
    }
    public void setPomtPlanes(PomtPlanes pomtPlanes) {
        this.pomtPlanes = pomtPlanes;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
