/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.datos.referencia.model;

import java.io.Serializable;
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
@Table(name = "sirhv_caudal_estado_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findAll", query = "SELECT s FROM SirhvCaudalEstadoUsuario s"),
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findByCaudalconcesionado", query = "SELECT s FROM SirhvCaudalEstadoUsuario s WHERE s.caudalconcesionado = :caudalconcesionado"),
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findByCantidad", query = "SELECT s FROM SirhvCaudalEstadoUsuario s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findByAutoridad", query = "SELECT s FROM SirhvCaudalEstadoUsuario s WHERE s.autoridad = :autoridad"),
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findByTipo", query = "SELECT s FROM SirhvCaudalEstadoUsuario s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SirhvCaudalEstadoUsuario.findByMunicipio", query = "SELECT s FROM SirhvCaudalEstadoUsuario s WHERE s.municipio = :municipio")
    })
public class SirhvCaudalEstadoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "caudalconcesionado")
    private Double caudalconcesionado;
    @Column(name = "cantidad")
    private Double cantidad;
    @Id
    @Column(name = "autoridad")
    private String autoridad;
    @Id
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "depto")
    private Integer depto;
    @Column(name = "municipio")
    private Integer municipio;
    @Column(name = "descDepartamento")
    private String descDepartamento;
    @Column(name = "descMunicipio")
    private String descMunicipio;

    public SirhvCaudalEstadoUsuario() {
    }

    public Double getCaudalconcesionado() {
        return caudalconcesionado;
    }

    public void setCaudalconcesionado(Double caudalconcesionado) {
        this.caudalconcesionado = caudalconcesionado;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDepto(Integer depto) {
        this.depto = depto;
    }

    public Integer getDepto() {
        return depto;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public Integer getMunicipio() {
        return municipio;
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
}
