package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "rurt_punto_vert_tratamiento")
public class PuntoVertimientoTratamientoPOJO implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_punto_vertimiento")
    private Long idPuntoVertimiento;
    @Column(name = "id_tratamiento")
    private Integer idTratamiento;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    
    @Transient
    private Long codigoAutoridad;

    public PuntoVertimientoTratamientoPOJO() {
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPuntoVertimiento() {
        return idPuntoVertimiento;
    }

    public void setIdPuntoVertimiento(Long idPuntoVertimiento) {
        this.idPuntoVertimiento = idPuntoVertimiento;
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}
