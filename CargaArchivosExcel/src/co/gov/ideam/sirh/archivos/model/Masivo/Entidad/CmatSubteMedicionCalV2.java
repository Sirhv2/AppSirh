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
@Table(name = "CMAT_SUBTE_MEDICION_CAL_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatSubteMedicionCalV2.findAllMedCalSubV2", query = "SELECT c FROM CmatSubteMedicionCalV2 c"),
    @NamedQuery(name = "CmatSubteMedicionCalV2.findByCodigoRechazoMedCalSubV2", query = "SELECT c FROM CmatSubteMedicionCalV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatSubteMedicionCalV2.findByIdRegistroMedCalSubV2", query = "SELECT c FROM CmatSubteMedicionCalV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueMedCalSubV2", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatSubteMedicionCalV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "ID_MUESTRA")
    private String idMuestra;
    @Column(name = "ID_PNTO_AGUA_SUBTE")
    private String idPntoAguaSubte;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "LABORATORIO")
    private String laboratorio;
    @Column(name = "OTRO_LABORATORIO")
    private String otroLaboratorio;
    @Column(name = "METODO_MUESTREO")
    private String metodoMuestreo;
    @Column(name = "LUGAR_MUESTREO")
    private String lugarMuestreo;
    @Column(name = "OTRO_LUGAR")
    private String otroLugar;
    @Column(name = "FECHA")
    private String fecha;
    @Column(name = "HORA")
    private String hora;
    @Column(name = "MIUTO")
    private String miuto;
    @Column(name = "HORARIO")
    private String horario;
    @Column(name = "PARAMETRO")
    private String parametro;
    @Column(name = "SIGNO")
    private String signo;
    @Column(name = "UNIDAD_MEDIDA")
    private String unidadMedida;
    @Column(name = "VALOR")
    private String valor;
    @Column(name = "VALOR_2")
    private String valor2;
    @Column(name = "VALOR_UNICO")
    private String valorUnico;
    @Column(name = "METODO_DETERMINACION")
    private String metodoDeterminacion;
    @Column(name = "LIMITE_DETECCION")
    private String limiteDeteccion;
    @Column(name = "PARAMETRO_ACREDITADO")
    private String parametroAcreditado;
    @JoinColumn(name = "ID_CONTROL_CARGUE", referencedColumnName = "ID")
    @ManyToOne
    private CmatControlCargueV2 idControlCargue;

    public CmatSubteMedicionCalV2() {
    }

    public CmatSubteMedicionCalV2(double idRegistro) {
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

    public String getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getIdPntoAguaSubte() {
        return idPntoAguaSubte;
    }

    public void setIdPntoAguaSubte(String idPntoAguaSubte) {
        this.idPntoAguaSubte = idPntoAguaSubte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getOtroLaboratorio() {
        return otroLaboratorio;
    }

    public void setOtroLaboratorio(String otroLaboratorio) {
        this.otroLaboratorio = otroLaboratorio;
    }

    public String getMetodoMuestreo() {
        return metodoMuestreo;
    }

    public void setMetodoMuestreo(String metodoMuestreo) {
        this.metodoMuestreo = metodoMuestreo;
    }

    public String getLugarMuestreo() {
        return lugarMuestreo;
    }

    public void setLugarMuestreo(String lugarMuestreo) {
        this.lugarMuestreo = lugarMuestreo;
    }

    public String getOtroLugar() {
        return otroLugar;
    }

    public void setOtroLugar(String otroLugar) {
        this.otroLugar = otroLugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMiuto() {
        return miuto;
    }

    public void setMiuto(String miuto) {
        this.miuto = miuto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getValorUnico() {
        return valorUnico;
    }

    public void setValorUnico(String valorUnico) {
        this.valorUnico = valorUnico;
    }

    public String getMetodoDeterminacion() {
        return metodoDeterminacion;
    }

    public void setMetodoDeterminacion(String metodoDeterminacion) {
        this.metodoDeterminacion = metodoDeterminacion;
    }

    public String getLimiteDeteccion() {
        return limiteDeteccion;
    }

    public void setLimiteDeteccion(String limiteDeteccion) {
        this.limiteDeteccion = limiteDeteccion;
    }

    public String getParametroAcreditado() {
        return parametroAcreditado;
    }

    public void setParametroAcreditado(String parametroAcreditado) {
        this.parametroAcreditado = parametroAcreditado;
    }

    public CmatControlCargueV2 getIdControlCargue() {
        return idControlCargue;
    }

    public void setIdControlCargue(CmatControlCargueV2 idControlCargue) {
        this.idControlCargue = idControlCargue;
    }

   

    @Override
    public String toString() {
        return "Entidades.CmatSubteMedicionCalV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
