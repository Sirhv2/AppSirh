/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.ideam.sirh.red.ideam.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "SIRHV_MUESTRAS_FQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SirhvMuestrasFq.findAll", query = "SELECT s FROM SirhvMuestrasFq s"),
    @NamedQuery(name = "SirhvMuestrasFq.findByEstacionid", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.estacionid = :estacionid"),
    @NamedQuery(name = "SirhvMuestrasFq.findByCodigomuestra", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.codigomuestra = :codigomuestra"),
    @NamedQuery(name = "SirhvMuestrasFq.findByIddetallemuestra", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.iddetallemuestra = :iddetallemuestra"),
    @NamedQuery(name = "SirhvMuestrasFq.findByCodigoanalisis", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.codigoanalisis = :codigoanalisis"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySucacatecodigocategoria", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.sucacatecodigocategoria = :sucacatecodigocategoria"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySucacodigosubcategoria", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.sucacodigosubcategoria = :sucacodigosubcategoria"),
    @NamedQuery(name = "SirhvMuestrasFq.findByDetecodigodetalletematica", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.detecodigodetalletematica = :detecodigodetalletematica"),
    @NamedQuery(name = "SirhvMuestrasFq.findByDeteconsecutivotematica", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.deteconsecutivotematica = :deteconsecutivotematica"),
    @NamedQuery(name = "SirhvMuestrasFq.findByNumalicuotas", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.numalicuotas = :numalicuotas"),
    @NamedQuery(name = "SirhvMuestrasFq.findByFechamuestreo", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.fechamuestreo = :fechamuestreo"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySupacodigounidad", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.supacodigounidad = :supacodigounidad"),
    @NamedQuery(name = "SirhvMuestrasFq.findByUnidad", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.unidad = :unidad"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySupacodigoparametro", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.supacodigoparametro = :supacodigoparametro"),
    @NamedQuery(name = "SirhvMuestrasFq.findByParametro", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.parametro = :parametro"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySupacatecodigocategoria", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.supacatecodigocategoria = :supacatecodigocategoria"),
    @NamedQuery(name = "SirhvMuestrasFq.findByCategoria", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.categoria = :categoria"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySumucodigosubmuestra", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.sumucodigosubmuestra = :sumucodigosubmuestra"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySubmuestra", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.submuestra = :submuestra"),
    @NamedQuery(name = "SirhvMuestrasFq.findBySupacodigosubcategoria", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.supacodigosubcategoria = :supacodigosubcategoria"),
    @NamedQuery(name = "SirhvMuestrasFq.findByValor", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.valor = :valor"),
    @NamedQuery(name = "SirhvMuestrasFq.findByFechaanalisis", query = "SELECT s FROM SirhvMuestrasFq s WHERE s.fechaanalisis = :fechaanalisis"),
    @NamedQuery(name = "SirhvMuestrasFq.findByEstacionMuestra", 
                query = " SELECT s FROM SirhvMuestrasFq s " +
                        " WHERE s.estacionid = :estacionid" +
                        " AND s.codigomuestra = :codigomuestra ")
    
    })
public class SirhvMuestrasFq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ESTACIONID", nullable = false)
    private Long estacionid;
    @Id
    @Column(name = "CODIGOMUESTRA", nullable = false)
    private Long codigomuestra;
    @Id
    @Column(name = "IDDETALLEMUESTRA", nullable = false)
    private Long iddetallemuestra;
    @Id
    @Column(name = "CODIGOANALISIS", nullable = false)
    private Long codigoanalisis;
    @Column(name = "SUCACATECODIGOCATEGORIA", nullable = false)
    private Long sucacatecodigocategoria;
    @Column(name = "SUCACODIGOSUBCATEGORIA", nullable = false)
    private Long sucacodigosubcategoria;
    @Column(name = "DETECODIGODETALLETEMATICA", nullable = false)
    private String detecodigodetalletematica;
    @Column(name = "DETECONSECUTIVOTEMATICA", nullable = false)
    private String deteconsecutivotematica;
    @Column(name = "NUMALICUOTAS", nullable = false)
    private Integer numalicuotas;
    @Column(name = "FECHAMUESTREO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamuestreo;
    @Column(name = "SUPACODIGOUNIDAD", nullable = false)
    private Long supacodigounidad;
    @Column(name = "UNIDAD")
    private String unidad;
    @Column(name = "SUPACODIGOPARAMETRO", nullable = false)
    private Long supacodigoparametro;
    @Column(name = "PARAMETRO")
    private String parametro;
    @Column(name = "SUPACATECODIGOCATEGORIA", nullable = false)
    private Long supacatecodigocategoria;
    @Column(name = "CATEGORIA")
    private String categoria;
    @Column(name = "SUMUCODIGOSUBMUESTRA", nullable = false)
    private Long sumucodigosubmuestra;
    @Column(name = "SUBMUESTRA")
    private String submuestra;
    @Column(name = "SUPACODIGOSUBCATEGORIA", nullable = false)
    private Long supacodigosubcategoria;
    @Column(name = "VALOR", nullable = false)
    private Double valor;
    @Column(name = "FECHAANALISIS", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaanalisis;

    public SirhvMuestrasFq() {
    }

    public void setEstacionid(Long estacionid) {
        this.estacionid = estacionid;
    }

    public Long getEstacionid() {
        return estacionid;
    }

    public void setCodigomuestra(Long codigomuestra) {
        this.codigomuestra = codigomuestra;
    }

    public Long getCodigomuestra() {
        return codigomuestra;
    }

    public void setIddetallemuestra(Long iddetallemuestra) {
        this.iddetallemuestra = iddetallemuestra;
    }

    public Long getIddetallemuestra() {
        return iddetallemuestra;
    }

    public void setCodigoanalisis(Long codigoanalisis) {
        this.codigoanalisis = codigoanalisis;
    }

    public Long getCodigoanalisis() {
        return codigoanalisis;
    }

    public void setSucacatecodigocategoria(Long sucacatecodigocategoria) {
        this.sucacatecodigocategoria = sucacatecodigocategoria;
    }

    public Long getSucacatecodigocategoria() {
        return sucacatecodigocategoria;
    }

    public void setSucacodigosubcategoria(Long sucacodigosubcategoria) {
        this.sucacodigosubcategoria = sucacodigosubcategoria;
    }

    public Long getSucacodigosubcategoria() {
        return sucacodigosubcategoria;
    }

    public void setDetecodigodetalletematica(String detecodigodetalletematica) {
        this.detecodigodetalletematica = detecodigodetalletematica;
    }

    public String getDetecodigodetalletematica() {
        return detecodigodetalletematica;
    }

    public void setDeteconsecutivotematica(String deteconsecutivotematica) {
        this.deteconsecutivotematica = deteconsecutivotematica;
    }

    public String getDeteconsecutivotematica() {
        return deteconsecutivotematica;
    }

    public void setNumalicuotas(Integer numalicuotas) {
        this.numalicuotas = numalicuotas;
    }

    public Integer getNumalicuotas() {
        return numalicuotas;
    }

    public void setFechamuestreo(Date fechamuestreo) {
        this.fechamuestreo = fechamuestreo;
    }

    public Date getFechamuestreo() {
        return fechamuestreo;
    }

    public void setSupacodigounidad(Long supacodigounidad) {
        this.supacodigounidad = supacodigounidad;
    }

    public Long getSupacodigounidad() {
        return supacodigounidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setSupacodigoparametro(Long supacodigoparametro) {
        this.supacodigoparametro = supacodigoparametro;
    }

    public Long getSupacodigoparametro() {
        return supacodigoparametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getParametro() {
        return parametro;
    }

    public void setSupacatecodigocategoria(Long supacatecodigocategoria) {
        this.supacatecodigocategoria = supacatecodigocategoria;
    }

    public Long getSupacatecodigocategoria() {
        return supacatecodigocategoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setSumucodigosubmuestra(Long sumucodigosubmuestra) {
        this.sumucodigosubmuestra = sumucodigosubmuestra;
    }

    public Long getSumucodigosubmuestra() {
        return sumucodigosubmuestra;
    }

    public void setSubmuestra(String submuestra) {
        this.submuestra = submuestra;
    }

    public String getSubmuestra() {
        return submuestra;
    }

    public void setSupacodigosubcategoria(Long supacodigosubcategoria) {
        this.supacodigosubcategoria = supacodigosubcategoria;
    }

    public Long getSupacodigosubcategoria() {
        return supacodigosubcategoria;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setFechaanalisis(Date fechaanalisis) {
        this.fechaanalisis = fechaanalisis;
    }

    public Date getFechaanalisis() {
        return fechaanalisis;
    }
}
