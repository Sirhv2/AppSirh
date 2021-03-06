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
@Table(name = "CMAT_PERSONA_JURIDICA_PV_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatPersonaJuridicaPvV2.findAllPerJurPvV2", query = "SELECT c FROM CmatPersonaJuridicaPvV2 c"),
    @NamedQuery(name = "CmatPersonaJuridicaPvV2.findByCodigoRechazoPerJurPvV2", query = "SELECT c FROM CmatPersonaJuridicaPvV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatPersonaJuridicaPvV2.findByIdRegistroPerJurPvV2", query = "SELECT c FROM CmatPersonaJuridicaPvV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCarguePerJurPvV2", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatPersonaJuridicaPvV2 implements Serializable {

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
    @Column(name = "NUM_ACT_ADM_AUT_V")
    private String numActAdmAutV;
    @Column(name = "FECHA_EXP_ACT_ADM")
    private String fechaExpActAdm;
    @Column(name = "NUM_EXPEDIENTE")
    private String numExpediente;
    @Column(name = "CAUDAL_AUTORIZADO")
    private String caudalAutorizado;
    @Column(name = "EVAL_AMBIEN_VERT")
    private String evalAmbienVert;
    @Column(name = "NUM_ACT_PRES_PLAN_CUMP")
    private String numActPresPlanCump;
    @Column(name = "FECHA_EXP_ACT_ADM_PLCU")
    private String fechaExpActAdmPlcu;
    @Column(name = "NUM_ACT_ADM_APRU_PLCU")
    private String numActAdmApruPlcu;
    @Column(name = "FECHA_EXP_APROB_PLAN")
    private String fechaExpAprobPlan;
    @Column(name = "VIG_INI_PLAN_CUMPL")
    private String vigIniPlanCumpl;
    @Column(name = "VIG_FIN_PLAN_CUMPL")
    private String vigFinPlanCumpl;
    @Column(name = "NUM_RESOL_OTOR_PV")
    private String numResolOtorPv;
    @Column(name = "FECHA_EXP_PV")
    private String fechaExpPv;
    @Column(name = "VIGENCIA_INI_PV")
    private String vigenciaIniPv;
    @Column(name = "VIGENCIA_FIN_PV")
    private String vigenciaFinPv;
    @Column(name = "NUM_ACT_ADM_PSMV")
    private String numActAdmPsmv;
    @Column(name = "FECHA_EXP_PSMV")
    private String fechaExpPsmv;
    @Column(name = "VIGENCIA_INICIAL_PSMV")
    private String vigenciaInicialPsmv;
    @Column(name = "VIGENCIA_FINAL_PSMV")
    private String vigenciaFinalPsmv;
    @Column(name = "NUM_ACT_APRUEBA__PSMV")
    private String numActApruebaPsmv;
    @Column(name = "FECHA_EXP_APRUB_PSMV")
    private String fechaExpAprubPsmv;
    @Column(name = "NUM_ACT_APRU_OBRAS_PSMV")
    private String numActApruObrasPsmv;
    @Column(name = "FECHA_EXP_APRU_OBRAS_PSMV")
    private String fechaExpApruObrasPsmv;
    @Column(name = "FECHA_NOTIF_APRU_OBR_PSMV")
    private String fechaNotifApruObrPsmv;
    @Column(name = "OBSERVACIONES_PSMV")
    private String observacionesPsmv;
    @Column(name = "NUM_ACT_ADM_APR_PLANOS")
    private String numActAdmAprPlanos;
    @Column(name = "FECHA_EXP_APROB_PLANOS")
    private String fechaExpAprobPlanos;
    @Column(name = "NUM_ACT_APROB_OBRAS")
    private String numActAprobObras;
    @Column(name = "FECHA_EXP_ACT_APR_OBRAS")
    private String fechaExpActAprObras;
    @Column(name = "TIPO_VERTIMIENTO")
    private String tipoVertimiento;
    @Column(name = "DEPARTAMENTO_VERTI")
    private String departamentoVerti;
    @Column(name = "MUNICIPIO_VERTI")
    private String municipioVerti;
    @Column(name = "TIPO_CENTRO_POBLADO_PV")
    private String tipoCentroPobladoPv;
    @Column(name = "CENTRO_POBLADO")
    private String centroPoblado;
    @Column(name = "AREA_PV")
    private String areaPv;
    @Column(name = "ZONA_PV")
    private String zonaPv;
    @Column(name = "SUBZONA")
    private String subzona;
    @Column(name = "TIPO_FUENTE_PV")
    private String tipoFuentePv;
    @Column(name = "FUENTE_PV")
    private String fuentePv;
    @Column(name = "TRAMO_PV")
    private String tramoPv;
    @Column(name = "TIPO_FLUJO_PV")
    private String tipoFlujoPv;
    @Column(name = "TIEMPO_DESCARGA_PV")
    private String tiempoDescargaPv;
    @Column(name = "FRECUENCIA_PV")
    private String frecuenciaPv;
    @Column(name = "CAUDAL_DISE\u00d1O_PV")
    private String caudalDisenoPv;
    @Column(name = "PRETRATAMIENTO")
    private String pretratamiento;
    @Column(name = "PRIMARIO")
    private String primario;
    @Column(name = "SECUNDARIO")
    private String secundario;
    @Column(name = "TERCIARIO")
    private String terciario;
    @Column(name = "PRETRATAMIENTO_OTROS")
    private String pretratamientoOtros;
    @Column(name = "SISTEMA_REF_PV")
    private String sistemaRefPv;
    @Column(name = "GRAD_LAT_PV")
    private String gradLatPv;
    @Column(name = "MIN_LAT_PV")
    private String minLatPv;
    @Column(name = "SEG_LAT_PV")
    private String segLatPv;
    @Column(name = "GRAD_LOG_PV")
    private String gradLogPv;
    @Column(name = "MIN_LOG_PV")
    private String minLogPv;
    @Column(name = "SEG_LOG_PV")
    private String segLogPv;
    @Column(name = "ALTITUD_PV")
    private String altitudPv;
    @Column(name = "DESCRIPCION_SITIO_PV")
    private String descripcionSitioPv;
    @Column(name = "NOMBRE_PUNTO_DESCARGA")
    private String nombrePuntoDescarga;
    @Column(name = "NOMBRE_PUNTO_AGUAS_ARRIBA_V")
    private String nombrePuntoAguasArribaV;
    @Column(name = "NOMBRE_PUNTO_AGUAS_ABAJO_V")
    private String nombrePuntoAguasAbajoV;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatPersonaJuridicaPvV2() {
    }

    public CmatPersonaJuridicaPvV2(double idRegistro) {
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

    public String getNumActAdmAutV() {
        return numActAdmAutV;
    }

    public void setNumActAdmAutV(String numActAdmAutV) {
        this.numActAdmAutV = numActAdmAutV;
    }

    public String getFechaExpActAdm() {
        return fechaExpActAdm;
    }

    public void setFechaExpActAdm(String fechaExpActAdm) {
        this.fechaExpActAdm = fechaExpActAdm;
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

    public String getEvalAmbienVert() {
        return evalAmbienVert;
    }

    public void setEvalAmbienVert(String evalAmbienVert) {
        this.evalAmbienVert = evalAmbienVert;
    }

    public String getNumActPresPlanCump() {
        return numActPresPlanCump;
    }

    public void setNumActPresPlanCump(String numActPresPlanCump) {
        this.numActPresPlanCump = numActPresPlanCump;
    }

    public String getFechaExpActAdmPlcu() {
        return fechaExpActAdmPlcu;
    }

    public void setFechaExpActAdmPlcu(String fechaExpActAdmPlcu) {
        this.fechaExpActAdmPlcu = fechaExpActAdmPlcu;
    }

    public String getNumActAdmApruPlcu() {
        return numActAdmApruPlcu;
    }

    public void setNumActAdmApruPlcu(String numActAdmApruPlcu) {
        this.numActAdmApruPlcu = numActAdmApruPlcu;
    }

    public String getFechaExpAprobPlan() {
        return fechaExpAprobPlan;
    }

    public void setFechaExpAprobPlan(String fechaExpAprobPlan) {
        this.fechaExpAprobPlan = fechaExpAprobPlan;
    }

    public String getVigIniPlanCumpl() {
        return vigIniPlanCumpl;
    }

    public void setVigIniPlanCumpl(String vigIniPlanCumpl) {
        this.vigIniPlanCumpl = vigIniPlanCumpl;
    }

    public String getVigFinPlanCumpl() {
        return vigFinPlanCumpl;
    }

    public void setVigFinPlanCumpl(String vigFinPlanCumpl) {
        this.vigFinPlanCumpl = vigFinPlanCumpl;
    }

    public String getNumResolOtorPv() {
        return numResolOtorPv;
    }

    public void setNumResolOtorPv(String numResolOtorPv) {
        this.numResolOtorPv = numResolOtorPv;
    }

    public String getFechaExpPv() {
        return fechaExpPv;
    }

    public void setFechaExpPv(String fechaExpPv) {
        this.fechaExpPv = fechaExpPv;
    }

    public String getVigenciaIniPv() {
        return vigenciaIniPv;
    }

    public void setVigenciaIniPv(String vigenciaIniPv) {
        this.vigenciaIniPv = vigenciaIniPv;
    }

    public String getVigenciaFinPv() {
        return vigenciaFinPv;
    }

    public void setVigenciaFinPv(String vigenciaFinPv) {
        this.vigenciaFinPv = vigenciaFinPv;
    }

    public String getNumActAdmPsmv() {
        return numActAdmPsmv;
    }

    public void setNumActAdmPsmv(String numActAdmPsmv) {
        this.numActAdmPsmv = numActAdmPsmv;
    }

    public String getFechaExpPsmv() {
        return fechaExpPsmv;
    }

    public void setFechaExpPsmv(String fechaExpPsmv) {
        this.fechaExpPsmv = fechaExpPsmv;
    }

    public String getVigenciaInicialPsmv() {
        return vigenciaInicialPsmv;
    }

    public void setVigenciaInicialPsmv(String vigenciaInicialPsmv) {
        this.vigenciaInicialPsmv = vigenciaInicialPsmv;
    }

    public String getVigenciaFinalPsmv() {
        return vigenciaFinalPsmv;
    }

    public void setVigenciaFinalPsmv(String vigenciaFinalPsmv) {
        this.vigenciaFinalPsmv = vigenciaFinalPsmv;
    }

    public String getNumActApruebaPsmv() {
        return numActApruebaPsmv;
    }

    public void setNumActApruebaPsmv(String numActApruebaPsmv) {
        this.numActApruebaPsmv = numActApruebaPsmv;
    }

    public String getFechaExpAprubPsmv() {
        return fechaExpAprubPsmv;
    }

    public void setFechaExpAprubPsmv(String fechaExpAprubPsmv) {
        this.fechaExpAprubPsmv = fechaExpAprubPsmv;
    }

    public String getNumActApruObrasPsmv() {
        return numActApruObrasPsmv;
    }

    public void setNumActApruObrasPsmv(String numActApruObrasPsmv) {
        this.numActApruObrasPsmv = numActApruObrasPsmv;
    }

    public String getFechaExpApruObrasPsmv() {
        return fechaExpApruObrasPsmv;
    }

    public void setFechaExpApruObrasPsmv(String fechaExpApruObrasPsmv) {
        this.fechaExpApruObrasPsmv = fechaExpApruObrasPsmv;
    }

    public String getFechaNotifApruObrPsmv() {
        return fechaNotifApruObrPsmv;
    }

    public void setFechaNotifApruObrPsmv(String fechaNotifApruObrPsmv) {
        this.fechaNotifApruObrPsmv = fechaNotifApruObrPsmv;
    }

    public String getObservacionesPsmv() {
        return observacionesPsmv;
    }

    public void setObservacionesPsmv(String observacionesPsmv) {
        this.observacionesPsmv = observacionesPsmv;
    }

    public String getNumActAdmAprPlanos() {
        return numActAdmAprPlanos;
    }

    public void setNumActAdmAprPlanos(String numActAdmAprPlanos) {
        this.numActAdmAprPlanos = numActAdmAprPlanos;
    }

    public String getFechaExpAprobPlanos() {
        return fechaExpAprobPlanos;
    }

    public void setFechaExpAprobPlanos(String fechaExpAprobPlanos) {
        this.fechaExpAprobPlanos = fechaExpAprobPlanos;
    }

    public String getNumActAprobObras() {
        return numActAprobObras;
    }

    public void setNumActAprobObras(String numActAprobObras) {
        this.numActAprobObras = numActAprobObras;
    }

    public String getFechaExpActAprObras() {
        return fechaExpActAprObras;
    }

    public void setFechaExpActAprObras(String fechaExpActAprObras) {
        this.fechaExpActAprObras = fechaExpActAprObras;
    }

    public String getTipoVertimiento() {
        return tipoVertimiento;
    }

    public void setTipoVertimiento(String tipoVertimiento) {
        this.tipoVertimiento = tipoVertimiento;
    }

    public String getDepartamentoVerti() {
        return departamentoVerti;
    }

    public void setDepartamentoVerti(String departamentoVerti) {
        this.departamentoVerti = departamentoVerti;
    }

    public String getMunicipioVerti() {
        return municipioVerti;
    }

    public void setMunicipioVerti(String municipioVerti) {
        this.municipioVerti = municipioVerti;
    }

    public String getTipoCentroPobladoPv() {
        return tipoCentroPobladoPv;
    }

    public void setTipoCentroPobladoPv(String tipoCentroPobladoPv) {
        this.tipoCentroPobladoPv = tipoCentroPobladoPv;
    }

    public String getCentroPoblado() {
        return centroPoblado;
    }

    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }

    public String getAreaPv() {
        return areaPv;
    }

    public void setAreaPv(String areaPv) {
        this.areaPv = areaPv;
    }

    public String getZonaPv() {
        return zonaPv;
    }

    public void setZonaPv(String zonaPv) {
        this.zonaPv = zonaPv;
    }

    public String getSubzona() {
        return subzona;
    }

    public void setSubzona(String subzona) {
        this.subzona = subzona;
    }

    public String getTipoFuentePv() {
        return tipoFuentePv;
    }

    public void setTipoFuentePv(String tipoFuentePv) {
        this.tipoFuentePv = tipoFuentePv;
    }

    public String getFuentePv() {
        return fuentePv;
    }

    public void setFuentePv(String fuentePv) {
        this.fuentePv = fuentePv;
    }

    public String getTramoPv() {
        return tramoPv;
    }

    public void setTramoPv(String tramoPv) {
        this.tramoPv = tramoPv;
    }

    public String getTipoFlujoPv() {
        return tipoFlujoPv;
    }

    public void setTipoFlujoPv(String tipoFlujoPv) {
        this.tipoFlujoPv = tipoFlujoPv;
    }

    public String getTiempoDescargaPv() {
        return tiempoDescargaPv;
    }

    public void setTiempoDescargaPv(String tiempoDescargaPv) {
        this.tiempoDescargaPv = tiempoDescargaPv;
    }

    public String getFrecuenciaPv() {
        return frecuenciaPv;
    }

    public void setFrecuenciaPv(String frecuenciaPv) {
        this.frecuenciaPv = frecuenciaPv;
    }

    public String getCaudalDisenoPv() {
        return caudalDisenoPv;
    }

    public void setCaudalDisenoPv(String caudalDisenoPv) {
        this.caudalDisenoPv = caudalDisenoPv;
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

    public String getPretratamientoOtros() {
        return pretratamientoOtros;
    }

    public void setPretratamientoOtros(String pretratamientoOtros) {
        this.pretratamientoOtros = pretratamientoOtros;
    }

    public String getSistemaRefPv() {
        return sistemaRefPv;
    }

    public void setSistemaRefPv(String sistemaRefPv) {
        this.sistemaRefPv = sistemaRefPv;
    }

    public String getGradLatPv() {
        return gradLatPv;
    }

    public void setGradLatPv(String gradLatPv) {
        this.gradLatPv = gradLatPv;
    }

    public String getMinLatPv() {
        return minLatPv;
    }

    public void setMinLatPv(String minLatPv) {
        this.minLatPv = minLatPv;
    }

    public String getSegLatPv() {
        return segLatPv;
    }

    public void setSegLatPv(String segLatPv) {
        this.segLatPv = segLatPv;
    }

    public String getGradLogPv() {
        return gradLogPv;
    }

    public void setGradLogPv(String gradLogPv) {
        this.gradLogPv = gradLogPv;
    }

    public String getMinLogPv() {
        return minLogPv;
    }

    public void setMinLogPv(String minLogPv) {
        this.minLogPv = minLogPv;
    }

    public String getSegLogPv() {
        return segLogPv;
    }

    public void setSegLogPv(String segLogPv) {
        this.segLogPv = segLogPv;
    }

    public String getAltitudPv() {
        return altitudPv;
    }

    public void setAltitudPv(String altitudPv) {
        this.altitudPv = altitudPv;
    }

    public String getDescripcionSitioPv() {
        return descripcionSitioPv;
    }

    public void setDescripcionSitioPv(String descripcionSitioPv) {
        this.descripcionSitioPv = descripcionSitioPv;
    }

    public String getNombrePuntoDescarga() {
        return nombrePuntoDescarga;
    }

    public void setNombrePuntoDescarga(String nombrePuntoDescarga) {
        this.nombrePuntoDescarga = nombrePuntoDescarga;
    }

    public String getNombrePuntoAguasArribaV() {
        return nombrePuntoAguasArribaV;
    }

    public void setNombrePuntoAguasArribaV(String nombrePuntoAguasArribaV) {
        this.nombrePuntoAguasArribaV = nombrePuntoAguasArribaV;
    }

    public String getNombrePuntoAguasAbajoV() {
        return nombrePuntoAguasAbajoV;
    }

    public void setNombrePuntoAguasAbajoV(String nombrePuntoAguasAbajoV) {
        this.nombrePuntoAguasAbajoV = nombrePuntoAguasAbajoV;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }



    @Override
    public String toString() {
        return "Entidades.CmatPersonaJuridicaPvV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
