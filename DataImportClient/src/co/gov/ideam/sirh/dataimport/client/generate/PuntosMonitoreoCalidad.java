
package co.gov.ideam.sirh.dataimport.client.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for puntosMonitoreoCalidad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="puntosMonitoreoCalidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionacceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gradoslatitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="gradoslongitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idautoridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idfuente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minutoslatitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="minutoslongitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrepunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segundoslatitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="segundoslongitud" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="sistemareferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subzona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipofuente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipopunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tramo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ubicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "puntosMonitoreoCalidad", propOrder = {
    "altitud",
    "area",
    "departamento",
    "descripcionacceso",
    "fuente",
    "gradoslatitud",
    "gradoslongitud",
    "id",
    "idautoridad",
    "idfuente",
    "minutoslatitud",
    "minutoslongitud",
    "municipio",
    "nombrepunto",
    "segundoslatitud",
    "segundoslongitud",
    "sistemareferencia",
    "subzona",
    "tipofuente",
    "tipopunto",
    "tramo",
    "ubicacion",
    "zona"
})
public class PuntosMonitoreoCalidad {

    protected Double altitud;
    protected String area;
    protected String departamento;
    protected String descripcionacceso;
    protected String fuente;
    protected Double gradoslatitud;
    protected Double gradoslongitud;
    protected Integer id;
    protected String idautoridad;
    protected Integer idfuente;
    protected Double minutoslatitud;
    protected Double minutoslongitud;
    protected String municipio;
    protected String nombrepunto;
    protected Double segundoslatitud;
    protected Double segundoslongitud;
    protected String sistemareferencia;
    protected String subzona;
    protected String tipofuente;
    protected String tipopunto;
    protected String tramo;
    protected String ubicacion;
    protected String zona;

    /**
     * Gets the value of the altitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAltitud() {
        return altitud;
    }

    /**
     * Sets the value of the altitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAltitud(Double value) {
        this.altitud = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
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
     * Gets the value of the fuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuente() {
        return fuente;
    }

    /**
     * Sets the value of the fuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuente(String value) {
        this.fuente = value;
    }

    /**
     * Gets the value of the gradoslatitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGradoslatitud() {
        return gradoslatitud;
    }

    /**
     * Sets the value of the gradoslatitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGradoslatitud(Double value) {
        this.gradoslatitud = value;
    }

    /**
     * Gets the value of the gradoslongitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGradoslongitud() {
        return gradoslongitud;
    }

    /**
     * Sets the value of the gradoslongitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGradoslongitud(Double value) {
        this.gradoslongitud = value;
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
     * Gets the value of the idfuente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdfuente() {
        return idfuente;
    }

    /**
     * Sets the value of the idfuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdfuente(Integer value) {
        this.idfuente = value;
    }

    /**
     * Gets the value of the minutoslatitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinutoslatitud() {
        return minutoslatitud;
    }

    /**
     * Sets the value of the minutoslatitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinutoslatitud(Double value) {
        this.minutoslatitud = value;
    }

    /**
     * Gets the value of the minutoslongitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinutoslongitud() {
        return minutoslongitud;
    }

    /**
     * Sets the value of the minutoslongitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinutoslongitud(Double value) {
        this.minutoslongitud = value;
    }

    /**
     * Gets the value of the municipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Sets the value of the municipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
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
     * Gets the value of the segundoslatitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslatitud() {
        return segundoslatitud;
    }

    /**
     * Sets the value of the segundoslatitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslatitud(Double value) {
        this.segundoslatitud = value;
    }

    /**
     * Gets the value of the segundoslongitud property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSegundoslongitud() {
        return segundoslongitud;
    }

    /**
     * Sets the value of the segundoslongitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSegundoslongitud(Double value) {
        this.segundoslongitud = value;
    }

    /**
     * Gets the value of the sistemareferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemareferencia() {
        return sistemareferencia;
    }

    /**
     * Sets the value of the sistemareferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemareferencia(String value) {
        this.sistemareferencia = value;
    }

    /**
     * Gets the value of the subzona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubzona() {
        return subzona;
    }

    /**
     * Sets the value of the subzona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubzona(String value) {
        this.subzona = value;
    }

    /**
     * Gets the value of the tipofuente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipofuente() {
        return tipofuente;
    }

    /**
     * Sets the value of the tipofuente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipofuente(String value) {
        this.tipofuente = value;
    }

    /**
     * Gets the value of the tipopunto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipopunto() {
        return tipopunto;
    }

    /**
     * Sets the value of the tipopunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipopunto(String value) {
        this.tipopunto = value;
    }

    /**
     * Gets the value of the tramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTramo() {
        return tramo;
    }

    /**
     * Sets the value of the tramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTramo(String value) {
        this.tramo = value;
    }

    /**
     * Gets the value of the ubicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Sets the value of the ubicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUbicacion(String value) {
        this.ubicacion = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
    }

}
