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
@Table(name = "CMAT_FUNIAS_DIAG_SANITARIO_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatFuniasDiagSanitarioV2.findAllDiagSani", query = "SELECT c FROM CmatFuniasDiagSanitarioV2 c"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueDiagSani", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue"),
    @NamedQuery(name = "CmatFuniasDiagSanitarioV2.findByCodigoRechazoDiagSani", query = "SELECT c FROM CmatFuniasDiagSanitarioV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatFuniasDiagSanitarioV2.findByIdRegistroDiagSani", query = "SELECT c FROM CmatFuniasDiagSanitarioV2 c WHERE c.idRegistro = :idRegistro")})
public class CmatFuniasDiagSanitarioV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_PTO_AGUA_SUBT")
    private String idPtoAguaSubt;
    @Column(name = "LETRINA")
    private String letrina;
    @Column(name = "DISTANCIA_LETRINA")
    private String distanciaLetrina;
    @Column(name = "CHARCO")
    private String charco;
    @Column(name = "DISTANCIA_CHARCO")
    private String distanciaCharco;
    @Column(name = "BASURA")
    private String basura;
    @Column(name = "DISTANCIA_BASURA")
    private String distanciaBasura;
    @Column(name = "BORDE_GRIETA")
    private String bordeGrieta;
    @Column(name = "DISTANCIA_BORDE_GRIETA")
    private String distanciaBordeGrieta;
    @Column(name = "CUBIERTA")
    private String cubierta;
    @Column(name = "PISO_CEMENTO")
    private String pisoCemento;
    @Column(name = "SELLO_SANITARIO")
    private String selloSanitario;
    @Column(name = "CERCO_ADECUADO")
    private String cercoAdecuado;
    @Column(name = "CAMPO_INFILTRACION")
    private String campoInfiltracion;
    @Column(name = "DISTANCIA_CAMPO_INFIL")
    private String distanciaCampoInfil;
    @Column(name = "CEMENTERIO")
    private String cementerio;
    @Column(name = "DISTANCIA_CEMENTERIO")
    private String distanciaCementerio;
    @Column(name = "ESTACION_SERVICIO")
    private String estacionServicio;
    @Column(name = "DISTACION_EDS")
    private String distacionEds;
    @Column(name = "LAGUNAS_OXI")
    private String lagunasOxi;
    @Column(name = "DISTANCIA_LAGUNAS")
    private String distanciaLagunas;
    @Column(name = "LAVADEROS_MOTOR")
    private String lavaderosMotor;
    @Column(name = "DISTANCIA_LAVADEROS")
    private String distanciaLavaderos;
    @Column(name = "PLANTAS_SACRIFICIO")
    private String plantasSacrificio;
    @Column(name = "DISTANCIA_PLANTAS_SACRI")
    private String distanciaPlantasSacri;
    @Column(name = "POZOS_ABANDONADOS")
    private String pozosAbandonados;
    @Column(name = "DISTANCIA_POZOS")
    private String distanciaPozos;
    @Column(name = "AGRICOLA")
    private String agricola;
    @Column(name = "DOMESTICO")
    private String domestico;
    @Column(name = "GANADERIA")
    private String ganaderia;
    @Column(name = "HOSPITALARIO")
    private String hospitalario;
    @Column(name = "INDUSTRIAL")
    private String industrial;
    @Column(name = "MINERO")
    private String minero;
    @Column(name = "BOTADERO_CIELO_ABIERTO")
    private String botaderoCieloAbierto;
    @Column(name = "COMPOSTAJE")
    private String compostaje;
    @Column(name = "INCINERACION")
    private String incineracion;
    @Column(name = "RECICLAJE")
    private String reciclaje;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatFuniasDiagSanitarioV2() {
    }

    public CmatFuniasDiagSanitarioV2(double idRegistro) {
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

    public String getIdPtoAguaSubt() {
        return idPtoAguaSubt;
    }

    public void setIdPtoAguaSubt(String idPtoAguaSubt) {
        this.idPtoAguaSubt = idPtoAguaSubt;
    }

    public String getLetrina() {
        return letrina;
    }

    public void setLetrina(String letrina) {
        this.letrina = letrina;
    }

    public String getDistanciaLetrina() {
        return distanciaLetrina;
    }

    public void setDistanciaLetrina(String distanciaLetrina) {
        this.distanciaLetrina = distanciaLetrina;
    }

    public String getCharco() {
        return charco;
    }

    public void setCharco(String charco) {
        this.charco = charco;
    }

    public String getDistanciaCharco() {
        return distanciaCharco;
    }

    public void setDistanciaCharco(String distanciaCharco) {
        this.distanciaCharco = distanciaCharco;
    }

    public String getBasura() {
        return basura;
    }

    public void setBasura(String basura) {
        this.basura = basura;
    }

    public String getDistanciaBasura() {
        return distanciaBasura;
    }

    public void setDistanciaBasura(String distanciaBasura) {
        this.distanciaBasura = distanciaBasura;
    }

    public String getBordeGrieta() {
        return bordeGrieta;
    }

    public void setBordeGrieta(String bordeGrieta) {
        this.bordeGrieta = bordeGrieta;
    }

    public String getDistanciaBordeGrieta() {
        return distanciaBordeGrieta;
    }

    public void setDistanciaBordeGrieta(String distanciaBordeGrieta) {
        this.distanciaBordeGrieta = distanciaBordeGrieta;
    }

    public String getCubierta() {
        return cubierta;
    }

    public void setCubierta(String cubierta) {
        this.cubierta = cubierta;
    }

    public String getPisoCemento() {
        return pisoCemento;
    }

    public void setPisoCemento(String pisoCemento) {
        this.pisoCemento = pisoCemento;
    }

    public String getSelloSanitario() {
        return selloSanitario;
    }

    public void setSelloSanitario(String selloSanitario) {
        this.selloSanitario = selloSanitario;
    }

    public String getCercoAdecuado() {
        return cercoAdecuado;
    }

    public void setCercoAdecuado(String cercoAdecuado) {
        this.cercoAdecuado = cercoAdecuado;
    }

    public String getCampoInfiltracion() {
        return campoInfiltracion;
    }

    public void setCampoInfiltracion(String campoInfiltracion) {
        this.campoInfiltracion = campoInfiltracion;
    }

    public String getDistanciaCampoInfil() {
        return distanciaCampoInfil;
    }

    public void setDistanciaCampoInfil(String distanciaCampoInfil) {
        this.distanciaCampoInfil = distanciaCampoInfil;
    }

    public String getCementerio() {
        return cementerio;
    }

    public void setCementerio(String cementerio) {
        this.cementerio = cementerio;
    }

    public String getDistanciaCementerio() {
        return distanciaCementerio;
    }

    public void setDistanciaCementerio(String distanciaCementerio) {
        this.distanciaCementerio = distanciaCementerio;
    }

    public String getEstacionServicio() {
        return estacionServicio;
    }

    public void setEstacionServicio(String estacionServicio) {
        this.estacionServicio = estacionServicio;
    }

    public String getDistacionEds() {
        return distacionEds;
    }

    public void setDistacionEds(String distacionEds) {
        this.distacionEds = distacionEds;
    }

    public String getLagunasOxi() {
        return lagunasOxi;
    }

    public void setLagunasOxi(String lagunasOxi) {
        this.lagunasOxi = lagunasOxi;
    }

    public String getDistanciaLagunas() {
        return distanciaLagunas;
    }

    public void setDistanciaLagunas(String distanciaLagunas) {
        this.distanciaLagunas = distanciaLagunas;
    }

    public String getLavaderosMotor() {
        return lavaderosMotor;
    }

    public void setLavaderosMotor(String lavaderosMotor) {
        this.lavaderosMotor = lavaderosMotor;
    }

    public String getDistanciaLavaderos() {
        return distanciaLavaderos;
    }

    public void setDistanciaLavaderos(String distanciaLavaderos) {
        this.distanciaLavaderos = distanciaLavaderos;
    }

    public String getPlantasSacrificio() {
        return plantasSacrificio;
    }

    public void setPlantasSacrificio(String plantasSacrificio) {
        this.plantasSacrificio = plantasSacrificio;
    }

    public String getDistanciaPlantasSacri() {
        return distanciaPlantasSacri;
    }

    public void setDistanciaPlantasSacri(String distanciaPlantasSacri) {
        this.distanciaPlantasSacri = distanciaPlantasSacri;
    }

    public String getPozosAbandonados() {
        return pozosAbandonados;
    }

    public void setPozosAbandonados(String pozosAbandonados) {
        this.pozosAbandonados = pozosAbandonados;
    }

    public String getDistanciaPozos() {
        return distanciaPozos;
    }

    public void setDistanciaPozos(String distanciaPozos) {
        this.distanciaPozos = distanciaPozos;
    }

    public String getAgricola() {
        return agricola;
    }

    public void setAgricola(String agricola) {
        this.agricola = agricola;
    }

    public String getDomestico() {
        return domestico;
    }

    public void setDomestico(String domestico) {
        this.domestico = domestico;
    }

    public String getGanaderia() {
        return ganaderia;
    }

    public void setGanaderia(String ganaderia) {
        this.ganaderia = ganaderia;
    }

    public String getHospitalario() {
        return hospitalario;
    }

    public void setHospitalario(String hospitalario) {
        this.hospitalario = hospitalario;
    }

    public String getIndustrial() {
        return industrial;
    }

    public void setIndustrial(String industrial) {
        this.industrial = industrial;
    }

    public String getMinero() {
        return minero;
    }

    public void setMinero(String minero) {
        this.minero = minero;
    }

    public String getBotaderoCieloAbierto() {
        return botaderoCieloAbierto;
    }

    public void setBotaderoCieloAbierto(String botaderoCieloAbierto) {
        this.botaderoCieloAbierto = botaderoCieloAbierto;
    }

    public String getCompostaje() {
        return compostaje;
    }

    public void setCompostaje(String compostaje) {
        this.compostaje = compostaje;
    }

    public String getIncineracion() {
        return incineracion;
    }

    public void setIncineracion(String incineracion) {
        this.incineracion = incineracion;
    }

    public String getReciclaje() {
        return reciclaje;
    }

    public void setReciclaje(String reciclaje) {
        this.reciclaje = reciclaje;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

  

    @Override
    public String toString() {
        return "Entidades.CmatFuniasDiagSanitarioV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
