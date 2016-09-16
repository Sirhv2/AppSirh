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
@Table(name = "CMAT_FUNIAS_MANANTIAL_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasManantialV2.findAllMana", query = "SELECT c FROM CmatFuniasManantialV2 c"),
    @NamedQuery(name = "CmatFuniasManantialV2.findByIdRegistroMana", query = "SELECT c FROM CmatFuniasManantialV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasManantialV2.findByCodigoRechazoMana", query = "SELECT c FROM CmatFuniasManantialV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueMana", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasManantialV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "CODIGO_RECHAZO")
    private String codigoRechazo;
    @Column(name = "ID_PTO_AGUA_SUBT")
    private String idPtoAguaSubt;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "OTRO_TIPO")
    private String otroTipo;
    @Column(name = "PERMANENCIA")
    private String permanencia;
    @Column(name = "MEDIO_SURGENCIA")
    private String medioSurgencia;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasManantialV2() {
    }

    public CmatFuniasManantialV2(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public double getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(double idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(String codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
    }

    public String getIdPtoAguaSubt() {
        return idPtoAguaSubt;
    }

    public void setIdPtoAguaSubt(String idPtoAguaSubt) {
        this.idPtoAguaSubt = idPtoAguaSubt;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOtroTipo() {
        return otroTipo;
    }

    public void setOtroTipo(String otroTipo) {
        this.otroTipo = otroTipo;
    }

    public String getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(String permanencia) {
        this.permanencia = permanencia;
    }

    public String getMedioSurgencia() {
        return medioSurgencia;
    }

    public void setMedioSurgencia(String medioSurgencia) {
        this.medioSurgencia = medioSurgencia;
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
        return "Entidades.CmatFuniasManantialV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
