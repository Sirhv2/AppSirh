package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "fntt_fuente_tramo_municipio")
public class FuenteTramoMunicipioPOJO implements Serializable {
    
    @Id
    @Column(name = "id_fuente", nullable=false)
    private Long idFuente;
    @Id
    @Column(name = "id_tramo", nullable=false)
    private Long idTramo;
    @Id
    @Column(name = "id_municipio", nullable=false)
    private Integer idMunicipio;
    @Id
    @Column(name = "id_departamento", nullable=false)
    private Integer idDepartamento;

    public FuenteTramoMunicipioPOJO() {
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }
}
