/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "part_ref_oferta_estac_subzonas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OfertaEstacSubzonas.findAll", query = "SELECT p FROM OfertaEstacSubzonas p"),
    @NamedQuery(name = "OfertaEstacSubzonas.findByIdSubzona", query = "SELECT p FROM OfertaEstacSubzonas p WHERE p.ofertaEstacSubzonasPK.idSubzona = :idSubzona"),
    @NamedQuery(name = "OfertaEstacSubzonas.findByIdEstacion", query = "SELECT p FROM OfertaEstacSubzonas p WHERE p.ofertaEstacSubzonasPK.idEstacion = :idEstacion")})
public class OfertaEstacSubzonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OfertaEstacSubzonasPK ofertaEstacSubzonasPK;

    public OfertaEstacSubzonas() {
    }

    public OfertaEstacSubzonas(OfertaEstacSubzonasPK ofertaEstacSubzonasPK) {
        this.ofertaEstacSubzonasPK = ofertaEstacSubzonasPK;
    }

    public OfertaEstacSubzonas(int idSubzona, int idEstacion) {
        this.ofertaEstacSubzonasPK = new OfertaEstacSubzonasPK(idSubzona, idEstacion);
    }
    
    
    
    public void setOfertaEstacSubzonasPK(OfertaEstacSubzonasPK ofertaEstacSubzonasPK) {
        this.ofertaEstacSubzonasPK = ofertaEstacSubzonasPK;
    }

    public OfertaEstacSubzonasPK getOfertaEstacSubzonasPK() {
        return ofertaEstacSubzonasPK;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ofertaEstacSubzonasPK != null ? ofertaEstacSubzonasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaEstacSubzonas)) {
            return false;
        }
        OfertaEstacSubzonas other = (OfertaEstacSubzonas) object;
        if ((this.ofertaEstacSubzonasPK == null && other.ofertaEstacSubzonasPK != null) || 
            (this.ofertaEstacSubzonasPK != null && !this.ofertaEstacSubzonasPK.equals(other.ofertaEstacSubzonasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonas[ partRefOfertaEstacSubzonasPK=" + ofertaEstacSubzonasPK + " ]";
    }

    
}
