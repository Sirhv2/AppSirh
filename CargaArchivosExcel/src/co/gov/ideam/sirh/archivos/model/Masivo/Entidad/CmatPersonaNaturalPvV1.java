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
@Table(name = "CMAT_PERSONA_NATURAL_PV_V1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatPersonaNaturalPvV1.findAllPerNatPvV1", query = "SELECT c FROM CmatPersonaNaturalPvV1 c"),
    @NamedQuery(name = "CmatPersonaNaturalPvV1.findByCodigoRechazoPerNatPvV1", query = "SELECT c FROM CmatPersonaNaturalPvV1 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatPersonaNaturalPvV1.findByIdRegistroPerNatPvV1", query = "SELECT c FROM CmatPersonaNaturalPvV1 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCarguePerNatPvV1", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatPersonaNaturalPvV1 implements Serializable {

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
    @Column(name = "TIPO_DOC_USUARIO")
    private String tipoDocUsuario;
    @Column(name = "NUM_DOC_USUARIO")
    private String numDocUsuario;
    @Column(name = "DEPTO_USUARIO")
    private String deptoUsuario;
    @Column(name = "MUNICIPIO_USUARIO")
    private String municipioUsuario;
    @Column(name = "DIR_CORRESPONDENCIA_USUARIO")
    private String dirCorrespondenciaUsuario;
    @Column(name = "E_MAIL_USUARIO")
    private String eMailUsuario;
    @Column(name = "TEL_USUARIO")
    private String telUsuario;
    @Column(name = "FAX_USUARIO")
    private String faxUsuario;
    @Column(name = "NOMBRE_PREDIO")
    private String nombrePredio;
    @Column(name = "TIPO_TENENCIA")
    private String tipoTenencia;
    @Column(name = "DEPTO_PREDIO")
    private String deptoPredio;
    @Column(name = "MUNICIPIO_PREDIO")
    private String municipioPredio;
    @Column(name = "TIPO_CENTRO_POBLADO_PREDIO")
    private String tipoCentroPobladoPredio;
    @Column(name = "NOMBRE_CENTRO_POBLADO_PREDIO")
    private String nombreCentroPobladoPredio;
    @Column(name = "CEDULA_CATASTRAL_PREDIO")
    private String cedulaCatastralPredio;
    @Column(name = "MATRICULA_INMOBILIARIA_PREDIO")
    private String matriculaInmobiliariaPredio;
    @Column(name = "AREA_TOTAL_DEL_PREDIO_PREDIO")
    private String areaTotalDelPredioPredio;
    @Column(name = "DIRECCION_DEL_PREDIO")
    private String direccionDelPredio;
    @Column(name = "CLASIFICACION_SUELO")
    private String clasificacionSuelo;
    @Column(name = "NUM_ADMON_INICIA_AUT_PV")
    private String numAdmonIniciaAutPv;
    @Column(name = "FECHA_EXPEDICI\u00d3N_ACTO_ADMON")
    private String fechaExpedicionActoAdmon;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "CAUDAL_AUTORIZADO_")
    private String caudalAutorizado;
    @Column(name = "EVALUACI\u00d3N_AMBIENTAL_PV")
    private String evaluacionAmbientalPv;
    @Column(name = "NUM_ADMON_SOLICITA_PLAN_CUMPL")
    private String numAdmonSolicitaPlanCumpl;
    @Column(name = "FECHA_EXPE_ACTO_ADMON")
    private String fechaExpeActoAdmon;
    @Column(name = "NUM_ADMON_APRUB_PLAN_CMPLMINT")
    private String numAdmonAprubPlanCmplmint;
    @Column(name = "FECHA_EXPE_ACTO_ADMON_PLCMO")
    private String fechaExpeActoAdmonPlcmo;
    @Column(name = "FECHA_NOTIFI_APROB_PLN_CMPLMNT")
    private String fechaNotifiAprobPlnCmplmnt;
    @Column(name = "VIGENCIA_INI_PLAN_CMPLIMNT")
    private String vigenciaIniPlanCmplimnt;
    @Column(name = "VIGENCIA_FIN_PLAN_CMPLMNT")
    private String vigenciaFinPlanCmplmnt;
    @Column(name = "NUM_RESOL_OTORGA_PV")
    private String numResolOtorgaPv;
    @Column(name = "FECHA_EXPE_ACTO_ADMON_PV")
    private String fechaExpeActoAdmonPv;
    @Column(name = "VIGENCIA_INICIAL_PV")
    private String vigenciaInicialPv;
    @Column(name = "VIGENCIA_FINAL_PV")
    private String vigenciaFinalPv;
    @Column(name = "NUM_ACTO_ADMON_APROB_PLANOS")
    private String numActoAdmonAprobPlanos;
    @Column(name = "FECHA_DE_EXPED_ACTO_ADMON")
    private String fechaDeExpedActoAdmon;
    @Column(name = "NUM_ADMON_APROB_OBRAS")
    private String numAdmonAprobObras;
    @Column(name = "FECHA_EXPED_ADMON_APROB_OBRAS")
    private String fechaExpedAdmonAprobObras;
    @Column(name = "FECHA_NOTIFI_APROB_OBRAS")
    private String fechaNotifiAprobObras;
    @Column(name = "TIPO_VERTIMIENTO")
    private String tipoVertimiento;
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
    @Column(name = "TIPO_DE_FLUJO")
    private String tipoDeFlujo;
    @Column(name = "TIEMPO_DE_DESCARGA")
    private String tiempoDeDescarga;
    @Column(name = "FRECUENCIA")
    private String frecuencia;
    @Column(name = "CAUDAL_DISE\u00d1O_ST")
    private String caudalDisenoSt;
    @Column(name = "PRETRATAMIENTO")
    private String pretratamiento;
    @Column(name = "PRIMARIO")
    private String primario;
    @Column(name = "SECUNDARIO")
    private String secundario;
    @Column(name = "TERCIARIO")
    private String terciario;
    @Column(name = "OTROS")
    private String otros;
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
    @Column(name = "ALTITUD_")
    private String altitud;
    @Column(name = "DESCRIPCION_DEL_SITIO")
    private String descripcionDelSitio;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV1 idControlCargue;

    public CmatPersonaNaturalPvV1() {
    }

    public CmatPersonaNaturalPvV1(double idRegistro) {
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

    public String getDeptoUsuario() {
        return deptoUsuario;
    }

    public void setDeptoUsuario(String deptoUsuario) {
        this.deptoUsuario = deptoUsuario;
    }

    public String getMunicipioUsuario() {
        return municipioUsuario;
    }

    public void setMunicipioUsuario(String municipioUsuario) {
        this.municipioUsuario = municipioUsuario;
    }

    public String getDirCorrespondenciaUsuario() {
        return dirCorrespondenciaUsuario;
    }

    public void setDirCorrespondenciaUsuario(String dirCorrespondenciaUsuario) {
        this.dirCorrespondenciaUsuario = dirCorrespondenciaUsuario;
    }

    public String getEMailUsuario() {
        return eMailUsuario;
    }

    public void setEMailUsuario(String eMailUsuario) {
        this.eMailUsuario = eMailUsuario;
    }

    public String getTelUsuario() {
        return telUsuario;
    }

    public void setTelUsuario(String telUsuario) {
        this.telUsuario = telUsuario;
    }

    public String getFaxUsuario() {
        return faxUsuario;
    }

    public void setFaxUsuario(String faxUsuario) {
        this.faxUsuario = faxUsuario;
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

    public String getTipoCentroPobladoPredio() {
        return tipoCentroPobladoPredio;
    }

    public void setTipoCentroPobladoPredio(String tipoCentroPobladoPredio) {
        this.tipoCentroPobladoPredio = tipoCentroPobladoPredio;
    }

    public String getNombreCentroPobladoPredio() {
        return nombreCentroPobladoPredio;
    }

    public void setNombreCentroPobladoPredio(String nombreCentroPobladoPredio) {
        this.nombreCentroPobladoPredio = nombreCentroPobladoPredio;
    }

    public String getCedulaCatastralPredio() {
        return cedulaCatastralPredio;
    }

    public void setCedulaCatastralPredio(String cedulaCatastralPredio) {
        this.cedulaCatastralPredio = cedulaCatastralPredio;
    }

    public String getMatriculaInmobiliariaPredio() {
        return matriculaInmobiliariaPredio;
    }

    public void setMatriculaInmobiliariaPredio(String matriculaInmobiliariaPredio) {
        this.matriculaInmobiliariaPredio = matriculaInmobiliariaPredio;
    }

    public String getAreaTotalDelPredioPredio() {
        return areaTotalDelPredioPredio;
    }

    public void setAreaTotalDelPredioPredio(String areaTotalDelPredioPredio) {
        this.areaTotalDelPredioPredio = areaTotalDelPredioPredio;
    }

    public String getDireccionDelPredio() {
        return direccionDelPredio;
    }

    public void setDireccionDelPredio(String direccionDelPredio) {
        this.direccionDelPredio = direccionDelPredio;
    }

    public String getClasificacionSuelo() {
        return clasificacionSuelo;
    }

    public void setClasificacionSuelo(String clasificacionSuelo) {
        this.clasificacionSuelo = clasificacionSuelo;
    }

    public String getNumAdmonIniciaAutPv() {
        return numAdmonIniciaAutPv;
    }

    public void setNumAdmonIniciaAutPv(String numAdmonIniciaAutPv) {
        this.numAdmonIniciaAutPv = numAdmonIniciaAutPv;
    }

    public String getFechaExpedicionActoAdmon() {
        return fechaExpedicionActoAdmon;
    }

    public void setFechaExpedicionActoAdmon(String fechaExpedicionActoAdmon) {
        this.fechaExpedicionActoAdmon = fechaExpedicionActoAdmon;
    }

    public String getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getCaudalAutorizado() {
        return caudalAutorizado;
    }

    public void setCaudalAutorizado(String caudalAutorizado) {
        this.caudalAutorizado = caudalAutorizado;
    }

    public String getEvaluacionAmbientalPv() {
        return evaluacionAmbientalPv;
    }

    public void setEvaluacionAmbientalPv(String evaluacionAmbientalPv) {
        this.evaluacionAmbientalPv = evaluacionAmbientalPv;
    }

    public String getNumAdmonSolicitaPlanCumpl() {
        return numAdmonSolicitaPlanCumpl;
    }

    public void setNumAdmonSolicitaPlanCumpl(String numAdmonSolicitaPlanCumpl) {
        this.numAdmonSolicitaPlanCumpl = numAdmonSolicitaPlanCumpl;
    }

    public String getFechaExpeActoAdmon() {
        return fechaExpeActoAdmon;
    }

    public void setFechaExpeActoAdmon(String fechaExpeActoAdmon) {
        this.fechaExpeActoAdmon = fechaExpeActoAdmon;
    }

    public String getNumAdmonAprubPlanCmplmint() {
        return numAdmonAprubPlanCmplmint;
    }

    public void setNumAdmonAprubPlanCmplmint(String numAdmonAprubPlanCmplmint) {
        this.numAdmonAprubPlanCmplmint = numAdmonAprubPlanCmplmint;
    }

    public String getFechaExpeActoAdmonPlcmo() {
        return fechaExpeActoAdmonPlcmo;
    }

    public void setFechaExpeActoAdmonPlcmo(String fechaExpeActoAdmonPlcmo) {
        this.fechaExpeActoAdmonPlcmo = fechaExpeActoAdmonPlcmo;
    }

    public String getFechaNotifiAprobPlnCmplmnt() {
        return fechaNotifiAprobPlnCmplmnt;
    }

    public void setFechaNotifiAprobPlnCmplmnt(String fechaNotifiAprobPlnCmplmnt) {
        this.fechaNotifiAprobPlnCmplmnt = fechaNotifiAprobPlnCmplmnt;
    }

    public String getVigenciaIniPlanCmplimnt() {
        return vigenciaIniPlanCmplimnt;
    }

    public void setVigenciaIniPlanCmplimnt(String vigenciaIniPlanCmplimnt) {
        this.vigenciaIniPlanCmplimnt = vigenciaIniPlanCmplimnt;
    }

    public String getVigenciaFinPlanCmplmnt() {
        return vigenciaFinPlanCmplmnt;
    }

    public void setVigenciaFinPlanCmplmnt(String vigenciaFinPlanCmplmnt) {
        this.vigenciaFinPlanCmplmnt = vigenciaFinPlanCmplmnt;
    }

    public String getNumResolOtorgaPv() {
        return numResolOtorgaPv;
    }

    public void setNumResolOtorgaPv(String numResolOtorgaPv) {
        this.numResolOtorgaPv = numResolOtorgaPv;
    }

    public String getFechaExpeActoAdmonPv() {
        return fechaExpeActoAdmonPv;
    }

    public void setFechaExpeActoAdmonPv(String fechaExpeActoAdmonPv) {
        this.fechaExpeActoAdmonPv = fechaExpeActoAdmonPv;
    }

    public String getVigenciaInicialPv() {
        return vigenciaInicialPv;
    }

    public void setVigenciaInicialPv(String vigenciaInicialPv) {
        this.vigenciaInicialPv = vigenciaInicialPv;
    }

    public String getVigenciaFinalPv() {
        return vigenciaFinalPv;
    }

    public void setVigenciaFinalPv(String vigenciaFinalPv) {
        this.vigenciaFinalPv = vigenciaFinalPv;
    }

    public String getNumActoAdmonAprobPlanos() {
        return numActoAdmonAprobPlanos;
    }

    public void setNumActoAdmonAprobPlanos(String numActoAdmonAprobPlanos) {
        this.numActoAdmonAprobPlanos = numActoAdmonAprobPlanos;
    }

    public String getFechaDeExpedActoAdmon() {
        return fechaDeExpedActoAdmon;
    }

    public void setFechaDeExpedActoAdmon(String fechaDeExpedActoAdmon) {
        this.fechaDeExpedActoAdmon = fechaDeExpedActoAdmon;
    }

    public String getNumAdmonAprobObras() {
        return numAdmonAprobObras;
    }

    public void setNumAdmonAprobObras(String numAdmonAprobObras) {
        this.numAdmonAprobObras = numAdmonAprobObras;
    }

    public String getFechaExpedAdmonAprobObras() {
        return fechaExpedAdmonAprobObras;
    }

    public void setFechaExpedAdmonAprobObras(String fechaExpedAdmonAprobObras) {
        this.fechaExpedAdmonAprobObras = fechaExpedAdmonAprobObras;
    }

    public String getFechaNotifiAprobObras() {
        return fechaNotifiAprobObras;
    }

    public void setFechaNotifiAprobObras(String fechaNotifiAprobObras) {
        this.fechaNotifiAprobObras = fechaNotifiAprobObras;
    }

    public String getTipoVertimiento() {
        return tipoVertimiento;
    }

    public void setTipoVertimiento(String tipoVertimiento) {
        this.tipoVertimiento = tipoVertimiento;
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

    public String getTipoDeFlujo() {
        return tipoDeFlujo;
    }

    public void setTipoDeFlujo(String tipoDeFlujo) {
        this.tipoDeFlujo = tipoDeFlujo;
    }

    public String getTiempoDeDescarga() {
        return tiempoDeDescarga;
    }

    public void setTiempoDeDescarga(String tiempoDeDescarga) {
        this.tiempoDeDescarga = tiempoDeDescarga;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getCaudalDisenoSt() {
        return caudalDisenoSt;
    }

    public void setCaudalDisenoSt(String caudalDisenoSt) {
        this.caudalDisenoSt = caudalDisenoSt;
    }

    public String getPretratamiento() {
        return pretratamiento;
    }

    public void setPretratamiento(String pretratamiento) {
        this.pretratamiento = pretratamiento;
    }

    public String getPrimario() {
        return primario;
    }

    public void setPrimario(String primario) {
        this.primario = primario;
    }

    public String getSecundario() {
        return secundario;
    }

    public void setSecundario(String secundario) {
        this.secundario = secundario;
    }

    public String getTerciario() {
        return terciario;
    }

    public void setTerciario(String terciario) {
        this.terciario = terciario;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
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

    public String getDescripcionDelSitio() {
        return descripcionDelSitio;
    }

    public void setDescripcionDelSitio(String descripcionDelSitio) {
        this.descripcionDelSitio = descripcionDelSitio;
    }

    public CmatControlCargueV1 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV1 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

 

    @Override
    public String toString() {
        return "Entidades.CmatPersonaNaturalPvV1[ idRegistro=" + idRegistro + " ]";
    }
    
}
