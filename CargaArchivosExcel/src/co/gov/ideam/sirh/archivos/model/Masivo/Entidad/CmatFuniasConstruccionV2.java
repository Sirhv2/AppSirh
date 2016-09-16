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
@Table(name = "CMAT_FUNIAS_CONSTRUCCION_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasConstruccionV2.findAllConst", query = "SELECT c FROM CmatFuniasConstruccionV2 c"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueConst", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByCodigoRechazoConst", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdRegistroConst", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idRegistro = :idRegistro")})
public class CmatFuniasConstruccionV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PTO_AGUA_SUBTE")
    private String idPtoAguaSubte;
    @Column(name = "FECHA_CONSTRUCCION")
    private String fechaConstruccion;
    @Column(name = "DIAMETRO_INTERIOR")
    private String diametroInterior;
    @Column(name = "DIAMETRO_EXTERIOR")
    private String diametroExterior;
    @Column(name = "DIAMETRO_PERFORACION")
    private String diametroPerforacion;
    @Column(name = "MATERIAL_REVESTIMIENTO")
    private String materialRevestimiento;
    @Column(name = "METODO_CONSTRUCCION")
    private String metodoConstruccion;
    @Column(name = "COMPAÑIA_PERFORADORA")
    private String companiaPerfordora;
    @Column(name = "COLMATADO")
    private String colmatado;
    @Column(name = "COLAPSADO")
    private String colapsado;
    @Column(name = "PROFUNDIDAD_ENTUBADO")
    private String profundidadEntubado;
    @Column(name = "PROFUNDIDAD_PERFORACION")
    private String profundidadPerforacion;
    @Column(name = "CANTIDAD_GRAVILLA")
    private String cantidadGravilla;
    @Column(name = "NIVEL_ENGRAVILLADO")
    private String nivelEngravillado;
    @Column(name = "DIAMETRO_PROMEDIO")
    private String diametroPromedio;
    @Column(name = "EMBALSE")
    private String embalse;
    @Column(name = "CAPACIDAD_EMBALSE")
    private String capacidadEmbalse;
    @Column(name = "TANQUE")
    private String tanque;
    @Column(name = "CAPACIDAD_TANQUE")
    private String capacidadTanque;
    @Column(name = "ALBERCA")
    private String alberca;
    @Column(name = "CAPACIDAD_ALBERCA")
    private String capacidadAlberca;
    @Column(name = "OTRA_CONSTRUCCION")
    private String otraConstruccion;
    @Column(name = "CAPACIDAD_OTRA_CONSTR")
    private String capacidadOtraConstr;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasConstruccionV2() {
    }

    public CmatFuniasConstruccionV2(double idRegistro) {
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

    public String getIdPtoAguaSubte() {
        return idPtoAguaSubte;
    }

    public void setIdPtoAguaSubte(String idPtoAguaSubte) {
        this.idPtoAguaSubte = idPtoAguaSubte;
    }

    public String getFechaConstruccion() {
        return fechaConstruccion;
    }

    public void setFechaConstruccion(String fechaConstruccion) {
        this.fechaConstruccion = fechaConstruccion;
    }

    public String getDiametroInterior() {
        return diametroInterior;
    }

    public void setDiametroInterior(String diametroInterior) {
        this.diametroInterior = diametroInterior;
    }

    public String getDiametroExterior() {
        return diametroExterior;
    }

    public void setDiametroExterior(String diametroExterior) {
        this.diametroExterior = diametroExterior;
    }

    public String getDiametroPerforacion() {
        return diametroPerforacion;
    }

    public void setDiametroPerforacion(String diametroPerforacion) {
        this.diametroPerforacion = diametroPerforacion;
    }

    public String getMaterialRevestimiento() {
        return materialRevestimiento;
    }

    public void setMaterialRevestimiento(String materialRevestimiento) {
        this.materialRevestimiento = materialRevestimiento;
    }

    public String getMetodoConstruccion() {
        return metodoConstruccion;
    }

    public void setMetodoConstruccion(String metodoConstruccion) {
        this.metodoConstruccion = metodoConstruccion;
    }

    public String getCompaniaPerforadora() {
        return companiaPerfordora;
    }

    public void setCompaniaPerforadora(String companiaPerforadora) {
        this.companiaPerfordora = companiaPerforadora;
    }

    public String getColmatado() {
        return colmatado;
    }

    public void setColmatado(String colmatado) {
        this.colmatado = colmatado;
    }

    public String getColapsado() {
        return colapsado;
    }

    public void setColapsado(String colapsado) {
        this.colapsado = colapsado;
    }

    public String getProfundidadEntubado() {
        return profundidadEntubado;
    }

    public void setProfundidadEntubado(String profundidadEntubado) {
        this.profundidadEntubado = profundidadEntubado;
    }

    public String getProfundidadPerforacion() {
        return profundidadPerforacion;
    }

    public void setProfundidadPerforacion(String profundidadPerforacion) {
        this.profundidadPerforacion = profundidadPerforacion;
    }

    public String getCantidadGravilla() {
        return cantidadGravilla;
    }

    public void setCantidadGravilla(String cantidadGravilla) {
        this.cantidadGravilla = cantidadGravilla;
    }

    public String getNivelEngravillado() {
        return nivelEngravillado;
    }

    public void setNivelEngravillado(String nivelEngravillado) {
        this.nivelEngravillado = nivelEngravillado;
    }

    public String getDiametroPromedio() {
        return diametroPromedio;
    }

    public void setDiametroPromedio(String diametroPromedio) {
        this.diametroPromedio = diametroPromedio;
    }

    public String getEmbalse() {
        return embalse;
    }

    public void setEmbalse(String embalse) {
        this.embalse = embalse;
    }

    public String getCapacidadEmbalse() {
        return capacidadEmbalse;
    }

    public void setCapacidadEmbalse(String capacidadEmbalse) {
        this.capacidadEmbalse = capacidadEmbalse;
    }

    public String getTanque() {
        return tanque;
    }

    public void setTanque(String tanque) {
        this.tanque = tanque;
    }

    public String getCapacidadTanque() {
        return capacidadTanque;
    }

    public void setCapacidadTanque(String capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public String getAlberca() {
        return alberca;
    }

    public void setAlberca(String alberca) {
        this.alberca = alberca;
    }

    public String getCapacidadAlberca() {
        return capacidadAlberca;
    }

    public void setCapacidadAlberca(String capacidadAlberca) {
        this.capacidadAlberca = capacidadAlberca;
    }

    public String getOtraConstruccion() {
        return otraConstruccion;
    }

    public void setOtraConstruccion(String otraConstruccion) {
        this.otraConstruccion = otraConstruccion;
    }

    public String getCapacidadOtraConstr() {
        return capacidadOtraConstr;
    }

    public void setCapacidadOtraConstr(String capacidadOtraConstr) {
        this.capacidadOtraConstr = capacidadOtraConstr;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

 

    @Override
    public String toString() {
        return "Entidades.CmatFuniasConstruccionV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
