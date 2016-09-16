package co.gov.ideam.sirh.documentos.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;


@Entity
@Table(name = "nodos")
@NamedQueries( { @NamedQuery(name = "Nodo.findAll", query = "SELECT n FROM Nodo n")
        , @NamedQuery(name = "Nodo.findById", query = "SELECT n FROM Nodo n WHERE n.id = :id")
        , @NamedQuery(name = "Nodo.findByDescripcion", query = "SELECT n FROM Nodo n WHERE n.descripcion = :descripcion")
        , @NamedQuery(name = "Nodo.findByExt", query = "SELECT n FROM Nodo n WHERE n.ext = :ext")
        , @NamedQuery(name = "Nodo.findByMime", query = "SELECT n FROM Nodo n WHERE n.mime = :mime")
        , @NamedQuery(name = "Nodo.findByKeywords", query = "SELECT n FROM Nodo n WHERE n.keywords = :keywords")
        } )


//@javax.persistence.SequenceGenerator(name = "project_id_seq_archivos",  sequenceName = "seq_archivos")
public class Nodo implements Serializable {
   
    //@GeneratedValue(generator = "pomca_generator")

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq_archivos")
    @Column(name = "id")
    private Long id;

    private static final long serialVersionUID = 1L;

    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "ext")
    private String ext;
    @Column(name = "mime")
    private String mime;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    
    @Transient
    private Long codigoAutoridad;

/*    @OneToOne(optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Archivo archivo;*/

    /*@OneToOne(optional = false)
    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    private PomtArchivosXPlan pomtArchivosXPlan;
*/

/*    @JoinColumn(name = "id_padre", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    private Nodo nodo;
*/
    public Nodo() {
    }

    public Nodo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    /*public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }*/

 /*   public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
*/

    public void setPublicoDesc(String publicoDesc) {
        //this.publicoDesc = publicoDesc;
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
        if (!(object instanceof Nodo)) {
            return false;
        }
        Nodo other = (Nodo)object;
        if ((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.documento.model.Nodo[ id=" + id + " ]";
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
