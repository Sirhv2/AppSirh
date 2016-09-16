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
    @NamedQuery(name = "DatosSectoresArea.findAll", query = "select o from DatosSectoresArea o order by o.id"),
    @NamedQuery(name = "DatosSectoresArea.findANIO", query = "select o from DatosSectoresArea o where o.annioENA = :annioENA")

 
})
@Table(name = "ENA_SECTORES_AREAH")
public class DatosSectoresArea implements Serializable{
    @Id
    @Column(name = "ID")
    private Long id;
            
    @Column(name = "USOS_AGUA")
    private String usoAgua;
    
    @Column(name = "AREA_MAGDALENA")
    private Double areaMagdalena;
    
    @Column(name = "AREA_CARIBE")
    private Double areaCaribe;
    
    @Column(name = "AREA_ORINOCO")
    private Double areaOrinoco;
    
    @Column(name = "AREA_AMAZONAS")
    private Double areaAmazonas;
    
    @Column(name = "AREA_PACIFICO")
    private Double areaPacifico;

    @Column(name = "TOTAL_AREA")
    private Double total_area;
   
   @Column(name = "TOTAL_PORCENTAJE")
    private Double total_porcentaje;
    
    @Column(name = "ANNIO_ENA")
    private Integer annioENA;
    
    @Column(name = "PORCENTAJE_MAGDALENA")
    private Double porcentajeMagdalena;
    
    @Column(name = "PORCENTAJE_CARIBE")
    private Double porcentajeCaribe;
    
    @Column(name = "PORCENTAJE_ORINOCO")
    private Double porcentajeOrinoco;
    
    @Column(name = "PORCENTAJE_AMAZONAS")
    private Double porcentajeAmazonas;
    
    @Column(name = "PORCENTAJE_PACIFICO")
    private Double porcentajePacifico;
        

    public DatosSectoresArea() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsoAgua(String usoAgua) {
        this.usoAgua = usoAgua;
    }

    public String getUsoAgua() {
        return usoAgua;
    }

    public void setAreaMagdalena(Double areaMagdalena) {
        this.areaMagdalena = areaMagdalena;
    }

    public Double getAreaMagdalena() {
        return areaMagdalena;
    }

    public void setAreaCaribe(Double areaCaribe) {
        this.areaCaribe = areaCaribe;
    }

    public Double getAreaCaribe() {
        return areaCaribe;
    }

    public void setAreaOrinoco(Double areaOrinoco) {
        this.areaOrinoco = areaOrinoco;
    }

    public Double getAreaOrinoco() {
        return areaOrinoco;
    }

    public void setAreaAmazonas(Double areaAmazonas) {
        this.areaAmazonas = areaAmazonas;
    }

    public Double getAreaAmazonas() {
        return areaAmazonas;
    }

    public void setAreaPacifico(Double areaPacifico) {
        this.areaPacifico = areaPacifico;
    }

    public Double getAreaPacifico() {
        return areaPacifico;
    }


    public void setAnnioENA(Integer annioENA) {
        this.annioENA = annioENA;
    }

    public Integer getAnnioENA() {
        return annioENA;
    }

  

    public void setPorcentajeMagdalena(Double porcentajeMagdalena) {
        this.porcentajeMagdalena = porcentajeMagdalena;
    }

    public Double getPorcentajeMagdalena() {
        return porcentajeMagdalena;
    }

    public void setPorcentajeCaribe(Double porcentajeCaribe) {
        this.porcentajeCaribe = porcentajeCaribe;
    }

    public Double getPorcentajeCaribe() {
        return porcentajeCaribe;
    }

    public void setPorcentajeOrinoco(Double porcentajeOrinoco) {
        this.porcentajeOrinoco = porcentajeOrinoco;
    }

    public Double getPorcentajeOrinoco() {
        return porcentajeOrinoco;
    }

    public void setPorcentajeAmazonas(Double porcentajeAmazonas) {
        this.porcentajeAmazonas = porcentajeAmazonas;
    }

    public Double getPorcentajeAmazonas() {
        return porcentajeAmazonas;
    }

    public void setPorcentajePacifico(Double porcentajePacifico) {
        this.porcentajePacifico = porcentajePacifico;
    }

    public Double getPorcentajePacifico() {
        return porcentajePacifico;
    }

    public void setTotal_area(Double total_area) {
        this.total_area = total_area;
    }

    public Double getTotal_area() {
        return total_area;
    }

    public void setTotal_porcentaje(Double total_porcentaje) {
        this.total_porcentaje = total_porcentaje;
    }

    public Double getTotal_porcentaje() {
        return total_porcentaje;
    }
}
