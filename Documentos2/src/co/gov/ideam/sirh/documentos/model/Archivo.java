package co.gov.ideam.sirh.documentos.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "archivos")
@NamedQueries( { @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a")
               , @NamedQuery(name = "Archivo.findById", query = "SELECT a FROM Archivo a WHERE a.id = :id")
        } )
public class Archivo implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "archivo", nullable = false)
    private byte[] archivo;
    /*@JoinColumn(name = "id"
              , referencedColumnName = "id"
              , nullable = false
              , insertable = false
              , updatable = false)
    @OneToOne(optional = false)
    private Nodo nodo;*/

    public Archivo() {
    }

    public Archivo(Long id) {
        this.id = id;
    }

    public Archivo(Long id, byte[] archivo) {
        this.id = id;
        this.archivo = archivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    /*public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo)object;
        if ((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.documento.model.Archivo[ id=" + id + " ]";
    }

}
