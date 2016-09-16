package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "fntt_fuente_tramo_municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FnttFuenteTramoMunicipio.findAll", query = "SELECT f FROM FnttFuenteTramoMunicipio f"),
    @NamedQuery(name = "FnttFuenteTramoMunicipio.findByIdFuente", query = "SELECT f FROM FnttFuenteTramoMunicipio f WHERE f.fnttFuenteTramoMunicipioPK.idFuente = :idFuente"),
    @NamedQuery(name = "FnttFuenteTramoMunicipio.findByIdTramo", query = "SELECT f FROM FnttFuenteTramoMunicipio f WHERE f.fnttFuenteTramoMunicipioPK.idTramo = :idTramo"),
    @NamedQuery(name = "FnttFuenteTramoMunicipio.findByIdMunicipio", query = "SELECT f FROM FnttFuenteTramoMunicipio f WHERE f.fnttFuenteTramoMunicipioPK.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "FnttFuenteTramoMunicipio.findByIdDepartamento", query = "SELECT f FROM FnttFuenteTramoMunicipio f WHERE f.fnttFuenteTramoMunicipioPK.idDepartamento = :idDepartamento")})
public class FnttFuenteTramoMunicipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FnttFuenteTramoMunicipioPK fnttFuenteTramoMunicipioPK;
    
    
    @JoinColumn(name = "id_tramo", insertable = false, updatable = false ,
                referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FnttTramo fnttTramo;
    
    
    @JoinColumn(name = "id_fuente", insertable = false, updatable = false ,
                referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FnttFuente fnttFuente;
    

    public FnttFuenteTramoMunicipio() {
    }

    public FnttFuenteTramoMunicipio(FnttFuenteTramoMunicipioPK fnttFuenteTramoMunicipioPK) {
        this.fnttFuenteTramoMunicipioPK = fnttFuenteTramoMunicipioPK;
    }

    public FnttFuenteTramoMunicipio(Long idFuente, Long idTramo, Integer idMunicipio, Integer idDepartamento) {
        this.fnttFuenteTramoMunicipioPK = new FnttFuenteTramoMunicipioPK(idFuente, idTramo, idMunicipio, idDepartamento);
    }

    public FnttFuenteTramoMunicipioPK getFntFuenteTramoMunicipioPK() {
        return fnttFuenteTramoMunicipioPK;
    }

    public void setFnttFuenteTramoMunicipioPK(FnttFuenteTramoMunicipioPK fnttFuenteTramoMunicipioPK) {
        this.fnttFuenteTramoMunicipioPK = fnttFuenteTramoMunicipioPK;
    }

    public FnttTramo getFnttTramo() {
        return fnttTramo;
    }

    public void setFnttTramo(FnttTramo fnttTramo) {
        this.fnttTramo = fnttTramo;
    }

    public FnttFuente getFnttFuente() {
        return fnttFuente;
    }

    public void setFnttFuente(FnttFuente fnttFuente) {
        this.fnttFuente = fnttFuente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fnttFuenteTramoMunicipioPK != null ? fnttFuenteTramoMunicipioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FnttFuenteTramoMunicipio)) {
            return false;
        }
        FnttFuenteTramoMunicipio other = (FnttFuenteTramoMunicipio) object;
        if ((this.fnttFuenteTramoMunicipioPK == null && other.fnttFuenteTramoMunicipioPK != null) || (this.fnttFuenteTramoMunicipioPK != null && !this.fnttFuenteTramoMunicipioPK.equals(other.fnttFuenteTramoMunicipioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.FnttFuenteTramoMunicipio[ fnttFuenteTramoMunicipioPK=" + fnttFuenteTramoMunicipioPK + " ]";
    }
    
}
