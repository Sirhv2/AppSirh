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
@Table(name = "CMAT_FUNIAS_GEOFISICA_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasGeofisicaV2.findAllGeof", query = "SELECT c FROM CmatFuniasGeofisicaV2 c"),
    @NamedQuery(name = "CmatFuniasGeofisicaV2.findByCodigoRechazoGeof", query = "SELECT c FROM CmatFuniasGeofisicaV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasGeofisicaV2.findByIdRegistroGeof", query = "SELECT c FROM CmatFuniasGeofisicaV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueGeof", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasGeofisicaV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PTO_AGUA_SUB")
    private String idPtoAguaSub;
    @Column(name = "FECHA_MEDIDA")
    private String fechaMedida;
    @Column(name = "EQUIPO_UTILIZADO")
    private String equipoUtilizado;
    @Column(name = "TIPO_REGISTRO")
    private String tipoRegistro;
    @Column(name = "COMPAÑIA_EJECUTORA")
    private String companiaEjecutora;
    @Column(name = "RESISTIVIDAD_LODO")
    private String resistividadLodo;
    @Column(name = "TEMPERATURA_LODO")
    private String temperaturaLodo;
    @Column(name = "PROFUNDIDAD_REGISTRO")
    private String profundidadRegistro;
    @Column(name = "CALIDAD_REGISTRO")
    private String calidadRegistro;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasGeofisicaV2() {
    }

    public CmatFuniasGeofisicaV2(double idRegistro) {
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

    public String getIdPtoAguaSub() {
        return idPtoAguaSub;
    }

    public void setIdPtoAguaSub(String idPtoAguaSub) {
        this.idPtoAguaSub = idPtoAguaSub;
    }

    public String getFechaMedida() {
        return fechaMedida;
    }

    public void setFechaMedida(String fechaMedida) {
        this.fechaMedida = fechaMedida;
    }

    public String getEquipoUtilizado() {
        return equipoUtilizado;
    }

    public void setEquipoUtilizado(String equipoUtilizado) {
        this.equipoUtilizado = equipoUtilizado;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getCompaniaEjecutora() {
        return companiaEjecutora;
    }

    public void setCompaniaEjecutora(String companiaEjecutora) {
        this.companiaEjecutora = companiaEjecutora;
    }

    public String getResistividadLodo() {
        return resistividadLodo;
    }

    public void setResistividadLodo(String resistividadLodo) {
        this.resistividadLodo = resistividadLodo;
    }

    public String getTemperaturaLodo() {
        return temperaturaLodo;
    }

    public void setTemperaturaLodo(String temperaturaLodo) {
        this.temperaturaLodo = temperaturaLodo;
    }

    public String getProfundidadRegistro() {
        return profundidadRegistro;
    }

    public void setProfundidadRegistro(String profundidadRegistro) {
        this.profundidadRegistro = profundidadRegistro;
    }

    public String getCalidadRegistro() {
        return calidadRegistro;
    }

    public void setCalidadRegistro(String calidadRegistro) {
        this.calidadRegistro = calidadRegistro;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

 

    @Override
    public String toString() {
        return "Entidades.CmatFuniasGeofisicaV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
