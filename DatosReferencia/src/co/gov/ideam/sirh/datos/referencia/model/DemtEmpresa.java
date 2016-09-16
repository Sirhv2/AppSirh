/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "demt_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemtEmpresa.findAll", query = "SELECT d FROM DemtEmpresa d"),
    @NamedQuery(name = "DemtEmpresa.findById", query = "SELECT d FROM DemtEmpresa d WHERE d.id = :id"),
    @NamedQuery(name = "DemtEmpresa.findByNombre", query = "SELECT d FROM DemtEmpresa d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DemtEmpresa.findByNit", query = "SELECT d FROM DemtEmpresa d WHERE d.nit = :nit"),
    @NamedQuery(name = "DemtEmpresa.findByDireccion", query = "SELECT d FROM DemtEmpresa d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "DemtEmpresa.findByTelefono", query = "SELECT d FROM DemtEmpresa d WHERE d.telefono = :telefono")})
public class DemtEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Empresa", sequenceName="seq_empresa", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Empresa")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "nit", nullable = false)
    private String nit;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    
    @Transient
    private List<DemtTarifa> demtTarifaList;

    public DemtEmpresa() {
    }

    public DemtEmpresa(Integer id) {
        this.id = id;
    }

    public DemtEmpresa(Integer id, String nombre, String nit) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<DemtTarifa> getDemtTarifaList() {
        return demtTarifaList;
    }

    public void setDemtTarifaList(List<DemtTarifa> demtTarifaList) {
        this.demtTarifaList = demtTarifaList;
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
        if (!(object instanceof DemtEmpresa)) {
            return false;
        }
        DemtEmpresa other = (DemtEmpresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.datos.referencia.model.DemtEmpresa[ id=" + id + " ]";
    }
    
}
