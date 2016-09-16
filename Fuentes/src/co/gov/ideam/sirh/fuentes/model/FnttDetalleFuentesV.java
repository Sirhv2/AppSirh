package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "fntt_detalle_fuentes_v")
@NamedQueries( { 
            @NamedQuery(name = "FnttDetalleFuentesV.findAll", query = "SELECT f FROM FnttDetalleFuentesV f"),
    @NamedQuery(name = "FnttDetalleFuentesV.findByCodigoAutoridad", query = "SELECT f FROM FnttDetalleFuentesV f WHERE f.codigoAutoridad = :codigoAutoridad"),
    @NamedQuery(name = "FnttDetalleFuentesV.findByNombreAutoridad", query = "SELECT f FROM FnttDetalleFuentesV f WHERE f.nombreAutoridad = :nombreAutoridad")})
      
public class FnttDetalleFuentesV implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPO_FUENTE")
    private String tipoFuente;

    @Column(name = "COD_CUENCA")
    private String codCuenca;
    @Column(name = "NO_CAPTACIONES")
    private Integer noCaptaciones;

    @Column(name = "NOMBRE_TRAMO")
    private String nombreTramo;
    @Column(name = "LONGITUD_TRAMO")
    private Double longitudTramo;

    @Column(name = "LATITUD_PUNTO_I")
    private String latitudPuntoI;

    @Column(name = "LONGITUD_PUNTO_I")
    private String longitudPuntoI;

    @Column(name = "LATITUD_PUNTO_F")
    private String latitudPuntoF;

    @Column(name = "LONGITUD_PUNTO_F")
    private String longitudPuntoF;

    @Column(name = "OFERTA_HIDRICA_TOTAL")
    private Double ofertaHidricaTotal;

    @Column(name = "CODIGO_AUTORIDAD")
    private Integer codigoAutoridad;

    @Column(name = "NOMBRE_AUTORIDAD")
    private String nombreAutoridad;


    public FnttDetalleFuentesV() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipoFuente(String tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    public String getTipoFuente() {
        return tipoFuente;
    }

    public void setCodCuenca(String codCuenca) {
        this.codCuenca = codCuenca;
    }

    public String getCodCuenca() {
        return codCuenca;
    }

    public void setNoCaptaciones(Integer noCaptaciones) {
        this.noCaptaciones = noCaptaciones;
    }

    public Integer getNoCaptaciones() {
        return noCaptaciones;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public void setLongitudTramo(Double longitudTramo) {
        this.longitudTramo = longitudTramo;
    }

    public Double getLongitudTramo() {
        return longitudTramo;
    }

    public void setLatitudPuntoI(String latitudPuntoI) {
        this.latitudPuntoI = latitudPuntoI;
    }

    public String getLatitudPuntoI() {
        return latitudPuntoI;
    }

    public void setLongitudPuntoI(String longitudPuntoI) {
        this.longitudPuntoI = longitudPuntoI;
    }

    public String getLongitudPuntoI() {
        return longitudPuntoI;
    }

    public void setLatitudPuntoF(String latitudPuntoF) {
        this.latitudPuntoF = latitudPuntoF;
    }

    public String getLatitudPuntoF() {
        return latitudPuntoF;
    }

    public void setLongitudPuntoF(String longitudPuntoF) {
        this.longitudPuntoF = longitudPuntoF;
    }

    public String getLongitudPuntoF() {
        return longitudPuntoF;
    }

    public void setOfertaHidricaTotal(Double ofertaHidricaTotal) {
        this.ofertaHidricaTotal = ofertaHidricaTotal;
    }

    public Double getOfertaHidricaTotal() {
        return ofertaHidricaTotal;
    }

    public void setCodigoAutoridad(Integer codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Integer getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }
}


