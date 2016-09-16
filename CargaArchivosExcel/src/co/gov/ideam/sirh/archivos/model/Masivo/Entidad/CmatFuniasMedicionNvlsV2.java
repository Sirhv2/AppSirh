/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.ideam.sirh.archivos.model.Masivo.Entidad;

import java.io.Serializable;
import java.math.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Johan
 */
@Entity
@Table(name = "CMAT_FUNIAS_MEDICION_NVLS_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasMedicionNvlsV2.findAllMedic", query = "SELECT c FROM CmatFuniasMedicionNvlsV2 c"),
    @NamedQuery(name = "CmatFuniasMedicionNvlsV2.findByCodigoRechazoMedic", query = "SELECT c FROM CmatFuniasMedicionNvlsV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasMedicionNvlsV2.findByIdRegistroMedic", query = "SELECT c FROM CmatFuniasMedicionNvlsV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueMedic", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasMedicionNvlsV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PTO_AGUA_SUBTERRANEA")
    private String idPtoAguaSubterranea;
    @Column(name = "FECHA_INICIO_MEDICION")
    private String fechaInicioMedicion;
    @Column(name = "HORA")
    private String hora;
    @Column(name = "MINUTO")
    private String minuto;
    @Column(name = "HORARIO")
    private String horario;
    @Column(name = "COND_CLIMATICA_MUESTREO")
    private String condClimaticaMuestreo;
    @Column(name = "PROFUNDIDAD_MEDIDA")
    private String profundidadMedida;
    @Column(name = "COTA_TERRENO_PTO")
    private String cotaTerrenoPto;
    @Column(name = "METODO_MEDIDA")
    private String metodoMedida;
    @Column(name = "LUGAR_MEDICION_NVL_AGUA")
    private String lugarMedicionNvlAgua;
    @Column(name = "NIVEL_MEDIDO")
    private String nivelMedido;
    @Column(name = "TIPO_NIVEL")
    private String tipoNivel;
    @Column(name = "TIEMPO_BOMBEO")
    private String tiempoBombeo;
    @Column(name = "TIEMPO_DESDE_APAGADO_BOMBA")
    private String tiempoDesdeApagadoBomba;
    @Column(name = "METODO_MEDIDA_BOMBEO")
    private String metodoMedidaBombeo;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasMedicionNvlsV2() {
    }

    public CmatFuniasMedicionNvlsV2(double idRegistro) {
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

    public String getIdPtoAguaSubterranea() {
        return idPtoAguaSubterranea;
    }

    public void setIdPtoAguaSubterranea(String idPtoAguaSubterranea) {
        this.idPtoAguaSubterranea = idPtoAguaSubterranea;
    }

    public String getFechaInicioMedicion() {
        return fechaInicioMedicion;
    }

    public void setFechaInicioMedicion(String fechaInicioMedicion) {
        this.fechaInicioMedicion = fechaInicioMedicion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCondClimaticaMuestreo() {
        return condClimaticaMuestreo;
    }

    public void setCondClimaticaMuestreo(String condClimaticaMuestreo) {
        this.condClimaticaMuestreo = condClimaticaMuestreo;
    }

    public String getProfundidadMedida() {
        return profundidadMedida;
    }

    public void setProfundidadMedida(String profundidadMedida) {
        this.profundidadMedida = profundidadMedida;
    }

    public String getCotaTerrenoPto() {
        return cotaTerrenoPto;
    }

    public void setCotaTerrenoPto(String cotaTerrenoPto) {
        this.cotaTerrenoPto = cotaTerrenoPto;
    }

    public String getMetodoMedida() {
        return metodoMedida;
    }

    public void setMetodoMedida(String metodoMedida) {
        this.metodoMedida = metodoMedida;
    }

    public String getLugarMedicionNvlAgua() {
        return lugarMedicionNvlAgua;
    }

    public void setLugarMedicionNvlAgua(String lugarMedicionNvlAgua) {
        this.lugarMedicionNvlAgua = lugarMedicionNvlAgua;
    }

    public String getNivelMedido() {
        return nivelMedido;
    }

    public void setNivelMedido(String nivelMedido) {
        this.nivelMedido = nivelMedido;
    }

    public String getTipoNivel() {
        return tipoNivel;
    }

    public void setTipoNivel(String tipoNivel) {
        this.tipoNivel = tipoNivel;
    }

    public String getTiempoBombeo() {
        return tiempoBombeo;
    }

    public void setTiempoBombeo(String tiempoBombeo) {
        this.tiempoBombeo = tiempoBombeo;
    }

    public String getTiempoDesdeApagadoBomba() {
        return tiempoDesdeApagadoBomba;
    }

    public void setTiempoDesdeApagadoBomba(String tiempoDesdeApagadoBomba) {
        this.tiempoDesdeApagadoBomba = tiempoDesdeApagadoBomba;
    }

    public String getMetodoMedidaBombeo() {
        return metodoMedidaBombeo;
    }

    public void setMetodoMedidaBombeo(String metodoMedidaBombeo) {
        this.metodoMedidaBombeo = metodoMedidaBombeo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }



    @Override
    public String toString() {
        return "Entidades.CmatFuniasMedicionNvlsV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
