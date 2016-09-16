/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.oferta.model;

import java.io.Serializable;
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
@Table(name = "sirhv_oferta_multianual_diaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvOfertaMultianual.findByDrsAno", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.ano = :ano"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByDrsEstacionId", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.estacionId = :estacionId"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByDrsVarId", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.varId = :varId"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByTipo", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByEnero", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.enero = :enero"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByFebrero", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.febrero = :febrero"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByMarzo", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.marzo = :marzo"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByAbril", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.abril = :abril"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByMayo", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.mayo = :mayo"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByJunio", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.junio = :junio"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByJulio", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.julio = :julio"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByAgosto", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.agosto = :agosto"),
    @NamedQuery(name = "SirhvOfertaMultianual.findBySeptiembre", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.septiembre = :septiembre"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByOctubre", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.octubre = :octubre"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByNoviembre", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.noviembre = :noviembre"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByDiciembre", 
                query = "SELECT s FROM SirhvOfertaMultianual s WHERE s.diciembre = :diciembre"),
    @NamedQuery(name = "SirhvOfertaMultianual.findByEstacionVariable", 
                query = "SELECT s FROM SirhvOfertaMultianual s " +
                        "WHERE s.estacionId = :estacionId AND s.varId = :varId "),
    @NamedQuery(name = "SirhvOfertaMultianual.findByEstacionVariableAnio", 
                query = "SELECT s FROM SirhvOfertaMultianual s " +
                        "WHERE s.estacionId = :estacionId " +
                        "AND s.varId = :varId AND s.ano = :ano")
    })
public class SirhvOfertaMultianual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ano")
    private Integer ano;
    @Id
    @Column(name = "estacionId")
    private Long estacionId;
    @Id
    @Column(name = "varId")
    private String varId;
    @Id
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "enero")
    private Double enero;
    @Column(name = "febrero")
    private Double febrero;
    @Column(name = "marzo")
    private Double marzo;
    @Column(name = "abril")
    private Double abril;
    @Column(name = "mayo")
    private Double mayo;
    @Column(name = "junio")
    private Double junio;
    @Column(name = "julio")
    private Double julio;
    @Column(name = "agosto")
    private Double agosto;
    @Column(name = "septiembre")
    private Double septiembre;
    @Column(name = "octubre")
    private Double octubre;
    @Column(name = "noviembre")
    private Double noviembre;
    @Column(name = "diciembre")
    private Double diciembre;

    public SirhvOfertaMultianual() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getEnero() {
        return enero;
    }

    public void setEnero(Double enero) {
        this.enero = enero;
    }

    public Double getFebrero() {
        return febrero;
    }

    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }

    public Double getAbril() {
        return abril;
    }

    public void setAbril(Double abril) {
        this.abril = abril;
    }

    public Double getMayo() {
        return mayo;
    }

    public void setMayo(Double mayo) {
        this.mayo = mayo;
    }

    public Double getJunio() {
        return junio;
    }

    public void setJunio(Double junio) {
        this.junio = junio;
    }

    public Double getJulio() {
        return julio;
    }

    public void setJulio(Double julio) {
        this.julio = julio;
    }

    public Double getAgosto() {
        return agosto;
    }

    public void setAgosto(Double agosto) {
        this.agosto = agosto;
    }

    public Double getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Double septiembre) {
        this.septiembre = septiembre;
    }

    public Double getOctubre() {
        return octubre;
    }

    public void setOctubre(Double octubre) {
        this.octubre = octubre;
    }

    public Double getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Double noviembre) {
        this.noviembre = noviembre;
    }

    public Double getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Double diciembre) {
        this.diciembre = diciembre;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setEstacionId(Long estacionId) {
        this.estacionId = estacionId;
    }

    public Long getEstacionId() {
        return estacionId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getVarId() {
        return varId;
    }
}
