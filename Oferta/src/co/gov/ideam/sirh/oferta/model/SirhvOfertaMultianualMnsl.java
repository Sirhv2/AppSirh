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
@Table(name = "sirhv_oferta_multianual_mnsl")
@XmlRootElement
@NamedQueries({
 @NamedQuery(name = "SirhvOfertaMultianualMnsl.findAll", query = "SELECT s FROM SirhvOfertaMultianualMnsl s"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByTipo", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.tipo = :tipo"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByAno", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.ano = :ano"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByEstacionid", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.estacionid = :estacionid"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByVarid", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.varid = :varid"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByEnero", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.enero = :enero"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByFebrero", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.febrero = :febrero"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByMarzo", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.marzo = :marzo"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByAbril", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.abril = :abril"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByMayo", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.mayo = :mayo"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByJunio", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.junio = :junio"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByJulio", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.julio = :julio"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByAgosto", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.agosto = :agosto"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findBySeptiembre", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.septiembre = :septiembre"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByOctubre", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.octubre = :octubre"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByNoviembre", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.noviembre = :noviembre"),
     @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByDiciembre", query = "SELECT s FROM SirhvOfertaMultianualMnsl s WHERE s.diciembre = :diciembre"),
        @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByEstacionVariable", 
                query = "SELECT s FROM SirhvOfertaMultianualMnsl s " +
                        "WHERE s.estacionid = :estacionId AND s.varid = :varId "+
                        //" AND s.tipo='1-MEDIOS'" 
                        ""),//esta condicion se adiciono porque los tres valores son iguales
    @NamedQuery(name = "SirhvOfertaMultianualMnsl.findByEstacionVariableAnio", 
                query = "SELECT s FROM SirhvOfertaMultianualMnsl s " +
                        "WHERE s.estacionid = :estacionId " +
                        "AND s.varid = :varId AND s.ano = :ano "+
                        // " --AND s.tipo='1-MEDIOS'"
                        "")//esta condicion se adiciono porque los tres valores son iguales
    }
    )
public class SirhvOfertaMultianualMnsl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "tipo")
    private String tipo;
    @Id
    @Column(name = "ano")
    private Integer ano;
    @Id
    @Column(name = "estacionid")
    private Long estacionid;
    @Id
    @Column(name = "varid")
    private String varid;
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

    public SirhvOfertaMultianualMnsl() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getEstacionid() {
        return estacionid;
    }

    public void setEstacionid(Long estacionid) {
        this.estacionid = estacionid;
    }

    public String getVarid() {
        return varid;
    }

    public void setVarid(String varid) {
        this.varid = varid;
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
}