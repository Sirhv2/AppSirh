
package co.gov.ideam.sirh.datosena.model;

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
 * @author Lis
 */
@Entity
@Table(name = "ENA_AREA_POMCAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosAreaPomcas.findAll", query = "SELECT e FROM DatosAreaPomcas e"),
    @NamedQuery(name = "DatosAreaPomcas.findById", query = "SELECT e FROM DatosAreaPomcas e WHERE e.id = :id"),
    @NamedQuery(name = "DatosAreaPomcas.findByAreaHidrografica", query = "SELECT e FROM DatosAreaPomcas e WHERE e.areaHidrografica = :areaHidrografica"),
    @NamedQuery(name = "DatosAreaPomcas.findByAreaTerritorioKm", query = "SELECT e FROM DatosAreaPomcas e WHERE e.areaTerritorioKm = :areaTerritorioKm"),
    @NamedQuery(name = "DatosAreaPomcas.findByAreaObjetoPomcas", query = "SELECT e FROM DatosAreaPomcas e WHERE e.areaObjetoPomcas = :areaObjetoPomcas"),
    @NamedQuery(name = "DatosAreaPomcas.findByPorcentajeArea", query = "SELECT e FROM DatosAreaPomcas e WHERE e.porcentajeArea = :porcentajeArea"),
    @NamedQuery(name = "DatosAreaPomcas.findByAnnioEna", query = "SELECT e FROM DatosAreaPomcas e WHERE e.annioEna = :annioEna")})
public class DatosAreaPomcas implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;
   
    @Column(name = "AREA_HIDROGRAFICA")
    private String areaHidrografica;
    @Column(name = "AREA_TERRITORIO_KM")
    private Double areaTerritorioKm;
    @Column(name = "AREA_OBJETO_POMCAS")
    private Double areaObjetoPomcas;
    @Column(name = "PORCENTAJE_AREA")
    private Double porcentajeArea;
    @Column(name = "ANNIO_ENA")
    private BigInteger annioEna;

    public DatosAreaPomcas() {
    }

 



    public String getAreaHidrografica() {
        return areaHidrografica;
    }

    public void setAreaHidrografica(String areaHidrografica) {
        this.areaHidrografica = areaHidrografica;
    }

    public Double getAreaTerritorioKm() {
        return areaTerritorioKm;
    }

    public void setAreaTerritorioKm(Double areaTerritorioKm) {
        this.areaTerritorioKm = areaTerritorioKm;
    }

    public Double getAreaObjetoPomcas() {
        return areaObjetoPomcas;
    }

    public void setAreaObjetoPomcas(Double areaObjetoPomcas) {
        this.areaObjetoPomcas = areaObjetoPomcas;
    }

    public Double getPorcentajeArea() {
        return porcentajeArea;
    }

    public void setPorcentajeArea(Double porcentajeArea) {
        this.porcentajeArea = porcentajeArea;
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
