/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;

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
@Table(name = "demt_acueducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemtAcueducto.findAll", query = "SELECT d FROM DemtAcueducto d"),
    @NamedQuery(name = "DemtAcueducto.findById", query = "SELECT d FROM DemtAcueducto d WHERE d.id = :id"),
    @NamedQuery(name = "DemtAcueducto.findByNombre", query = "SELECT d FROM DemtAcueducto d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DemtAcueducto.findByNivel", query = "SELECT d FROM DemtAcueducto d WHERE d.nivel = :nivel"),
    @NamedQuery(name = "DemtAcueducto.findByDepartamento", query = "SELECT d FROM DemtAcueducto d WHERE d.departamento = :departamento"),
    @NamedQuery(name = "DemtAcueducto.findByMunicipio", query = "SELECT d FROM DemtAcueducto d WHERE d.municipio = :municipio"),
    @NamedQuery(name = "DemtAcueducto.findByEmpresa", query = "SELECT d FROM DemtAcueducto d WHERE d.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "DemtAcueducto.findByDepartamentoMunicipio", 
                query = "SELECT d FROM DemtAcueducto d WHERE d.departamento = :departamento AND d.municipio = :municipio")
    })
public class DemtAcueducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="Acueducto", sequenceName="seq_acueducto", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Acueducto")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "nivel", nullable = false)
    private Integer nivel;
    @Column(name = "departamento", nullable = false)
    private Integer departamento;
    @Column(name = "municipio", nullable = false)
    private Integer municipio;
    @Column(name = "empresa", nullable = false)
    private Integer idEmpresa;
    
    @Transient
    private DemtEmpresa empresa;

    public DemtAcueducto() {
    }

    public DemtAcueducto(Integer id) {
        this.id = id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setEmpresa(DemtEmpresa empresa) {
        this.empresa = empresa;
    }

    public DemtEmpresa getEmpresa() {
        return empresa;
    }
}
