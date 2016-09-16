package co.gov.ideam.sirh.oferta.model;

import co.gov.ideam.sirh.parametros.model.Divipola;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eduin
 */
@Entity
@Table(name = "siov_estaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SiovEstaciones.findAll", query = "SELECT s FROM SiovEstaciones s"),
    @NamedQuery(name = "SiovEstaciones.findByNombreEs", query = "SELECT s FROM SiovEstaciones s WHERE s.nombreEs = :nombreEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodCatalogoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codCatalogoEs = :codCatalogoEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodInternacionalEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codInternacionalEs = :codInternacionalEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodInternoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codInternoEs = :codInternoEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodReporteEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codReporteEs = :codReporteEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodOaciEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codOaciEs = :codOaciEs"),
    @NamedQuery(name = "SiovEstaciones.findByCodWmoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.codWmoEs = :codWmoEs"),
    @NamedQuery(name = "SiovEstaciones.findByCategoriaEs", query = "SELECT s FROM SiovEstaciones s WHERE s.categoriaEs = :categoriaEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescCategoria", query = "SELECT s FROM SiovEstaciones s WHERE s.descCategoria = :descCategoria"),
    @NamedQuery(name = "SiovEstaciones.findByClaseEs", query = "SELECT s FROM SiovEstaciones s WHERE s.claseEs = :claseEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescClase", query = "SELECT s FROM SiovEstaciones s WHERE s.descClase = :descClase"),
    @NamedQuery(name = "SiovEstaciones.findByTipoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.tipoEs = :tipoEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescTipo", query = "SELECT s FROM SiovEstaciones s WHERE s.descTipo = :descTipo"),
    @NamedQuery(name = "SiovEstaciones.findByEscalaEs", query = "SELECT s FROM SiovEstaciones s WHERE s.escalaEs = :escalaEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescEscala", query = "SELECT s FROM SiovEstaciones s WHERE s.descEscala = :descEscala"),
    @NamedQuery(name = "SiovEstaciones.findByEstadoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.estadoEs = :estadoEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescEstado", query = "SELECT s FROM SiovEstaciones s WHERE s.descEstado = :descEstado"),
    @NamedQuery(name = "SiovEstaciones.findByFechaAplicacionEs", query = "SELECT s FROM SiovEstaciones s WHERE s.fechaAplicacionEs = :fechaAplicacionEs"),
    @NamedQuery(name = "SiovEstaciones.findByObjInstalacionEs", query = "SELECT s FROM SiovEstaciones s WHERE s.objInstalacionEs = :objInstalacionEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescObjetivoInst", query = "SELECT s FROM SiovEstaciones s WHERE s.descObjetivoInst = :descObjetivoInst"),
    @NamedQuery(name = "SiovEstaciones.findByComenInstalacionEs", query = "SELECT s FROM SiovEstaciones s WHERE s.comenInstalacionEs = :comenInstalacionEs"),
    @NamedQuery(name = "SiovEstaciones.findBySatEnlaceEs", query = "SELECT s FROM SiovEstaciones s WHERE s.satEnlaceEs = :satEnlaceEs"),
    @NamedQuery(name = "SiovEstaciones.findBySatNombreEs", query = "SELECT s FROM SiovEstaciones s WHERE s.satNombreEs = :satNombreEs"),
    @NamedQuery(name = "SiovEstaciones.findBySatVentanaEs", query = "SELECT s FROM SiovEstaciones s WHERE s.satVentanaEs = :satVentanaEs"),
    @NamedQuery(name = "SiovEstaciones.findBySatProtocoloEs", query = "SELECT s FROM SiovEstaciones s WHERE s.satProtocoloEs = :satProtocoloEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescSatProtocolo", query = "SELECT s FROM SiovEstaciones s WHERE s.descSatProtocolo = :descSatProtocolo"),
    @NamedQuery(name = "SiovEstaciones.findBySatTipoEs", query = "SELECT s FROM SiovEstaciones s WHERE s.satTipoEs = :satTipoEs"),
    @NamedQuery(name = "SiovEstaciones.findByDescSatTipo", query = "SELECT s FROM SiovEstaciones s WHERE s.descSatTipo = :descSatTipo"),
    @NamedQuery(name = "SiovEstaciones.findByAlturaReferenciaEs", query = "SELECT s FROM SiovEstaciones s WHERE s.alturaReferenciaEs = :alturaReferenciaEs"),
    @NamedQuery(name = "SiovEstaciones.findByGradosLatitud", query = "SELECT s FROM SiovEstaciones s WHERE s.gradosLatitud = :gradosLatitud"),
    @NamedQuery(name = "SiovEstaciones.findByMinutosLatitud", query = "SELECT s FROM SiovEstaciones s WHERE s.minutosLatitud = :minutosLatitud"),
    @NamedQuery(name = "SiovEstaciones.findBySegundosLatitud", query = "SELECT s FROM SiovEstaciones s WHERE s.segundosLatitud = :segundosLatitud"),
    @NamedQuery(name = "SiovEstaciones.findByDireccionLatitud", query = "SELECT s FROM SiovEstaciones s WHERE s.direccionLatitud = :direccionLatitud"),
    @NamedQuery(name = "SiovEstaciones.findByGradosLongitud", query = "SELECT s FROM SiovEstaciones s WHERE s.gradosLongitud = :gradosLongitud"),
    @NamedQuery(name = "SiovEstaciones.findByMinutosLongitud", query = "SELECT s FROM SiovEstaciones s WHERE s.minutosLongitud = :minutosLongitud"),
    @NamedQuery(name = "SiovEstaciones.findBySegundosLongitud", query = "SELECT s FROM SiovEstaciones s WHERE s.segundosLongitud = :segundosLongitud"),
    @NamedQuery(name = "SiovEstaciones.findByDireccionLongitud", query = "SELECT s FROM SiovEstaciones s WHERE s.direccionLongitud = :direccionLongitud"),
    @NamedQuery(name = "SiovEstaciones.findByAltitud", query = "SELECT s FROM SiovEstaciones s WHERE s.altitud = :altitud"),
    @NamedQuery(name = "SiovEstaciones.findByNombrePma", query = "SELECT s FROM SiovEstaciones s WHERE s.nombrePma = :nombrePma"),
    @NamedQuery(name = "SiovEstaciones.findByEstadoPma", query = "SELECT s FROM SiovEstaciones s WHERE s.estadoPma = :estadoPma"),
    @NamedQuery(name = "SiovEstaciones.findByIdDv", query = "SELECT s FROM SiovEstaciones s WHERE s.idDv = :idDv"),
    @NamedQuery(name = "SiovEstaciones.findByCodigoDivipola", query = "SELECT s FROM SiovEstaciones s WHERE s.codigoDivipola = :codigoDivipola"),
    @NamedQuery(name = "SiovEstaciones.findByDescDivipola", query = "SELECT s FROM SiovEstaciones s WHERE s.descDivipola = :descDivipola"),
    @NamedQuery(name = "SiovEstaciones.findByIdFgda", query = "SELECT s FROM SiovEstaciones s WHERE s.idFgda = :idFgda"),
    @NamedQuery(name = "SiovEstaciones.findByNombreFgda", query = "SELECT s FROM SiovEstaciones s WHERE s.nombreFgda = :nombreFgda"),
    @NamedQuery(name = "SiovEstaciones.findByIdArea", query = "SELECT s FROM SiovEstaciones s WHERE s.idArea = :idArea"),
    @NamedQuery(name = "SiovEstaciones.findByNomArea", query = "SELECT s FROM SiovEstaciones s WHERE s.nomArea = :nomArea"),
    @NamedQuery(name = "SiovEstaciones.findByIdZona", query = "SELECT s FROM SiovEstaciones s WHERE s.idZona = :idZona"),
    @NamedQuery(name = "SiovEstaciones.findByNomZona", query = "SELECT s FROM SiovEstaciones s WHERE s.nomZona = :nomZona"),
    @NamedQuery(name = "SiovEstaciones.findByIdSubzona", query = "SELECT s FROM SiovEstaciones s WHERE s.idSubzona = :idSubzona"),
    @NamedQuery(name = "SiovEstaciones.findByNomSubzona", query = "SELECT s FROM SiovEstaciones s WHERE s.nomSubzona = :nomSubzona"),
    @NamedQuery(name = "SiovEstaciones.findByIdCorriente", query = "SELECT s FROM SiovEstaciones s WHERE s.idCorriente = :idCorriente"),
    @NamedQuery(name = "SiovEstaciones.findByIdOrg", query = "SELECT s FROM SiovEstaciones s WHERE s.idOrg = :idOrg"),
    @NamedQuery(name = "SiovEstaciones.findBySiglaOrg", query = "SELECT s FROM SiovEstaciones s WHERE s.siglaOrg = :siglaOrg"),
    @NamedQuery(name = "SiovEstaciones.findByIdEs", query = "SELECT s FROM SiovEstaciones s WHERE s.idEs = :idEs"),
    @NamedQuery(name = "SiovEstaciones.findByIdPma", query = "SELECT s FROM SiovEstaciones s WHERE s.idPma = :idPma"),
    @NamedQuery(name = "SiovEstaciones.findByFechaCreacionEs", query = "SELECT s FROM SiovEstaciones s WHERE s.fechaCreacionEs = :fechaCreacionEs"),
    @NamedQuery(name = "SiovEstaciones.findByFechaModificacionEs", query = "SELECT s FROM SiovEstaciones s WHERE s.fechaModificacionEs = :fechaModificacionEs"),
    @NamedQuery(name = "SiovEstaciones.findBySiglaFgda", query = "SELECT s FROM SiovEstaciones s WHERE s.siglaFgda = :siglaFgda")})
public class SiovEstaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombre_es", nullable = false)
    private String nombreEs;
    @Column(name = "cod_catalogo_es")
    private String codCatalogoEs;
    @Column(name = "cod_internacional_es")
    private String codInternacionalEs;
    @Column(name = "cod_interno_es")
    private String codInternoEs;
    @Column(name = "cod_reporte_es")
    private String codReporteEs;
    @Column(name = "cod_oaci_es")
    private String codOaciEs;
    @Column(name = "cod_wmo_es")
    private String codWmoEs;
    @Basic(optional = false)
    @Column(name = "categoria_es", nullable = false)
    private String categoriaEs;
    @Column(name = "desc_categoria")
    private String descCategoria;
    @Basic(optional = false)
    @Column(name = "clase_es")
    private String claseEs;
    @Column(name = "desc_clase")
    private String descClase;
    @Basic(optional = false)
    @Column(name = "tipo_es", nullable = false)
    private String tipoEs;
    @Column(name = "desc_tipo")
    private String descTipo;
    @Column(name = "escala_es")
    private String escalaEs;
    @Column(name = "desc_escala")
    private String descEscala;
    @Basic(optional = false)
    @Column(name = "estado_es", nullable = false)
    private String estadoEs;
    @Column(name = "desc_estado")
    private String descEstado;
    @Basic(optional = false)
    @Column(name = "fecha_aplicacion_es", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAplicacionEs;
    @Basic(optional = false)
    @Column(name = "obj_instalacion_es", nullable = false)
    private String objInstalacionEs;
    @Column(name = "desc_objetivo_inst")
    private String descObjetivoInst;
    @Column(name = "comen_instalacion_es")
    private String comenInstalacionEs;
    @Column(name = "sat_enlace_es")
    private String satEnlaceEs;
    @Column(name = "sat_nombre_es")
    private String satNombreEs;
    @Column(name = "sat_ventana_es")
    private String satVentanaEs;
    @Column(name = "sat_protocolo_es")
    private String satProtocoloEs;
    @Column(name = "desc_sat_protocolo")
    private String descSatProtocolo;
    @Column(name = "sat_tipo_es")
    private String satTipoEs;
    @Column(name = "desc_sat_tipo")
    private String descSatTipo;
    @Basic(optional = false)
    @Column(name = "altura_referencia_es", nullable = false)
    private double alturaReferenciaEs;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grados_latitud")
    private Double gradosLatitud;
    @Column(name = "minutos_latitud")
    private Double minutosLatitud;
    @Column(name = "segundos_latitud")
    private Double segundosLatitud;
    @Column(name = "direccion_latitud")
    private String direccionLatitud;
    @Column(name = "grados_longitud")
    private Double gradosLongitud;
    @Column(name = "minutos_longitud")
    private Double minutosLongitud;
    @Column(name = "segundos_longitud")
    private Double segundosLongitud;
    @Column(name = "direccion_longitud")
    private String direccionLongitud;
    @Column(name = "altitud")
    private Double altitud;
    @Column(name = "nombre_pma")
    private String nombrePma;
    @Column(name = "estado_pma")
    private String estadoPma;
    @Column(name = "id_dv")
    private Double idDv;
    @Column(name = "codigo_divipola")
    private String codigoDivipola;
    @Column(name = "desc_divipola")
    private String descDivipola;
    @Column(name = "id_fgda")
    private Double idFgda;
    @Column(name = "nombre_fgda")
    private String nombreFgda;
    @Column(name = "id_area")
    private Double idArea;
    @Column(name = "nom_area")
    private String nomArea;
    @Column(name = "id_zona")
    private Double idZona;
    @Column(name = "nom_zona")
    private String nomZona;
    @Column(name = "id_subzona")
    private String idSubzona;
    @Column(name = "nom_subzona")
    private String nomSubzona;
    @Column(name = "id_corriente")
    private String idCorriente;
    @Column(name = "id_org")
    private Double idOrg;
    @Column(name = "sigla_org")
    private String siglaOrg;
    @Id
    @Basic(optional = false)
    @Column(name = "id_es", nullable = false)
    private Long idEs;
    @Basic(optional = false)
    @Column(name = "id_pma", nullable = false)
    private double idPma;
    @Basic(optional = false)
    @Column(name = "fecha_creacion_es", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionEs;
    @Basic(optional = false)
    @Column(name = "fecha_modificacion_es", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacionEs;
    @Column(name = "sigla_fgda")
    private String siglaFgda;
    
    @Transient
    private Divipola departamento;
    
    // HUGO 20150115
    @Transient
    private Boolean tieneFews;
    
    @Transient
    private String urlFews;

    public SiovEstaciones() {
    }

    public SiovEstaciones(Long idEs) {
        this.idEs = idEs;
    }

    public SiovEstaciones(Long idEs, String nombreEs, String categoriaEs, String claseEs, String tipoEs, String estadoEs, Date fechaAplicacionEs, String objInstalacionEs, double alturaReferenciaEs, double idPma, Date fechaCreacionEs, Date fechaModificacionEs) {
        this.idEs = idEs;
        this.nombreEs = nombreEs;
        this.categoriaEs = categoriaEs;
        this.claseEs = claseEs;
        this.tipoEs = tipoEs;
        this.estadoEs = estadoEs;
        this.fechaAplicacionEs = fechaAplicacionEs;
        this.objInstalacionEs = objInstalacionEs;
        this.alturaReferenciaEs = alturaReferenciaEs;
        this.idPma = idPma;
        this.fechaCreacionEs = fechaCreacionEs;
        this.fechaModificacionEs = fechaModificacionEs;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getCodCatalogoEs() {
        return codCatalogoEs;
    }

    public void setCodCatalogoEs(String codCatalogoEs) {
        this.codCatalogoEs = codCatalogoEs;
    }

    public String getCodInternacionalEs() {
        return codInternacionalEs;
    }

    public void setCodInternacionalEs(String codInternacionalEs) {
        this.codInternacionalEs = codInternacionalEs;
    }

    public String getCodInternoEs() {
        return codInternoEs;
    }

    public void setCodInternoEs(String codInternoEs) {
        this.codInternoEs = codInternoEs;
    }

    public String getCodReporteEs() {
        return codReporteEs;
    }

    public void setCodReporteEs(String codReporteEs) {
        this.codReporteEs = codReporteEs;
    }

    public String getCodOaciEs() {
        return codOaciEs;
    }

    public void setCodOaciEs(String codOaciEs) {
        this.codOaciEs = codOaciEs;
    }

    public String getCodWmoEs() {
        return codWmoEs;
    }

    public void setCodWmoEs(String codWmoEs) {
        this.codWmoEs = codWmoEs;
    }

    public String getCategoriaEs() {
        return categoriaEs;
    }

    public void setCategoriaEs(String categoriaEs) {
        this.categoriaEs = categoriaEs;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public String getClaseEs() {
        return claseEs;
    }

    public void setClaseEs(String claseEs) {
        this.claseEs = claseEs;
    }

    public String getDescClase() {
        return descClase;
    }

    public void setDescClase(String descClase) {
        this.descClase = descClase;
    }

    public String getTipoEs() {
        return tipoEs;
    }

    public void setTipoEs(String tipoEs) {
        this.tipoEs = tipoEs;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public String getEscalaEs() {
        return escalaEs;
    }

    public void setEscalaEs(String escalaEs) {
        this.escalaEs = escalaEs;
    }

    public String getDescEscala() {
        return descEscala;
    }

    public void setDescEscala(String descEscala) {
        this.descEscala = descEscala;
    }

    public String getEstadoEs() {
        return estadoEs;
    }

    public void setEstadoEs(String estadoEs) {
        this.estadoEs = estadoEs;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public Date getFechaAplicacionEs() {
        return fechaAplicacionEs;
    }

    public void setFechaAplicacionEs(Date fechaAplicacionEs) {
        this.fechaAplicacionEs = fechaAplicacionEs;
    }

    public String getObjInstalacionEs() {
        return objInstalacionEs;
    }

    public void setObjInstalacionEs(String objInstalacionEs) {
        this.objInstalacionEs = objInstalacionEs;
    }

    public String getDescObjetivoInst() {
        return descObjetivoInst;
    }

    public void setDescObjetivoInst(String descObjetivoInst) {
        this.descObjetivoInst = descObjetivoInst;
    }

    public String getComenInstalacionEs() {
        return comenInstalacionEs;
    }

    public void setComenInstalacionEs(String comenInstalacionEs) {
        this.comenInstalacionEs = comenInstalacionEs;
    }

    public String getSatEnlaceEs() {
        return satEnlaceEs;
    }

    public void setSatEnlaceEs(String satEnlaceEs) {
        this.satEnlaceEs = satEnlaceEs;
    }

    public String getSatNombreEs() {
        return satNombreEs;
    }

    public void setSatNombreEs(String satNombreEs) {
        this.satNombreEs = satNombreEs;
    }

    public String getSatVentanaEs() {
        return satVentanaEs;
    }

    public void setSatVentanaEs(String satVentanaEs) {
        this.satVentanaEs = satVentanaEs;
    }

    public String getSatProtocoloEs() {
        return satProtocoloEs;
    }

    public void setSatProtocoloEs(String satProtocoloEs) {
        this.satProtocoloEs = satProtocoloEs;
    }

    public String getDescSatProtocolo() {
        return descSatProtocolo;
    }

    public void setDescSatProtocolo(String descSatProtocolo) {
        this.descSatProtocolo = descSatProtocolo;
    }

    public String getSatTipoEs() {
        return satTipoEs;
    }

    public void setSatTipoEs(String satTipoEs) {
        this.satTipoEs = satTipoEs;
    }

    public String getDescSatTipo() {
        return descSatTipo;
    }

    public void setDescSatTipo(String descSatTipo) {
        this.descSatTipo = descSatTipo;
    }

    public double getAlturaReferenciaEs() {
        return alturaReferenciaEs;
    }

    public void setAlturaReferenciaEs(double alturaReferenciaEs) {
        this.alturaReferenciaEs = alturaReferenciaEs;
    }

    public Double getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(Double gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public Double getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(Double minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public Double getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(Double segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public String getDireccionLatitud() {
        return direccionLatitud;
    }

    public void setDireccionLatitud(String direccionLatitud) {
        this.direccionLatitud = direccionLatitud;
    }

    public Double getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(Double gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public Double getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(Double minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public Double getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(Double segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public String getDireccionLongitud() {
        return direccionLongitud;
    }

    public void setDireccionLongitud(String direccionLongitud) {
        this.direccionLongitud = direccionLongitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public String getNombrePma() {
        return nombrePma;
    }

    public void setNombrePma(String nombrePma) {
        this.nombrePma = nombrePma;
    }

    public String getEstadoPma() {
        return estadoPma;
    }

    public void setEstadoPma(String estadoPma) {
        this.estadoPma = estadoPma;
    }

    public Double getIdDv() {
        return idDv;
    }

    public void setIdDv(Double idDv) {
        this.idDv = idDv;
    }

    public String getCodigoDivipola() {
        return codigoDivipola;
    }

    public void setCodigoDivipola(String codigoDivipola) {
        this.codigoDivipola = codigoDivipola;
    }

    public String getDescDivipola() {
        return descDivipola;
    }

    public void setDescDivipola(String descDivipola) {
        this.descDivipola = descDivipola;
    }

    public Double getIdFgda() {
        return idFgda;
    }

    public void setIdFgda(Double idFgda) {
        this.idFgda = idFgda;
    }

    public String getNombreFgda() {
        return nombreFgda;
    }

    public void setNombreFgda(String nombreFgda) {
        this.nombreFgda = nombreFgda;
    }

    public Double getIdArea() {
        return idArea;
    }

    public void setIdArea(Double idArea) {
        this.idArea = idArea;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }

    public Double getIdZona() {
        return idZona;
    }

    public void setIdZona(Double idZona) {
        this.idZona = idZona;
    }

    public String getNomZona() {
        return nomZona;
    }

    public void setNomZona(String nomZona) {
        this.nomZona = nomZona;
    }

    public String getIdSubzona() {
        return idSubzona;
    }

    public void setIdSubzona(String idSubzona) {
        this.idSubzona = idSubzona;
    }

    public String getNomSubzona() {
        return nomSubzona;
    }

    public void setNomSubzona(String nomSubzona) {
        this.nomSubzona = nomSubzona;
    }

    public String getIdCorriente() {
        return idCorriente;
    }

    public void setIdCorriente(String idCorriente) {
        this.idCorriente = idCorriente;
    }

    public Double getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Double idOrg) {
        this.idOrg = idOrg;
    }

    public String getSiglaOrg() {
        return siglaOrg;
    }

    public void setSiglaOrg(String siglaOrg) {
        this.siglaOrg = siglaOrg;
    }

    public Long getIdEs() {
        return idEs;
    }

    public void setIdEs(Long idEs) {
        this.idEs = idEs;
    }

    public double getIdPma() {
        return idPma;
    }

    public void setIdPma(double idPma) {
        this.idPma = idPma;
    }

    public Date getFechaCreacionEs() {
        return fechaCreacionEs;
    }

    public void setFechaCreacionEs(Date fechaCreacionEs) {
        this.fechaCreacionEs = fechaCreacionEs;
    }

    public Date getFechaModificacionEs() {
        return fechaModificacionEs;
    }

    public void setFechaModificacionEs(Date fechaModificacionEs) {
        this.fechaModificacionEs = fechaModificacionEs;
    }

    public String getSiglaFgda() {
        return siglaFgda;
    }

    public void setSiglaFgda(String siglaFgda) {
        this.siglaFgda = siglaFgda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEs != null ? idEs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiovEstaciones)) {
            return false;
        }
        SiovEstaciones other = (SiovEstaciones) object;
        if ((this.idEs == null && other.idEs != null) || (this.idEs != null && !this.idEs.equals(other.idEs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.oferta.model.SiovEstaciones[ idEs=" + idEs + " ]";
    }


    public void setDepartamento(Divipola departamento) {
        this.departamento = departamento;
    }

    public Divipola getDepartamento() {
        return departamento;
    }

    public void setTieneFews(Boolean tieneFews) {
        this.tieneFews = tieneFews;
    }

    public Boolean getTieneFews() {
        return tieneFews;
    }

    public void setUrlFews(String urlFews) {
        this.urlFews = urlFews;
    }

    public String getUrlFews() {
        return urlFews;
    }
}
