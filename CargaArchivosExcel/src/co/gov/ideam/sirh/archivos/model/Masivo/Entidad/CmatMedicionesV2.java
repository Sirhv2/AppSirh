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
@Table(name = "CMAT_MEDICIONES_V2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmatMedicionesV2.findAllMed", query = "SELECT c FROM CmatMedicionesV2 c"),
    @NamedQuery(name = "CmatMedicionesV2.findByCodigoRechazoMed", query = "SELECT c FROM CmatMedicionesV2 c WHERE c.codigoRechazo = :codigoRechazo"),
    @NamedQuery(name = "CmatMedicionesV2.findByIdRegistroMed", query = "SELECT c FROM CmatMedicionesV2 c WHERE c.idRegistro = :idRegistro"),
    @NamedQuery(name = "CmatFuniasConstruccionV2.findByIdControlCargueMed", query = "SELECT c FROM CmatFuniasConstruccionV2 c WHERE c.idControlCargue = :idControlCargue")})
public class CmatMedicionesV2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_RECHAZO")
    private Long codigoRechazo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO")
    private double idRegistro;
    @Column(name = "IDENTIFICADOR_MUESTRA")
    private String identificadorMuestra;
    @Column(name = "NOMBRE_PUNTO")
    private String nombrePunto;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NOMBRE_LABORATORIO")
    private String nombreLaboratorio;
    @Column(name = "OTRO_LABORATORIO")
    private String otroLaboratorio;
    @Column(name = "TIPO_MUESTRA")
    private String tipoMuestra;
    @Column(name = "FECHA")
    private String fecha;
    @Column(name = "HORA")
    private String hora;
    @Column(name = "MIN")
    private String min;
    @Column(name = "HORARIO")
    private String horario;
    @Column(name = "CAUDAL")
    private String caudal;
    @Column(name = "NUM_VERTICALES")
    private String numVerticales;
    @Column(name = "INTERVALO_TIEMPO")
    private String intervaloTiempo;
    @Column(name = "DURACION_MUESTREO")
    private String duracionMuestreo;
    @Column(name = "NUM_SUBMUESTRAS")
    private String numSubmuestras;
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

    public CmatMedicionesV2() {
    }

    public CmatMedicionesV2(double idRegistro) {
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

    public String getIdentificadorMuestra() {
        return identificadorMuestra;
    }

    public void setIdentificadorMuestra(String identificadorMuestra) {
        this.identificadorMuestra = identificadorMuestra;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public String getOtroLaboratorio() {
        return otroLaboratorio;
    }

    public void setOtroLaboratorio(String otroLaboratorio) {
        this.otroLaboratorio = otroLaboratorio;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
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

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCaudal() {
        return caudal;
    }

    public void setCaudal(String caudal) {
        this.caudal = caudal;
    }

    public String getNumVerticales() {
        return numVerticales;
    }

    public void setNumVerticales(String numVerticales) {
        this.numVerticales = numVerticales;
    }

    public String getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setIntervaloTiempo(String intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }

    public String getDuracionMuestreo() {
        return duracionMuestreo;
    }

    public void setDuracionMuestreo(String duracionMuestreo) {
        this.duracionMuestreo = duracionMuestreo;
    }

    public String getNumSubmuestras() {
        return numSubmuestras;
    }

    public void setNumSubmuestras(String numSubmuestras) {
        this.numSubmuestras = numSubmuestras;
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
        return "Entidades.CmatMedicionesV2[ idRegistro=" + idRegistro + " ]";
    }
    
}
