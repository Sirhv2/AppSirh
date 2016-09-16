
package co.gov.ideam.sirh.dataimport.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for muestrasMediciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="muestrasMediciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caudal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="descripcionacceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracionmuestreo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="fechamuestra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horamuestra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horamuestreo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idautoridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idpunto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="intervalotiempo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="limitedeteccion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="metododeterminacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minmuestreo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrelaboratorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrepunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numsubmuestras" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numverticales" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="otrolaboratorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parametro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parametroacreditado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipomuestra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unidadmedida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="valor2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="valorcaracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "muestrasMediciones", propOrder = {
    "caudal",
    "descripcionacceso",
    "duracionmuestreo",
    "fechamuestra",
    "horamuestra",
    "horamuestreo",
    "horario",
    "id",
    "idautoridad",
    "idpunto",
    "intervalotiempo",
    "limitedeteccion",
    "metododeterminacion",
    "minmuestreo",
    "nombrelaboratorio",
    "nombrepunto",
    "numsubmuestras",
    "numverticales",
    "otrolaboratorio",
    "parametro",
    "parametroacreditado",
    "signo",
    "tipomuestra",
    "unidadmedida",
    "valor",
    "valor2",
    "valorcaracter"
})
public class MuestrasMediciones {

    protected Double caudal;
    protected String descripcionacceso;
    protected Double duracionmuestreo;
    protected String fechamuestra;
    protected String horamuestra;
    protected String horamuestreo;
    protected String horario;
    protected Integer id;
    protected String idautoridad;
    protected Integer idpunto;
    protected Double intervalotiempo;
    protected Double limitedeteccion;
    protected String metododeterminacion;
    protected String minmuestreo;
    protected String nombrelaboratorio;
    protected String nombrepunto;
    protected Integer numsubmuestras;
    protected Integer numverticales;
    protected String otrolaboratorio;
    protected String parametro;
    protected String parametroacreditado;
    protected String signo;
    protected String tipomuestra;
    protected String unidadmedida;
    protected Double valor;
    protected Double valor2;
    protected String valorcaracter;

    /**
     * Gets the value of the caudal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCaudal() {
        return caudal;
    }

    /**
     * Sets the value of the caudal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCaudal(Double value) {
        this.caudal = value;
    }

    /**
     * Gets the value of the descripcionacceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionacceso() {
        return descripcionacceso;
    }

    /**
     * Sets the value of the descripcionacceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionacceso(String value) {
        this.descripcionacceso = value;
    }

    /**
     * Gets the value of the duracionmuestreo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDuracionmuestreo() {
        return duracionmuestreo;
    }

    /**
     * Sets the value of the duracionmuestreo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDuracionmuestreo(Double value) {
        this.duracionmuestreo = value;
    }

    /**
     * Gets the value of the fechamuestra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechamuestra() {
        return fechamuestra;
    }

    /**
     * Sets the value of the fechamuestra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechamuestra(String value) {
        this.fechamuestra = value;
    }

    /**
     * Gets the value of the horamuestra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoramuestra() {
        return horamuestra;
    }

    /**
     * Sets the value of the horamuestra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoramuestra(String value) {
        this.horamuestra = value;
    }

    /**
     * Gets the value of the horamuestreo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoramuestreo() {
        return horamuestreo;
    }

    /**
     * Sets the value of the horamuestreo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoramuestreo(String value) {
        this.horamuestreo = value;
    }

    /**
     * Gets the value of the horario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Sets the value of the horario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorario(String value) {
        this.horario = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the idautoridad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdautoridad() {
        return idautoridad;
    }

    /**
     * Sets the value of the idautoridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdautoridad(String value) {
        this.idautoridad = value;
    }

    /**
     * Gets the value of the idpunto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdpunto() {
        return idpunto;
    }

    /**
     * Sets the value of the idpunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdpunto(Integer value) {
        this.idpunto = value;
    }

    /**
     * Gets the value of the intervalotiempo property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIntervalotiempo() {
        return intervalotiempo;
    }

    /**
     * Sets the value of the intervalotiempo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIntervalotiempo(Double value) {
        this.intervalotiempo = value;
    }

    /**
     * Gets the value of the limitedeteccion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLimitedeteccion() {
        return limitedeteccion;
    }

    /**
     * Sets the value of the limitedeteccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLimitedeteccion(Double value) {
        this.limitedeteccion = value;
    }

    /**
     * Gets the value of the metododeterminacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetododeterminacion() {
        return metododeterminacion;
    }

    /**
     * Sets the value of the metododeterminacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetododeterminacion(String value) {
        this.metododeterminacion = value;
    }

    /**
     * Gets the value of the minmuestreo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinmuestreo() {
        return minmuestreo;
    }

    /**
     * Sets the value of the minmuestreo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinmuestreo(String value) {
        this.minmuestreo = value;
    }

    /**
     * Gets the value of the nombrelaboratorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrelaboratorio() {
        return nombrelaboratorio;
    }

    /**
     * Sets the value of the nombrelaboratorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrelaboratorio(String value) {
        this.nombrelaboratorio = value;
    }

    /**
     * Gets the value of the nombrepunto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrepunto() {
        return nombrepunto;
    }

    /**
     * Sets the value of the nombrepunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrepunto(String value) {
        this.nombrepunto = value;
    }

    /**
     * Gets the value of the numsubmuestras property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumsubmuestras() {
        return numsubmuestras;
    }

    /**
     * Sets the value of the numsubmuestras property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumsubmuestras(Integer value) {
        this.numsubmuestras = value;
    }

    /**
     * Gets the value of the numverticales property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumverticales() {
        return numverticales;
    }

    /**
     * Sets the value of the numverticales property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumverticales(Integer value) {
        this.numverticales = value;
    }

    /**
     * Gets the value of the otrolaboratorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtrolaboratorio() {
        return otrolaboratorio;
    }

    /**
     * Sets the value of the otrolaboratorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtrolaboratorio(String value) {
        this.otrolaboratorio = value;
    }

    /**
     * Gets the value of the parametro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * Sets the value of the parametro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametro(String value) {
        this.parametro = value;
    }

    /**
     * Gets the value of the parametroacreditado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametroacreditado() {
        return parametroacreditado;
    }

    /**
     * Sets the value of the parametroacreditado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametroacreditado(String value) {
        this.parametroacreditado = value;
    }

    /**
     * Gets the value of the signo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigno() {
        return signo;
    }

    /**
     * Sets the value of the signo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigno(String value) {
        this.signo = value;
    }

    /**
     * Gets the value of the tipomuestra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipomuestra() {
        return tipomuestra;
    }

    /**
     * Sets the value of the tipomuestra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipomuestra(String value) {
        this.tipomuestra = value;
    }

    /**
     * Gets the value of the unidadmedida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadmedida() {
        return unidadmedida;
    }

    /**
     * Sets the value of the unidadmedida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadmedida(String value) {
        this.unidadmedida = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValor(Double value) {
        this.valor = value;
    }

    /**
     * Gets the value of the valor2 property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValor2() {
        return valor2;
    }

    /**
     * Sets the value of the valor2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValor2(Double value) {
        this.valor2 = value;
    }

    /**
     * Gets the value of the valorcaracter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorcaracter() {
        return valorcaracter;
    }

    /**
     * Sets the value of the valorcaracter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorcaracter(String value) {
        this.valorcaracter = value;
    }

}
