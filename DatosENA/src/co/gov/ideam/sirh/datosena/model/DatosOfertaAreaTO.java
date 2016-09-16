package co.gov.ideam.sirh.datosena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "DatosOfertaAreaTO.findAll", query = "select o from DatosOfertaAreaTO o"),
    @NamedQuery(name = "DatosOfertaAreaTO.findIdArea", query = "select o from DatosOfertaAreaTO o where o.idArea = :idArea")

 
})
@Table(name = "ENA_OFERTATOTAL_V")
public class DatosOfertaAreaTO implements Serializable{
    @Id
    @Column(name = "ID_AREA")
    private Long idArea;
    
    @Column(name = "AREA_KM")
    private Double areaKM;
    
    @Column(name = "OFERTA_TOTAL")
    private Double ofertaTotal;
    
    @Column(name = "PORCENTAJE_OFERTATOTAL")
    private Double porcentajeOfertaTotal;
    
    @Column(name = "OFERTA_ANNIOMEDIO")
    private Double oferaAnnioMedio;
    
    @Column(name = "OFERTA_ANNIOSECO")
    private Double oferaAnnioSeco;
    
    @Column(name = "PORCENTAJE_REDUCCION")
    private Double porcentajeReduccion;
    
    @Column(name = "CAUDAL")
    private Double caudal;
    
    @Column(name = "ANNIO_ENA")
    private Integer annioENA;
    
    
    @Column(name = "AREA")
    private String area;
       
                       
        

    public DatosOfertaAreaTO() {
    }



    public void setAreaKM(Double areaKM) {
        this.areaKM = areaKM;
    }

    public Double getAreaKM() {
        return areaKM;
    }

    public void setPorcentajeOfertaTotal(Double porcentajeOfertaTotal) {
        this.porcentajeOfertaTotal = porcentajeOfertaTotal;
    }

    public Double getPorcentajeOfertaTotal() {
        return porcentajeOfertaTotal;
    }

    public void setOferaAnnioMedio(Double oferaAnnioMedio) {
        this.oferaAnnioMedio = oferaAnnioMedio;
    }

    public Double getOferaAnnioMedio() {
        return oferaAnnioMedio;
    }

    public void setOferaAnnioSeco(Double oferaAnnioSeco) {
        this.oferaAnnioSeco = oferaAnnioSeco;
    }

    public Double getOferaAnnioSeco() {
        return oferaAnnioSeco;
    }

    public void setPorcentajeReduccion(Double porcentajeReduccion) {
        this.porcentajeReduccion = porcentajeReduccion;
    }

    public Double getPorcentajeReduccion() {
        return porcentajeReduccion;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }



    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setOfertaTotal(Double ofertaTotal) {
        this.ofertaTotal = ofertaTotal;
    }

    public Double getOfertaTotal() {
        return ofertaTotal;
    }

    public void setAnnioENA(Integer annioENA) {
        this.annioENA = annioENA;
    }

    public Integer getAnnioENA() {
        return annioENA;
    }


    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }
}
