package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "subtt_funias_documentos")
@NamedQueries({
    @NamedQuery(name = "SubttFuniasDocumentos.findAll", 
                query = "SELECT s FROM SubttFuniasDocumentos s"),
    @NamedQuery(name = "SubttFuniasDocumentos.findById", 
                query = "SELECT s FROM SubttFuniasDocumentos s WHERE s.id = :id"),
    @NamedQuery(name = "SubttFuniasDocumentos.findByFunias", 
                query = "SELECT s FROM SubttFuniasDocumentos s WHERE s.idFunias = :idFunias")
})
public class SubttFuniasDocumentos implements Serializable {
    
    @GenericGenerator(name = "file_funias_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_FILE_FUNIAS")
                                    })
        
    @Id
    @Column(name = "id", nullable=false)
   // @GeneratedValue(generator = "file_funias_generator") 
    private Long id;
    @Column(name = "documento", nullable = true)
    private byte[] documento;
    @Column(name = "id_funias")
    private Long idFunias;
    @Column(name = "tipo")
    private String tipo;
    
    
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Autoridades autoridad;

    public SubttFuniasDocumentos() {
    }

    public SubttFuniasDocumentos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public Long getIdFunias() {
        return idFunias;
    }

    public void setIdFunias(Long idFunias) {
        this.idFunias = idFunias;
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
        if (!(object instanceof SubttFuniasDocumentos)) {
            return false;
        }
        SubttFuniasDocumentos other = (SubttFuniasDocumentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasDocumentos[ id=" + id + " ]";
    }


    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

