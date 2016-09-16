/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.pomca.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "POMT_DETALLE_CUENCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PomtDetalleCuenca.findAll", query = "SELECT p FROM PomtDetalleCuenca p"),
    @NamedQuery(name = "PomtDetalleCuenca.findById", query = "SELECT p FROM PomtDetalleCuenca p WHERE p.id = :id"),
    @NamedQuery(name = "PomtDetalleCuenca.findByCodigo", query = "SELECT p FROM PomtDetalleCuenca p WHERE p.codigo = :codigo")})
public class PomtDetalleCuenca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "fuente_principal", nullable = false)
    private Long fuentePrincipal;
    @Transient
    private FnttFuente fuente;
    @Transient
    private List afluentes;
    @Transient
    private Cuenca cuenca;
    
    public PomtDetalleCuenca() {
    }

    public PomtDetalleCuenca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setFuentePrincipal(Long fuentePrincipal) {
        this.fuentePrincipal = fuentePrincipal;
    }

    public Long getFuentePrincipal() {
        return fuentePrincipal;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }
    
    public void setAfluentes(List afluentes) {
        this.afluentes = afluentes;
    }

    public List getAfluentes() {
        return afluentes;
    }
    
    public void setCuenca(Cuenca cuenca) {
        this.cuenca = cuenca;
    }

    public Cuenca getCuenca() {
        return cuenca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PomtDetalleCuenca)) {
            return false;
        }
        PomtDetalleCuenca other = (PomtDetalleCuenca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.pomca.model.PomtDetalleCuenca[ id=" + id + " ]";
    }

}
