/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "demt_facturado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemtFacturado.findAll", query = "SELECT d FROM DemtFacturado d"),
    @NamedQuery(name = "DemtFacturado.findById", query = "SELECT d FROM DemtFacturado d WHERE d.id = :id"),
    @NamedQuery(name = "DemtFacturado.findByConsumo", query = "SELECT d FROM DemtFacturado d WHERE d.consumo = :consumo"),
    @NamedQuery(name = "DemtFacturado.findByValorTarifaPlena", query = "SELECT d FROM DemtFacturado d WHERE d.valorTarifaPlena = :valorTarifaPlena")})
public class DemtFacturado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Factura", sequenceName="seq_factura", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Factura")
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "consumo")
    private BigDecimal consumo;
    @Column(name = "valor_tarifa_plena")
    private Integer valorTarifaPlena;
    @Column(name = "tarifas")
    private Integer idTarifas;
    @Transient
    private DemtTarifa tarifas;

    public DemtFacturado() {
    }

    public DemtFacturado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getConsumo() {
        return consumo;
    }

    public void setConsumo(BigDecimal consumo) {
        this.consumo = consumo;
    }

    public Integer getValorTarifaPlena() {
        return valorTarifaPlena;
    }

    public void setValorTarifaPlena(Integer valorTarifaPlena) {
        this.valorTarifaPlena = valorTarifaPlena;
    }

    public DemtTarifa getTarifas() {
        return tarifas;
    }

    public void setTarifas(DemtTarifa tarifas) {
        this.tarifas = tarifas;
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
        if (!(object instanceof DemtFacturado)) {
            return false;
        }
        DemtFacturado other = (DemtFacturado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.datos.referencia.model.DemtFacturado[ id=" + id + " ]";
    }
    
}
