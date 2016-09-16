/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.pomca.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "POMT_AFLUENTES_CUENCA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PomtAfluentesCuenca.findAll", query = "SELECT p FROM PomtAfluentesCuenca p"),
    @NamedQuery(name = "PomtAfluentesCuenca.findById", query = "SELECT p FROM PomtAfluentesCuenca p WHERE p.id = :id"),
    @NamedQuery(name = "PomtAfluentesCuenca.findAllByCuenca", query = "SELECT p FROM PomtAfluentesCuenca p WHERE p.idCuenca = :idCuenca")
    })
public class PomtAfluentesCuenca implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="AfluenteCuenca", sequenceName="SEQ_AFLUENTES_CUENCA", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AfluenteCuenca")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "id_cuenca", nullable = false)
    private Long idCuenca;
    @Column(name = "id_fuente", nullable = false)
    private Long idFuente;
    @Transient
    private PomtDetalleCuenca cuenca;
    @Transient
    private FnttFuente fuente;

    public PomtAfluentesCuenca() {
    }

    public PomtAfluentesCuenca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setIdCuenca(Long idCuenca) {
        this.idCuenca = idCuenca;
    }

    public Long getIdCuenca() {
        return idCuenca;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setCuenca(PomtDetalleCuenca cuenca) {
        this.cuenca = cuenca;
    }

    public PomtDetalleCuenca getCuenca() {
        return cuenca;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
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
        if (!(object instanceof PomtAfluentesCuenca)) {
            return false;
        }
        PomtAfluentesCuenca other = (PomtAfluentesCuenca) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.pomca.model.PomtAfluentesCuenca[ id=" + id + " ]";
    }

}
