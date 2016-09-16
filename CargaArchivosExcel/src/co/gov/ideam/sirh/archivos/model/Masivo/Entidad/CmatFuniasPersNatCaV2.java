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
@Table(name = "CMAT_FUNIAS_PERS_NAT_CA_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasPersNatCaV2.findAllFunNatCa", query = "SELECT c FROM CmatFuniasPersNatCaV2 c"),
    @NamedQuery(name = "CmatFuniasPersNatCaV2.findByCodigoRechazoFunNatCa", query = "SELECT c FROM CmatFuniasPersNatCaV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasPersNatCaV2.findByIdRegistroFunNatCa", query = "SELECT c FROM CmatFuniasPersNatCaV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueFunNatCa", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasPersNatCaV2 implements Serializable {

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
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Column(name = "NUM_IDENTIFICACION")
    private String numIdentificacion;
    @Column(name = "DEPTO_CORRESPONDENCIA")
    private String deptoCorrespondencia;
    @Column(name = "MUNICIPIO_CORRESPONDENCIA")
    private String municipioCorrespondencia;
    @Column(name = "DIR_CORRESPONDENCIA")
    private String dirCorrespondencia;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "NOMBRE_PREDIO")
    private String nombrePredio;
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
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "NUM_RESOLUCION")
    private String numResolucion;
    @Column(name = "FECHA_EXPEDICION")
    private String fechaExpedicion;
    @Column(name = "FECHA_NOTIFICACION")
    private String fechaNotificacion;
    @Column(name = "CAUDAL_CONCESIONADO")
    private String caudalConcesionado;
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
    @Column(name = "PROVINCIA_CAP")
    private String provinciaCap;
    @Column(name = "SISTEMA_ACUIFERO_NAC_CAP")
    private String sistemaAcuiferoNacCap;
    @Column(name = "UNIDAD_HIDRO_CAP")
    private String unidadHidroCap;
    @Column(name = "ID_PTO_AGUAS_SUB")
    private String idPtoAguasSub;
    @Column(name = "TIPO_PTO_AGUA_SUB")
    private String tipoPtoAguaSub;
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
    @Column(name = "ALTITUD_CAP")
    private String altitudCap;
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

    public CmatFuniasPersNatCaV2() {
    }

    public CmatFuniasPersNatCaV2(double idRegistro) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getCaudalConcesionado() {
        return caudalConcesionado;
    }

    public void setCaudalConcesionado(String caudalConcesionado) {
        this.caudalConcesionado = caudalConcesionado;
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

    public String getProvinciaCap() {
        return provinciaCap;
    }

    public void setProvinciaCap(String provinciaCap) {
        this.provinciaCap = provinciaCap;
    }

    public String getSistemaAcuiferoNacCap() {
        return sistemaAcuiferoNacCap;
    }

    public void setSistemaAcuiferoNacCap(String sistemaAcuiferoNacCap) {
        this.sistemaAcuiferoNacCap = sistemaAcuiferoNacCap;
    }

    public String getUnidadHidroCap() {
        return unidadHidroCap;
    }

    public void setUnidadHidroCap(String unidadHidroCap) {
        this.unidadHidroCap = unidadHidroCap;
    }

    public String getIdPtoAguasSub() {
        return idPtoAguasSub;
    }

    public void setIdPtoAguasSub(String idPtoAguasSub) {
        this.idPtoAguasSub = idPtoAguasSub;
    }

    public String getTipoPtoAguaSub() {
        return tipoPtoAguaSub;
    }

    public void setTipoPtoAguaSub(String tipoPtoAguaSub) {
        this.tipoPtoAguaSub = tipoPtoAguaSub;
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

    public String getAltitudCap() {
        return altitudCap;
    }

    public void setAltitudCap(String altitudCap) {
        this.altitudCap = altitudCap;
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
        return "Entidades.CmatFuniasPersNatCaV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
