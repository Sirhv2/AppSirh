package co.gov.ideam.sirh.oferta.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.sql.Timestamp;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "part_ref_oferta_estac_subzonas")
@NamedQueries( { @NamedQuery(name = "PartRefOfertaEstacSubzona.findAll",
                             query =
                             "SELECT p FROM PartRefOfertaEstacSubzona p")
        ,
        @NamedQuery(name = "PartRefOfertaEstacSubzona.findByIdEstacion", query =
                    "SELECT p FROM PartRefOfertaEstacSubzona p WHERE p.idEstacion = :idEstacion")
        } )


public class PartRefOfertaEstacSubzona implements Serializable {

    @Id
    @Column(name = "id_estacion")
    private String idEstacion;

    @Column(name = "id_subzona")
    private String idSubzona;

    @Column(name = "urlFews")
    private String urlFews;

    @Column(name = "IRH")
    private Double irh;

    @Column(name = "AREACUENCA")
    private Double areaCuenca;

    @Column(name = "PERIMETRO")
    private Double perimetro;

    @Column(name = "PENDIENTEMEDIACUENCA")
    private Double pendienteMediaCuenca;

    @Column(name = "ELEVACIONMEDIA")
    private Double elevacionMedia;

    @Column(name = "DESNIVEL")
    private Double desnivel;

    @Column(name = "COMPACIDAD")
    private Double compacidad;

    @Column(name = "DENSIDADDRENEJE")
    private Double densidadDrenaje;

    @Column(name = "DENSIDADCORRIENTE")
    private Double densidadCorriente;

    @Column(name = "DEFICITAGUA")
    private Double deficitAgua;

    @Column(name = "PENDIENTEMEDIACAUCE")
    private Double pendienteMediaCauce;

    @Column(name = "LONGITUDCAUCE")
    private Double longitudCauce;

    @Column(name = "DESNIVELCAUCE")
    private Double desnivelCauce;

    @Column(name = "BOSQUE")
    private Double bosque;

    @Transient
    private String bosqueStr;

    @Transient
    private String desnivelCauceStr;

    @Transient
    private String longitudCauceStr;

    @Transient
    private String pendienteMediaCauceStr;

    @Transient
    private String deficitAguaStr;

    @Transient
    private String densidadCorrienteStr;

    @Transient
    private String densidadDrenajeStr;

    @Transient
    private String compacidadStr;

    @Transient
    private String desnivelStr;

    @Transient
    private String elevacionMediaStr;

    @Transient
    private String pendienteMediaCuencaStr;

    @Transient
    private String perimetroStr;

    @Transient
    private String areaCuencaStr;

    public PartRefOfertaEstacSubzona() {
    }


    public void setIdEstacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getIdEstacion() {
        return idEstacion;
    }

    public void setIdSubzona(String idSubzona) {
        this.idSubzona = idSubzona;
    }

    public String getIdSubzona() {
        return idSubzona;
    }

    public void setUrlFews(String urlFews) {
        this.urlFews = urlFews;
    }

    public String getUrlFews() {
        return urlFews;
    }

    public void setIrh(Double irh) {
        this.irh = irh;
    }

    public Double getIrh() {
        return irh;
    }

    public void setAreaCuenca(Double areaCuenca) {
        this.areaCuenca = areaCuenca;
    }

    public Double getAreaCuenca() {
        return areaCuenca;
    }

    public void setPerimetro(Double perimetro) {
        this.perimetro = perimetro;
    }

    public Double getPerimetro() {
        return perimetro;
    }

    public void setPendienteMediaCuenca(Double pendienteMediaCuenca) {
        this.pendienteMediaCuenca = pendienteMediaCuenca;
    }

    public Double getPendienteMediaCuenca() {
        return pendienteMediaCuenca;
    }

    public void setElevacionMedia(Double elevacionMedia) {
        this.elevacionMedia = elevacionMedia;
    }

    public Double getElevacionMedia() {
        return elevacionMedia;
    }

    public void setDesnivel(Double desnivel) {
        this.desnivel = desnivel;
    }

    public Double getDesnivel() {
        return desnivel;
    }

    public void setCompacidad(Double compacidad) {
        this.compacidad = compacidad;
    }

    public Double getCompacidad() {
        return compacidad;
    }

    public void setDensidadDrenaje(Double densidadDrenaje) {
        this.densidadDrenaje = densidadDrenaje;
    }

    public Double getDensidadDrenaje() {
        return densidadDrenaje;
    }

    public void setDensidadCorriente(Double densidadCorriente) {
        this.densidadCorriente = densidadCorriente;
    }

    public Double getDensidadCorriente() {
        return densidadCorriente;
    }

    public void setDeficitAgua(Double deficitAgua) {
        this.deficitAgua = deficitAgua;
    }

    public Double getDeficitAgua() {
        return deficitAgua;
    }

    public void setPendienteMediaCauce(Double pendienteMediaCauce) {
        this.pendienteMediaCauce = pendienteMediaCauce;
    }

    public Double getPendienteMediaCauce() {
        return pendienteMediaCauce;
    }

    public void setLongitudCauce(Double longitudCauce) {
        this.longitudCauce = longitudCauce;
    }

    public Double getLongitudCauce() {
        return longitudCauce;
    }

    public void setDesnivelCauce(Double desnivelCauce) {
        this.desnivelCauce = desnivelCauce;
    }

    public Double getDesnivelCauce() {
        return desnivelCauce;
    }

    public void setBosque(Double bosque) {
        this.bosque = bosque;
    }

    public Double getBosque() {
        return bosque;

    }

    public void setBosqueStr(String bosqueStr) {
        this.bosqueStr = bosqueStr;
    }

    public String getBosqueStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(bosque);
    }


    public void setDesnivelCauceStr(String desnivelCauceStr) {
        this.desnivelCauceStr = desnivelCauceStr;
    }

    public String getDesnivelCauceStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(desnivelCauce);
    }

    public void setLongitudCauceStr(String longitudCauceStr) {
        this.longitudCauceStr = longitudCauceStr;
    }

    public String getLongitudCauceStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(longitudCauce);
    }

    public void setPendienteMediaCauceStr(String pendienteMediaCauceStr) {
        this.pendienteMediaCauceStr = pendienteMediaCauceStr;
    }

    public String getPendienteMediaCauceStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(pendienteMediaCauce);
    }

    public void setDeficitAguaStr(String deficitAguaStr) {
        this.deficitAguaStr = deficitAguaStr;
    }

    public String getDeficitAguaStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(deficitAgua);
    }

    public void setDensidadCorrienteStr(String densidadCorrienteStr) {
        this.densidadCorrienteStr = densidadCorrienteStr;
    }

    public String getDensidadCorrienteStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(densidadCorriente);
    }

    public void setDensidadDrenajeStr(String densidadDrenajeStr) {
        this.densidadDrenajeStr = densidadDrenajeStr;
    }

    public String getDensidadDrenajeStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(densidadDrenaje);
    }

    public void setCompacidadStr(String compacidadStr) {
        this.compacidadStr = compacidadStr;
    }

    public String getCompacidadStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(compacidad);
    }

    public void setDesnivelStr(String desnivelStr) {
        this.desnivelStr = desnivelStr;
    }

    public String getDesnivelStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(desnivel);
    }

    public void setElevacionMediaStr(String elevacionMediaStr) {
        this.elevacionMediaStr = elevacionMediaStr;
    }

    public String getElevacionMediaStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(elevacionMedia);
    }

    public void setPendienteMediaCuencaStr(String pendienteMediaCuencaStr) {
        this.pendienteMediaCuencaStr = pendienteMediaCuencaStr;
    }

    public String getPendienteMediaCuencaStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(pendienteMediaCuenca);
    }

    public void setPerimetroStr(String perimetroStr) {
        this.perimetroStr = perimetroStr;
    }

    public String getPerimetroStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(perimetro);
    }

    public void setAreaCuencaStr(String areaCuencaStr) {
        this.areaCuencaStr = areaCuencaStr;
    }

    public String getAreaCuencaStr() {
        DecimalFormat myFormatter = new DecimalFormat("###,###.##");
        return myFormatter.format(areaCuenca);
    }
}
