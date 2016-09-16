/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;
import java.io.Serializable;
import java.math.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_PERSONA_NATURAL_CA_V1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatPersonaNaturalCaV1.findAllPerNatCaV1", query = "SELECT c FROM CmatPersonaNaturalCaV1 c"),
    @NamedQuery(name = "CmatPersonaNaturalCaV1.findByCodigoRechazoPerNatCaV1", query = "SELECT c FROM CmatPersonaNaturalCaV1 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatPersonaNaturalCaV1.findByIdRegistroPerNatCaV1", query = "SELECT c FROM CmatPersonaNaturalCaV1 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCarguePerNatCaV1", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatPersonaNaturalCaV1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "TIPO_PERSONA")
    private String tipoPersona;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "APELLIDO_USUARIO")
    private String apellidoUsuario;
    @Column(name = "TIPO_DOC")
    private String tipoDoc;
    @Column(name = "NUM_DOC")
    private String numDoc;
    @Column(name = "DEPTO_CORRESPONDENCIA")
    private String deptoCorrespondencia;
    @Column(name = "MUNICIPIO_CORRESPONDENCIA")
    private String municipioCorrespondencia;
    @Column(name = "DIRECCION_CORRESPONDENCIA")
    private String direccionCorrespondencia;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "NOMBRE_PREDIO")
    private String nombrePredio;
    @Column(name = "TIPO_TENENCIA")
    private String tipoTenencia;
    @Column(name = "DEPTO_PREDIO")
    private String deptoPredio;
    @Column(name = "MUNICIPIO_PREDIO")
    private String municipioPredio;
    @Column(name = "TIPO_CENTRO_POBLADO")
    private String tipoCentroPoblado;
    @Column(name = "NOMBRE_CENTRO_POBLADO")
    private String nombreCentroPoblado;
    @Column(name = "CEDULA_CATASTRAL")
    private String cedulaCatastral;
    @Column(name = "MATRICULA_INMOBILIARIA")
    private String matriculaInmobiliaria;
    @Column(name = "AREA_TOTAL_PREDIO")
    private String areaTotalPredio;
    @Column(name = "DIRECCION_PREDIO")
    private String direccionPredio;
    @Column(name = "CLASIFICACION_SUELO")
    private String clasificacionSuelo;
    @Column(name = "SISTEMA_REF")
    private String sistemaRef;
    @Column(name = "GRAD_LAT")
    private String gradLat;
    @Column(name = "MIN_LAT")
    private String minLat;
    @Column(name = "SEG_LAT")
    private String segLat;
    @Column(name = "GRAD_LOG")
    private String gradLog;
    @Column(name = "MIN_LOG")
    private String minLog;
    @Column(name = "SEG_LOG")
    private String segLog;
    @Column(name = "ALTITUD")
    private String altitud;
    @Column(name = "DESCRIP_SITIO")
    private String descripSitio;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "NUM_RESOLUCION_ASIGNA_CAUDAL")
    private String numResolucionAsignaCaudal;
    @Column(name = "FECHA_EXPE_RESOLUCION")
    private String fechaExpeResolucion;
    @Column(name = "FECHA_DE_NOTIFICACION")
    private String fechaDeNotificacion;
    @Column(name = "CAUDAL_CONCESIONADO")
    private String caudalConcesionado;
    @Column(name = "NUM_RESOLUCION_APROB_PLANOS")
    private String numResolucionAprobPlanos;
    @Column(name = "FECHA_EXPE_RESOL_APROB_PLANOS")
    private String fechaExpeResolAprobPlanos;
    @Column(name = "FECHA_NOTIFI_APROB_PLANOS")
    private String fechaNotifiAprobPlanos;
    @Column(name = "NUM_RESOLUCION_APROB_OBRA")
    private String numResolucionAprobObra;
    @Column(name = "FECHA_EXPE_RESOL_APRO_OBRA")
    private String fechaExpeResolAproObra;
    @Column(name = "FECHA_NOTIFI_APROB_OBRA")
    private String fechaNotifiAprobObra;
    @Column(name = "VIGENCIA_DESDE")
    private String vigenciaDesde;
    @Column(name = "VIGENCIA_HASTA")
    private String vigenciaHasta;
    @Column(name = "TIPO_DE_FUENTE")
    private String tipoDeFuente;
    @Column(name = "AREA_HIDROGR\u00c1FICA")
    private String areaHidrografica;
    @Column(name = "ZONA_HIDROGR\u00c1FICA")
    private String zonaHidrografica;
    @Column(name = "SUBZONA_HIDROGR\u00c1FICA")
    private String subzonaHidrografica;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FUENTE")
    private String fuente;
    @Column(name = "TRAMO")
    private String tramo;
    @Column(name = "IDENTIFICADOR_PUNTO_AGUAS_SUB")
    private String identificadorPuntoAguasSub;
    @Column(name = "PROVINCIA_H")
    private String provinciaH;
    @Column(name = "UNIDAD_H")
    private String unidadH;
    @Column(name = "ADUCCION")
    private String aduccion;
    @Column(name = "DESARENADOR")
    private String desarenador;
    @Column(name = "PTAP")
    private String ptap;
    @Column(name = "RED_DISTRIBUCION")
    private String redDistribucion;
    @Column(name = "TANQUE")
    private String tanque;
    @Column(name = "TIPO_CAPTACION")
    private String tipoCaptacion;
    @Column(name = "OFERTA_TOTAL")
    private String ofertaTotal;
    @Column(name = "AREA_CAPTACION")
    private String areaCaptacion;
    @Column(name = "OFERTA_DISPONIBLE")
    private String ofertaDisponible;
    @Column(name = "MACROMEDICION")
    private String macromedicion;
    @Column(name = "ESTADO_CAP")
    private String estadoCap;
    @Column(name = "CAUDAL_DISE\u00d1O_CAP")
    private String caudalDisenoCap;
    @Column(name = "CONTINUIDAD_SERVICIO")
    private String continuidadServicio;
    @Column(name = "TIENE_SERVIDUMBRE")
    private String tieneServidumbre;
    @Column(name = "SISTEMA_REF_CAP")
    private String sistemaRefCap;
    @Column(name = "GRAD_LAT_CAP")
    private String gradLatCap;
    @Column(name = "MIN_LAT_CAP")
    private String minLatCap;
    @Column(name = "SEG_LAT_CAP")
    private String segLatCap;
    @Column(name = "GRAD_LOG_CAP")
    private String gradLogCap;
    @Column(name = "MIN_LOG_CAP")
    private String minLogCap;
    @Column(name = "SEG_LOG_CAP")
    private String segLogCap;
    @Column(name = "ALTITUD_CAP")
    private String altitudCap;
    @Column(name = "OBSERVACIONES_CAP")
    private String observacionesCap;
    @Column(name = "CAUDAL_CONSUMO")
    private String caudalConsumo;
    @Column(name = "NO_PER_PERMANENTES_CONSUMO")
    private String noPerPermanentesConsumo;
    @Column(name = "NO_PER_TRANSITORIAS_CONSUMO")
    private String noPerTransitoriasConsumo;
    @Column(name = "APROVECH_CONSUMO")
    private String aprovechConsumo;
    @Column(name = "TIPO_ANIMAL_ABREV")
    private String tipoAnimalAbrev;
    @Column(name = "CAUDAL__ABREV")
    private String caudalAbrev;
    @Column(name = "NO_ANIMALES_ABREV")
    private String noAnimalesAbrev;
    @Column(name = "TIPO_ANIMAL_PESCA")
    private String tipoAnimalPesca;
    @Column(name = "CAUDAL__PESCA")
    private String caudalPesca;
    @Column(name = "NO_ANIMALES")
    private String noAnimales;
    @Column(name = "TIPO_CULTIVO_SILVICULTURA")
    private String tipoCultivoSilvicultura;
    @Column(name = "CAUDAL_SILVICULTURA")
    private String caudalSilvicultura;
    @Column(name = "PRODUCCION_SILVICULTURA")
    private String produccionSilvicultura;
    @Column(name = "EFICIENCIA_SILVICULTURA")
    private String eficienciaSilvicultura;
    @Column(name = "AREA_SILVICULTURA")
    private String areaSilvicultura;
    @Column(name = "TIPO_DE_USO_OTRO")
    private String tipoDeUsoOtro;
    @Column(name = "DECRIPCION_OTRO")
    private String decripcionOtro;
    @Column(name = "CAUDAL_OTRO")
    private String caudalOtro;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV1 idControlCargue;

    public CmatPersonaNaturalCaV1() {
    }

    public CmatPersonaNaturalCaV1(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Long getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(Long codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public double getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getDeptoCorrespondencia() {
        return deptoCorrespondencia;
    }

    public void setDeptoCorrespondencia(String deptoCorrespondencia) {
        this.deptoCorrespondencia = deptoCorrespondencia;
    }

    public String getMunicipioCorrespondencia() {
        return municipioCorrespondencia;
    }

    public void setMunicipioCorrespondencia(String municipioCorrespondencia) {
        this.municipioCorrespondencia = municipioCorrespondencia;
    }

    public String getDireccionCorrespondencia() {
        return direccionCorrespondencia;
    }

    public void setDireccionCorrespondencia(String direccionCorrespondencia) {
        this.direccionCorrespondencia = direccionCorrespondencia;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public String getTipoTenencia() {
        return tipoTenencia;
    }

    public void setTipoTenencia(String tipoTenencia) {
        this.tipoTenencia = tipoTenencia;
    }

    public String getDeptoPredio() {
        return deptoPredio;
    }

    public void setDeptoPredio(String deptoPredio) {
        this.deptoPredio = deptoPredio;
    }

    public String getMunicipioPredio() {
        return municipioPredio;
    }

    public void setMunicipioPredio(String municipioPredio) {
        this.municipioPredio = municipioPredio;
    }

    public String getTipoCentroPoblado() {
        return tipoCentroPoblado;
    }

    public void setTipoCentroPoblado(String tipoCentroPoblado) {
        this.tipoCentroPoblado = tipoCentroPoblado;
    }

    public String getNombreCentroPoblado() {
        return nombreCentroPoblado;
    }

    public void setNombreCentroPoblado(String nombreCentroPoblado) {
        this.nombreCentroPoblado = nombreCentroPoblado;
    }

    public String getCedulaCatastral() {
        return cedulaCatastral;
    }

    public void setCedulaCatastral(String cedulaCatastral) {
        this.cedulaCatastral = cedulaCatastral;
    }

    public String getMatriculaInmobiliaria() {
        return matriculaInmobiliaria;
    }

    public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
        this.matriculaInmobiliaria = matriculaInmobiliaria;
    }

    public String getAreaTotalPredio() {
        return areaTotalPredio;
    }

    public void setAreaTotalPredio(String areaTotalPredio) {
        this.areaTotalPredio = areaTotalPredio;
    }

    public String getDireccionPredio() {
        return direccionPredio;
    }

    public void setDireccionPredio(String direccionPredio) {
        this.direccionPredio = direccionPredio;
    }

    public String getClasificacionSuelo() {
        return clasificacionSuelo;
    }

    public void setClasificacionSuelo(String clasificacionSuelo) {
        this.clasificacionSuelo = clasificacionSuelo;
    }

    public String getSistemaRef() {
        return sistemaRef;
    }

    public void setSistemaRef(String sistemaRef) {
        this.sistemaRef = sistemaRef;
    }

    public String getGradLat() {
        return gradLat;
    }

    public void setGradLat(String gradLat) {
        this.gradLat = gradLat;
    }

    public String getMinLat() {
        return minLat;
    }

    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    public String getSegLat() {
        return segLat;
    }

    public void setSegLat(String segLat) {
        this.segLat = segLat;
    }

    public String getGradLog() {
        return gradLog;
    }

    public void setGradLog(String gradLog) {
        this.gradLog = gradLog;
    }

    public String getMinLog() {
        return minLog;
    }

    public void setMinLog(String minLog) {
        this.minLog = minLog;
    }

    public String getSegLog() {
        return segLog;
    }

    public void setSegLog(String segLog) {
        this.segLog = segLog;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getDescripSitio() {
        return descripSitio;
    }

    public void setDescripSitio(String descripSitio) {
        this.descripSitio = descripSitio;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNumResolucionAsignaCaudal() {
        return numResolucionAsignaCaudal;
    }

    public void setNumResolucionAsignaCaudal(String numResolucionAsignaCaudal) {
        this.numResolucionAsignaCaudal = numResolucionAsignaCaudal;
    }

    public String getFechaExpeResolucion() {
        return fechaExpeResolucion;
    }

    public void setFechaExpeResolucion(String fechaExpeResolucion) {
        this.fechaExpeResolucion = fechaExpeResolucion;
    }

    public String getFechaDeNotificacion() {
        return fechaDeNotificacion;
    }

    public void setFechaDeNotificacion(String fechaDeNotificacion) {
        this.fechaDeNotificacion = fechaDeNotificacion;
    }

    public String getCaudalConcesionado() {
        return caudalConcesionado;
    }

    public void setCaudalConcesionado(String caudalConcesionado) {
        this.caudalConcesionado = caudalConcesionado;
    }

    public String getNumResolucionAprobPlanos() {
        return numResolucionAprobPlanos;
    }

    public void setNumResolucionAprobPlanos(String numResolucionAprobPlanos) {
        this.numResolucionAprobPlanos = numResolucionAprobPlanos;
    }

    public String getFechaExpeResolAprobPlanos() {
        return fechaExpeResolAprobPlanos;
    }

    public void setFechaExpeResolAprobPlanos(String fechaExpeResolAprobPlanos) {
        this.fechaExpeResolAprobPlanos = fechaExpeResolAprobPlanos;
    }

    public String getFechaNotifiAprobPlanos() {
        return fechaNotifiAprobPlanos;
    }

    public void setFechaNotifiAprobPlanos(String fechaNotifiAprobPlanos) {
        this.fechaNotifiAprobPlanos = fechaNotifiAprobPlanos;
    }

    public String getNumResolucionAprobObra() {
        return numResolucionAprobObra;
    }

    public void setNumResolucionAprobObra(String numResolucionAprobObra) {
        this.numResolucionAprobObra = numResolucionAprobObra;
    }

    public String getFechaExpeResolAproObra() {
        return fechaExpeResolAproObra;
    }

    public void setFechaExpeResolAproObra(String fechaExpeResolAproObra) {
        this.fechaExpeResolAproObra = fechaExpeResolAproObra;
    }

    public String getFechaNotifiAprobObra() {
        return fechaNotifiAprobObra;
    }

    public void setFechaNotifiAprobObra(String fechaNotifiAprobObra) {
        this.fechaNotifiAprobObra = fechaNotifiAprobObra;
    }

    public String getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(String vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public String getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(String vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public String getTipoDeFuente() {
        return tipoDeFuente;
    }

    public void setTipoDeFuente(String tipoDeFuente) {
        this.tipoDeFuente = tipoDeFuente;
    }

    public String getAreaHidrografica() {
        return areaHidrografica;
    }

    public void setAreaHidrografica(String areaHidrografica) {
        this.areaHidrografica = areaHidrografica;
    }

    public String getZonaHidrografica() {
        return zonaHidrografica;
    }

    public void setZonaHidrografica(String zonaHidrografica) {
        this.zonaHidrografica = zonaHidrografica;
    }

    public String getSubzonaHidrografica() {
        return subzonaHidrografica;
    }

    public void setSubzonaHidrografica(String subzonaHidrografica) {
        this.subzonaHidrografica = subzonaHidrografica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getIdentificadorPuntoAguasSub() {
        return identificadorPuntoAguasSub;
    }

    public void setIdentificadorPuntoAguasSub(String identificadorPuntoAguasSub) {
        this.identificadorPuntoAguasSub = identificadorPuntoAguasSub;
    }

    public String getProvinciaH() {
        return provinciaH;
    }

    public void setProvinciaH(String provinciaH) {
        this.provinciaH = provinciaH;
    }

    public String getUnidadH() {
        return unidadH;
    }

    public void setUnidadH(String unidadH) {
        this.unidadH = unidadH;
    }

    public String getAduccion() {
        return aduccion;
    }

    public void setAduccion(String aduccion) {
        this.aduccion = aduccion;
    }

    public String getDesarenador() {
        return desarenador;
    }

    public void setDesarenador(String desarenador) {
        this.desarenador = desarenador;
    }

    public String getPtap() {
        return ptap;
    }

    public void setPtap(String ptap) {
        this.ptap = ptap;
    }

    public String getRedDistribucion() {
        return redDistribucion;
    }

    public void setRedDistribucion(String redDistribucion) {
        this.redDistribucion = redDistribucion;
    }

    public String getTanque() {
        return tanque;
    }

    public void setTanque(String tanque) {
        this.tanque = tanque;
    }

    public String getTipoCaptacion() {
        return tipoCaptacion;
    }

    public void setTipoCaptacion(String tipoCaptacion) {
        this.tipoCaptacion = tipoCaptacion;
    }

    public String getOfertaTotal() {
        return ofertaTotal;
    }

    public void setOfertaTotal(String ofertaTotal) {
        this.ofertaTotal = ofertaTotal;
    }

    public String getAreaCaptacion() {
        return areaCaptacion;
    }

    public void setAreaCaptacion(String areaCaptacion) {
        this.areaCaptacion = areaCaptacion;
    }

    public String getOfertaDisponible() {
        return ofertaDisponible;
    }

    public void setOfertaDisponible(String ofertaDisponible) {
        this.ofertaDisponible = ofertaDisponible;
    }

    public String getMacromedicion() {
        return macromedicion;
    }

    public void setMacromedicion(String macromedicion) {
        this.macromedicion = macromedicion;
    }

    public String getEstadoCap() {
        return estadoCap;
    }

    public void setEstadoCap(String estadoCap) {
        this.estadoCap = estadoCap;
    }

    public String getCaudalDisenoCap() {
        return caudalDisenoCap;
    }

    public void setCaudalDisenoCap(String caudalDisenoCap) {
        this.caudalDisenoCap = caudalDisenoCap;
    }

    public String getContinuidadServicio() {
        return continuidadServicio;
    }

    public void setContinuidadServicio(String continuidadServicio) {
        this.continuidadServicio = continuidadServicio;
    }

    public String getTieneServidumbre() {
        return tieneServidumbre;
    }

    public void setTieneServidumbre(String tieneServidumbre) {
        this.tieneServidumbre = tieneServidumbre;
    }

    public String getSistemaRefCap() {
        return sistemaRefCap;
    }

    public void setSistemaRefCap(String sistemaRefCap) {
        this.sistemaRefCap = sistemaRefCap;
    }

    public String getGradLatCap() {
        return gradLatCap;
    }

    public void setGradLatCap(String gradLatCap) {
        this.gradLatCap = gradLatCap;
    }

    public String getMinLatCap() {
        return minLatCap;
    }

    public void setMinLatCap(String minLatCap) {
        this.minLatCap = minLatCap;
    }

    public String getSegLatCap() {
        return segLatCap;
    }

    public void setSegLatCap(String segLatCap) {
        this.segLatCap = segLatCap;
    }

    public String getGradLogCap() {
        return gradLogCap;
    }

    public void setGradLogCap(String gradLogCap) {
        this.gradLogCap = gradLogCap;
    }

    public String getMinLogCap() {
        return minLogCap;
    }

    public void setMinLogCap(String minLogCap) {
        this.minLogCap = minLogCap;
    }

    public String getSegLogCap() {
        return segLogCap;
    }

    public void setSegLogCap(String segLogCap) {
        this.segLogCap = segLogCap;
    }

    public String getAltitudCap() {
        return altitudCap;
    }

    public void setAltitudCap(String altitudCap) {
        this.altitudCap = altitudCap;
    }

    public String getObservacionesCap() {
        return observacionesCap;
    }

    public void setObservacionesCap(String observacionesCap) {
        this.observacionesCap = observacionesCap;
    }

    public String getCaudalConsumo() {
        return caudalConsumo;
    }

    public void setCaudalConsumo(String caudalConsumo) {
        this.caudalConsumo = caudalConsumo;
    }

    public String getNoPerPermanentesConsumo() {
        return noPerPermanentesConsumo;
    }

    public void setNoPerPermanentesConsumo(String noPerPermanentesConsumo) {
        this.noPerPermanentesConsumo = noPerPermanentesConsumo;
    }

    public String getNoPerTransitoriasConsumo() {
        return noPerTransitoriasConsumo;
    }

    public void setNoPerTransitoriasConsumo(String noPerTransitoriasConsumo) {
        this.noPerTransitoriasConsumo = noPerTransitoriasConsumo;
    }

    public String getAprovechConsumo() {
        return aprovechConsumo;
    }

    public void setAprovechConsumo(String aprovechConsumo) {
        this.aprovechConsumo = aprovechConsumo;
    }

    public String getTipoAnimalAbrev() {
        return tipoAnimalAbrev;
    }

    public void setTipoAnimalAbrev(String tipoAnimalAbrev) {
        this.tipoAnimalAbrev = tipoAnimalAbrev;
    }

    public String getCaudalAbrev() {
        return caudalAbrev;
    }

    public void setCaudalAbrev(String caudalAbrev) {
        this.caudalAbrev = caudalAbrev;
    }

    public String getNoAnimalesAbrev() {
        return noAnimalesAbrev;
    }

    public void setNoAnimalesAbrev(String noAnimalesAbrev) {
        this.noAnimalesAbrev = noAnimalesAbrev;
    }

    public String getTipoAnimalPesca() {
        return tipoAnimalPesca;
    }

    public void setTipoAnimalPesca(String tipoAnimalPesca) {
        this.tipoAnimalPesca = tipoAnimalPesca;
    }

    public String getCaudalPesca() {
        return caudalPesca;
    }

    public void setCaudalPesca(String caudalPesca) {
        this.caudalPesca = caudalPesca;
    }

    public String getNoAnimales() {
        return noAnimales;
    }

    public void setNoAnimales(String noAnimales) {
        this.noAnimales = noAnimales;
    }

    public String getTipoCultivoSilvicultura() {
        return tipoCultivoSilvicultura;
    }

    public void setTipoCultivoSilvicultura(String tipoCultivoSilvicultura) {
        this.tipoCultivoSilvicultura = tipoCultivoSilvicultura;
    }

    public String getCaudalSilvicultura() {
        return caudalSilvicultura;
    }

    public void setCaudalSilvicultura(String caudalSilvicultura) {
        this.caudalSilvicultura = caudalSilvicultura;
    }

    public String getProduccionSilvicultura() {
        return produccionSilvicultura;
    }

    public void setProduccionSilvicultura(String produccionSilvicultura) {
        this.produccionSilvicultura = produccionSilvicultura;
    }

    public String getEficienciaSilvicultura() {
        return eficienciaSilvicultura;
    }

    public void setEficienciaSilvicultura(String eficienciaSilvicultura) {
        this.eficienciaSilvicultura = eficienciaSilvicultura;
    }

    public String getAreaSilvicultura() {
        return areaSilvicultura;
    }

    public void setAreaSilvicultura(String areaSilvicultura) {
        this.areaSilvicultura = areaSilvicultura;
    }

    public String getTipoDeUsoOtro() {
        return tipoDeUsoOtro;
    }

    public void setTipoDeUsoOtro(String tipoDeUsoOtro) {
        this.tipoDeUsoOtro = tipoDeUsoOtro;
    }

    public String getDecripcionOtro() {
        return decripcionOtro;
    }

    public void setDecripcionOtro(String decripcionOtro) {
        this.decripcionOtro = decripcionOtro;
    }

    public String getCaudalOtro() {
        return caudalOtro;
    }

    public void setCaudalOtro(String caudalOtro) {
        this.caudalOtro = caudalOtro;
    }

    public CmatControlCargueV1 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV1 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }



    @Override
    public String toString() {
        return "Entidades.CmatPersonaNaturalCaV1[ idRegistro=" + idRegistro + " ]";
    }
    
}
