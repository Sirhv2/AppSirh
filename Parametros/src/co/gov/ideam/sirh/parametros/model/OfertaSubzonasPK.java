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
public class OfertaSubzonasPK implements Serializable {
    @Column(name = "id_subzona", nullable = false)
    private Integer idSubzona;
    @Column(name = "anno_ena", nullable = false)
    private Integer annoEna;

    public OfertaSubzonasPK() {
    }

    public OfertaSubzonasPK(Integer idSubzona, Integer annoEna) {
        this.idSubzona = idSubzona;
        this.annoEna = annoEna;
    }

    public Integer getIdSubzona() {
        return idSubzona;
    }

    public void setIdSubzona(Integer idSubzona) {
        this.idSubzona = idSubzona;
    }

    public Integer getAnnoEna() {
        return annoEna;
    }

    public void setAnnoEna(Integer annoEna) {
        this.annoEna = annoEna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSubzona;
        hash += (int) annoEna;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaSubzonasPK)) {
            return false;
        }
        OfertaSubzonasPK other = (OfertaSubzonasPK) object;
        if (this.idSubzona != other.idSubzona) {
            return false;
        }
        if (this.annoEna != other.annoEna) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.parametros.model.OfertaSubzonasPK[ idSubzona=" + idSubzona + ", annoEna=" + annoEna + " ]";
    }
    
}
