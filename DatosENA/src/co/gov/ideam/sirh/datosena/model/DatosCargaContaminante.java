package co.gov.ideam.sirh.datosena.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lis
 */
@Entity
@Table(name = "ENA_CARGA_CONTAMINANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosCargaContaminante.findAll", query = "SELECT e FROM DatosCargaContaminante e"),
    @NamedQuery(name = "DatosCargaContaminante.findById", query = "SELECT e FROM DatosCargaContaminante e WHERE e.id = :id"),
    @NamedQuery(name = "DatosCargaContaminante.findByParametro", query = "SELECT e FROM DatosCargaContaminante e WHERE e.parametro = :parametro"),
    @NamedQuery(name = "DatosCargaContaminante.findBySigla", query = "SELECT e FROM DatosCargaContaminante e WHERE e.sigla = :sigla"),
    @NamedQuery(name = "DatosCargaContaminante.findByCafe", query = "SELECT e FROM DatosCargaContaminante e WHERE e.cafe = :cafe"),
    @NamedQuery(name = "DatosCargaContaminante.findByIndustria", query = "SELECT e FROM DatosCargaContaminante e WHERE e.industria = :industria"),
    @NamedQuery(name = "DatosCargaContaminante.findByDomestico", query = "SELECT e FROM DatosCargaContaminante e WHERE e.domestico = :domestico"),
    @NamedQuery(name = "DatosCargaContaminante.findByTonAnnio", query = "SELECT e FROM DatosCargaContaminante e WHERE e.tonAnnio = :tonAnnio"),
    @NamedQuery(name = "DatosCargaContaminante.findByTonDia", query = "SELECT e FROM DatosCargaContaminante e WHERE e.tonDia = :tonDia"),
    @NamedQuery(name = "DatosCargaContaminante.findByAnnioEna", query = "SELECT e FROM DatosCargaContaminante e WHERE e.annioEna = :annioEna")})
public class DatosCargaContaminante implements Serializable {
   
    @Id
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PARAMETRO")
    private String parametro;
   
    @Column(name = "SIGLA")
    private String sigla;
    @Column(name = "CAFE")
    private Double cafe;
    @Column(name = "INDUSTRIA")
    private Double industria;
    @Column(name = "DOMESTICO")
    private Double domestico;
    @Column(name = "TON_ANNIO")
    private Double tonAnnio;
    @Column(name = "TON_DIA")
    private Double tonDia;
    @Column(name = "ANNIO_ENA")
    private BigInteger annioEna;

    public DatosCargaContaminante() {
    }



    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Double getCafe() {
        return cafe;
    }

    public void setCafe(Double cafe) {
        this.cafe = cafe;
    }

    public Double getIndustria() {
        return industria;
    }

    public void setIndustria(Double industria) {
        this.industria = industria;
    }

    public Double getDomestico() {
        return domestico;
    }

    public void setDomestico(Double domestico) {
        this.domestico = domestico;
    }

    public Double getTonAnnio() {
        return tonAnnio;
    }

    public void setTonAnnio(Double tonAnnio) {
        this.tonAnnio = tonAnnio;
    }

    public Double getTonDia() {
        return tonDia;
    }

    public void setTonDia(Double tonDia) {
        this.tonDia = tonDia;
    }

    public BigInteger getAnnioEna() {
        return annioEna;
    }

    public void setAnnioEna(BigInteger annioEna) {
        this.annioEna = annioEna;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
