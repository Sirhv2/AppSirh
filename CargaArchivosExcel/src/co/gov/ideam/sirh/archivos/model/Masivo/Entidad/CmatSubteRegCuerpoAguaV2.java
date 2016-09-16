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
@Table(name = "CMAT_SUBTE_REG_CUERPO_AGUA_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatSubteRegCuerpoAguaV2.findAllRegAguaSubV2", query = "SELECT c FROM CmatSubteRegCuerpoAguaV2 c"),
    @NamedQuery(name = "CmatSubteRegCuerpoAguaV2.findByCodigoRechazoRegAguaSubV2", query = "SELECT c FROM CmatSubteRegCuerpoAguaV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatSubteRegCuerpoAguaV2.findByIdRegistroRegAguaSubV2", query = "SELECT c FROM CmatSubteRegCuerpoAguaV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueRegAguaSubV2", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatSubteRegCuerpoAguaV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_FUENTE")
    private String idFuente;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "PROVINCIA_HIDRO")
    private String provinciaHidro;
    @Column(name = "SISTEMA_ACUIFERO")
    private String sistemaAcuifero;
    @Column(name = "NOMBRE_UNIDAD_HIDRO")
    private String nombreUnidadHidro;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CONDUCTIVIDAD")
    private String conductividad;
    @Column(name = "TRANSMISIVIDAD")
    private String transmisividad;
    @Column(name = "CARACTERISTICAS")
    private String caracteristicas;
    @Column(name = "ESPESOR")
    private String espesor;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatSubteRegCuerpoAguaV2() {
    }

    public CmatSubteRegCuerpoAguaV2(double idRegistro) {
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

    public String getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(String idFuente) {
        this.idFuente = idFuente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProvinciaHidro() {
        return provinciaHidro;
    }

    public void setProvinciaHidro(String provinciaHidro) {
        this.provinciaHidro = provinciaHidro;
    }

    public String getSistemaAcuifero() {
        return sistemaAcuifero;
    }

    public void setSistemaAcuifero(String sistemaAcuifero) {
        this.sistemaAcuifero = sistemaAcuifero;
    }

    public String getNombreUnidadHidro() {
        return nombreUnidadHidro;
    }

    public void setNombreUnidadHidro(String nombreUnidadHidro) {
        this.nombreUnidadHidro = nombreUnidadHidro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConductividad() {
        return conductividad;
    }

    public void setConductividad(String conductividad) {
        this.conductividad = conductividad;
    }

    public String getTransmisividad() {
        return transmisividad;
    }

    public void setTransmisividad(String transmisividad) {
        this.transmisividad = transmisividad;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEspesor() {
        return espesor;
    }

    public void setEspesor(String espesor) {
        this.espesor = espesor;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

   
    @Override
    public String toString() {
        return "Entidades.CmatSubteRegCuerpoAguaV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
