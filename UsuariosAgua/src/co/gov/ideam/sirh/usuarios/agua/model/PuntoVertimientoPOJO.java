package co.gov.ideam.sirh.usuarios.agua.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
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
@Table(name = "rurt_punto_vertimiento")
public class PuntoVertimientoPOJO implements Serializable{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "departamento")
    private Integer idDepartamento;
    @Column(name = "municipio")
    private Integer idMunicipio;
    @Column(name = "tipo_centro_poblado")
    private Integer idTipoCentroPoblado;
    @Column(name = "nombre_centro_poblado")
    private String nombreCentroPoblado;
    @Column(name = "tipo_vertimiento")
    private Integer tipoVertimiento;
    @Column(name = "tipo_flujo")
    private Integer tipoFlujo;
    @Column(name = "tiempo_descarga")
    private Double tiempoDescarga;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Column(name = "caudal_disegno")
    private Double caudalDisegno;
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
    @Column(name = "id_permiso_vertimiento")
    private Long idPermisoVertimiento;
    @Column(name = "id_tramo")
    private Long idTramo;
    @Column(name = "id_fuente")
    private Long idFuente; 
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Autoridades autoridad;
    
    public PuntoVertimientoPOJO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setTipoVertimiento(Integer tipoVertimiento) {
        this.tipoVertimiento = tipoVertimiento;
    }

    public Integer getTipoVertimiento() {
        return tipoVertimiento;
    }

    public void setTipoFlujo(Integer tipoFlujo) {
        this.tipoFlujo = tipoFlujo;
    }

    public Integer getTipoFlujo() {
        return tipoFlujo;
    }

    public void setTiempoDescarga(Double tiempoDescarga) {
        this.tiempoDescarga = tiempoDescarga;
    }

    public Double getTiempoDescarga() {
        return tiempoDescarga;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setCaudalDisegno(Double caudalDisegno) {
        this.caudalDisegno = caudalDisegno;
    }

    public Double getCaudalDisegno() {
        return caudalDisegno;
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

    public void setIdPermisoVertimiento(Long idPermisoVertimiento) {
        this.idPermisoVertimiento = idPermisoVertimiento;
    }

    public Long getIdPermisoVertimiento() {
        return idPermisoVertimiento;
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
}
