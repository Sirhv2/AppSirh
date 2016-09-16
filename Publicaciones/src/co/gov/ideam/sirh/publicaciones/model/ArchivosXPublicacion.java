package co.gov.ideam.sirh.publicaciones.model;

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
@NamedQueries( { @NamedQuery(name = "ArchivosXPublicacion.findAll",           query = "select o from ArchivosXPublicacion o")
               , @NamedQuery(name = "ArchivosXPublicacion.findByPublicacion", query = "SELECT o FROM ArchivosXPublicacion o WHERE o.publicacion = :publicacion")
             } )
@Table(name = "pomt_archivos_publicaciones")
public class ArchivosXPublicacion implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id_archivo", nullable = false)
    private Long id;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modulo", referencedColumnName = "id",  nullable = false)
    private Publicacion publicacion;
    public ArchivosXPublicacion() {
    }
    public Publicacion getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
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
