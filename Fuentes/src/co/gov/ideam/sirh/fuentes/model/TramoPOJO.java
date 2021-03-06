package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "fntt_tramo")
public class TramoPOJO implements Serializable {
    
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "oferta_hidrica_total")
    private BigDecimal ofertaHidricaTotal;
    @Column(name = "grados_pi")
    private Integer gradosPi;
    @Column(name = "minutos_pi")
    private Integer minutosPi;
    @Column(name = "segundos_pi")
    private BigDecimal segundosPi;
    @Column(name = "altitud_pi")
    private BigDecimal altitudPi;
    @Column(name = "grados_pf")
    private Integer gradosPf;
    @Column(name = "minutos_pf")
    private Integer minutosPf;
    @Column(name = "segundos_pf")
    private BigDecimal segundosPf;
    @Column(name = "altitud_pf")
    private BigDecimal altitudPf;
    @Column(name = "grados_long_pi")
    private Integer gradosLongPi;
    @Column(name = "minutos_long_pi")
    private Integer minutosLongPi;    
    @Column(name = "segundos_long_pi")
    private BigDecimal segundosLongPi;
    @Column(name = "grados_long_pf")
    private Integer gradosLongPf;
    @Column(name = "minutos_long_pf")
    private Integer minutosLongPf;    
    @Column(name = "segundos_long_pf")
    private BigDecimal segundosLongPf;
    @Column(name = "tipo_flujo")
    private Integer tipo_flujo;
    @Column(name = "sistema_referencia")
    private Integer sistema_referencia;
    @Column(name = "id_fuente")
    private Long id_fuente;
    @Column(name = "id_area")
    private Integer id_area;
    @Column(name = "id_zona")
    private Integer id_zona;
    @Column(name = "id_subzona")
    private Integer id_subzona;
    //cDoncel
    @Column(name = "observacion_tramo")
    private String observacion;    
    @Transient
    private Long codigoAutoridad;
    //@Transient
    //private List municipios;

   
    public TramoPOJO() {
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setOfertaHidricaTotal(BigDecimal ofertaHidricaTotal) {
        this.ofertaHidricaTotal = ofertaHidricaTotal;
    }

    public BigDecimal getOfertaHidricaTotal() {
        return ofertaHidricaTotal;
    }

    public void setGradosPi(Integer gradosPi) {
        this.gradosPi = gradosPi;
    }

    public Integer getGradosPi() {
        return gradosPi;
    }

    public void setMinutosPi(Integer minutosPi) {
        this.minutosPi = minutosPi;
    }

    public Integer getMinutosPi() {
        return minutosPi;
    }

    public void setSegundosPi(BigDecimal segundosPi) {
        this.segundosPi = segundosPi;
    }

    public BigDecimal getSegundosPi() {
        return segundosPi;
    }

    public void setAltitudPi(BigDecimal altitudPi) {
        this.altitudPi = altitudPi;
    }

    public BigDecimal getAltitudPi() {
        return altitudPi;
    }

    public void setGradosPf(Integer gradosPf) {
        this.gradosPf = gradosPf;
    }

    public Integer getGradosPf() {
        return gradosPf;
    }

    public void setMinutosPf(Integer minutosPf) {
        this.minutosPf = minutosPf;
    }

    public Integer getMinutosPf() {
        return minutosPf;
    }

    public void setSegundosPf(BigDecimal segundosPf) {
        this.segundosPf = segundosPf;
    }

    public BigDecimal getSegundosPf() {
        return segundosPf;
    }

    public void setAltitudPf(BigDecimal altitudPf) {
        this.altitudPf = altitudPf;
    }

    public BigDecimal getAltitudPf() {
        return altitudPf;
    }

    public void setGradosLongPi(Integer gradosLongPi) {
        this.gradosLongPi = gradosLongPi;
    }

    public Integer getGradosLongPi() {
        return gradosLongPi;
    }

    public void setMinutosLongPi(Integer minutosLongPi) {
        this.minutosLongPi = minutosLongPi;
    }

    public Integer getMinutosLongPi() {
        return minutosLongPi;
    }

    public void setSegundosLongPi(BigDecimal segundosLongPi) {
        this.segundosLongPi = segundosLongPi;
    }

    public BigDecimal getSegundosLongPi() {
        return segundosLongPi;
    }

    public void setGradosLongPf(Integer gradosLongPf) {
        this.gradosLongPf = gradosLongPf;
    }

    public Integer getGradosLongPf() {
        return gradosLongPf;
    }

    public void setMinutosLongPf(Integer minutosLongPf) {
        this.minutosLongPf = minutosLongPf;
    }

    public Integer getMinutosLongPf() {
        return minutosLongPf;
    }

    public void setSegundosLongPf(BigDecimal segundosLongPf) {
        this.segundosLongPf = segundosLongPf;
    }

    public BigDecimal getSegundosLongPf() {
        return segundosLongPf;
    }

    public void setTipo_flujo(Integer tipo_flujo) {
        this.tipo_flujo = tipo_flujo;
    }

    public Integer getTipo_flujo() {
        return tipo_flujo;
    }

    public void setSistema_referencia(Integer sistema_referencia) {
        this.sistema_referencia = sistema_referencia;
    }

    public Integer getSistema_referencia() {
        return sistema_referencia;
    }

    public void setId_fuente(Long id_fuente) {
        this.id_fuente = id_fuente;
    }

    public Long getId_fuente() {
        return id_fuente;
    }


    public void setId_area(Integer id_area) {
        this.id_area = id_area;
    }

    public Integer getId_area() {
        return id_area;
    }

    public void setId_zona(Integer id_zona) {
        this.id_zona = id_zona;
    }

    public Integer getId_zona() {
        return id_zona;
    }

    public void setId_subzona(Integer id_subzona) {
        this.id_subzona = id_subzona;
    }

    public Integer getId_subzona() {
        return id_subzona;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

/*
    public void setMunicipios(List municipios) {
        this.municipios = municipios;
    }

    public List getMunicipios() {
        return municipios;
    }
*/


  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
}
