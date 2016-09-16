package co.gov.ideam.sirh.datosena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
    @NamedQuery(name = "DatosCuerposLenticosTO.findAll", query = "select o from DatosCuerposLenticosTO o"),
    @NamedQuery(name = "DatosCuerposLenticosTO.findIdArea", query = "select o from DatosCuerposLenticosTO o where o.idArea = :idArea")

 
})
@Table(name = "ena_cuerpos_lenticos_v")
public class DatosCuerposLenticosTO implements Serializable{
    @Id
    @Column(name = "ID_AREA")
    private Long idArea;
    
    @Column(name = "ANNIO_ENA")
    private Integer annioENA;
    
    
    @Column(name = "AREA")
    private String area;
       
       
    @Column(name = "cienagas_cantidad")
    private Integer cienagas_cantidad;
    @Column(name = "cienagas_area_ha")
    private Double cienagas_area_ha;
    
    
    @Column(name = "embalses_cantidad")
    private Integer embalses_cantidad;
    @Column(name = "embalses_area_ha")
    private Double embalses_area_ha;
    
    
    @Column(name = "lagunas_cantidad")
    private Integer lagunas_cantidad;
    @Column(name = "lagunas_area_ha")
    private Double lagunas_area_ha;
    
   @Column(name = "pantanos_cantidad")
    private Integer pantanos_cantidad;
    @Column(name = "pantanos_area_ha")
    private Double pantanos_area_ha;
    
   
    @Column(name = "TOTAL_CANTIDAD")
     private Integer total_cantidad;
     @Column(name = "TOTAL_AREA_HA")
     private Double total_area_ha;
     
    

    public DatosCuerposLenticosTO() {
    }



   

   

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public Long getIdArea() {
        return idArea;
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

    public void setCienagas_cantidad(Integer cienagas_cantidad) {
        this.cienagas_cantidad = cienagas_cantidad;
    }

    public Integer getCienagas_cantidad() {
        return cienagas_cantidad;
    }

    public void setCienagas_area_ha(Double cienagas_area_ha) {
        this.cienagas_area_ha = cienagas_area_ha;
    }

    public Double getCienagas_area_ha() {
        return cienagas_area_ha;
    }

    public void setEmbalses_cantidad(Integer embalses_cantidad) {
        this.embalses_cantidad = embalses_cantidad;
    }

    public Integer getEmbalses_cantidad() {
        return embalses_cantidad;
    }

    public void setEmbalses_area_ha(Double embalses_area_ha) {
        this.embalses_area_ha = embalses_area_ha;
    }

    public Double getEmbalses_area_ha() {
        return embalses_area_ha;
    }

    public void setLagunas_cantidad(Integer lagunas_cantidad) {
        this.lagunas_cantidad = lagunas_cantidad;
    }

    public Integer getLagunas_cantidad() {
        return lagunas_cantidad;
    }

    public void setLagunas_area_ha(Double lagunas_area_ha) {
        this.lagunas_area_ha = lagunas_area_ha;
    }

    public Double getLagunas_area_ha() {
        return lagunas_area_ha;
    }

    public void setPantanos_cantidad(Integer pantanos_cantidad) {
        this.pantanos_cantidad = pantanos_cantidad;
    }

    public Integer getPantanos_cantidad() {
        return pantanos_cantidad;
    }

    public void setPantanos_area_ha(Double pantanos_area_ha) {
        this.pantanos_area_ha = pantanos_area_ha;
    }

    public Double getPantanos_area_ha() {
        return pantanos_area_ha;
    }

    public void setTotal_cantidad(Integer total_cantidad) {
        this.total_cantidad = total_cantidad;
    }

    public Integer getTotal_cantidad() {
        return total_cantidad;
    }

    public void setTotal_area_ha(Double total_area_ha) {
        this.total_area_ha = total_area_ha;
    }

    public Double getTotal_area_ha() {
        return total_area_ha;
    }
}
