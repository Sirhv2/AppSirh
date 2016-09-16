package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "subtt_funias_documentos")
public class SubttFuniasDocumentosPOJO implements Serializable {
        
    @Id
    @Column(name = "id", nullable=false)
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

    public SubttFuniasDocumentosPOJO() {
    }

    public SubttFuniasDocumentosPOJO(Long id) {
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
