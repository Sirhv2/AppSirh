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
@Table(name = "CMAT_PERSONA_JURIDICA_CA_V1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatPersonaJuridicaCaV1.findAllPerJurCaV1", query = "SELECT c FROM CmatPersonaJuridicaCaV1 c"),
    @NamedQuery(name = "CmatPersonaJuridicaCaV1.findByCodigoRechazoPerJurCaV1", query = "SELECT c FROM CmatPersonaJuridicaCaV1 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatPersonaJuridicaCaV1.findByIdRegistroPerJurCaV1", query = "SELECT c FROM CmatPersonaJuridicaCaV1 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCarguePerJurCaV1", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatPersonaJuridicaCaV1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "TIPO_USUARIO")
    private String tipoUsuario;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "TIPO_DOC_USUARIO")
    private String tipoDocUsuario;
    @Column(name = "NUM_DOC_USUARIO")
    private String numDocUsuario;
    @Column(name = "ACTIVAD_ECO")
    private String activadEco;
    @Column(name = "DIR_CORRESPONDENCIA")
    private String dirCorrespondencia;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "TEL_USUARIO")
    private String telUsuario;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "NOMBRE_RPRE_LEGAL")
    private String nombreRpreLegal;
    @Column(name = "APELLIDO_RPRE_LEGAL")
    private String apellidoRpreLegal;
    @Column(name = "TIPO_DOC_RPRE_LEGAL")
    private String tipoDocRpreLegal;
    @Column(name = "NUM_DOC_RPRE_LEGAL")
    private String numDocRpreLegal;
    @Column(name = "DEPTO_RPRE_LEGAL")
    private String deptoRpreLegal;
    @Column(name = "MUNICIPIO_RPRE_LEGAL")
    private String municipioRpreLegal;
    @Column(name = "DIR_CORRESPONDENCIA_RPRE_LEGAL")
    private String dirCorrespondenciaRpreLegal;
    @Column(name = "TEL_RPRE_LEGAL")
    private String telRpreLegal;
    @Column(name = "NOMBRE_DEL_SITIO")
    private String nombreDelSitio;
    @Column(name = "TIPO_DE_TENENCIA")
    private String tipoDeTenencia;
    @Column(name = "DEPTO_EMPRE")
    private String deptoEmpre;
    @Column(name = "MUNICIPIO_EMPRE")
    private String municipioEmpre;
    @Column(name = "TIPO_CENTRO_POBLADO_EMPRE")
    private String tipoCentroPobladoEmpre;
    @Column(name = "NOMBRE_CENTRO_POBLADO_EMPRE")
    private String nombreCentroPobladoEmpre;
    @Column(name = "CEDULA_CATASTRAL_EMPRE")
    private String cedulaCatastralEmpre;
    @Column(name = "MATRICULA_CATASTRAL_EMPRE")
    private String matriculaCatastralEmpre;
    @Column(name = "AREA_PREDIO_EMPRE")
    private String areaPredioEmpre;
    @Column(name = "DIRECCION_PREDIO_EMPRE")
    private String direccionPredioEmpre;
    @Column(name = "CAUDAL_CONCESIONADO_EMPRE")
    private String caudalConcesionadoEmpre;
    @Column(name = "CLASIFICACION_SUELO_EMPRE")
    private String clasificacionSueloEmpre;
    @Column(name = "SISTEMA_REF_PREDIO")
    private String sistemaRefPredio;
    @Column(name = "GRAD_LAT_PREDIO")
    private String gradLatPredio;
    @Column(name = "MIN_LAT_PREDIO")
    private String minLatPredio;
    @Column(name = "SEG_LAT_PREDIO")
    private String segLatPredio;
    @Column(name = "GRAD_LOG_PREDIO")
    private String gradLogPredio;
    @Column(name = "MIN_LOG_PREDIO")
    private String minLogPredio;
    @Column(name = "SEG_LOG_PREDIO")
    private String segLogPredio;
    @Column(name = "ALTITUD_PREDIO")
    private String altitudPredio;
    @Column(name = "DESCRIP_SITIO_PREDIO")
    private String descripSitioPredio;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "NUM_RESOLUCION_ASIG_CAUDAL")
    private String numResolucionAsigCaudal;
    @Column(name = "FECHA_EXPEDICION_ASIG_CAUDAL")
    private String fechaExpedicionAsigCaudal;
    @Column(name = "FECHA_NUMTIFI_ASIG_CAUDAL")
    private String fechaNumtifiAsigCaudal;
    @Column(name = "NUM_RESOLUCION_APROB_PLANOS")
    private String numResolucionAprobPlanos;
    @Column(name = "FECHA_EXPEDICION__APROB_PLANOS")
    private String fechaExpedicionAprobPlanos;
    @Column(name = "FECHA_NOTIFI_APROB_PLANOS")
    private String fechaNotifiAprobPlanos;
    @Column(name = "NUM_RESOLUCION_OBRAS")
    private String numResolucionObras;
    @Column(name = "FECHA_EXPEDICION__OBRAS")
    private String fechaExpedicionObras;
    @Column(name = "FECHA_NUMTIFICACION_OBRAS")
    private String fechaNumtificacionObras;
    @Column(name = "FECHA_INICIAL")
    private String fechaInicial;
    @Column(name = "FECHA_FINAL")
    private String fechaFinal;
    @Column(name = "TIPO_DE_FUENTE")
    private String tipoDeFuente;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "TIPO_CENTRO_POBLADO")
    private String tipoCentroPoblado;
    @Column(name = "CENTRO_POBLADO")
    private String centroPoblado;
    @Column(name = "AREA_H")
    private String areaH;
    @Column(name = "ZONA_H")
    private String zonaH;
    @Column(name = "SUBZONA_H")
    private String subzonaH;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FUENTE")
    private String fuente;
    @Column(name = "TRAMO")
    private String tramo;
    @Column(name = "ID_PUNTO_AGUAS_SUB")
    private String idPuntoAguasSub;
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
    @Column(name = "AFORO")
    private String aforo;
    @Column(name = "AREA_CAPTACION_")
    private String areaCaptacion;
    @Column(name = "OFERTA_DISPONIBLE")
    private String ofertaDisponible;
    @Column(name = "MACROMEDICION")
    private String macromedicion;
    @Column(name = "ESTADO_CAPTACION")
    private String estadoCaptacion;
    @Column(name = "CAUDAL_DISE\u00d1O_CAP_MEDIDO")
    private String caudalDisenoCapMedido;
    @Column(name = "CONTINUIDAD_SERVICIO")
    private String continuidadServicio;
    @Column(name = "SERVIDUMBRE")
    private String servidumbre;
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
    @Column(name = "CAUDAL_ABASTE")
    private String caudalAbaste;
    @Column(name = "NUM_PER_PERMANENTES_ABASTE")
    private String numPerPermanentesAbaste;
    @Column(name = "NUM_PER_TRANSITORIAS_ABASTE")
    private String numPerTransitoriasAbaste;
    @Column(name = "APROVECH_ABASTE")
    private String aprovechAbaste;
    @Column(name = "TIPO_ANIMAL_ABREV")
    private String tipoAnimalAbrev;
    @Column(name = "CAUDAL_ABREV")
    private String caudalAbrev;
    @Column(name = "NUM_ANIMALES_ABREV")
    private String numAnimalesAbrev;
    @Column(name = "TIPO_ANIMAL_PESCA")
    private String tipoAnimalPesca;
    @Column(name = "CAUDALPESCA")
    private String caudalpesca;
    @Column(name = "NUM_ANIMALES_PESCA")
    private String numAnimalesPesca;
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
    @Column(name = "TIPO_DE_USO_OTROS")
    private String tipoDeUsoOtros;
    @Column(name = "DECRIPCION_OTROS")
    private String decripcionOtros;
    @Column(name = "CAUDAL_OTROS")
    private String caudalOtros;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV1 idControlCargue;

    public CmatPersonaJuridicaCaV1() {
    }

    public CmatPersonaJuridicaCaV1(double idRegistro) {
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoDocUsuario() {
        return tipoDocUsuario;
    }

    public void setTipoDocUsuario(String tipoDocUsuario) {
        this.tipoDocUsuario = tipoDocUsuario;
    }

    public String getNumDocUsuario() {
        return numDocUsuario;
    }

    public void setNumDocUsuario(String numDocUsuario) {
        this.numDocUsuario = numDocUsuario;
    }

    public String getActivadEco() {
        return activadEco;
    }

    public void setActivadEco(String activadEco) {
        this.activadEco = activadEco;
    }

    public String getDirCorrespondencia() {
        return dirCorrespondencia;
    }

    public void setDirCorrespondencia(String dirCorrespondencia) {
        this.dirCorrespondencia = dirCorrespondencia;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelUsuario() {
        return telUsuario;
    }

    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNombreRpreLegal() {
        return nombreRpreLegal;
    }

    public void setNombreRpreLegal(String nombreRpreLegal) {
        this.nombreRpreLegal = nombreRpreLegal;
    }

    public String getApellidoRpreLegal() {
        return apellidoRpreLegal;
    }

    public void setApellidoRpreLegal(String apellidoRpreLegal) {
        this.apellidoRpreLegal = apellidoRpreLegal;
    }

    public String getTipoDocRpreLegal() {
        return tipoDocRpreLegal;
    }

    public void setTipoDocRpreLegal(String tipoDocRpreLegal) {
        this.tipoDocRpreLegal = tipoDocRpreLegal;
    }

    public String getNumDocRpreLegal() {
        return numDocRpreLegal;
    }

    public void setNumDocRpreLegal(String numDocRpreLegal) {
        this.numDocRpreLegal = numDocRpreLegal;
    }

    public String getDeptoRpreLegal() {
        return deptoRpreLegal;
    }

    public void setDeptoRpreLegal(String deptoRpreLegal) {
        this.deptoRpreLegal = deptoRpreLegal;
    }

    public String getMunicipioRpreLegal() {
        return municipioRpreLegal;
    }

    public void setMunicipioRpreLegal(String municipioRpreLegal) {
        this.municipioRpreLegal = municipioRpreLegal;
    }

    public String getDirCorrespondenciaRpreLegal() {
        return dirCorrespondenciaRpreLegal;
    }

    public void setDirCorrespondenciaRpreLegal(String dirCorrespondenciaRpreLegal) {
        this.dirCorrespondenciaRpreLegal = dirCorrespondenciaRpreLegal;
    }

    public String getTelRpreLegal() {
        return telRpreLegal;
    }

    public void setTelRpreLegal(String telRpreLegal) {
        this.telRpreLegal = telRpreLegal;
    }

    public String getNombreDelSitio() {
        return nombreDelSitio;
    }

    public void setNombreDelSitio(String nombreDelSitio) {
        this.nombreDelSitio = nombreDelSitio;
    }

    public String getTipoDeTenencia() {
        return tipoDeTenencia;
    }

    public void setTipoDeTenencia(String tipoDeTenencia) {
        this.tipoDeTenencia = tipoDeTenencia;
    }

    public String getDeptoEmpre() {
        return deptoEmpre;
    }

    public void setDeptoEmpre(String deptoEmpre) {
        this.deptoEmpre = deptoEmpre;
    }

    public String getMunicipioEmpre() {
        return municipioEmpre;
    }

    public void setMunicipioEmpre(String municipioEmpre) {
        this.municipioEmpre = municipioEmpre;
    }

    public String getTipoCentroPobladoEmpre() {
        return tipoCentroPobladoEmpre;
    }

    public void setTipoCentroPobladoEmpre(String tipoCentroPobladoEmpre) {
        this.tipoCentroPobladoEmpre = tipoCentroPobladoEmpre;
    }

    public String getNombreCentroPobladoEmpre() {
        return nombreCentroPobladoEmpre;
    }

    public void setNombreCentroPobladoEmpre(String nombreCentroPobladoEmpre) {
        this.nombreCentroPobladoEmpre = nombreCentroPobladoEmpre;
    }

    public String getCedulaCatastralEmpre() {
        return cedulaCatastralEmpre;
    }

    public void setCedulaCatastralEmpre(String cedulaCatastralEmpre) {
        this.cedulaCatastralEmpre = cedulaCatastralEmpre;
    }

    public String getMatriculaCatastralEmpre() {
        return matriculaCatastralEmpre;
    }

    public void setMatriculaCatastralEmpre(String matriculaCatastralEmpre) {
        this.matriculaCatastralEmpre = matriculaCatastralEmpre;
    }

    public String getAreaPredioEmpre() {
        return areaPredioEmpre;
    }

    public void setAreaPredioEmpre(String areaPredioEmpre) {
        this.areaPredioEmpre = areaPredioEmpre;
    }

    public String getDireccionPredioEmpre() {
        return direccionPredioEmpre;
    }

    public void setDireccionPredioEmpre(String direccionPredioEmpre) {
        this.direccionPredioEmpre = direccionPredioEmpre;
    }

    public String getCaudalConcesionadoEmpre() {
        return caudalConcesionadoEmpre;
    }

    public void setCaudalConcesionadoEmpre(String caudalConcesionadoEmpre) {
        this.caudalConcesionadoEmpre = caudalConcesionadoEmpre;
    }

    public String getClasificacionSueloEmpre() {
        return clasificacionSueloEmpre;
    }

    public void setClasificacionSueloEmpre(String clasificacionSueloEmpre) {
        this.clasificacionSueloEmpre = clasificacionSueloEmpre;
    }

    public String getSistemaRefPredio() {
        return sistemaRefPredio;
    }

    public void setSistemaRefPredio(String sistemaRefPredio) {
        this.sistemaRefPredio = sistemaRefPredio;
    }

    public String getGradLatPredio() {
        return gradLatPredio;
    }

    public void setGradLatPredio(String gradLatPredio) {
        this.gradLatPredio = gradLatPredio;
    }

    public String getMinLatPredio() {
        return minLatPredio;
    }

    public void setMinLatPredio(String minLatPredio) {
        this.minLatPredio = minLatPredio;
    }

    public String getSegLatPredio() {
        return segLatPredio;
    }

    public void setSegLatPredio(String segLatPredio) {
        this.segLatPredio = segLatPredio;
    }

    public String getGradLogPredio() {
        return gradLogPredio;
    }

    public void setGradLogPredio(String gradLogPredio) {
        this.gradLogPredio = gradLogPredio;
    }

    public String getMinLogPredio() {
        return minLogPredio;
    }

    public void setMinLogPredio(String minLogPredio) {
        this.minLogPredio = minLogPredio;
    }

    public String getSegLogPredio() {
        return segLogPredio;
    }

    public void setSegLogPredio(String segLogPredio) {
        this.segLogPredio = segLogPredio;
    }

    public String getAltitudPredio() {
        return altitudPredio;
    }

    public void setAltitudPredio(String altitudPredio) {
        this.altitudPredio = altitudPredio;
    }

    public String getDescripSitioPredio() {
        return descripSitioPredio;
    }

    public void setDescripSitioPredio(String descripSitioPredio) {
        this.descripSitioPredio = descripSitioPredio;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNumResolucionAsigCaudal() {
        return numResolucionAsigCaudal;
    }

    public void setNumResolucionAsigCaudal(String numResolucionAsigCaudal) {
        this.numResolucionAsigCaudal = numResolucionAsigCaudal;
    }

    public String getFechaExpedicionAsigCaudal() {
        return fechaExpedicionAsigCaudal;
    }

    public void setFechaExpedicionAsigCaudal(String fechaExpedicionAsigCaudal) {
        this.fechaExpedicionAsigCaudal = fechaExpedicionAsigCaudal;
    }

    public String getFechaNumtifiAsigCaudal() {
        return fechaNumtifiAsigCaudal;
    }

    public void setFechaNumtifiAsigCaudal(String fechaNumtifiAsigCaudal) {
        this.fechaNumtifiAsigCaudal = fechaNumtifiAsigCaudal;
    }

    public String getNumResolucionAprobPlanos() {
        return numResolucionAprobPlanos;
    }

    public void setNumResolucionAprobPlanos(String numResolucionAprobPlanos) {
        this.numResolucionAprobPlanos = numResolucionAprobPlanos;
    }

    public String getFechaExpedicionAprobPlanos() {
        return fechaExpedicionAprobPlanos;
    }

    public void setFechaExpedicionAprobPlanos(String fechaExpedicionAprobPlanos) {
        this.fechaExpedicionAprobPlanos = fechaExpedicionAprobPlanos;
    }

    public String getFechaNotifiAprobPlanos() {
        return fechaNotifiAprobPlanos;
    }

    public void setFechaNotifiAprobPlanos(String fechaNotifiAprobPlanos) {
        this.fechaNotifiAprobPlanos = fechaNotifiAprobPlanos;
    }

    public String getNumResolucionObras() {
        return numResolucionObras;
    }

    public void setNumResolucionObras(String numResolucionObras) {
        this.numResolucionObras = numResolucionObras;
    }

    public String getFechaExpedicionObras() {
        return fechaExpedicionObras;
    }

    public void setFechaExpedicionObras(String fechaExpedicionObras) {
        this.fechaExpedicionObras = fechaExpedicionObras;
    }

    public String getFechaNumtificacionObras() {
        return fechaNumtificacionObras;
    }

    public void setFechaNumtificacionObras(String fechaNumtificacionObras) {
        this.fechaNumtificacionObras = fechaNumtificacionObras;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTipoDeFuente() {
        return tipoDeFuente;
    }

    public void setTipoDeFuente(String tipoDeFuente) {
        this.tipoDeFuente = tipoDeFuente;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTipoCentroPoblado() {
        return tipoCentroPoblado;
    }

    public void setTipoCentroPoblado(String tipoCentroPoblado) {
        this.tipoCentroPoblado = tipoCentroPoblado;
    }

    public String getCentroPoblado() {
        return centroPoblado;
    }

    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }

    public String getAreaH() {
        return areaH;
    }

    public void setAreaH(String areaH) {
        this.areaH = areaH;
    }

    public String getZonaH() {
        return zonaH;
    }

    public void setZonaH(String zonaH) {
        this.zonaH = zonaH;
    }

    public String getSubzonaH() {
        return subzonaH;
    }

    public void setSubzonaH(String subzonaH) {
        this.subzonaH = subzonaH;
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

    public String getIdPuntoAguasSub() {
        return idPuntoAguasSub;
    }

    public void setIdPuntoAguasSub(String idPuntoAguasSub) {
        this.idPuntoAguasSub = idPuntoAguasSub;
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

    public String getAforo() {
        return aforo;
    }

    public void setAforo(String aforo) {
        this.aforo = aforo;
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

    public String getEstadoCaptacion() {
        return estadoCaptacion;
    }

    public void setEstadoCaptacion(String estadoCaptacion) {
        this.estadoCaptacion = estadoCaptacion;
    }

    public String getCaudalDisenoCapMedido() {
        return caudalDisenoCapMedido;
    }

    public void setCaudalDisenoCapMedido(String caudalDisenoCapMedido) {
        this.caudalDisenoCapMedido = caudalDisenoCapMedido;
    }

    public String getContinuidadServicio() {
        return continuidadServicio;
    }

    public void setContinuidadServicio(String continuidadServicio) {
        this.continuidadServicio = continuidadServicio;
    }

    public String getServidumbre() {
        return servidumbre;
    }

    public void setServidumbre(String servidumbre) {
        this.servidumbre = servidumbre;
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

    public String getCaudalAbaste() {
        return caudalAbaste;
    }

    public void setCaudalAbaste(String caudalAbaste) {
        this.caudalAbaste = caudalAbaste;
    }

    public String getNumPerPermanentesAbaste() {
        return numPerPermanentesAbaste;
    }

    public void setNumPerPermanentesAbaste(String numPerPermanentesAbaste) {
        this.numPerPermanentesAbaste = numPerPermanentesAbaste;
    }

    public String getNumPerTransitoriasAbaste() {
        return numPerTransitoriasAbaste;
    }

    public void setNumPerTransitoriasAbaste(String numPerTransitoriasAbaste) {
        this.numPerTransitoriasAbaste = numPerTransitoriasAbaste;
    }

    public String getAprovechAbaste() {
        return aprovechAbaste;
    }

    public void setAprovechAbaste(String aprovechAbaste) {
        this.aprovechAbaste = aprovechAbaste;
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

    public String getNumAnimalesAbrev() {
        return numAnimalesAbrev;
    }

    public void setNumAnimalesAbrev(String numAnimalesAbrev) {
        this.numAnimalesAbrev = numAnimalesAbrev;
    }

    public String getTipoAnimalPesca() {
        return tipoAnimalPesca;
    }

    public void setTipoAnimalPesca(String tipoAnimalPesca) {
        this.tipoAnimalPesca = tipoAnimalPesca;
    }

    public String getCaudalpesca() {
        return caudalpesca;
    }

    public void setCaudalpesca(String caudalpesca) {
        this.caudalpesca = caudalpesca;
    }

    public String getNumAnimalesPesca() {
        return numAnimalesPesca;
    }

    public void setNumAnimalesPesca(String numAnimalesPesca) {
        this.numAnimalesPesca = numAnimalesPesca;
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

    public String getTipoDeUsoOtros() {
        return tipoDeUsoOtros;
    }

    public void setTipoDeUsoOtros(String tipoDeUsoOtros) {
        this.tipoDeUsoOtros = tipoDeUsoOtros;
    }

    public String getDecripcionOtros() {
        return decripcionOtros;
    }

    public void setDecripcionOtros(String decripcionOtros) {
        this.decripcionOtros = decripcionOtros;
    }

    public String getCaudalOtros() {
        return caudalOtros;
    }

    public void setCaudalOtros(String caudalOtros) {
        this.caudalOtros = caudalOtros;
    }

    public CmatControlCargueV1 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV1 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

  
    @Override
    public String toString() {
        return "Entidades.CmatPersonaJuridicaCaV1[ idRegistro=" + idRegistro + " ]";
    }
    
}
