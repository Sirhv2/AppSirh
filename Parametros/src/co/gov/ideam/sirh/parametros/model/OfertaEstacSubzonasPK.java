/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Eduin
 */
@Embeddable
public class OfertaEstacSubzonasPK implements Serializable {
    @Column(name = "id_subzona", nullable = false)
    private Integer idSubzona;
    @Column(name = "id_estacion", nullable = false)
    private Integer idEstacion;

    public OfertaEstacSubzonasPK() {
    }

    public OfertaEstacSubzonasPK(Integer idSubzona, Integer idEstacion) {
        this.idSubzona = idSubzona;
        this.idEstacion = idEstacion;
    }

    public Integer getIdSubzona() {
        return idSubzona;
    }

    public void setIdSubzona(Integer idSubzona) {
        this.idSubzona = idSubzona;
    }

    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSubzona;
        hash += (int) idEstacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaEstacSubzonasPK)) {
            return false;
        }
        OfertaEstacSubzonasPK other = (OfertaEstacSubzonasPK) object;
        if (this.idSubzona != other.idSubzona) {
            return false;
        }
        if (this.idEstacion != other.idEstacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonasPK[ idSubzona=" + idSubzona + ", idEstacion=" + idEstacion + " ]";
    }
    
}
