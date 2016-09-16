package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rurt_captacion")
public class CaptacionPOJO implements Serializable{
    
    @Id
    @Column(name="id", nullable = false)
    private Long codigo;
    @Column(name = "departamento")
    private Integer idDepartamento;
    @Column(name = "municipio")
    private Integer idMunicipio;
    @Column(name = "tipo_centro_poblado")
    private Integer idTipoCentroPoblado;
    @Column(name = "nombre_centro_poblado")
    private String nombreCentroPoblado;
    @Column(name = "oferta_hidrica_total")
    private Double ofertaHidricaTotal;
    @Column(name = "oferta_disponible")
    private Double ofertaDisponible;
    @Column(name = "continuidad")
    private String continuidad;
    @Column(name = "estado_capatacion")
    private Integer estadoCaptacion;
    @Column(name = "caudal_disegno")
    private Double caudalDisegno;
    @Column(name = "caudal_vertimiento")
    private Double caudalVertimiento;
    @Column(name = "sistema_referencia")
    private Integer sistemaReferencia;
    @Column(name = "grados_lat")
    private Integer gradosLat;
    @Column(name = "minutos_lat")
    private Integer minutosLat;
    @Column(name = "segundos_lat")
    private Double segundosLat;
    @Column(name = "grados_long")
    private Integer gradosLong;
    @Column(name = "minutos_long")
    private Integer minutosLong;
    @Column(name = "segundos_long")
    private Double segundosLong;
    @Column(name = "altitud")
    private Double altitud;
    @Column(name = "descripcion_acceso")
    private String descripcionAcceso;
    @Column(name = "provincia_hidrogeologica")
    private Integer provinciaHidrogeologica;
    @Column(name = "unidad_hidrogeologica")
    private String unidadHidrogeologica;
    @Column(name = "tipo_punto")
    private Integer tipoPunto;
    @Column(name = "identificador_punto")
    private String identificadorPunto;
    @Column(name = "metodo_extraccion")
    private Integer metodoExtraccion;
    @Column(name = "tipo_captacion")
    private Integer tipoCaptacion;
    @Column(name = "area_captacion")
    private Double areaCaptacion;
    @Column(name = "tipo_fuente_captacion")
    private Integer tipoFuenteCaptacion;
    @Column(name = "tiene_macro_medicion")
    private Integer tieneMacroMedicion;
    @Column(name = "tiene_servidumbre")
    private Integer tieneServidumbre;
    @Column(name = "id_area")
    private Integer idArea;
    @Column(name = "id_zona")
    private Integer idZona;
    @Column(name = "id_subzona")
    private Integer idSubzona;
    @Column(name = "id_concesion")
    private Long idConcesion;
    @Column(name = "id_tramo")
    private Long idTramo;
    @Column(name = "id_fuente")
    private Long idFuente;
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Autoridades autoridad;
    //Pilar
    @Column(name = "red_monitoreo")
    private Integer red_monitoreo;
  
    //cDoncel  
    @Column(name = "observacion")
    private String observacion;

    
    public CaptacionPOJO() {
    }


    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdTipoCentroPoblado(Integer idTipoCentroPoblado) {
        this.idTipoCentroPoblado = idTipoCentroPoblado;
    }

    public Integer getIdTipoCentroPoblado() {
        return idTipoCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public void setOfertaHidricaTotal(Double ofertaHidricaTotal) {
        this.ofertaHidricaTotal = ofertaHidricaTotal;
    }

    public Double getOfertaHidricaTotal() {
        return ofertaHidricaTotal;
    }

    public void setOfertaDisponible(Double ofertaDisponible) {
        this.ofertaDisponible = ofertaDisponible;
    }

    public Double getOfertaDisponible() {
        return ofertaDisponible;
    }

    public void setContinuidad(String continuidad) {
        this.continuidad = continuidad;
    }

    public String getContinuidad() {
        return continuidad;
    }

    public void setEstadoCaptacion(Integer estadoCaptacion) {
        this.estadoCaptacion = estadoCaptacion;
    }

    public Integer getEstadoCaptacion() {
        return estadoCaptacion;
    }

    public void setCaudalDisegno(Double caudalDisegno) {
        this.caudalDisegno = caudalDisegno;
    }

    public Double getCaudalDisegno() {
        return caudalDisegno;
    }

    public void setCaudalVertimiento(Double caudalVertimiento) {
        this.caudalVertimiento = caudalVertimiento;
    }

    public Double getCaudalVertimiento() {
        return caudalVertimiento;
    }

    public void setSistemaReferencia(Integer sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public Integer getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setGradosLat(Integer gradosLat) {
        this.gradosLat = gradosLat;
    }

    public Integer getGradosLat() {
        return gradosLat;
    }

    public void setMinutosLat(Integer minutosLat) {
        this.minutosLat = minutosLat;
    }

    public Integer getMinutosLat() {
        return minutosLat;
    }

    public void setSegundosLat(Double segundosLat) {
        this.segundosLat = segundosLat;
    }

    public Double getSegundosLat() {
        return segundosLat;
    }

    public void setGradosLong(Integer gradosLong) {
        this.gradosLong = gradosLong;
    }

    public Integer getGradosLong() {
        return gradosLong;
    }

    public void setMinutosLong(Integer minutosLong) {
        this.minutosLong = minutosLong;
    }

    public Integer getMinutosLong() {
        return minutosLong;
    }

    public void setSegundosLong(Double segundosLong) {
        this.segundosLong = segundosLong;
    }

    public Double getSegundosLong() {
        return segundosLong;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setDescripcionAcceso(String descripcionAcceso) {
        this.descripcionAcceso = descripcionAcceso;
    }

    public String getDescripcionAcceso() {
        return descripcionAcceso;
    }

    public void setProvinciaHidrogeologica(Integer provinciaHidrogeologica) {
        this.provinciaHidrogeologica = provinciaHidrogeologica;
    }

    public Integer getProvinciaHidrogeologica() {
        return provinciaHidrogeologica;
    }

    public void setUnidadHidrogeologica(String unidadHidrogeologica) {
        this.unidadHidrogeologica = unidadHidrogeologica;
    }

    public String getUnidadHidrogeologica() {
        return unidadHidrogeologica;
    }

    public void setTipoPunto(Integer tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public Integer getTipoPunto() {
        return tipoPunto;
    }

    public void setIdentificadorPunto(String identificadorPunto) {
        this.identificadorPunto = identificadorPunto;
    }

    public String getIdentificadorPunto() {
        return identificadorPunto;
    }

    public void setMetodoExtraccion(Integer metodoExtraccion) {
        this.metodoExtraccion = metodoExtraccion;
    }

    public Integer getMetodoExtraccion() {
        return metodoExtraccion;
    }

    public void setTipoCaptacion(Integer tipoCaptacion) {
        this.tipoCaptacion = tipoCaptacion;
    }

    public Integer getTipoCaptacion() {
        return tipoCaptacion;
    }

    public void setAreaCaptacion(Double areaCaptacion) {
        this.areaCaptacion = areaCaptacion;
    }

    public Double getAreaCaptacion() {
        return areaCaptacion;
    }

    public void setTipoFuenteCaptacion(Integer tipoFuenteCaptacion) {
        this.tipoFuenteCaptacion = tipoFuenteCaptacion;
    }

    public Integer getTipoFuenteCaptacion() {
        return tipoFuenteCaptacion;
    }

    public void setTieneMacroMedicion(Integer tieneMacroMedicion) {
        this.tieneMacroMedicion = tieneMacroMedicion;
    }

    public Integer getTieneMacroMedicion() {
        return tieneMacroMedicion;
    }

    public void setTieneServidumbre(Integer tieneServidumbre) {
        this.tieneServidumbre = tieneServidumbre;
    }

    public Integer getTieneServidumbre() {
        return tieneServidumbre;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdSubzona(Integer idSubzona) {
        this.idSubzona = idSubzona;
    }

    public Integer getIdSubzona() {
        return idSubzona;
    }

    public void setIdConcesion(Long idConcesion) {
        this.idConcesion = idConcesion;
    }

    public Long getIdConcesion() {
        return idConcesion;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setAutoridad(Autoridades autoridad) {
        this.autoridad = autoridad;
    }

    public Autoridades getAutoridad() {
        return autoridad;
    }

    public void setRed_monitoreo(Integer red_monitoreo) {
        this.red_monitoreo = red_monitoreo;
    }

    public Integer getRed_monitoreo() {
        return red_monitoreo;
    }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
}
