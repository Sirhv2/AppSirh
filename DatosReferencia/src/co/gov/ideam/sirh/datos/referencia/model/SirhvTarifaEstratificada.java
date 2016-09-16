/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "sirhv_tarifa_estratificada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvTarifaEstratificada.findAll", query = "SELECT s FROM SirhvTarifaEstratificada s"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByDepartamento", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.departamento = :departamento"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByMunicipio", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.municipio = :municipio"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByAgno", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.agno = :agno"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByClase", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.clase = :clase"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByTipoCargo", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.tipoCargo = :tipoCargo"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato1", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato1 = :estrato1"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato2", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato2 = :estrato2"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato3", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato3 = :estrato3"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato4", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato4 = :estrato4"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato5", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato5 = :estrato5"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato6", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato6 = :estrato6"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato10", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato10 = :estrato10"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato11", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato11 = :estrato11"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByEstrato12", query = "SELECT s FROM SirhvTarifaEstratificada s WHERE s.estrato12 = :estrato12"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByAgnoClase", 
                query = "SELECT s FROM SirhvTarifaEstratificada s " +
                        "WHERE s.agno = :agno AND s.clase = :clase"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByAgnoClaseCargo", 
                query = "SELECT s FROM SirhvTarifaEstratificada s " +
                        "WHERE s.agno = :agno AND s.clase = :clase AND s.tipoCargo = :tipoCargo"),
    @NamedQuery(name = "SirhvTarifaEstratificada.findByAgnoCargo", 
                query = "SELECT s FROM SirhvTarifaEstratificada s " +
                        "WHERE s.agno = :agno AND s.tipoCargo = :tipoCargo")
    })
public class SirhvTarifaEstratificada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "departamento")
    private Integer departamento;
    @Id
    @Column(name = "municipio")
    private Integer municipio;
    @Id
    @Column(name = "agno")
    private Integer agno;
    @Id
    @Column(name = "clase")
    private Integer clase;
    @Id
    @Column(name = "tipocargo")
    private String tipoCargo;
    @Id
    @Column(name = "estrato1")
    private Double estrato1;
    @Column(name = "estrato2")
    private Double estrato2;
    @Column(name = "estrato3")
    private Double estrato3;
    @Column(name = "estrato4")
    private Double estrato4;
    @Column(name = "estrato5")
    private Double estrato5;
    @Column(name = "estrato6")
    private Double estrato6;
    @Column(name = "estrato10")
    private Double estrato10;
    @Column(name = "estrato11")
    private Double estrato11;
    @Column(name = "estrato12")
    private Double estrato12;
    
    @Column(name = "descDepartamento")
    private String descDepartamento;
    @Column(name = "descMunicipio")
    private String descMunicipio;
    @Column(name = "descClase")
    private String descClase;

    public SirhvTarifaEstratificada() {
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getAgno() {
        return agno;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    public String getTipoCargo() {
        return tipoCargo;
    }


    public void setEstrato1(Double estrato1) {
        this.estrato1 = estrato1;
    }

    public Double getEstrato1() {
        return estrato1;
    }

    public void setEstrato2(Double estrato2) {
        this.estrato2 = estrato2;
    }

    public Double getEstrato2() {
        return estrato2;
    }

    public void setEstrato3(Double estrato3) {
        this.estrato3 = estrato3;
    }

    public Double getEstrato3() {
        return estrato3;
    }

    public void setEstrato4(Double estrato4) {
        this.estrato4 = estrato4;
    }

    public Double getEstrato4() {
        return estrato4;
    }

    public void setEstrato5(Double estrato5) {
        this.estrato5 = estrato5;
    }

    public Double getEstrato5() {
        return estrato5;
    }

    public void setEstrato6(Double estrato6) {
        this.estrato6 = estrato6;
    }

    public Double getEstrato6() {
        return estrato6;
    }

    public void setEstrato10(Double estrato10) {
        this.estrato10 = estrato10;
    }

    public Double getEstrato10() {
        return estrato10;
    }

    public void setEstrato11(Double estrato11) {
        this.estrato11 = estrato11;
    }

    public Double getEstrato11() {
        return estrato11;
    }

    public void setEstrato12(Double estrato12) {
        this.estrato12 = estrato12;
    }

    public Double getEstrato12() {
        return estrato12;
    }


    public void setDescDepartamento(String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }

    public String getDescDepartamento() {
        return descDepartamento;
    }

    public void setDescMunicipio(String descMunicipio) {
        this.descMunicipio = descMunicipio;
    }

    public String getDescMunicipio() {
        return descMunicipio;
    }

    public void setDescClase(String descClase) {
        this.descClase = descClase;
    }

    public String getDescClase() {
        return descClase;
    }
}
