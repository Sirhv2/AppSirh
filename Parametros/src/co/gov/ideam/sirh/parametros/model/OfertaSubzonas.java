/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "part_ref_oferta_subzonas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OfertaSubzonas.findAll", query = "SELECT p FROM OfertaSubzonas p"),
    @NamedQuery(name = "OfertaSubzonas.findByIdSubzona", query = "SELECT p FROM OfertaSubzonas p WHERE p.ofertaSubzonasPK.idSubzona = :idSubzona"),
    @NamedQuery(name = "OfertaSubzonas.findByOfertaMedia", query = "SELECT p FROM OfertaSubzonas p WHERE p.ofertaMedia = :ofertaMedia"),
    @NamedQuery(name = "OfertaSubzonas.findByOfertaSeca", query = "SELECT p FROM OfertaSubzonas p WHERE p.ofertaSeca = :ofertaSeca"),
    @NamedQuery(name = "OfertaSubzonas.findByDemanda", query = "SELECT p FROM OfertaSubzonas p WHERE p.demanda = :demanda"),
    @NamedQuery(name = "OfertaSubzonas.findByIuaAnnoMedio", query = "SELECT p FROM OfertaSubzonas p WHERE p.iuaAnnoMedio = :iuaAnnoMedio"),
    @NamedQuery(name = "OfertaSubzonas.findByCategIuaAnnoMedio", query = "SELECT p FROM OfertaSubzonas p WHERE p.categIuaAnnoMedio = :categIuaAnnoMedio"),
    @NamedQuery(name = "OfertaSubzonas.findByIuaAnnoSeco", query = "SELECT p FROM OfertaSubzonas p WHERE p.iuaAnnoSeco = :iuaAnnoSeco"),
    @NamedQuery(name = "OfertaSubzonas.findByCategIuaAnnoSeco", query = "SELECT p FROM OfertaSubzonas p WHERE p.categIuaAnnoSeco = :categIuaAnnoSeco"),
    @NamedQuery(name = "OfertaSubzonas.findByIndRegulacion", query = "SELECT p FROM OfertaSubzonas p WHERE p.indRegulacion = :indRegulacion"),
    @NamedQuery(name = "OfertaSubzonas.findByCategIndRegulacion", query = "SELECT p FROM OfertaSubzonas p WHERE p.categIndRegulacion = :categIndRegulacion"),
    @NamedQuery(name = "OfertaSubzonas.findByIndVulnerabAnnoMedio", query = "SELECT p FROM OfertaSubzonas p WHERE p.indVulnerabAnnoMedio = :indVulnerabAnnoMedio"),
    @NamedQuery(name = "OfertaSubzonas.findByIndVulnerabAnnoSeco", query = "SELECT p FROM OfertaSubzonas p WHERE p.indVulnerabAnnoSeco = :indVulnerabAnnoSeco"),
    @NamedQuery(name = "OfertaSubzonas.findByIacalAnnoMedio", query = "SELECT p FROM OfertaSubzonas p WHERE p.iacalAnnoMedio = :iacalAnnoMedio"),
    @NamedQuery(name = "OfertaSubzonas.findByIacalAnnoSeco", query = "SELECT p FROM OfertaSubzonas p WHERE p.iacalAnnoSeco = :iacalAnnoSeco"),
    @NamedQuery(name = "OfertaSubzonas.findByAnnoEna", query = "SELECT p FROM OfertaSubzonas p WHERE p.ofertaSubzonasPK.annoEna = :annoEna")})
public class OfertaSubzonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OfertaSubzonasPK ofertaSubzonasPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "oferta_media")
    private Double ofertaMedia;
    @Column(name = "oferta_seca")
    private Double ofertaSeca;
    @Column(name = "demanda")
    private Double demanda;
    @Column(name = "iua_anno_medio")
    private Double iuaAnnoMedio;
    @Column(name = "categ_iua_anno_medio")
    private String categIuaAnnoMedio;
    @Column(name = "iua_anno_seco")
    private Double iuaAnnoSeco;
    @Column(name = "categ_iua_anno_seco")
    private String categIuaAnnoSeco;
    @Column(name = "ind_regulacion")
    private Double indRegulacion;
    @Column(name = "categ_ind_regulacion")
    private String categIndRegulacion;
    @Column(name = "ind_vulnerab_anno_medio")
    private String indVulnerabAnnoMedio;
    @Column(name = "ind_vulnerab_anno_seco")
    private String indVulnerabAnnoSeco;
    @Column(name = "iacal_anno_medio")
    private String iacalAnnoMedio;
    @Column(name = "iacal_anno_seco")
    private String iacalAnnoSeco;
    
    public OfertaSubzonas() {
    }


    public void setOfertaSubzonasPK(OfertaSubzonasPK ofertaSubzonasPK) {
        this.ofertaSubzonasPK = ofertaSubzonasPK;
    }

    public OfertaSubzonasPK getOfertaSubzonasPK() {
        return ofertaSubzonasPK;
    }
    
    public Double getOfertaMedia() {
        return ofertaMedia;
    }

    public void setOfertaMedia(Double ofertaMedia) {
        this.ofertaMedia = ofertaMedia;
    }

    public Double getOfertaSeca() {
        return ofertaSeca;
    }

    public void setOfertaSeca(Double ofertaSeca) {
        this.ofertaSeca = ofertaSeca;
    }

    public Double getDemanda() {
        return demanda;
    }

    public void setDemanda(Double demanda) {
        this.demanda = demanda;
    }

    public Double getIuaAnnoMedio() {
        return iuaAnnoMedio;
    }

    public void setIuaAnnoMedio(Double iuaAnnoMedio) {
        this.iuaAnnoMedio = iuaAnnoMedio;
    }

    public String getCategIuaAnnoMedio() {
        return categIuaAnnoMedio;
    }

    public void setCategIuaAnnoMedio(String categIuaAnnoMedio) {
        this.categIuaAnnoMedio = categIuaAnnoMedio;
    }

    public Double getIuaAnnoSeco() {
        return iuaAnnoSeco;
    }

    public void setIuaAnnoSeco(Double iuaAnnoSeco) {
        this.iuaAnnoSeco = iuaAnnoSeco;
    }

    public String getCategIuaAnnoSeco() {
        return categIuaAnnoSeco;
    }

    public void setCategIuaAnnoSeco(String categIuaAnnoSeco) {
        this.categIuaAnnoSeco = categIuaAnnoSeco;
    }

    public Double getIndRegulacion() {
        return indRegulacion;
    }

    public void setIndRegulacion(Double indRegulacion) {
        this.indRegulacion = indRegulacion;
    }

    public String getCategIndRegulacion() {
        return categIndRegulacion;
    }

    public void setCategIndRegulacion(String categIndRegulacion) {
        this.categIndRegulacion = categIndRegulacion;
    }

    public String getIndVulnerabAnnoMedio() {
        return indVulnerabAnnoMedio;
    }

    public void setIndVulnerabAnnoMedio(String indVulnerabAnnoMedio) {
        this.indVulnerabAnnoMedio = indVulnerabAnnoMedio;
    }

    public String getIndVulnerabAnnoSeco() {
        return indVulnerabAnnoSeco;
    }

    public void setIndVulnerabAnnoSeco(String indVulnerabAnnoSeco) {
        this.indVulnerabAnnoSeco = indVulnerabAnnoSeco;
    }

    public String getIacalAnnoMedio() {
        return iacalAnnoMedio;
    }

    public void setIacalAnnoMedio(String iacalAnnoMedio) {
        this.iacalAnnoMedio = iacalAnnoMedio;
    }

    public String getIacalAnnoSeco() {
        return iacalAnnoSeco;
    }

    public void setIacalAnnoSeco(String iacalAnnoSeco) {
        this.iacalAnnoSeco = iacalAnnoSeco;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ofertaSubzonasPK != null ? ofertaSubzonasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaSubzonas)) {
            return false;
        }
        OfertaSubzonas other = (OfertaSubzonas) object;
        if ((this.ofertaSubzonasPK == null && other.ofertaSubzonasPK != null) || (this.ofertaSubzonasPK != null && !this.ofertaSubzonasPK.equals(other.ofertaSubzonasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.parametros.model.OfertaSubzonas[ ofertaSubzonasPK=" + ofertaSubzonasPK + " ]";
    }

}
