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
@Table(name = "CMAT_FUNIAS_PERS_JUR_CA_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasPersJurCaV2.findAllFunJurCa", query = "SELECT c FROM CmatFuniasPersJurCaV2 c"),
    @NamedQuery(name = "CmatFuniasPersJurCaV2.findByIdControlCargueFunJurCa", query = "SELECT c FROM CmatFuniasPersJurCaV2 c WHERE c.idControlCargue = :idControlCargue"),
    @NamedQuery(name = "CmatFuniasPersJurCaV2.findByCodigoRechazoFunJurCa", query = "SELECT c FROM CmatFuniasPersJurCaV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasPersJurCaV2.findByIdRegistroFunJurCa", query = "SELECT c FROM CmatFuniasPersJurCaV2 c WHERE c.idRegistro = :idRegistro")})
public class CmatFuniasPersJurCaV2 implements Serializable {

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
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    @Column(name = "NUM_DOCUMENTO")
    private String numDocumento;
    @Column(name = "ACTIVIDAD_ECONOMICA")
    private String actividadEconomica;
    @Column(name = "DIR_CORRESPONDENCIA")
    private String dirCorrespondencia;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "OBSERVACIONES_USUARIO")
    private String observacionesUsuario;
    @Column(name = "NOMBRE_RPSTE_LEGAL")
    private String nombreRpsteLegal;
    @Column(name = "APELLIDO_RPSTE_LEGAL")
    private String apellidoRpsteLegal;
    @Column(name = "TIPO_DOC_RPSTE_LEGAL")
    private String tipoDocRpsteLegal;
    @Column(name = "NUM_DOC_RPSTE_LEGAL")
    private String numDocRpsteLegal;
    @Column(name = "DEPTO_RPSTE_LEGAL")
    private String deptoRpsteLegal;
    @Column(name = "MUNICIPIO_RPSTE_LEGAL")
    private String municipioRpsteLegal;
    @Column(name = "DIR_RPSTE_LEGAL")
    private String dirRpsteLegal;
    @Column(name = "TELEFONO_RPSTE_LEGAL")
    private String telefonoRpsteLegal;
    @Column(name = "NOMBRE_SITIO")
    private String nombreSitio;
    @Column(name = "TIPO_TENENCIA")
    private String tipoTenencia;
    @Column(name = "DEPARTAMENTO_PREDIO")
    private String departamentoPredio;
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
    @Column(name = "AREA")
    private String area;
    @Column(name = "DIRECCION_PREDIO")
    private String direccionPredio;
    @Column(name = "CAUDAL_CONC_EMP")
    private String caudalConcEmp;
    @Column(name = "CLASIFICACION_SUELO")
    private String clasificacionSuelo;
    @Column(name = "SISTEMA_REF")
    private String sistemaRef;
    @Column(name = "GRAD_LAT_PREDIO")
    private String gradLatPredio;
    @Column(name = "MIN_LAT__PREDIO")
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
    @Column(name = "DESCRIPCION_SITIO_GEO")
    private String descripcionSitioGeo;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "NUM_RESOLUCION")
    private String numResolucion;
    @Column(name = "FECHA_EXPEDICION")
    private String fechaExpedicion;
    @Column(name = "TIPO_CAUDAL_CONCESIONADO")
    private String tipoCaudalConcesionado;
    @Column(name = "CAUDAL_CONCESIONADO")
    private String caudalConcesionado;
    @Column(name = "FECHA_NOTIFICACION")
    private String fechaNotificacion;
    @Column(name = "CAUDAL_CONC_TEMP_SECA")
    private String caudalConcTempSeca;
    @Column(name = "CAUDAL_CONC_TEMP_HUM")
    private String caudalConcTempHum;
    @Column(name = "REGIMEN_H_DIA_TEMP_SECA")
    private String regimenHDiaTempSeca;
    @Column(name = "REGIMEN_H_DIA_TEMP_HUM")
    private String regimenHDiaTempHum;
    @Column(name = "NUM_RESOLUCION_APROB")
    private String numResolucionAprob;
    @Column(name = "FECHA_EXPEDICION_APROB")
    private String fechaExpedicionAprob;
    @Column(name = "FECHA_NOTIFI_APROB_PLANOS")
    private String fechaNotifiAprobPlanos;
    @Column(name = "NUM_RESOLUCION_OBRAS")
    private String numResolucionObras;
    @Column(name = "FECHA_EXPEDICION_OBRAS")
    private String fechaExpedicionObras;
    @Column(name = "FECHA_NOTIFICACION_OBRAS")
    private String fechaNotificacionObras;
    @Column(name = "VIGENCIA_DESDE")
    private String vigenciaDesde;
    @Column(name = "VIGENCIA_HASTA")
    private String vigenciaHasta;
    @Column(name = "TIPO_FUENTE")
    private String tipoFuente;
    @Column(name = "DEPARTAMENTO_CAP")
    private String departamentoCap;
    @Column(name = "MUNICIPIO_CAP")
    private String municipioCap;
    @Column(name = "TIPO_CENTRO_POBLADO_CAP")
    private String tipoCentroPobladoCap;
    @Column(name = "NOMBRE_CENTRO_POBLADO_CAP")
    private String nombreCentroPobladoCap;
    @Column(name = "AREA_CAP")
    private String areaCap;
    @Column(name = "ZONA_CAP")
    private String zonaCap;
    @Column(name = "SUBZONA_CAP")
    private String subzonaCap;
    @Column(name = "TIPO_FUENTE_CAP")
    private String tipoFuenteCap;
    @Column(name = "FUENTE_CAP")
    private String fuenteCap;
    @Column(name = "TRAMO_CAP")
    private String tramoCap;
    @Column(name = "ID_PTO_AGUA_SUBTERRANEA")
    private String idPtoAguaSubterranea;
    @Column(name = "NOMBRE_PTO_AA")
    private String nombrePtoAa;
    @Column(name = "PROVINCIA_CAP")
    private String provinciaCap;
    @Column(name = "SISTEMA_ACUIFERO")
    private String sistemaAcuifero;
    @Column(name = "OTRO_SISTEMA_ACUIFERO")
    private String otroSistemaAcuifero;
    @Column(name = "UNIDA_HIDRO")
    private String unidaHidro;
    @Column(name = "OTRAS_UNIDADES_HIDRO")
    private String otrasUnidadesHidro;
    @Column(name = "TIPO_PTO_AGUA_SUBTE")
    private String tipoPtoAguaSubte;
    @Column(name = "TIPO_MANANTIAL")
    private String tipoManantial;
    @Column(name = "OTRO_TIPO_MANANTIAL")
    private String otroTipoManantial;
    @Column(name = "PERMANENCIA_MANANTIAL")
    private String permanenciaManantial;
    @Column(name = "MEDIO_SURGENCIA_MANANTIAL")
    private String medioSurgenciaManantial;
    @Column(name = "OTRA_SURGENCIA_MANANTIAL")
    private String otraSurgenciaManantial;
    @Column(name = "CONDICION_PTO")
    private String condicionPto;
    @Column(name = "OTRA_CONDICION")
    private String otraCondicion;
    @Column(name = "HACE_PARTE_RED_MON_REG_ASUB")
    private String haceParteRedMonRegAsub;
    @Column(name = "HACE_PARTE_RED_BASICA_NAC")
    private String haceParteRedBasicaNac;
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
    @Column(name = "OFERTA_HIDRICA")
    private String ofertaHidrica;
    @Column(name = "OFERTA_TOTAL")
    private String ofertaTotal;
    @Column(name = "OFERTA_DISPONIBLE")
    private String ofertaDisponible;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "TIPO_CAPTACION")
    private String tipoCaptacion;
    @Column(name = "AREA_CAPTACION")
    private String areaCaptacion;
    @Column(name = "MEDICION")
    private String medicion;
    @Column(name = "ESTADO_CAPTACION")
    private String estadoCaptacion;
    @Column(name = "CONTINUIDAD_SERVICIO")
    private String continuidadServicio;
    @Column(name = "SERVIDUMBRE")
    private String servidumbre;
    @Column(name = "CAUDAL_DISE\u00d1O_CAP_MEDIDO")
    private String caudalDisenoCapMedido;
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
    @Column(name = "COTA_TERRENO_PTO_AGUA_SUB")
    private String cotaTerrenoPtoAguaSub;
    @Column(name = "METODO_MEDIDA_COTA")
    private String metodoMedidaCota;
    @Column(name = "DESCRIPCION_SITIO_CAP")
    private String descripcionSitioCap;
    @Column(name = "CAUDAL_CONSUMO_HUMANO")
    private String caudalConsumoHumano;
    @Column(name = "NUM_PERSONAS_PERMANENTES")
    private String numPersonasPermanentes;
    @Column(name = "NUM_PERSONAS_TRANSITORIAS")
    private String numPersonasTransitorias;
    @Column(name = "APROVECHAMIENTO")
    private String aprovechamiento;
    @Column(name = "TIPO_ANIMAL_ABREVADEROS")
    private String tipoAnimalAbrevaderos;
    @Column(name = "CAUDAL_ABREVADEROS")
    private String caudalAbrevaderos;
    @Column(name = "NUM_ANIMALES_ABREVADEROS")
    private String numAnimalesAbrevaderos;
    @Column(name = "TIPO_ANIMAL_PESCA")
    private String tipoAnimalPesca;
    @Column(name = "CAUDAL_PESCA")
    private String caudalPesca;
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
    @Column(name = "TIPO_USO_OTROS")
    private String tipoUsoOtros;
    @Column(name = "DESCRIPCION_OTROS")
    private String descripcionOtros;
    @Column(name = "CAUDAL_OTROS")
    private String caudalOtros;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasPersJurCaV2() {
    }

    public CmatFuniasPersJurCaV2(double idRegistro) {
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getObservacionesUsuario() {
        return observacionesUsuario;
    }

    public void setObservacionesUsuario(String observacionesUsuario) {
        this.observacionesUsuario = observacionesUsuario;
    }

    public String getNombreRpsteLegal() {
        return nombreRpsteLegal;
    }

    public void setNombreRpsteLegal(String nombreRpsteLegal) {
        this.nombreRpsteLegal = nombreRpsteLegal;
    }

    public String getApellidoRpsteLegal() {
        return apellidoRpsteLegal;
    }

    public void setApellidoRpsteLegal(String apellidoRpsteLegal) {
        this.apellidoRpsteLegal = apellidoRpsteLegal;
    }

    public String getTipoDocRpsteLegal() {
        return tipoDocRpsteLegal;
    }

    public void setTipoDocRpsteLegal(String tipoDocRpsteLegal) {
        this.tipoDocRpsteLegal = tipoDocRpsteLegal;
    }

    public String getNumDocRpsteLegal() {
        return numDocRpsteLegal;
    }

    public void setNumDocRpsteLegal(String numDocRpsteLegal) {
        this.numDocRpsteLegal = numDocRpsteLegal;
    }

    public String getDeptoRpsteLegal() {
        return deptoRpsteLegal;
    }

    public void setDeptoRpsteLegal(String deptoRpsteLegal) {
        this.deptoRpsteLegal = deptoRpsteLegal;
    }

    public String getMunicipioRpsteLegal() {
        return municipioRpsteLegal;
    }

    public void setMunicipioRpsteLegal(String municipioRpsteLegal) {
        this.municipioRpsteLegal = municipioRpsteLegal;
    }

    public String getDirRpsteLegal() {
        return dirRpsteLegal;
    }

    public void setDirRpsteLegal(String dirRpsteLegal) {
        this.dirRpsteLegal = dirRpsteLegal;
    }

    public String getTelefonoRpsteLegal() {
        return telefonoRpsteLegal;
    }

    public void setTelefonoRpsteLegal(String telefonoRpsteLegal) {
        this.telefonoRpsteLegal = telefonoRpsteLegal;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public String getTipoTenencia() {
        return tipoTenencia;
    }

    public void setTipoTenencia(String tipoTenencia) {
        this.tipoTenencia = tipoTenencia;
    }

    public String getDepartamentoPredio() {
        return departamentoPredio;
    }

    public void setDepartamentoPredio(String departamentoPredio) {
        this.departamentoPredio = departamentoPredio;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDireccionPredio() {
        return direccionPredio;
    }

    public void setDireccionPredio(String direccionPredio) {
        this.direccionPredio = direccionPredio;
    }

    public String getCaudalConcEmp() {
        return caudalConcEmp;
    }

    public void setCaudalConcEmp(String caudalConcEmp) {
        this.caudalConcEmp = caudalConcEmp;
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

    public String getDescripcionSitioGeo() {
        return descripcionSitioGeo;
    }

    public void setDescripcionSitioGeo(String descripcionSitioGeo) {
        this.descripcionSitioGeo = descripcionSitioGeo;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNumResolucion() {
        return numResolucion;
    }

    public void setNumResolucion(String numResolucion) {
        this.numResolucion = numResolucion;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getTipoCaudalConcesionado() {
        return tipoCaudalConcesionado;
    }

    public void setTipoCaudalConcesionado(String tipoCaudalConcesionado) {
        this.tipoCaudalConcesionado = tipoCaudalConcesionado;
    }

    public String getCaudalConcesionado() {
        return caudalConcesionado;
    }

    public void setCaudalConcesionado(String caudalConcesionado) {
        this.caudalConcesionado = caudalConcesionado;
    }

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getCaudalConcTempSeca() {
        return caudalConcTempSeca;
    }

    public void setCaudalConcTempSeca(String caudalConcTempSeca) {
        this.caudalConcTempSeca = caudalConcTempSeca;
    }

    public String getCaudalConcTempHum() {
        return caudalConcTempHum;
    }

    public void setCaudalConcTempHum(String caudalConcTempHum) {
        this.caudalConcTempHum = caudalConcTempHum;
    }

    public String getRegimenHDiaTempSeca() {
        return regimenHDiaTempSeca;
    }

    public void setRegimenHDiaTempSeca(String regimenHDiaTempSeca) {
        this.regimenHDiaTempSeca = regimenHDiaTempSeca;
    }

    public String getRegimenHDiaTempHum() {
        return regimenHDiaTempHum;
    }

    public void setRegimenHDiaTempHum(String regimenHDiaTempHum) {
        this.regimenHDiaTempHum = regimenHDiaTempHum;
    }

    public String getNumResolucionAprob() {
        return numResolucionAprob;
    }

    public void setNumResolucionAprob(String numResolucionAprob) {
        this.numResolucionAprob = numResolucionAprob;
    }

    public String getFechaExpedicionAprob() {
        return fechaExpedicionAprob;
    }

    public void setFechaExpedicionAprob(String fechaExpedicionAprob) {
        this.fechaExpedicionAprob = fechaExpedicionAprob;
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

    public String getFechaNotificacionObras() {
        return fechaNotificacionObras;
    }

    public void setFechaNotificacionObras(String fechaNotificacionObras) {
        this.fechaNotificacionObras = fechaNotificacionObras;
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

    public String getTipoFuente() {
        return tipoFuente;
    }

    public void setTipoFuente(String tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    public String getDepartamentoCap() {
        return departamentoCap;
    }

    public void setDepartamentoCap(String departamentoCap) {
        this.departamentoCap = departamentoCap;
    }

    public String getMunicipioCap() {
        return municipioCap;
    }

    public void setMunicipioCap(String municipioCap) {
        this.municipioCap = municipioCap;
    }

    public String getTipoCentroPobladoCap() {
        return tipoCentroPobladoCap;
    }

    public void setTipoCentroPobladoCap(String tipoCentroPobladoCap) {
        this.tipoCentroPobladoCap = tipoCentroPobladoCap;
    }

    public String getNombreCentroPobladoCap() {
        return nombreCentroPobladoCap;
    }

    public void setNombreCentroPobladoCap(String nombreCentroPobladoCap) {
        this.nombreCentroPobladoCap = nombreCentroPobladoCap;
    }

    public String getAreaCap() {
        return areaCap;
    }

    public void setAreaCap(String areaCap) {
        this.areaCap = areaCap;
    }

    public String getZonaCap() {
        return zonaCap;
    }

    public void setZonaCap(String zonaCap) {
        this.zonaCap = zonaCap;
    }

    public String getSubzonaCap() {
        return subzonaCap;
    }

    public void setSubzonaCap(String subzonaCap) {
        this.subzonaCap = subzonaCap;
    }

    public String getTipoFuenteCap() {
        return tipoFuenteCap;
    }

    public void setTipoFuenteCap(String tipoFuenteCap) {
        this.tipoFuenteCap = tipoFuenteCap;
    }

    public String getFuenteCap() {
        return fuenteCap;
    }

    public void setFuenteCap(String fuenteCap) {
        this.fuenteCap = fuenteCap;
    }

    public String getTramoCap() {
        return tramoCap;
    }

    public void setTramoCap(String tramoCap) {
        this.tramoCap = tramoCap;
    }

    public String getIdPtoAguaSubterranea() {
        return idPtoAguaSubterranea;
    }

    public void setIdPtoAguaSubterranea(String idPtoAguaSubterranea) {
        this.idPtoAguaSubterranea = idPtoAguaSubterranea;
    }

    public String getNombrePtoAa() {
        return nombrePtoAa;
    }

    public void setNombrePtoAa(String nombrePtoAa) {
        this.nombrePtoAa = nombrePtoAa;
    }

    public String getProvinciaCap() {
        return provinciaCap;
    }

    public void setProvinciaCap(String provinciaCap) {
        this.provinciaCap = provinciaCap;
    }

    public String getSistemaAcuifero() {
        return sistemaAcuifero;
    }

    public void setSistemaAcuifero(String sistemaAcuifero) {
        this.sistemaAcuifero = sistemaAcuifero;
    }

    public String getOtroSistemaAcuifero() {
        return otroSistemaAcuifero;
    }

    public void setOtroSistemaAcuifero(String otroSistemaAcuifero) {
        this.otroSistemaAcuifero = otroSistemaAcuifero;
    }

    public String getUnidaHidro() {
        return unidaHidro;
    }

    public void setUnidaHidro(String unidaHidro) {
        this.unidaHidro = unidaHidro;
    }

    public String getOtrasUnidadesHidro() {
        return otrasUnidadesHidro;
    }

    public void setOtrasUnidadesHidro(String otrasUnidadesHidro) {
        this.otrasUnidadesHidro = otrasUnidadesHidro;
    }

    public String getTipoPtoAguaSubte() {
        return tipoPtoAguaSubte;
    }

    public void setTipoPtoAguaSubte(String tipoPtoAguaSubte) {
        this.tipoPtoAguaSubte = tipoPtoAguaSubte;
    }

    public String getTipoManantial() {
        return tipoManantial;
    }

    public void setTipoManantial(String tipoManantial) {
        this.tipoManantial = tipoManantial;
    }

    public String getOtroTipoManantial() {
        return otroTipoManantial;
    }

    public void setOtroTipoManantial(String otroTipoManantial) {
        this.otroTipoManantial = otroTipoManantial;
    }

    public String getPermanenciaManantial() {
        return permanenciaManantial;
    }

    public void setPermanenciaManantial(String permanenciaManantial) {
        this.permanenciaManantial = permanenciaManantial;
    }

    public String getMedioSurgenciaManantial() {
        return medioSurgenciaManantial;
    }

    public void setMedioSurgenciaManantial(String medioSurgenciaManantial) {
        this.medioSurgenciaManantial = medioSurgenciaManantial;
    }

    public String getOtraSurgenciaManantial() {
        return otraSurgenciaManantial;
    }

    public void setOtraSurgenciaManantial(String otraSurgenciaManantial) {
        this.otraSurgenciaManantial = otraSurgenciaManantial;
    }

    public String getCondicionPto() {
        return condicionPto;
    }

    public void setCondicionPto(String condicionPto) {
        this.condicionPto = condicionPto;
    }

    public String getOtraCondicion() {
        return otraCondicion;
    }

    public void setOtraCondicion(String otraCondicion) {
        this.otraCondicion = otraCondicion;
    }

    public String getHaceParteRedMonRegAsub() {
        return haceParteRedMonRegAsub;
    }

    public void setHaceParteRedMonRegAsub(String haceParteRedMonRegAsub) {
        this.haceParteRedMonRegAsub = haceParteRedMonRegAsub;
    }

    public String getHaceParteRedBasicaNac() {
        return haceParteRedBasicaNac;
    }

    public void setHaceParteRedBasicaNac(String haceParteRedBasicaNac) {
        this.haceParteRedBasicaNac = haceParteRedBasicaNac;
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

    public String getOfertaHidrica() {
        return ofertaHidrica;
    }

    public void setOfertaHidrica(String ofertaHidrica) {
        this.ofertaHidrica = ofertaHidrica;
    }

    public String getOfertaTotal() {
        return ofertaTotal;
    }

    public void setOfertaTotal(String ofertaTotal) {
        this.ofertaTotal = ofertaTotal;
    }

    public String getOfertaDisponible() {
        return ofertaDisponible;
    }

    public void setOfertaDisponible(String ofertaDisponible) {
        this.ofertaDisponible = ofertaDisponible;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoCaptacion() {
        return tipoCaptacion;
    }

    public void setTipoCaptacion(String tipoCaptacion) {
        this.tipoCaptacion = tipoCaptacion;
    }

    public String getAreaCaptacion() {
        return areaCaptacion;
    }

    public void setAreaCaptacion(String areaCaptacion) {
        this.areaCaptacion = areaCaptacion;
    }

    public String getMedicion() {
        return medicion;
    }

    public void setMedicion(String medicion) {
        this.medicion = medicion;
    }

    public String getEstadoCaptacion() {
        return estadoCaptacion;
    }

    public void setEstadoCaptacion(String estadoCaptacion) {
        this.estadoCaptacion = estadoCaptacion;
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

    public String getCaudalDisenoCapMedido() {
        return caudalDisenoCapMedido;
    }

    public void setCaudalDisenoCapMedido(String caudalDisenoCapMedido) {
        this.caudalDisenoCapMedido = caudalDisenoCapMedido;
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

    public String getCotaTerrenoPtoAguaSub() {
        return cotaTerrenoPtoAguaSub;
    }

    public void setCotaTerrenoPtoAguaSub(String cotaTerrenoPtoAguaSub) {
        this.cotaTerrenoPtoAguaSub = cotaTerrenoPtoAguaSub;
    }

    public String getMetodoMedidaCota() {
        return metodoMedidaCota;
    }

    public void setMetodoMedidaCota(String metodoMedidaCota) {
        this.metodoMedidaCota = metodoMedidaCota;
    }

    public String getDescripcionSitioCap() {
        return descripcionSitioCap;
    }

    public void setDescripcionSitioCap(String descripcionSitioCap) {
        this.descripcionSitioCap = descripcionSitioCap;
    }

    public String getCaudalConsumoHumano() {
        return caudalConsumoHumano;
    }

    public void setCaudalConsumoHumano(String caudalConsumoHumano) {
        this.caudalConsumoHumano = caudalConsumoHumano;
    }

    public String getNumPersonasPermanentes() {
        return numPersonasPermanentes;
    }

    public void setNumPersonasPermanentes(String numPersonasPermanentes) {
        this.numPersonasPermanentes = numPersonasPermanentes;
    }

    public String getNumPersonasTransitorias() {
        return numPersonasTransitorias;
    }

    public void setNumPersonasTransitorias(String numPersonasTransitorias) {
        this.numPersonasTransitorias = numPersonasTransitorias;
    }

    public String getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(String aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public String getTipoAnimalAbrevaderos() {
        return tipoAnimalAbrevaderos;
    }

    public void setTipoAnimalAbrevaderos(String tipoAnimalAbrevaderos) {
        this.tipoAnimalAbrevaderos = tipoAnimalAbrevaderos;
    }

    public String getCaudalAbrevaderos() {
        return caudalAbrevaderos;
    }

    public void setCaudalAbrevaderos(String caudalAbrevaderos) {
        this.caudalAbrevaderos = caudalAbrevaderos;
    }

    public String getNumAnimalesAbrevaderos() {
        return numAnimalesAbrevaderos;
    }

    public void setNumAnimalesAbrevaderos(String numAnimalesAbrevaderos) {
        this.numAnimalesAbrevaderos = numAnimalesAbrevaderos;
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

    public String getTipoUsoOtros() {
        return tipoUsoOtros;
    }

    public void setTipoUsoOtros(String tipoUsoOtros) {
        this.tipoUsoOtros = tipoUsoOtros;
    }

    public String getDescripcionOtros() {
        return descripcionOtros;
    }

    public void setDescripcionOtros(String descripcionOtros) {
        this.descripcionOtros = descripcionOtros;
    }

    public String getCaudalOtros() {
        return caudalOtros;
    }

    public void setCaudalOtros(String caudalOtros) {
        this.caudalOtros = caudalOtros;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }



    @Override
    public String toString() {
        return "Entidades.CmatFuniasPersJurCaV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
