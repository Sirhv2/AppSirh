package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rurt_captacion_componentes")
public class RurtCaptacionComponentePOJO  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_captacion")
    private Long idCaptacion;
    @Column(name = "id_componente")
    private Integer idComponente;
    
    @Transient
    private Long codigoAutoridad;

    public RurtCaptacionComponentePOJO() {
    }

    public RurtCaptacionComponentePOJO(Long id) {
        this.id = id;
    }
    
    //atributo transient
    public Long getCodigoAutoridad() {

        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCaptacion() {
        return idCaptacion;
    }

    public void setIdCaptacion(Long idCaptacion) {
        this.idCaptacion = idCaptacion;
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }
}
