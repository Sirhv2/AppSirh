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
@NamedQueries( { @NamedQuery(name = "PomtArchivosXDeterminantes.findAll", query = "select o from PomtArchivosXDeterminantes o")
               , @NamedQuery(name = "PomtArchivosXDeterminantes.findByPomtDeterminantes", query = "SELECT o FROM PomtArchivosXDeterminantes o WHERE o.Determinantes = :determinantes")
               , @NamedQuery(name = "PomtArchivosXDeterminantes.findById", query = "SELECT o FROM PomtArchivosXDeterminantes o WHERE o.id = :id")
               , @NamedQuery(name = "PomtArchivosXDeterminantes.findByarchiPlan", query = "SELECT o FROM PomtArchivosXDeterminantes o WHERE o.id = :id and o.Determinantes = :determinantes ")
             } )
@Table(name = "pomt_archivos_determinantes")
public class PomtArchivosXDeterminantes implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id_archivo", nullable = false)
    private Long id;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id",  nullable = false)
    private DeterminanteAmbiental Determinantes;
    public PomtArchivosXDeterminantes() {
    }
    public DeterminanteAmbiental getDeterminantes() {
        return Determinantes;
    }
    public void setDeterminantes(DeterminanteAmbiental determinantes) {
        this.Determinantes = determinantes;
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
