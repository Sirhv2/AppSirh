package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FnttFuenteTramoMunicipioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_fuente")
    private Long idFuente;
    @Basic(optional = false)
    @Column(name = "id_tramo")
    private Long idTramo;
    @Basic(optional = false)
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Basic(optional = false)
    @Column(name = "id_departamento")
    private Integer idDepartamento;

    public FnttFuenteTramoMunicipioPK() {
    }

    public FnttFuenteTramoMunicipioPK(Long idFuente, Long idTramo, Integer idMunicipio, Integer idDepartamento) {
        this.idFuente = idFuente;
        this.idTramo = idTramo;
        this.idMunicipio = idMunicipio;
        this.idDepartamento = idDepartamento;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (long) idFuente;
        hash += (long) idTramo;
        hash += (int) idMunicipio;
        hash += (int) idDepartamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FnttFuenteTramoMunicipioPK)) {
            return false;
        }
        FnttFuenteTramoMunicipioPK other = (FnttFuenteTramoMunicipioPK) object;
        if (this.idFuente != other.idFuente) {
            return false;
        }
        if (this.idTramo != other.idTramo) {
            return false;
        }
        if (this.idMunicipio != other.idMunicipio) {
            return false;
        }
        if (this.idDepartamento != other.idDepartamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.FntFuenteTramoMunicipioPK[ idFuente=" + idFuente + ", idTramo=" + idTramo + ", idMunicipio=" + idMunicipio + ", idDepartamento=" + idDepartamento + " ]";
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
}
