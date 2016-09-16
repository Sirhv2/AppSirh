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
@Table(name = "CMAT_FUNIAS_EXPLOTACION_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasExplotacionV2.findAllExplot", query = "SELECT c FROM CmatFuniasExplotacionV2 c"),
    @NamedQuery(name = "CmatFuniasExplotacionV2.findByCodigoRechazoExplot", query = "SELECT c FROM CmatFuniasExplotacionV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasExplotacionV2.findByIdRegistroExplot", query = "SELECT c FROM CmatFuniasExplotacionV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueExplot", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatFuniasExplotacionV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PUNTO_AGUAS_SUB")
    private String idPuntoAguasSub;
    @Column(name = "METODO_EXPLOTACION")
    private String metodoExplotacion;
    @Column(name = "TIPO_ENERGIA")
    private String tipoEnergia;
    @Column(name = "CLASE_BOMBA")
    private String claseBomba;
    @Column(name = "MODELO_BOMBA")
    private String modeloBomba;
    @Column(name = "POTENCIA_BOMBA")
    private String potenciaBomba;
    @Column(name = "PROFUNDIDAD_SUCCION")
    private String profundidadSuccion;
    @Column(name = "DIAMETRO_TUBERIA_DESCARGA")
    private String diametroTuberiaDescarga;
    @Column(name = "LONGITUD_TUB_DESCARGA")
    private String longitudTubDescarga;
    @Column(name = "MATERIAL_TUB_DESCARGA")
    private String materialTubDescarga;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasExplotacionV2() {
    }

    public CmatFuniasExplotacionV2(double idRegistro) {
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

    public String getIdPuntoAguasSub() {
        return idPuntoAguasSub;
    }

    public void setIdPuntoAguasSub(String idPuntoAguasSub) {
        this.idPuntoAguasSub = idPuntoAguasSub;
    }

    public String getMetodoExplotacion() {
        return metodoExplotacion;
    }

    public void setMetodoExplotacion(String metodoExplotacion) {
        this.metodoExplotacion = metodoExplotacion;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public String getClaseBomba() {
        return claseBomba;
    }

    public void setClaseBomba(String claseBomba) {
        this.claseBomba = claseBomba;
    }

    public String getModeloBomba() {
        return modeloBomba;
    }

    public void setModeloBomba(String modeloBomba) {
        this.modeloBomba = modeloBomba;
    }

    public String getPotenciaBomba() {
        return potenciaBomba;
    }

    public void setPotenciaBomba(String potenciaBomba) {
        this.potenciaBomba = potenciaBomba;
    }

    public String getProfundidadSuccion() {
        return profundidadSuccion;
    }

    public void setProfundidadSuccion(String profundidadSuccion) {
        this.profundidadSuccion = profundidadSuccion;
    }

    public String getDiametroTuberiaDescarga() {
        return diametroTuberiaDescarga;
    }

    public void setDiametroTuberiaDescarga(String diametroTuberiaDescarga) {
        this.diametroTuberiaDescarga = diametroTuberiaDescarga;
    }

    public String getLongitudTubDescarga() {
        return longitudTubDescarga;
    }

    public void setLongitudTubDescarga(String longitudTubDescarga) {
        this.longitudTubDescarga = longitudTubDescarga;
    }

    public String getMaterialTubDescarga() {
        return materialTubDescarga;
    }

    public void setMaterialTubDescarga(String materialTubDescarga) {
        this.materialTubDescarga = materialTubDescarga;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

  

    @Override
    public String toString() {
        return "Entidades.CmatFuniasExplotacionV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
