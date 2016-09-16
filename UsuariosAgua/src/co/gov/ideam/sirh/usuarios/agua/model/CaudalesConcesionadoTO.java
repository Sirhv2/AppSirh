package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "sirh_caudales_concesionados_v")
@NamedQueries( { @NamedQuery(name = "CaudalesConcesionadoTO.findAll",
                             query = "SELECT s FROM CaudalesConcesionadoTO s")
        ,
        @NamedQuery(name = "CaudalesConcesionadoTO.findAutoridadAll", query = "SELECT s FROM CaudalesConcesionadoTO s where s.id_autoridad = :codigoAutoridad ")
        } )
public class CaudalesConcesionadoTO implements Serializable {


    @Id
    @Column(name = "num", nullable = false)
    private Long num;
    @Column(name = "idfuente", nullable = false)
    private Long id;
    @Column(name = "fuente")
    private String fuente;

    @Column(name = "id_subzona")
    private Long id_subzona;
    @Column(name = "subzona")
    private String subzona;


    @Column(name = "tramos_id")
    private Long tramo_id;
    @Column(name = "tramo")
    private String tramo;
    @Column(name = "numero_concesiones")
    private Integer numero_concesiones;
    @Column(name = "caudal_concesionado")
    private Double caudal_concesionado;
    @Column(name = "id_autoridad")
    private Long id_autoridad;
    @Column(name = "autoridad")
    private String autoridad;

    @Column(name = "oferta_disponible")
    private Double oferta_disponible;

    @Column(name = "IUA")
    private Double IUA;


    @Transient
    private String categoria_IAU;
    @Transient
    private String imgcateg_IAU;

    public CaudalesConcesionadoTO() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }


    public void setId_autoridad(Long id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public Long getId_autoridad() {
        return id_autoridad;
    }

    public void setNumero_concesiones(Integer numero_concesiones) {
        this.numero_concesiones = numero_concesiones;
    }

    public Integer getNumero_concesiones() {
        return numero_concesiones;
    }

    public void setCaudal_concesionado(Double caudal_concesionado) {
        this.caudal_concesionado = caudal_concesionado;
    }

    public Double getCaudal_concesionado() {
        return caudal_concesionado;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public void setTramo_id(Long tramo_id) {
        this.tramo_id = tramo_id;
    }

    public Long getTramo_id() {
        return tramo_id;
    }

    public void setId_subzona(Long id_subzona) {
        this.id_subzona = id_subzona;
    }

    public Long getId_subzona() {
        return id_subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getSubzona() {
        return subzona;
    }


    public void setOferta_disponible(Double oferta_disponible) {
        this.oferta_disponible = oferta_disponible;
    }

    public Double getOferta_disponible() {
        return oferta_disponible;
    }

    public void setIUA(Double IUA) {
        this.IUA = IUA;
    }

    public Double getIUA() {
        return IUA;
    }

    public void setCategoria_IAU(String categoria_IAU) {

     
        this.categoria_IAU = categoria_IAU;
        System.out.println("bbbbbbbbb  categoria_IAU" + categoria_IAU);
    }


    public String getCategoria_IAU() {
        return categoria_IAU;
    }

    public void setImgcateg_IAU(String imgcateg_IAU) {
       
        this.imgcateg_IAU = imgcateg_IAU;
    }

    public String getImgcateg_IAU() {
        return imgcateg_IAU;
    }
}
