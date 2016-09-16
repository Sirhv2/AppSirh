/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "demt_tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemtTarifa.findAll", query = "SELECT d FROM DemtTarifa d"),
    @NamedQuery(name = "DemtTarifa.findById", query = "SELECT d FROM DemtTarifa d WHERE d.id = :id"),
    @NamedQuery(name = "DemtTarifa.findByCargoFijo", query = "SELECT d FROM DemtTarifa d WHERE d.cargoFijo = :cargoFijo"),
    @NamedQuery(name = "DemtTarifa.findByCargoBasico", query = "SELECT d FROM DemtTarifa d WHERE d.cargoBasico = :cargoBasico"),
    @NamedQuery(name = "DemtTarifa.findByCargoComplementario", query = "SELECT d FROM DemtTarifa d WHERE d.cargoComplementario = :cargoComplementario"),
    @NamedQuery(name = "DemtTarifa.findByCargoSuntuario", query = "SELECT d FROM DemtTarifa d WHERE d.cargoSuntuario = :cargoSuntuario"),
    @NamedQuery(name = "DemtTarifa.findByMes", query = "SELECT d FROM DemtTarifa d WHERE d.mes = :mes"),
    @NamedQuery(name = "DemtTarifa.findByAgno", query = "SELECT d FROM DemtTarifa d WHERE d.agno = :agno"),
    @NamedQuery(name = "DemtTarifa.findByDepartamento", query = "SELECT d FROM DemtTarifa d WHERE d.departamento = :departamento"),
    @NamedQuery(name = "DemtTarifa.findByMunicipio", query = "SELECT d FROM DemtTarifa d WHERE d.municipio = :municipio"),
    @NamedQuery(name = "DemtTarifa.findByEmpresa", query = "SELECT d FROM DemtTarifa d WHERE d.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "DemtTarifa.findByFiltros", 
                query = "SELECT d FROM DemtTarifa d " +
                        "WHERE d.municipio = :municipio AND d.departamento = :departamento " +
                        "AND d.agno = :agno AND d.mes = :mes AND d.idEmpresa = :idEmpresa " +
                        "AND d.estrato = :estrato AND d.clase = :clase" 
                )
    })
public class DemtTarifa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="tarifaAcu", sequenceName="seq_tarifa", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tarifaAcu")
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cargo_fijo", nullable = false)
    private BigDecimal cargoFijo;
    @Column(name = "cargo_basico", nullable = true)
    private BigDecimal cargoBasico;
    @Column(name = "cargo_complementario", nullable = true)
    private BigDecimal cargoComplementario;
    @Column(name = "cargo_suntuario", nullable = true)
    private BigDecimal cargoSuntuario;
    @Column(name = "mes", nullable = false)
    private int mes;
    @Column(name = "agno", nullable = false)
    private int agno;
    @Column(name = "departamento", nullable = false)
    private int departamento;
    @Column(name = "municipio", nullable = false)
    private int municipio;
    @Column(name = "empresa", nullable = false)
    private Integer idEmpresa;
    @Column(name = "estrato", nullable = false)
    private int estrato;
    @Column(name = "servicio", nullable = false)
    private int servicio;
    @Column(name = "clase", nullable = false)
    private int clase;
    
    
    
    @Column(name = "tar_plena", nullable = true)
    private BigDecimal tar_plena;
    @Column(name = "tar_consumo", nullable = true)
    private BigDecimal tar_consumo;
    
    @Transient
    private List<DemtFacturado> demtFacturadoList;
    @Transient
    private DemtEmpresa empresa;

    public DemtTarifa() {
    }

    public DemtTarifa(Integer id) {
        this.id = id;
    }

    public DemtTarifa(Integer id, BigDecimal cargoFijo, BigDecimal cargoBasico, BigDecimal cargoComplementario, BigDecimal cargoSuntuario, int mes, int agno, int departamento, int municipio) {
        this.id = id;
        this.cargoFijo = cargoFijo;
        this.cargoBasico = cargoBasico;
        this.cargoComplementario = cargoComplementario;
        this.cargoSuntuario = cargoSuntuario;
        this.mes = mes;
        this.agno = agno;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCargoFijo() {
        return cargoFijo;
    }

    public void setCargoFijo(BigDecimal cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    public BigDecimal getCargoBasico() {
        return cargoBasico;
    }

    public void setCargoBasico(BigDecimal cargoBasico) {
        this.cargoBasico = cargoBasico;
    }

    public BigDecimal getCargoComplementario() {
        return cargoComplementario;
    }

    public void setCargoComplementario(BigDecimal cargoComplementario) {
        this.cargoComplementario = cargoComplementario;
    }

    public BigDecimal getCargoSuntuario() {
        return cargoSuntuario;
    }

    public void setCargoSuntuario(BigDecimal cargoSuntuario) {
        this.cargoSuntuario = cargoSuntuario;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    

    @XmlTransient
    public List<DemtFacturado> getDemtFacturadoList() {
        return demtFacturadoList;
    }

    public void setDemtFacturadoList(List<DemtFacturado> demtFacturadoList) {
        this.demtFacturadoList = demtFacturadoList;
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
        if (!(object instanceof DemtTarifa)) {
            return false;
        }
        DemtTarifa other = (DemtTarifa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.datos.referencia.model.DemtTarifa[ id=" + id + " ]";
    }

    public void setEmpresa(DemtEmpresa empresa) {
        this.empresa = empresa;
    }

    public DemtEmpresa getEmpresa() {
        return empresa;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public int getClase() {
        return clase;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public int getServicio() {
        return servicio;
    }

    public void setTar_plena(BigDecimal tar_plena) {
        this.tar_plena = tar_plena;
    }

    public BigDecimal getTar_plena() {
        return tar_plena;
    }

    public void setTar_consumo(BigDecimal tar_consumo) {
        this.tar_consumo = tar_consumo;
    }

    public BigDecimal getTar_consumo() {
        return tar_consumo;
    }
}
